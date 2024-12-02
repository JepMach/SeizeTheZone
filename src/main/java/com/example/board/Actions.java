package com.example.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public interface Actions {



    default void takling(int[] startFelt, int[] slutFelt, int[]retning, Brikker aktivBrik, Brikker reaktivBrik, BrætTilstand bræt){

        int[] tjekFelt = new int[]{slutFelt[0]+retning[0],slutFelt[1]+retning[1]};
        ArrayList<int[]> flytteKø = new ArrayList<>();
        int[] taklerFraPos = new int[]{slutFelt[0]-retning[0],slutFelt[1]-retning[1]};
        flytteKø.add(taklerFraPos);
        flytteKø.add(slutFelt);
        try {
            do {
                if (Objects.equals(bræt.bræt[tjekFelt[0]][tjekFelt[1]].navn, "Bold")) {
                    bræt.bræt[tjekFelt[0]][tjekFelt[1]].flytBold();
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

            if (!(aktivBrik.stærkTakler ==0) && reaktivBrik.standhaftig==0){
                reaktivBrik.væltet=true;
            }

        } catch (Exception e){
            bræt.bræt[flytteKø.getLast()[0]][flytteKø.getLast()[1]].væltet=true;
        }
        bræt.bræt[startFelt[0]][startFelt[1]]=null;
        bræt.bræt[slutFelt[0]][slutFelt[1]]=aktivBrik;

    }
    default void opsamling(){

    }

    default void flytBold(int[] orgPos, BrætTilstand bræt){
        ArrayList<int[]> muligePos = new ArrayList<>();

        int x=-1;
        int y=-1;

        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++) {
                try {
                    if (!(bræt.bræt[orgPos[0]+x][orgPos[1]+y] ==null)){
                        x++;
                        continue;
                    }
                    int[] tomPos= new int[]{orgPos[0]+x,orgPos[1]+y};
                    if (tomPos[0]==orgPos[0] && tomPos[1]==orgPos[1]){
                        x++;
                        continue;
                    }
                    muligePos.add(tomPos);
                } catch (Exception ignored) {}
            }
            y++;
        }

        try {
            Collections.shuffle(muligePos);
            Brikker bold = bræt.bræt[orgPos[1]][orgPos[2]];
            bræt.bræt[orgPos[0]][orgPos[1]] = null;
            bræt.bræt[muligePos.getFirst()[0]][muligePos.getFirst()[1]] = bold;
        } catch (Exception e){
            //Hvad der sker hvis der ikke er nogen tomme felter til bolden
        }

    }
}
