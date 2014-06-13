package com.lmax.disruptor.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class TestShow {
	// number of elements to create within the ring buffer
	private static final int BUFFER_SIZE = 16;
	// JDK 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
	private final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
	// 单生产者，策略使用YieldingWaitStrategy
	private final RingBuffer<ValueEvent> ringBuffer = RingBuffer.create(ProducerType.SINGLE, ValueEvent.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());
	// 游标
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
	// 消费者
	private final ConsumeEventHandler handler = new ConsumeEventHandler();
	private final BatchEventProcessor<ValueEvent> batchEventProcessor = new BatchEventProcessor<ValueEvent>(ringBuffer, sequenceBarrier, handler);

	public TestShow() {
		ringBuffer.addGatingSequences(batchEventProcessor.getSequence());
		// 2X版本：ringBuffer.setGatingSequences(batchEventProcessor.getSequence());
	}

	public void consume() {
		EXECUTOR.submit(batchEventProcessor);
	}

	public void produce() {
		new Thread(new Producer(ringBuffer)).start();
	}

	public void shutdown() {
		EXECUTOR.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		TestShow test = new TestShow();
		test.produce();
		test.produce();
		test.produce();
		test.consume();
		test.shutdown();
		Thread.sleep(5000);
		System.exit(0);
	}

}