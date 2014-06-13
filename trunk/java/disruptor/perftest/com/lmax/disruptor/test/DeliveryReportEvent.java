package com.lmax.disruptor.test;

import com.lmax.disruptor.EventFactory;

public class DeliveryReportEvent {
	private DeliveryReport deliveryReport;

	public DeliveryReport getDeliveryReport() {
		return deliveryReport;
	}

	public void setDeliveryReport(DeliveryReport deliveryReport) {
		this.deliveryReport = deliveryReport;
	}

	public final static EventFactory<DeliveryReportEvent> EVENT_FACTORY = new EventFactory<DeliveryReportEvent>() {
		public DeliveryReportEvent newInstance() {
			return new DeliveryReportEvent();
		}
	};
}