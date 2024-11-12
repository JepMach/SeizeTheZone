package com.example.board;

import java.util.ArrayList;
import java.util.Arrays;

public class Brik extends SpilObjekt{
    public final String navn;
    //private Bold harBold;
    private boolean væltet=false;
    //private Spiller ejer;
    private boolean brugtBevægelse;
    private boolean brugtAction;
    private Bold harBold;

    //private final ArrayList<Felt> bevægelse;


    public Brik(String navn, int x, int y){
        this.navn=navn;
        this.set(Bræt.getBræt()[x][y]);
    }
    @Override
    public Brik getObj() {
        return this;
    }
    @Override
    public String getObjType() {
        return "BRIK";
    }



    public Bold getBold(){
        return this.harBold;
    }
    public void pickUpBold(Bold bold){
        this.harBold=bold;
        bold.fjern();
    }

    /*
    public ArrayList<Felt> getBevægelse(){
        return this.bevægelse;
    }

    public ArrayList<Felt> createBevægelse(){
        Felt[][] brikBevægelse = new Felt[5][5];
        ArrayList<Felt> bevægelsesListe = new ArrayList<>();
        int y=-2;
        int x=-2;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                try {
                    brikBevægelse[i][j] = Bræt.getBræt()[this.getObjPos().getX()][this.getObjPos().getY()];
                } catch (Exception ignored){}
                x++;
            }
            x=-2;
            y++;
        }
        for (Felt[] array : brikBevægelse) {
            bevægelsesListe.addAll(Arrays.asList(array));
        }

        return bevægelsesListe;
    }
    public boolean isVæltet(){
        return this.væltet;
    }
    public void brugAction() {
        this.brugtAction=true;
    }
    public void brugBevægelse() {
        this.brugtBevægelse = true;
    }

    @Override
    public void tackle(){
        System.out.println("TACKLE");
    }
    @Override
    public int tackleCost(){
        return 3;
    }







    public static void createSpillerBrik(Spiller spiller, String brikNavn){
        Brik nyBrik = new Brik(brikNavn);
        nyBrik.hold=spiller.hold;
        nyBrik.brikEjer=spiller;

        for (int i=0; i<spiller.spillerBrikker.length;i++){
            if (spiller.spillerBrikker[i]==null){
                spiller.spillerBrikker[i]=nyBrik;
                break;
            }
        }
    }

    public void setPos(int y, int x) {
        this.brikPos[0]=y;
        this.brikPos[1]=x;
    }
    public int[] getBrikPos() {
        return brikPos;
    }
    public void resetBrik(){
        this.brugtBevægelse=false;
        this.brugtAction=false;
    }
    public void væltBrik(){
        this.væltet=true;
    }
    public void rejsBrik(){
        this.væltet=false;
    }
    public boolean isBoldHolder(){
        return this.boldHolder;
    }
     */



}

