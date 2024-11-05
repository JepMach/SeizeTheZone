import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {





       /*
        Spiller rødSpiller = new Spiller(true);
        Spiller blåSpiller = new Spiller(false);

        Brik.createSpillerBrik(rødSpiller,"QB");
        Brik.createSpillerBrik(rødSpiller,"WR");
        Brik.createSpillerBrik(rødSpiller,"RB");

        Brik.createSpillerBrik(blåSpiller,"TE");
        Brik.createSpillerBrik(blåSpiller,"FB");
        Brik.createSpillerBrik(blåSpiller,"CB");



        Bræt.createBræt(rødSpiller,blåSpiller);



        int i=0;
        for (Brik brik: rødSpiller.getSpillerBrikker()){
            Bræt.setBrik(brik,i,8);
            i++;
        }

        int j=0;
        for (Brik brik: blåSpiller.getSpillerBrikker()){
            Bræt.setBrik(brik,j,9);
            j++;
        }
        recursiveMain();



        Bræt.printBoard();

        rødSpiller.vælgFelt(Bræt.bræt[rødSpiller.getSpillerBrikker()[0].pos.getPos()[0]-1][rødSpiller.getSpillerBrikker()[0].pos.getPos()[1]+1]);
        rødSpiller.vælgFelt(Bræt.bræt[rødSpiller.getSpillerBrikker()[0].pos.getPos()[0]-2][rødSpiller.getSpillerBrikker()[0].pos.getPos()[1]+2]);


        System.out.println(rødSpiller.spillerBrikker[0].navn+" pos: "+Arrays.toString(rødSpiller.spillerBrikker[0].pos.getPos()));
        System.out.println("  ");

        Bræt.printBoard();
         */

    }

    public static void recursiveMain(){
        Bræt.spillere[0].setActivePlayer();
        Bræt.spillere[1].setActivePlayer();
        Bræt.nyTur();
            recursiveMain();
    }
}