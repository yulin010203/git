#ifndef ARRAY_LOCK_FREE_QUEUE_H2
#define ARRAY_LOCK_FREE_QUEUE_H2

//#include <stdint.h>     // uint32_t
#include <assert.h>

#ifdef WIN32
#include <windows.h>
#else
#include <sys/types.h>
#endif//WIN32

typedef unsigned int uint32_t;
typedef int			 int32_t;

// the default size of the free-lock queue
#ifndef ARRAY_LOCK_FREE_Q_DEFAULT_SIZE
#define ARRAY_LOCK_FREE_Q_DEFAULT_SIZE	1024
#endif

/// the array lock free queue
template <typename Ty, uint32_t Q_SIZE = ARRAY_LOCK_FREE_Q_DEFAULT_SIZE>
class ArrayLockFreeQueue2
{
	typedef Ty ElemType;
	typedef struct QueueNode
	{
		bool	 isEmpty;
		uint32_t writting;
		uint32_t reading;
		ElemType data;
	}QueueNode;

public:
	ArrayLockFreeQueue2() {
		memset(theQue, 0, sizeof(theQue));
		writeIndex_	= readIndex_ = 0;
		clear();
	}
	virtual ~ArrayLockFreeQueue2(){}

	bool push(const ElemType& theData)
	{
		// if full queue
		if (nWritableCount_ <= 0)
			return false;

		// 首先减少nWritableCount_，或者nWritableCount_小于线程数，则必有线程失败
		// 失败后导致nWritableCount_小于0，先自增并返回
		if (apr_atomic_dec32(&nWritableCount_) < 0)
		{
			// push元素的线程数大于writableCount_个数
			apr_atomic_inc32(&nWritableCount_);
			return false;
		}
		// 如果线程执行到此处，表示队列肯定有可写数的位置

		// get the write index
		uint32_t curWriteIndex = apr_atomic_inc32(&writeIndex_);
		uint32_t i = 0;
		for (i = 0; i < Q_SIZE; ++i, ++curWriteIndex)
		{
			curWriteIndex = countToIndex(curWriteIndex);
			if (!theQue[curWriteIndex].isEmpty)
				continue;

			// set writting flag: current thread is writting data to the queue
			// 由于apr_atomic_inc32返回old value，如果两个线程并行运行到此处时，
			// 第一个抢到的线程进行自加后，旧值仍为0表示成功，而第2个或者更多其它的线程，又对writting自加，
			// 则writting值大于0，表示不成功
			if (apr_atomic_inc32(&theQue[curWriteIndex].writting) > 0)
				continue;
			break;
		}

		theQue[curWriteIndex].data		= theData;
		theQue[curWriteIndex].isEmpty	= false;
		theQue[curWriteIndex].reading	= 0;
		apr_atomic_inc32(&nReadableCount_);

		return true;
	}
	bool pop(ElemType& theData)
	{
		// if empty queue
		if (nReadableCount_ <= 0)
			return false;
		// pop元素的线程大于可读数量
		if (apr_atomic_dec32(&nReadableCount_) < 0)
		{
			apr_atomic_inc32(&nReadableCount_);
			return false;
		}

		// get read index
		//uint32_t curReadIndex = apr_atomic_inc32(&readIndex_);
		uint32_t curReadIndex = readIndex_;
		apr_atomic_inc32(&readIndex_);
		uint32_t i = 0;
		for (i = 0; i < Q_SIZE; ++i, ++curReadIndex)
		{
			curReadIndex = countToIndex(curReadIndex);
			if (theQue[curReadIndex].isEmpty)
				continue;

			// set reading flag: current thread is reading data
			if (apr_atomic_inc32(&theQue[curReadIndex].reading) > 0)
				continue;
			break;
		}

		theData = theQue[curReadIndex].data;
		theQue[curReadIndex].isEmpty	= true;
		theQue[curReadIndex].writting	= 0;
		apr_atomic_inc32(&nWritableCount_);

		return true;
	}

private:
	QueueNode theQue[Q_SIZE];
	volatile uint32_t	writeIndex_;
	volatile uint32_t	readIndex_;
	volatile uint32_t	nWritableCount_;
	volatile uint32_t	nReadableCount_;

	inline uint32_t countToIndex(uint32_t passIndex){
		// if Q_SIZE is a power of 2, this statement can be as
		return (passIndex & (Q_SIZE - 1));
	}
	void clear()
	{		
		nWritableCount_	= Q_SIZE;
		nReadableCount_	= 0;
		for (uint32_t i = 0; i < Q_SIZE; ++i)
		{
			theQue[i].isEmpty = true;
			theQue[i].reading = 0;
			theQue[i].writting= 0;
		}
	}
};

#endif//ARRAY_LOCK_FREE_QUEUE2
