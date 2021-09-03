package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 2000;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";
    public static int medicHealing = 20;
    public static int[] heroesHealth = {300, 250, 300, 200, 350}; //жизнь
    public static int[] heroesDamage = {40, 50, 25, 15, 0};
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Mental", "LuckyMan", "Medic"};


    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        heroesHit();
        luckyManAbility();
        medicHealing();
        fightInfo();
    }


    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];

    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] - bossDamage;
            if (heroesHealth[i] < 0){
                System.out.println(bossDamage != heroesHealth[i]);
            }

        }
    }




    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackTypes[i])) {
                    Random random2 = new Random();
                    int koef = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * koef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koef;
                    }
                    System.out.println(heroesAttackTypes[i] + " critical hit " + heroesDamage[i] * koef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }
    }

    public static void luckyManAbility(){ //ловкач
        Random random3 = new Random();
        boolean luckyManChance = random3.nextBoolean();
        if (heroesHealth[3] <= 0){
            heroesHealth[3] = 0;
        } else {
            if (luckyManChance){
                heroesHealth[3] += bossDamage;
                System.out.println("LuckyMan dodged!");
            }
        }
    }

    public static void medicHealing(){
        if (heroesHealth[4] > 0){
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0){
                    heroesHealth[i] += medicHealing;
                }
            }
            System.out.println("Medic cured!");
        }
    }





    public static void fightInfo(){
        System.out.println("_____________________________");
        System.out.println("Boss health:" + bossHealth);
        System.out.println("Warrior health:" + heroesHealth[0]);
        System.out.println("Magic health:" + heroesHealth[1]);
        System.out.println("Kinetic health:" + heroesHealth[2]);
        System.out.println("LuckyMan health:" + heroesHealth[3]);
        System.out.println("Medic health:" + heroesHealth[4]);
        System.out.println("_______________________________");

    }
}

