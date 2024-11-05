import java.util.ArrayList;
import java.util.Arrays;

public class Bræt {
    private static Felt[][] bræt;
    private static Felt[] målFelter;
    private static Felt[] kampLinje;
    private static int maxTurer;
    private static int tur;
    private static Spiller aktivSpiller;
    private static Spiller angriber;


    public Bræt(int turer, int brætX, int brætY){
        maxTurer = turer;
        bræt = new Felt[brætX][brætY];
        målFelter = new Felt[brætY*2];
        int målLinje=0;
        for (int x=0;x<brætX;x++){
            for (int y=0;y<brætY;y++){

                Felt felt = new Felt(x,y);
                bræt[x][y] = felt;

                if (x==0 || x==brætX-1){
                    målFelter[målLinje]=felt;
                    målLinje++;
                }
            }

        }
    }

    public static void nyTur(){
        tur ++;
    }
    public static int getTurer(){
        return tur;
    }
    public static Felt[] getMål(){
        return målFelter;
    }
    public static Felt[] getKampLinje(){
        return kampLinje;
    }
    public static Spiller getAktivSpiller() {
        return aktivSpiller;
    }
    public static Spiller getAngriber() {
        return angriber;
    }
    public static Felt[][] getBræt() {
        return bræt;
    }

    public static void flytSpilObj(SpilObjekt obj, Felt felt){
        obj.fjern();
        obj.set(felt);
    }

    public static void vælgFelt(int x, int y){
        Felt felt=bræt[x][y];
            if (Handler.getAktivBrik() == null) {
                for (Brik b:aktivSpiller.getSpillerBrikker()){
                    if(b.getObjPos()==felt){
                        Handler.setAktivBrik(b);
                        return;
                    }
                }
            }
            else if (felt==Handler.getAktivBrik().getObjPos()){
                Handler.setAktivBrik(null);
            }
            else if (getGrænseFelter(Handler.getEndeFelt()).contains(felt)) {
                Handler.addToQueue(felt);
            }
    }


    public static ArrayList<Felt> getGrænseFelter(Felt orgFelt){
        ArrayList<Felt> grænseListe = new ArrayList<>();
        Felt[][] grænseFelter = new Felt[3][3];
        int y=-1;
        int x=-1;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                try {
                    grænseFelter[j][i] = bræt[orgFelt.getX()+ x][orgFelt.getY() + y];
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
    public static ArrayList<Felt> getTommeGrænseFelter(Felt felt){
        ArrayList<Felt> tommeFelter = new ArrayList<>();
        for (Felt tFelt: getGrænseFelter(felt)){
            if (tFelt.isTomt()){
                tommeFelter.add(tFelt);
            }
        }
        return tommeFelter;
    }

    public static void printBoard(){
        Felt felt;
        for (int i=0;i<bræt[1].length;i++){
            for (int j=0;j<bræt[0].length;j++){
                felt=bræt[i][j];
                StringBuilder string = new StringBuilder();
                string.append(felt.getPrintVærdi());
                string.insert(0,"["+Handler.getFeltStatus(felt)[0]);
                string.append(Handler.getFeltStatus(felt)[1]).append("]");
                System.out.print(string);
            }
            System.out.println(" ");
        }
        /*
                1. prio
                Dynamisk type af felt
               - valgt felt
               - valgte brik
               - Muligt bevægelses felt
               - n/a

               2. prio
                Objekt på felt
                - brik
                - væltet brik
                - bold
                - brik og bold
                - tom

                3.prio
                Statisk type af felt
                - Mål
                - Kamplinje
                - n/a
                 */
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






    public static void printFeltStatusTom(Felt felt, StringBuilder string){
    //Tomt felt
        switch (getFeltStatus(felt)){
            case 0:
                string.append("  ");
                break;
            /*    */
            case 1:
                string.insert(1,"¤¤");
                break;
            /* ¤¤ */
            case 2:
                string.insert(1,">>");
                break;
            /* >> */
            default:
                string.append("  ");
                break;
            /*    */

        }
    }
    public static void printFeltStatusObjekt(Felt felt, StringBuilder string){
        //Spil objekt på felt
        switch (getFeltStatus(felt)){
            case 0:
                string.insert(0," ").append(" ");
                break;
            /* GO */
            case 1:
                string.insert(0,"¤").append("¤");
                break;
            /*¤QB¤*/
            case 2:
                string.insert(0,">").append(">");
                break;
            /*>WR>*/
            default:
            string.insert(0," ").append(" ");
            break;
            /* TE */

        }
    }
    /*------------------------------------------------------*/


}
