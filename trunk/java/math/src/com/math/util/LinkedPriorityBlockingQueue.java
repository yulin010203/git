package com.math.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 优先级阻塞队列
 * 
 * @author 陈霖 2015-2-5
 * @param <E>
 */
public class LinkedPriorityBlockingQueue<E> {

	/**
	 * 阻塞队列
	 */
	private final BlockingQueue<E> queue;
	/**
	 * 优先非阻塞队列
	 */
	private final Queue<E> firstQueue;

	/**
	 * 阻塞队列容量
	 */
	private int capacity;

	/**
	 * 无限长度
	 */
	public LinkedPriorityBlockingQueue() {
		this(Integer.MAX_VALUE);
	}

	/**
	 * 有限长度
	 * 
	 * @param capacity
	 */
	public LinkedPriorityBlockingQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		this.capacity = capacity;
		this.queue = new LinkedBlockingQueue<E>(capacity);
		this.firstQueue = new LinkedList<E>();
	}

	/**
	 * @param e
	 * @return boolean
	 */
	public boolean add(E e) {
		return queue.add(e);
	}

	/**
	 * @param e
	 * @return boolean
	 */
	public boolean addPrior(E e) {
		return firstQueue.add(e);
	}

	/**
	 * @param e
	 * @throws InterruptedException
	 */
	public void put(E e) throws InterruptedException {
		queue.put(e);
	}

	/**
	 * @return E
	 */
	public E poll() {
		if (!firstQueue.isEmpty()) {
			return firstQueue.poll();
		}
		return queue.poll();
	}

	/**
	 * @return E
	 */
	public E peek() {
		if (!firstQueue.isEmpty()) {
			return firstQueue.peek();
		}
		return queue.peek();
	}

	/**
	 * @return E
	 * @throws InterruptedException
	 */
	public E take() throws InterruptedException {
		if (!firstQueue.isEmpty()) {
			return firstQueue.poll();
		}
		return queue.take();
	}

	/**
	 * @return E
	 */
	public E remove() {
		E e = queue.poll();
		if (e == null) {
			e = firstQueue.poll();
		}
		if (e != null) {
			return e;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * @param o
	 * @return boolean
	 */
	public boolean remove(Object o) {
		if (o == null)
			return false;
		boolean remove = queue.remove(o);
		if (!remove) {
			remove = firstQueue.remove(o);
		}
		return remove;
	}

	/**
	 * @param o
	 * @return boolean
	 */
	public boolean contains(Object o) {
		return queue.contains(o) || firstQueue.contains(o);
	}

	/**
	 * @return boolean
	 */
	public boolean isEmpty() {
		return queue.isEmpty() && firstQueue.isEmpty();
	}

	/**
	 * @return int
	 */
	public int size() {
		return queue.size() + firstQueue.size();
	}
}
