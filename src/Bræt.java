import java.util.ArrayList;
import java.util.Arrays;

public class Bræt {
    public static Felt[][] bræt = new Felt[8][18];
    static Felt[] målFelter = new Felt[16];
    static Felt[] kampLinje = new Felt[8];
    static Spiller[] spillere = new Spiller[2];
    static final int totalTurns=16;
    static int turer=1;
    static Brik valgteBrik;
    static ArrayList<Brik> brikQueue = new ArrayList<>();
    static ArrayList<Felt> muligeFelter = new ArrayList<>();
    static ArrayList<Felt> valgteFelter = new ArrayList<>();


    public Bræt(Spiller spiller1, Spiller spiller2){
        spillere[0]=spiller1;
        spillere[1]=spiller2;
    }

    public static void createBræt(Spiller spiller1, Spiller spiller2){
        new Bræt(spiller1, spiller2);
        int målLinje=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<18;j++){
                Felt felt = new Felt(i,j);
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
    public static void nyTur(){
            turer ++;
    }

    public static int getTurer(){
        return turer;
    }

    public static void fjernBrik(int y, int x){
        bræt[y][x].fjernBrik();
    }

    public static void setBrik(Brik brik, int y, int x){
        bræt[y][x].setBrikPåFelt(brik);
    }

    public static void vælgBrikPåFelt(int y, int x){
        muligeFelter.clear();
        valgteFelter.clear();
        valgteBrik = bræt[y][x].getBrikPåFelt();
        if (!getValgteBrik().brugtBevægelse) {
            for (Felt[] array : valgteBrik.getBrikBevægelse()) {
                muligeFelter.addAll(Arrays.asList(array));
            }
        } else {
            System.out.println("Brikken har bevæget sig");
        }
    }
    public static Brik getValgteBrik(){
        return valgteBrik;
    }


    public static void vælgFelt(int y, int x){

        if (!getGrænseFelter(bræt[y][x]).contains(getEndeFelt())){
            System.out.println("Felt for langt væk");
            return;
        }

        if (muligeFelter.contains(bræt[y][x])) {
            if (valgteFelter.contains(bræt[y][x])) {
                valgteFelter.remove(bræt[y][x]);
                System.out.println(" ");
                System.out.println("Fjernet felt");
                System.out.println(" ");
            } else {
                valgteFelter.add(bræt[y][x]);
                System.out.println(" ");
                System.out.println("Tilføjet felt");
                System.out.println(" ");
                if (!(bræt[y][x].brikPåFelt ==null)){
                    muligeFelter.clear();
                    muligeFelter.add(bræt[y][x]);
                    muligeFelter.addAll(valgteFelter);
                }
            }
        } else {
            System.out.println(" ");
            System.out.println("Ulovligt felt");
            System.out.println(" ");
        }
    }


    public static Felt getEndeFelt() {
        try {
            return valgteFelter.getLast();
        } catch (Exception intetValgt){
            return bræt[valgteBrik.getBrikPos()[0]][valgteBrik.getBrikPos()[1]];
        }
    }
    public static void flytBrik(Brik brik, Felt felt){
        if (brik.getBrikPos()==felt.getPos()){
            System.out.println(" ");
            System.out.println("Brik står allerede på dette felt");
            System.out.println(" ");
            clearValg();
            return;
        }

        int[] dir;
        if (brik == valgteBrik && valgteFelter.size()>1) {
            dir = getDirection(valgteFelter.get(valgteFelter.size() - 2), felt);
        } else {
            dir = getDirection(bræt[brik.getBrikPos()[0]][brik.getBrikPos()[1]], felt);
        }



        if (!felt.getFeltTomt()) {
            Brik tBrik = felt.getBrikPåFelt();
            if (!brikQueue.contains(brik)){
                brikQueue.add(brik);
            }
            brikQueue.add(tBrik);
            Felt nyFelt;
            try {
                nyFelt = bræt[felt.getPos()[0]+dir[0]][felt.getPos()[1]+dir[1]];
                flytBrik(tBrik,nyFelt);
            } catch (Exception e) {
                tBrik.væltBrik();
                fjernBrik(valgteBrik.getBrikPos()[0],valgteBrik.getBrikPos()[1]);
                try {
                    setBrik(valgteBrik,valgteFelter.get(valgteFelter.size() - 2).getPos()[0],valgteFelter.get(valgteFelter.size() - 2).getPos()[1]);
                } catch (Exception o){
                    setBrik(getValgteBrik(),getValgteBrik().getBrikPos()[0],getValgteBrik().getBrikPos()[1]);
                }
            }

        } else {
            Felt lastFelt =bræt[brik.getBrikPos()[0]][brik.getBrikPos()[1]];
            fjernBrik(brik.getBrikPos()[0], brik.getBrikPos()[1]);
            setBrik(brik, felt.getPos()[0], felt.getPos()[1]);

            try {
                brikQueue.removeLast();
                Brik qBrik = brikQueue.getLast();
                flytBrik(qBrik, lastFelt);
            } catch (Exception ignored){}
        }
        clearValg();
    }

    public static int[] getDirection(Felt fra, Felt til){
        int Y,X;
        Y= Integer.compare(til.getPos()[0], fra.getPos()[0]);
        X= Integer.compare(til.getPos()[1], fra.getPos()[1]);
        return new int[]{Y,X};
    }
    public static ArrayList<Felt> getGrænseFelter(Felt orgFelt){
        ArrayList<Felt> grænseListe = new ArrayList<>();
        Felt[][] grænseFelter = new Felt[3][3];
        int y=-1;
        int x=-1;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                try {
                    grænseFelter[i][j] = bræt[orgFelt.getPos()[0] + y][orgFelt.getPos()[1] + x];
                } catch (Exception ignored){}
                x++;
            }
            x=-1;
            y++;
        }
        for (Felt[] array : grænseFelter) {
            grænseListe.addAll(Arrays.asList(array));
        }
        return grænseListe;
    }

    public static void clearValg(){
        muligeFelter.clear();
        valgteFelter.clear();
        valgteBrik = null;
    }
    /*------------------------------------------------------*/

    public static void printBoard(){

        for (int i=0;i<8;i++){
            for (int j=0;j<18;j++){
                if (muligeFelter.contains(bræt[i][j])){
                    if (valgteFelter.contains(bræt[i][j])){
                        if (!(bræt[i][j].brikPåFelt ==null)) {
                            System.out.print("[¤" + bræt[i][j].brikPåFelt.navn + "¤]");
                        } else {
                            System.out.print("[ ¤¤ ]");
                        }
                    } else if(!(bræt[i][j].getFeltTomt())){
                        if (bræt[i][j].getBrikPåFelt()==valgteBrik){
                            System.out.print("[ "+bræt[i][j].brikPåFelt.navn+" ]");
                        }else {
                            System.out.print("[(" + bræt[i][j].brikPåFelt.navn + ")]");
                        }
                    } else {
                        System.out.print("[ -> ]");
                    }
                } else if(!(bræt[i][j].brikPåFelt ==null)){
                    if(!(bræt[i][j].brikPåFelt.væltet)) {
                        System.out.print("[ " + bræt[i][j].brikPåFelt.navn + " ]");
                    } else {
                        System.out.print(" X" + bræt[i][j].brikPåFelt.navn + "X ");
                    }
                } else {
                    System.out.print(Arrays.toString(bræt[i][j].getPos()));
                    //System.out.print("[    ]");
                }
            }
            System.out.println(" ");
        }
    }
}
