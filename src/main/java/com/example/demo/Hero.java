package com.example.demo;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hero {
    private String name;
    private Image image;
    private int tier;
    private int level;
    private String tierImg;
    private int hp;
    private int totalHp;
    private int attack;
    //Speed: unit will attack twice if unit's speed is at least twice more than foes speed
    private int speed;
    //Defense: resists physical attacks
    private int defense;
    //Resistance: resists magical attacks
    private int resistance;
    private int exp;
    private int skillPoints;
    private Weapon weapon;
    private Locations location;

    public Hero(String n, String i, int t, int h, int a, int s, int d, int r){
        name = n;
        try {
            FileInputStream input = new FileInputStream(i);
            image = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tier = t;
        level = 1;
        if(t == 3){
            tierImg = "src/main/resources/threeStar.png";
        } else if (t == 4){
            tierImg = "src/main/resources/fourStar.png";
        } else {
            tierImg = "src/main/resources/fiveStar.png";
        }
        hp = h;
        totalHp = h;
        attack = a;
        speed = s;
        defense = d;
        resistance = r;
        exp = 0;
        skillPoints = 0;
        location = null;
    }

    public String getName() {
        return name;
    }

    public Image getImage(){
        return image;
    }

    public int getTier() {
        return tier;
    }

    public int getLevel() {
        return level;
    }

    public String getTierImg() {
        return tierImg;
    }

    public int getHp() {
        return hp;
    }

    public int getTotalHp() {
        return totalHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return defense;
    }

    public int getResistance() {
        return resistance;
    }

    public int getExp() {
        return exp;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Locations getLocation() {
        return location;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public void levelUp() {
        this.level++;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setImage(String i){
        try {
            FileInputStream input = new FileInputStream(i);
            image = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
