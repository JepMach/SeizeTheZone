package com.example.board;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<Brikker> spillerBrikker1 = new ArrayList<>();
    static ArrayList<Brikker> spillerBrikker2 = new ArrayList<>();
    static ArrayList<ArrayList<Brikker>> brætBrikker = new ArrayList<>();
    public static void main(String[] args) {
        spillerBrikker1.add(new Brikker("QB"));
        spillerBrikker1.add(new Brikker("WR"));
        spillerBrikker2.add(new Brikker("RB"));
        spillerBrikker2.add(new Brikker("LM"));

        brætBrikker.add(spillerBrikker1);
        brætBrikker.add(spillerBrikker2);


        BrætTilstand testBræt=new BrætTilstand(brætBrikker,18,8,7,7);
        testBræt.printTest();

        int t=0;
        do {
            System.out.println(t);
            t++;
        } while (t<10);




    }
}
