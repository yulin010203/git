package com.lmax.disruptor.demo;

import com.lmax.disruptor.RingBuffer;

public class Producer implements Runnable {
	private RingBuffer<ValueEvent> ringBuffer = null;

	public Producer(RingBuffer<ValueEvent> rb) {
		ringBuffer = rb;
	}

	public void run() {
		// Publishers claim events in sequence
		long sequence = ringBuffer.next();
		ValueEvent event = ringBuffer.get(sequence);
		for (int i=0; i <3000;i++){
			event.setValue(i); // this could be more complex with multiple fields
			// make the event available to EventProcessors
			ringBuffer.publish(sequence);
		}
	}
}