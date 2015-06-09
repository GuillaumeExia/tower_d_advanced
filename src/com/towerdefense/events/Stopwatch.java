package com.towerdefense.events;

public class Stopwatch implements Runnable {

	public static String timeToString(long durationSec) {

		int m = (int) ((durationSec % 3600) / 60);
		int s = (int) (durationSec % 60);

		String resultString = "";

		if (m > 0) {
			resultString += m + ":";
		}
		if (s > 0) {
			resultString += s + "";
		}
		if ((m <= 0) && (s <= 0)) {
			resultString = "0";
		}

		return resultString;

	}

	private Thread stopwatch;

	private int seconde = 0;

	private long startTime = 0;

	private long endTime = 0;

	private long startPause = 0;

	private long endPause = 0;
	private long duration = 0;

	public String getDurationInText() {
		return timeToString(this.getDurationSeconds());
	}

	public long getDurationSeconds() {
		return this.duration / 1000;
	}

	public String getTimeIs() {
		this.duration = (System.currentTimeMillis() - this.startTime) / 1000;
		return timeToString(this.duration);
	}

	public void pause() {
		if (this.startTime == 0) {
			return;
		}
		this.endPause = System.currentTimeMillis();
	}

	public void resume() {
		if (this.startTime == 0) {
			return;
		}
		if (this.startPause == 0) {
			return;
		}
		this.endPause = System.currentTimeMillis();
		this.startTime = (this.startTime + this.endPause) - this.startPause;
		this.endTime = 0;
		this.startPause = 0;
		this.endPause = 0;
		this.duration = 0;
	}

	@Override
	public void run() {
		try {
			while (this.stopwatch.isAlive()) {
				this.seconde++;
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
		}
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.endTime = 0;
		this.startPause = 0;
		this.endPause = 0;
		this.duration = 0;
	}

	public void stop() {
		if (this.startTime == 0) {
			return;
		}
		this.endTime = System.currentTimeMillis();
		this.duration = (this.endTime - this.startTime) - (this.endPause - this.startPause);
		this.startTime = 0;
		this.endTime = 0;
		this.startPause = 0;
		this.endPause = 0;
	}

}
