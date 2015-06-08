package com.towerdefense.towerdefense;

import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.entities.mobs.MobFactory;

import java.util.Random;

public class MobSpawn {

    public Mob getRandomMob() {
        Random random = new Random();
        return MobFactory.createMob(random
                .nextInt(MobFactory.MOB_TYPE_AMOUNT + 1));
    }
}
