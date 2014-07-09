package clock;

public class Clock {

	private int hour;
	private int minute;
	private int second;
	private int millis;

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setMillis(int millis) {
		this.millis = millis;
	}

	public int getMillis() {
		return millis;
	}

	@Override
	public String toString() {
		return hour + ":" + minute + ":" + second + "." + millis;
	}

}