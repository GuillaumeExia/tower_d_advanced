package com.towerdefense.towerdefense;

import com.towerdefense.towerdefense.objects.Ground;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GlobalVariables {
    public static String nickname;
    private static BufferedImage sprite = null;
    public static int life = 1000;
    public static int money = 10;
    public static Ground spawnpoint;

    public static BufferedImage getSprite(){
        if(sprite == null){
            try { sprite = ImageIO.read(GlobalVariables.class.getResource("/res/images/sprite.png")); }
            catch (IOException e) {}
        }
        return sprite;
    }
}
