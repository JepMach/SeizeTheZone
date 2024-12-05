package com.example.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public interface Actions {

    default ArrayList<int[]> getBevægelse(ArrayList<int[]> brikBevægelse, BrætTilstand bræt){
        ArrayList<int[]> bevægelse = brikBevægelse;

        for (int[] bevægelsesFelt: brikBevægelse){
            if (bræt.bræt[bevægelsesFelt[0]][bevægelsesFelt[1]]==null)continue;
            bevægelse.remove(bevægelsesFelt);
        }
        return bevægelse;
    }
    default void takling(int[] startFelt, int[] slutFelt, int[]retning, Brikker aktivBrik, Brikker reaktivBrik, BrætTilstand bræt){

        int[] tjekFelt = new int[]{slutFelt[0]+retning[0],slutFelt[1]+retning[1]};
        ArrayList<int[]> flytteKø = new ArrayList<>();
        int[] taklerFraPos = new int[]{slutFelt[0]-retning[0],slutFelt[1]-retning[1]};
        flytteKø.add(taklerFraPos);
        flytteKø.add(slutFelt);
        try {
            do {
                if (Objects.equals(bræt.bræt[tjekFelt[0]][tjekFelt[1]].navn, "Bold")) {
                    bræt.bræt[tjekFelt[0]][tjekFelt[1]].flytBold(tjekFelt,bræt);
                    break;
                } else {
                    flytteKø.add(tjekFelt);
                }
                tjekFelt[0] += retning[0];
                tjekFelt[1] += retning[1];
            } while (!(bræt.bræt[tjekFelt[0]][tjekFelt[1]] == null));

            for (int[] brikPos : flytteKø.reversed()) {
                Brikker flytteBrik = bræt.bræt[brikPos[0]][brikPos[1]];
                bræt.bræt[brikPos[0]][brikPos[1]] = null;
                bræt.bræt[brikPos[0] + retning[0]][brikPos[1] + retning[1]] = flytteBrik;
            }

            if (!(reaktivBrik.klæbeHænder ==0)){
                reaktivBrik.harBold=false;
                flytBold(slutFelt,bræt);
            }

        } catch (Exception e){
            bræt.bræt[flytteKø.getLast()[0]][flytteKø.getLast()[1]].væltet=true;
            flytteKø.removeLast();
            for (int[] brikPos : flytteKø.reversed()) {
                Brikker flytteBrik = bræt.bræt[brikPos[0]][brikPos[1]];
                bræt.bræt[brikPos[0]][brikPos[1]] = null;
                bræt.bræt[brikPos[0] + retning[0]][brikPos[1] + retning[1]] = flytteBrik;
            }
        }
        bræt.bræt[startFelt[0]][startFelt[1]]=null;
        bræt.bræt[slutFelt[0]][slutFelt[1]]=aktivBrik;

    }

    default void flytBold(int[] orgPos, BrætTilstand bræt){
        ArrayList<int[]> muligePos = new ArrayList<>();
        ArrayList<int[]> exceptionPos = new ArrayList<>();

        int x=-1;
        int y=-1;

        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++) {
                try {
                    int[] tomPos= new int[]{orgPos[0]+x,orgPos[1]+y};
                    muligePos.add(tomPos);
                    exceptionPos.add(tomPos);
                    x++;
                } catch (Exception ignored) {}
            }
            y++;
        }

        try {
            for (int[] tommePos: muligePos){
                if (!(bræt.bræt[tommePos[0]][tommePos[1]] ==null)){
                    muligePos.remove(tommePos);
                }
                if (tommePos==orgPos) {
                    muligePos.remove(tommePos);
                }
            }
            Collections.shuffle(muligePos);
            int[] landePos = muligePos.getLast();
            Brikker bold = bræt.bræt[orgPos[1]][orgPos[2]];
            bræt.bræt[orgPos[0]][orgPos[1]] = null;
            bræt.bræt[landePos[0]][landePos[1]] = bold;
        } catch (Exception e){
            //Hvad der sker hvis der ikke er nogen tomme felter til bolden
            Collections.shuffle(exceptionPos);
            int[] landePos = exceptionPos.getLast();
            bræt.bræt[orgPos[0]][orgPos[1]] = null;
            bræt.bræt[landePos[0]][landePos[1]].harBold=true;
        }

    }
}