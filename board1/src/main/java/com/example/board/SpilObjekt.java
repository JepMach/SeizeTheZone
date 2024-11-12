package com.example.board;

import javafx.scene.image.Image;

import static com.example.board.Grafik.CELL_SIZE;

public abstract class SpilObjekt {
    private Felt objPos;
    private Image objSprite;

    public SpilObjekt(){
    }

    public Image getObjSprite() {
        return objSprite;
    }
    public void setObjSprite(String path) {
        this.objSprite = new Image(path, CELL_SIZE, CELL_SIZE, true, true);
    }

    public void fjern(){
        this.objPos.fjernFeltObjekt();
        this.objPos=null;
    }
    public void set(Felt felt){
        this.objPos=felt;
        this.objPos.setFeltObjekt(this);
    }
    public void flyt(Felt felt){
        this.fjern();
        this.set(felt);
    }

    public Felt getObjPos() {
        return objPos;
    }
    abstract public SpilObjekt getObj();
    abstract public String getObjType();
}
