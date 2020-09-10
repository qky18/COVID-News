package com.java.qiukeyue.bean;

public class CovidData {
    private boolean inChina;
    private String name;
    private int confirmed;
    private int suspected;
    private int cured;
    private int dead;

    public CovidData(boolean inChina, String name, int confirmed, int suspected, int cured, int dead){
        this.inChina = inChina;
        this.name = name;
        this.confirmed = confirmed;
        this.suspected = suspected;
        this.cured = cured;
        this.dead = dead;
    }

    public boolean isInChina() {
        return inChina;
    }

    public void setInChina(boolean inChina) {
        this.inChina = inChina;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getSuspected() {
        return suspected;
    }

    public void setSuspected(int suspected) {
        this.suspected = suspected;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
