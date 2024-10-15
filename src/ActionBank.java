/*import java.util.Arrays;

public interface ActionBank {
    default void tackle(Spiller spiller){
        Felt tackleTo = spiller.getEndeFelt();
        Felt tackleFrom;
        try {
            tackleFrom = spiller.valgteFelter.get(spiller.valgteFelter.size() - 2);
        }catch (Exception etValgtFelt){
            tackleFrom = spiller.valgteBrik.getPos();
        }
        int[] direction = getDirection(tackleFrom,tackleTo);


        spiller.getEndeFelt().brikPåFelt.setPos(Bræt.bræt[tackleFrom.getPos()[1]+direction[1]][tackleTo.getPos()[0]+direction[0]]);
        spiller.valgteBrik.setPos(tackleTo);
        System.out.print(Arrays.toString(Bræt.bræt[tackleFrom.getPos()[1]+direction[1]][tackleTo.getPos()[0]+direction[0]].getPos()));
    }
    private int[] getDirection(Felt felt1, Felt felt2){


        if (felt2.getPos()[0]<felt1.getPos()[0]){
            X=-1;
        } else if (felt2.getPos()[0]> felt1.getPos()[0]){
            X=1;
        } else {
            X=0;
        }

        if (felt2.getPos()[1]<felt1.getPos()[1]){
            Y=-1;
        } else if (felt2.getPos()[1]> felt1.getPos()[1]){
            Y=1;
        } else {
            Y=0;
        }





    }

    default boolean getFeltInfo(int y, int x){
        return Bræt.bræt[y][x].getFeltTomt();
    }
}*/
