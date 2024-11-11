/*
package com.example.board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Handler {

    private static Brik aktivBrik;
    private static Bold bold;
    private static ArrayList<Brik> brikQueue;
    private static ArrayList<Felt> feltQueue;

    public Handler(){

    }
    public static Brik getAktivBrik() {
        return aktivBrik;
    }
    public static void setAktivBrik(Brik aktivBrik) {
        clearHandler();
        Handler.aktivBrik = aktivBrik;
        addToQueue(aktivBrik);
        //Add bevægelsesmuligheder
    }

    public static Bold getBold() {
        return bold;
    }
    public static void setBold(Bold bold) {
        Handler.bold = bold;
    }
    public static ArrayList<Brik> getBrikQueue() {
        return brikQueue;
    }
    public static ArrayList<Felt> getFeltQueue() {
        return feltQueue;
    }
    public static void addToQueue(Brik brik){
        brikQueue.add(brik);
    }
    public static void addToQueue(Felt felt){
        feltQueue.add(felt);
    }
    public static void clearHandler(){
        aktivBrik=null;
        bold=null;
        brikQueue.clear();
        feltQueue.clear();
    }

    public static Felt getEndeFelt() {
        try {
            return feltQueue.getLast();
        } catch (Exception intetValgt){
            return aktivBrik.getObjPos();
        }
    }
    public static Felt getPenUlFelt() {
        try {
            return feltQueue.get(feltQueue.size()-2);
        } catch (Exception intetValgt){
            return aktivBrik.getObjPos();
        }
    }


    public static int[] getDirection(Felt fra, Felt til){
        int X,Y;
        X= Integer.compare(til.getX(), fra.getX());
        Y= Integer.compare(til.getY(), fra.getY());
        return new int[]{Y,X};
    }
    public static Felt getDirFelt(Felt fra, Felt til){
        return Bræt.getBræt()[til.getX()+getDirection(fra,til)[0]][til.getY()+getDirection(fra,til)[1]];
    }

    public static void handleRyk(){
        Felt endeFelt = getEndeFelt();
        Felt penUlFelt = getPenUlFelt();
        Felt dirFelt = getDirFelt(penUlFelt,endeFelt);
        ArrayList<Felt> tommeFelter = Bræt.getTommeGrænseFelter(endeFelt);
        Collections.shuffle(tommeFelter);

        switch (endeFelt.getFeltVærdi()){
            case 0:
                //Tomt felt
                aktivBrik.flyt(endeFelt);
                break;
            case 1:
                //Brik
                break;
            case 2:
                //Væltet brik
                Brik vBrik = (Brik) endeFelt.getFeltObjekt().getObj();
                vBrik.flyt(tommeFelter.getFirst());
                aktivBrik.flyt(endeFelt);
                break;
            case 3:
                //Brik med bold
                break;
            case 4:
                //Bold
                aktivBrik.flyt(endeFelt);
                aktivBrik.pickUpBold(bold);
                break;
            default:

        }

        clearHandler();
    }
}

 */

