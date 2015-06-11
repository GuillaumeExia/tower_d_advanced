package com.towerdefense.events;

public class Stopwatch {

	public static String timeToString(long durationSec) {

		int m = (int) ((durationSec % 3600) / 60);
		int s = (int) (durationSec % 60);

		String resultString = "";

		if ((m < 10) && (s < 10)) {
			resultString += "0" + m + ":" + "0" + s;
		}
		else if ((m < 10) && (s >= 10)) {
			resultString += "0" + m + ":" + s;
		}
		else if ((m > 10) && (s < 10)) {
			resultString += m + ":" + "0" + s;
		}
		else if (((m > 10) && (s > 10))) {
			resultString += m + ":" + s;
		}
		return resultString;

	}

	int duration;

	private long startTime = 0;
	// private long endTime = 0;
	private long startPause;

	private long totalPauseTime = 0;

	// private long endPause;
	// private long duration = 0;

	public String getTimeIs() {
		if (this.duration == 0) {
			return timeToString((System.currentTimeMillis() - this.startTime - this.totalPauseTime) / 1000);
		}
		else {
			return timeToString(this.duration);
		}
	}

	public int getTimeIsInt() {
		if (this.duration == 0) {
			this.duration = (int) ((System.currentTimeMillis() - this.startTime - this.totalPauseTime) / 1000);
		}
		return this.duration;
	}

	public void pause() {
		this.startPause = System.currentTimeMillis();
	}

	public void resume() {
		this.totalPauseTime += (System.currentTimeMillis() - this.startPause);
		// this.endPause = System.currentTimeMillis();
	}

	public void setTimeIs(int time) {
		this.duration = time;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();

	}

	/*
	 * public void stop() { if (this.startTime == 0) { return; } this.endTime =
	 * System.currentTimeMillis(); this.duration = (this.endTime -
	 * this.startTime) - (this.endPause - this.startPause);
	 * 
	 * }
	 */

}
