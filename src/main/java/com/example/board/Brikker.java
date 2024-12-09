package com.example.board;

import java.util.ArrayList;

public class Brikker implements Actions {
    String navn;
    //Brik status
    boolean væltet=false;
    boolean harBold=false;
    boolean brugtBevægelse=false;
    boolean brugtAction=false;

    //Atributter
    int standhaftig=0;
    int klæbeHænder=0;

    public Brikker(){}
    public Brikker(String navn){
        this.navn=navn;
    }
    public ArrayList<int[]> bevægelsesMuligheder() {
        ArrayList<int[]> bevægelse = new ArrayList<>();
        return bevægelse;
    }

    public ArrayList<String> lovligeActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Tackling");
        actions.add("Bevægelse");

        if (harBold){
            actions.add("Kast Bold");
        }

        if (brugtBevægelse){
            actions.remove("Bevægelse");
        }
        if (brugtAction && actions.contains("Tackling")){
            actions.remove("Tackling");
        }
        if (brugtAction && actions.contains("Kast Bold")){
            actions.remove("Kast Bold");
        } return actions;
    }
}

