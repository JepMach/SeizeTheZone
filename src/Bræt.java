import java.util.ArrayList;
import java.util.Arrays;

public class Bræt {
    public static Felt[][] bræt = new Felt[8][18];
    static Felt[] målFelter = new Felt[16];
    Felt[] kampLinje = new Felt[8];
    static Spiller[] spillere = new Spiller[2];
    static int turer;


    public Bræt(Spiller spiller1, Spiller spiller2){
        spillere[0]=spiller1;
        spillere[1]=spiller2;

    }

    public static void createBræt(Spiller spiller1, Spiller spiller2){
        Bræt spilleBræt = new Bræt(spiller1,spiller2);
        turer=16;
        int målLinje=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<18;j++){
                Felt felt = new Felt(i,j);
                felt.feltTomt=true;
                bræt[i][j] = felt;
                if (j==0 || j==17){
                    målFelter[målLinje]=felt;
                    felt.målFelt=true;
                    målLinje++;
                }
            }
        }

    }

    /*---------------- Brik bevægelse ------------------------*/
    public ArrayList<Felt> getTommeFelter(Felt[] brikBevægelse){
        ArrayList<Felt> muligeFelter = new ArrayList<>();
        for (Felt felt:brikBevægelse){
            if(felt.getFeltTomt()){
                muligeFelter.add(felt);
            }
        }
        return muligeFelter;
    }

    public ArrayList<Felt> getTaklinger(Felt[] brikBevægelse){
        ArrayList<Felt> muligeTaklinger = new ArrayList<>();
        for (Felt felt:brikBevægelse){
            if(!(felt.brikPåFelt ==null)){
                muligeTaklinger.add(felt);
            }
        }
        return muligeTaklinger;
    }

    public Felt getBoldPickUp (Felt[] brikBevægelse){
        for (Felt felt:brikBevægelse){
            if(!(felt.boldPåFelt ==null)){
                return felt;
            }
        }
        return null;
    }
    /*------------------------------------------------------*/

    public static void printBoard(){

        for (int i=0;i<8;i++){
            for (int j=0;j<18;j++){
                if (spillere[0].muligeFelter.contains(bræt[i][j])){
                    if (spillere[0].valgteFelter.contains(bræt[i][j])){
                        if (!(bræt[i][j].brikPåFelt ==null)) {
                            System.out.print("[¤" + bræt[i][j].brikPåFelt.navn + "¤]");
                        } else {
                            System.out.print("[ ¤¤ ]");
                        }
                    } else if(!(bræt[i][j].brikPåFelt ==null)){
                        System.out.print("[("+bræt[i][j].brikPåFelt.navn+")]");
                    } else if (bræt[i][j].feltTomt){
                        System.out.print("[ -> ]");
                    }
                } else if(!(bræt[i][j].brikPåFelt ==null)){
                    System.out.print("[ "+bræt[i][j].brikPåFelt.navn+" ]");
                } else {
                    //System.out.print(Arrays.toString(bræt[i][j].getPos()));
                    System.out.print("[    ]");
                }

            }
            System.out.println(" ");
        }


        System.out.println(" ");
    }


}
