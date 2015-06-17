package com.towerdefense.towerdefense;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import com.towerdefense.towerdefense.objects.Ground;

public class GlobalVariables {
	public static String nickname;
	private static BufferedImage sprite = null;
	private static BufferedImage sprites = null;
	public static int life = 10;
	public static int money = 5000;
	public static Ground spawnpoint;
	public static Timer timer;

	/*
	 * public static BufferedImage getSprite() { if (sprite == null) { try {
	 * sprite = ImageIO.read(GlobalVariables.class
	 * .getResource("/res/images/sprite.png")); } catch (IOException e) { } }
	 * return sprite; }
	 */

	public static void dropMoney(int loss) {
		money -= loss;
	}

	public static void earnMoney(int gain) {
		money += gain;
	}

	public static BufferedImage getSprites() {
		if (sprites == null) {
			try {
				sprites = ImageIO.read(GlobalVariables.class
						.getResource("/res/images/sprites.png"));
			} catch (IOException e) {
			}
		}
		return sprites;
	}
}
