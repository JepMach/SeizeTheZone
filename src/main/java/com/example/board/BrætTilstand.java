package com.example.board;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.beans.PropertyChangeSupport;

public class BrætTilstand {
    //Spiller variabler
    ArrayList<ArrayList<Brikker>> spillerBrikker = new ArrayList<>();
    int[] spillerMål = new int[]{0,0};
    int [] actionPoints;

    //Bræt variabler
    Brikker[][] bræt;
    ArrayList<int[]> brikKoordinater = new ArrayList<>();
    final int [][][] brætKoordinater;
    int tur;

    //Input variabler
    ArrayList<int[]> valgteFelter = new ArrayList<>();

    //PropertyChange
    private final PropertyChangeSupport brætÆndring = new PropertyChangeSupport(this);

    public BrætTilstand(ArrayList<ArrayList<Brikker>> brikker, int brætX, int brætY, int actionPoints1, int actionsPoint2) {
        spillerBrikker.add(new ArrayList<>(brikker.get(0)));
        spillerBrikker.add(new ArrayList<>(brikker.get(1)));
        brætKoordinater=new int[brætX][brætY][2];
        bræt=new Brikker[brætX][brætY];
        Brikker bold = new Brikker("Bold");
        actionPoints=new int[]{actionPoints1,actionsPoint2};
        tur=1;
        int brikNr=0; //indeksnummer i en given spillers arrayliste af brikker
        int spillerNr = 0; // Når spillerNr=0 vælges første arrayliste med spillerbrikker

        for (int i=0;i<brætY;i++){
            for (int j=0;j<brætX;j++){
                brætKoordinater[j][i]= new int[]{j,i};
                bræt[j][i]=null;
                try{
                    if (j==3 && i==3+brikNr) {
                        bræt[j][i] = spillerBrikker.get(spillerNr).get(brikNr);
                        brikKoordinater.add(new int[]{j,i});
                        spillerNr++;
                    }
                    if (j==brætX-4 && i==3+brikNr){
                        bræt[j][i] = spillerBrikker.get(spillerNr).get(brikNr);
                        brikKoordinater.add(new int[]{j,i});
                        spillerNr--;
                        brikNr++;
                    }
                    if (j==6 && i==3){
                        bræt[j][i] = bold;
                        brikKoordinater.add(new int[]{j,i});
                    }

                }catch(Exception ignored){}
            }
        }
        for (int[] fuk: brikKoordinater){
            System.out.println(Arrays.toString(fuk));
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        brætÆndring.addPropertyChangeListener(pcl);
    }

    public void opdaterValgteFelter(int[] nytValg){
        BrætTilstand orgValgteFelter = new BrætTilstand(this.spillerBrikker,this.bræt.length,this.bræt[1].length,this.actionPoints[0],this.actionPoints[1]);
        this.valgteFelter.add(nytValg);
        this.brætÆndring.firePropertyChange("ValgteFelter",orgValgteFelter,this);
    }

    public void opdaterBrætTest(int[] orgPos, int[] nyPos){
        BrætTilstand orgBræt = new BrætTilstand(this.spillerBrikker,this.bræt.length,this.bræt[1].length,this.actionPoints[0],this.actionPoints[1]);
        Brikker brik = bræt[orgPos[0]][orgPos[1]];
        bræt[orgPos[0]][orgPos[1]]=null;
        brikKoordinater.remove(orgPos);
        bræt[nyPos[0]][nyPos[1]]=brik;
        brikKoordinater.add(nyPos);
        this.valgteFelter.clear();
        this.brætÆndring.firePropertyChange("brikker",orgBræt , this);
    }
}

/*
    public void printTest(){
        for (int i=0; i<bræt[1].length;i++){
            for (int j=0; j<bræt.length;j++){
                if (bræt[j][i]==null){
                    System.out.print(Arrays.toString(brætKoordinater[j][i]));
                } else {
                    System.out.print("[ "+bræt[j][i].navn+" ]");
                }
            }
            System.out.println(" ");
        }
    }

     */


    /*
    public static void flytSpilObj(SpilObjekt obj, Felt felt) {
        obj.fjern();
        obj.set(felt);
    }

    public static void vælgFelt(int x, int y) {
        Felt felt = bræt[x][y];
        if (Handler.getAktivBrik() == null) {
            for (Brik b : aktivSpiller.getSpillerBrikker()) {
                if (b.getObjPos() == felt) {
                    Handler.setAktivBrik(b);
                    return;
                }
            }
        } else if (felt == Handler.getAktivBrik().getObjPos()) {
            Handler.setAktivBrik(null);
        } else if (getGrænseFelter(Handler.getEndeFelt()).contains(felt)) {
            Handler.addToQueue(felt);
        }
    }

    public static ArrayList<Felt> getGrænseFelter(Felt orgFelt) {
        ArrayList<Felt> grænseListe = new ArrayList<>();
        Felt[][] grænseFelter = new Felt[3][3];
        int y = -1;
        int x = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    grænseFelter[j][i] = bræt[orgFelt.getX() + x][orgFelt.getY() + y];
                } catch (Exception ignored) {
                }
                x++;
            }
            x = -1;
            y++;
        }
        for (Felt[] array : grænseFelter) {
            grænseListe.addAll(Arrays.asList(array));
        }
        return grænseListe;
    }

    public static ArrayList<Felt> getTommeGrænseFelter(Felt felt) {
        ArrayList<Felt> tommeFelter = new ArrayList<>();
        for (Felt tFelt : getGrænseFelter(felt)) {
            if (tFelt.isTomt()) {
                tommeFelter.add(tFelt);
            }
        }
        return tommeFelter;
    }
    public static void flytBrik(Brik brik, Felt felt) {
        if (brik.getBrikPos() == felt.getPos()) {
            System.out.println(" ");
            System.out.println("Brik står allerede på dette felt");
            System.out.println(" ");
            clearValg();
            return;
        }
        int[] dir;
        if (brik == valgteBrik && valgteFelter.size() > 1) {
            dir = getDirection(valgteFelter.get(valgteFelter.size() - 2), felt);
        } else {
            dir = getDirection(bræt[brik.getBrikPos()[0]][brik.getBrikPos()[1]], felt);
        }
        if (!felt.getFeltTomt()) {
            Brik tBrik = felt.getBrikPåFelt();
            if (!brikQueue.contains(brik)) {
                brikQueue.add(brik);
            }
            brikQueue.add(tBrik);
            Felt nyFelt;
            try {
                nyFelt = bræt[felt.getPos()[0] + dir[0]][felt.getPos()[1] + dir[1]];
                flytBrik(tBrik, nyFelt);
            } catch (Exception e) {
                tBrik.væltBrik();
                fjernBrik(valgteBrik.getBrikPos()[0], valgteBrik.getBrikPos()[1]);
                try {
                    setBrik(valgteBrik, valgteFelter.get(valgteFelter.size() - 2).getPos()[0], valgteFelter.get(valgteFelter.size() - 2).getPos()[1]);
                } catch (Exception o) {
                    setBrik(getValgteBrik(), getValgteBrik().getBrikPos()[0], getValgteBrik().getBrikPos()[1]);
                }
            }
        } else {
            Felt lastFelt = bræt[brik.getBrikPos()[0]][brik.getBrikPos()[1]];
            fjernBrik(brik.getBrikPos()[0], brik.getBrikPos()[1]);
            setBrik(brik, felt.getPos()[0], felt.getPos()[1]);
            try {
                brikQueue.removeLast();
                Brik qBrik = brikQueue.getLast();
                flytBrik(qBrik, lastFelt);
            } catch (Exception ignored) {
            }
        }
        clearValg();
    }
     */