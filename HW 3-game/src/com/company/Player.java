package com.company;

import java.util.Random;

public class Player {
    private String type;  //archer || swordsman
    private double health;
    private double hitStrength;

    Random random = new Random();
    public Player(String type){
        if(type.contains("archer")){
            this.type = type;
            this.health = 50;//random.nextInt(100);
            this.hitStrength = 6; //5 + (5*random.nextDouble()); //5-10
        }
        if(type.contains("swordsman")){
            this.type = type;
            this.health = 40; //random.nextInt(100);;
            this.hitStrength = 10; //5 + (10*random.nextDouble()); //5-15;
        }
    }

//    public Creature(String type, double health) {
//        this.type = type;
//        this.health = health;
//        this.hitStrength = hitStrength;
//    }
//
//    public Creature() {
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHitStrength() {
        return hitStrength;
    }

    public void setHitStrength(double hitStrength) {
        this.hitStrength = hitStrength;
    }

    public void getHit(Player by_creature){

        if (by_creature.getHealth() == 0){
            //System.out.println("opponent is dead -- finish of the game");
            return;
        }
        else {
            double opponentHitStrength = by_creature.getHitStrength();
            //if archer hits swordsman by it's default hitStrength
            //swordsman's protection shield will reduce archer's hitStrength by 30%
            if(by_creature.getType().contains("archer")){
                opponentHitStrength = opponentHitStrength - (opponentHitStrength * 0.3);
            }
            //if swordsman hits archer by it's default hitStrength
            //archer's protection shield will reduce swordsman's hitStrength by 15%
            if(by_creature.getType().contains("swordsman")){
                opponentHitStrength = opponentHitStrength - (opponentHitStrength * 0.15);
            }

            //after getting hit, the player's lives count will reduce
            // based on the opponent's  hit strength (after using protection shield)
            double healthAfterdamage = getHealth() - opponentHitStrength;
            if(healthAfterdamage > 0 ){
                setHealth(healthAfterdamage);
            } else {
                //System.out.println(getType() + " is dead");
                setHealth(0);
            }
        }


    }

//    //if player decides how much hitstrength to use


    @Override
    public String toString() {
        return
                "player: " + getType()
                        + ", remaining life count: " + getHealth() + "\n";
//                "after getting hit by the opponent " +
//                getType() + "'s remaining life count is "
//                + getHealth() + "with hitStrength" +getHitStrength() + "\n";
    }
}

