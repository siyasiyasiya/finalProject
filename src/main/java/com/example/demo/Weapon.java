package com.example.demo;

public class Weapon {
    private String name;
    private String type;
    private int might;
    private int range;
    private String image;
    private boolean healer;
    private int countdown = 0;

    public Weapon(String n, String t, int m){
        name = n;
        type = t;
        might = m;
        if(name.equals("Bow") || name.equals("Staff") || name.equals("Tome")){
            range = 2;
        } else {
            range = 1;
        }
        if(name.equals("Staff") || name.equals("Tome")){
            healer = true;
        } else {
            healer = false;
        }
        image = "src/main/resources/" + type + name + ".png";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getMight() {
        return might;
    }

    public int getRange() {
        return range;
    }

    public String getImage() {
        return image;
    }

    public boolean isHealer(){
        return healer;
    }

    public boolean canHeal(){
        return countdown == 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMight(int might) {
        this.might = might;
    }

    public void resetCountdown(){
        if(healer){
            this.countdown = 3;
        }
    }

    public void countdown(){
        if(countdown>0){
            countdown--;
        }
    }
}
