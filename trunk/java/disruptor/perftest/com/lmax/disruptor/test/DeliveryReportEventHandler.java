package com.lmax.disruptor.test;

import com.lmax.disruptor.EventHandler;

public class DeliveryReportEventHandler implements EventHandler<DeliveryReportEvent> {
	// private static DeliveryReportRepository repository = new
	// DeliveryReportRepository();
	public void onEvent(DeliveryReportEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(event.getDeliveryReport().getMessageId());
		// repository.updateDeliveryReport(event.getDeliveryReport());
	}
}