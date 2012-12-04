package utility;

import interfaces.Drawable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.JLabel;

public class ClockDisplay implements Drawable {

	private static ClockDisplay sharedClock;
	private boolean isEnabled;
	private double interval;
	private int hour, minute, second;
	private JLabel timeLabel;

	@SuppressWarnings("unused")
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ClockDisplay.class);
	private BufferedImage image;

	public static ClockDisplay getInstance() {
		if (sharedClock == null) {
			sharedClock = new ClockDisplay();

			String timeString = "00:00:00";
			sharedClock.timeLabel = new JLabel(timeString);

			sharedClock.timeLabel.setForeground(Color.blue);
			sharedClock.timeLabel.setVisible(false);

		}
		return sharedClock;
	}

	public void reset() {
		//if(this.isEnabled == true || this.isEnabled == false){
		hour = 0;
		minute = 0;
		second = 0;
		//}
	}

	private void updateClock() {

		interval += 0.1;
		DecimalFormat dtime = new DecimalFormat("#.###");
		interval = Double.valueOf(dtime.format(interval));

		String strHour = (sharedClock.getHour() <= 9 ? ("0" + sharedClock
				.getHour()) : sharedClock.getHour()).toString();
		String strMinute = (sharedClock.getMinute() <= 9 ? ("0" + sharedClock
				.getMinute()) : sharedClock.getMinute()).toString();
		String strSecond = (sharedClock.getSecond() <= 9 ? ("0" + sharedClock
				.getSecond()) : sharedClock.getSecond()).toString();
		timeLabel.setText(strHour + ":" + strMinute + ":" + strSecond);

		if (interval % 1.0 == 0.0) {
			second += 1;
			sharedClock.setSecond(second);
			if (sharedClock.second % 60 == 0 && sharedClock.minute != 59) {
				minute += 1;
				second = 0;
				sharedClock.setMinute(minute);
				sharedClock.setSecond(second);
				interval = 0.0;
			} else if (sharedClock.second % 60 == 0 && minute % 59 == 0) {
				hour += 1;
				minute = 0;
				second = 0;
				setHour(hour);
				setMinute(minute);
				setSecond(second);
			}
		}

	}

	public void draw(Graphics g) {
		if (sharedClock.isEnabled())
			sharedClock.updateClock();
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);

		int x = (int) (188 * ResizeHelper.getInstance().getxFactor());
		int y = (int) (20 * ResizeHelper.getInstance().getyFactor());
		int width = (int) (50 * ResizeHelper.getInstance().getxFactor());
		int height = (int) (50 * ResizeHelper.getInstance().getyFactor());
		image = null;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		image.getGraphics();

		g.setFont(new Font("sansserif", Font.BOLD, 16));
		g2.drawString(sharedClock.timeLabel.getText(), x, y);

	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;

	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public double getInterval() {
		return interval;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

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

	@Override
	public boolean isVisible() {
		return this.timeLabel.isVisible();
	}

	public void setVisible(boolean visible) {
		this.timeLabel.setVisible(visible);
	}

	@Override
	public String getLayer() {
		return Constants.ALL_LAYERS;
	}

}
