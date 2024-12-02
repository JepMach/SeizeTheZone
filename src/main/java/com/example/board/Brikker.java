package com.example.board;

public class Brikker implements Actions {
    String navn;
    //Brik status
    boolean væltet=false;
    boolean harBold=false;
    boolean brugtBevægelse=false;
    boolean brugtAction=false;

    //Atributter
    int standhaftig=0;
    int stærkTakler=0;
    int klæbeHænder=0;

    public Brikker(){}
    public Brikker(String navn){
        this.navn=navn;
    }

}

