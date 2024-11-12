package com.example.board;

import javafx.scene.image.Image;

import java.util.Objects;

import static com.example.board.Grafik.CELL_SIZE;

public class Felt {
    private final int posX;
    private final int posY;
    private SpilObjekt feltObjekt;
    private Image feltSprite;

    public Felt(int xPos, int yPos){
        this.posX=xPos;
        this.posY=yPos;
    }

    public Image getFeltSprite() {
        return feltSprite;
    }

    public void setFeltSprite(String path) {
        this.feltSprite = new Image(path, CELL_SIZE, CELL_SIZE, true, true);
    }

    public boolean isTomt(){
        return this.feltObjekt != null;
    }
    public SpilObjekt getFeltObjekt(){
        return this.feltObjekt;
    }
    public void fjernFeltObjekt(){
        this.feltObjekt=null;
    }
    public void setFeltObjekt(SpilObjekt obj){
        this.feltObjekt=obj;
    }
    public int getX() {
        return this.posX;
    }
    public int getY() {
        return this.posY;
    }


    /*
    public void fjernBrik(){
        this.brikPåFelt=null;
    }
    public void setBrikPåFelt(Brik brik){
        brik.setPos(this.pos[0],this.pos[1]);
        this.brikPåFelt=brik;
    }
    public Brik getBrikPåFelt(){
        return brikPåFelt;
    }
    public boolean getFeltTomt(){
        return boldPåFelt == null;
    }
    public void fjernBold(){
        this.boldPåFelt=null;
    }
    public void setBoldPåFelt(Bold bold){
        bold.setBoldFelt(this);
        this.boldPåFelt=bold;
    }
    public Bold getBoldPåFelt() {
        return boldPåFelt;
    }

    public boolean isMålFelt() {
        return målFelt;
    }

     */


}
