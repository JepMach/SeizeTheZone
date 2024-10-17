import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Bold {
    static int[] boldPos= new int[2];
    static Brik boldBrik;
    static Felt boldFelt;
    static Spiller spillerMedBold;
    static boolean holdtAfBrik=false;

    public Bold(){
    }

    public static void createBold(Spiller spiller,int y, int x){
        Bold bold = new Bold();
        spillerMedBold=spiller;
        spiller.givBold();
        Bræt.bræt[y][x].setBoldPåFelt(bold);
    }
    public static void turnOver(Spiller spiller1, Spiller spiller2){
        spiller2.tabBold();
        spillerMedBold=spiller1;
        spiller1.givBold();
    }
    public void pickUp(Brik brik){
        brik.pickUpBold(this);
        boldFelt=null;
        holdtAfBrik=true;
        boldBrik=brik;
    }
    public void setBoldPos(int y, int x){
        boldPos[0]=y;
        boldPos[1]=x;
    }
    public void setBoldFelt(Felt felt){
        boldFelt=felt;
        this.setBoldPos(felt.getPos()[0],felt.getPos()[1]);
    }
    public void setBoldBrik(Brik brik){
        boldBrik=brik;
        this.setBoldPos(brik.getBrikPos()[0],brik.getBrikPos()[1]);
    }
    public static void clearBold(){
        boldBrik=null;
        boldFelt=null;
        spillerMedBold=null;
        holdtAfBrik=false;
        boldPos=null;
    }
    public void dropped(Felt felt){
        clearBold();
        ArrayList<Felt> landeFelter = new ArrayList<>(Bræt.getGrænseFelter(felt));
        Collections.shuffle(landeFelter);
        boldFelt=landeFelter.getFirst();
        boldFelt.setBoldPåFelt(this);
    }
}
