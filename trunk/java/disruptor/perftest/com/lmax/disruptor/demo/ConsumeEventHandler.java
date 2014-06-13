package com.lmax.disruptor.demo;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;

public class ConsumeEventHandler implements EventHandler<ValueEvent>, LifecycleAware {
	public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("处理事件对象：" + event.getValue());
		// Thread.sleep(2000);
	}

	public void onStart() {
		System.out.println("开始处理事件");
	}

	public void onShutdown() {
		System.out.println("结束处理事件");
	}
}