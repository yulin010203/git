package com.math.util;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 有序可重复链表<br>
 * 1.添加进的元素根据元素所实现的Comparable接口进行自动排序<br>
 * 2.元素允许重复(重复是指2+个元素进行equals比较是相等的)<br>
 * 3.支持remove(E e)<br>
 * 4.可对集合按排好的顺序进行遍历，且能多次遍历 <br>
 * 实现此有序链表的目的是，TreeSet中的元素支持排序但元素是不能重复的，而PriorityQueue中的元素虽然能排序也支持重复，
 * 但由于PriorityQueue是队列
 * ，所以如果按排序顺序遍历，则必须poll出所有元素，即只能遍历一次，如果用foreach方法遍历PriorityQueue中的元素
 * ，则并不是按排序顺序遍历，而是按元素添加的顺序
 * 
 * @author 钟城
 */
public class TreeList<E> implements Iterable<E> {

	private Entry<E> header;
	private Entry<E> tail;

	// 默认排序器
	private Comparator<E> comparator;

	private int size = 0;

	public TreeList() {

	}

	/**
	 * comparator接口暂不支持
	 * 
	 * @param comparator
	 */
	public TreeList(Comparator<E> comparator) {
		this.comparator = comparator;
		throw new UnsupportedOperationException("comparator接口暂不支持");
	}

	/**
	 * 插入元素(用插入排序将新元素添加至链表中合适的位置)
	 * 
	 * @param e
	 * @return
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (size == 0) {
			Entry<E> newEntry = new Entry<E>(e);
			header = newEntry;
		} else if (size == 1) {
			Comparable<? super E> key = (Comparable<? super E>) e;
			if (key.compareTo(header.element) <= 0) {
				Entry<E> newEntry = new Entry<E>(e, header, null);
				header.previous = newEntry;
				header = newEntry;
				tail = header.next;
			} else {
				Entry<E> newEntry = new Entry<E>(e, null, header);
				header.next = newEntry;
				tail = newEntry;
			}
		} else {
			Comparable<? super E> key = (Comparable<? super E>) e;
			if (key.compareTo(header.element) <= 0) {
				Entry<E> newEntry = new Entry<E>(e, header, null);
				header.previous = newEntry;
				header = newEntry;
			} else {
				Entry<E> c = header;
				boolean find = false;
				while ((c = c.next) != null) {
					if (key.compareTo(c.element) <= 0) {
						Entry<E> newEntry = new Entry<E>(e, c, c.previous);
						c.previous.next = newEntry;
						c.previous = newEntry;
						find = true;
						break;
					}
				}
				// 没找着，说明e应该放在最后
				if (!find) {
					Entry<E> newEntry = new Entry<E>(e, null, tail);
					tail.next = newEntry;
					tail = newEntry;
				}
			}
		}
		size++;
		return true;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean remove(E e) {
		if (size == 0) {
			return false;
		}
		boolean removed = false;
		if (size == 1) {
			if (header.element.equals(e)) {
				header = null;
				tail = null;
				removed = true;
			}
		} else if (size == 2) {
			if (header.element.equals(e)) {
				tail.previous = null;
				tail.next = null;
				header = tail;
				tail = null;
				removed = true;
			} else if (tail.element.equals(e)) {
				header.previous = null;
				header.next = null;
				tail = null;
				removed = true;
			}
		} else {
			for (Entry<E> c = header; c != null; c = c.next) {
				if (c.element.equals(e)) {// 找到要删除的元素
					Entry<E> before = c.previous;
					Entry<E> after = c.next;
					if (before != null) {
						before.next = after;
					} else {
						header = after;
						// 如果要删除的元素是第1个，则需要更改header
					}
					if (after != null) {
						after.previous = before;
					} else {
						// 如果要删除的元素是最后1个，改需要更改tail
						tail = before;
					}
					removed = true;
					break;
				}
			}
		}
		if (removed) {
			size--;
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		header = tail = null;
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new AscendingIterator(this);
	}

	private class AscendingIterator<E> implements Iterator<E> {
		private Entry<E> header;
		private Entry<E> current;

		AscendingIterator(TreeList<E> treeList) {
			this.header = treeList.header;
		}

		@Override
		public boolean hasNext() {
			if (header == null) {
				return false;
			}
			if (current == null) {
				return true;
			}
			return current.next != null;
		}

		@Override
		public E next() {
			if (header == null) {
				throw new NoSuchElementException();
			}
			E e = null;
			if (current == null) {
				e = header.element;
				current = header;
			} else {
				if (current.next == null) {
					throw new NoSuchElementException();
				}
				e = current.next.element;
				current = current.next;
			}
			return e;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public String toString() {
		Iterator<E> i = iterator();
		if (i == null || !i.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			E e = i.next();
			sb.append(e);
			if (!i.hasNext())
				return sb.append(']').toString();
			sb.append(", ");
		}
	}

	private static class Entry<E> {
		E element;
		Entry<E> previous;
		Entry<E> next;

		Entry(E element) {
			this(element, null, null);
		}

		Entry(E element, Entry<E> next, Entry<E> previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
	}

}
