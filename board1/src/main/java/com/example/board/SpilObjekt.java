package com.example.board;

public abstract class SpilObjekt {
    private Felt objPos;

    public SpilObjekt(){
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
