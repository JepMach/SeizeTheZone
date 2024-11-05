import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Handler {

    private static Brik aktivBrik;
    private static Bold bold;
    private static ArrayList<Brik> brikQueue;
    private static ArrayList<Felt> feltQueue;

    public Handler(){

    }
    public static Brik getAktivBrik() {
        return aktivBrik;
    }
    public static void setAktivBrik(Brik aktivBrik) {
        clearHandler();
        Handler.aktivBrik = aktivBrik;
        addToQueue(aktivBrik);
        //Add bevægelsesmuligheder
    }

    public static Bold getBold() {
        return bold;
    }
    public static void setBold(Bold bold) {
        Handler.bold = bold;
    }
    public static ArrayList<Brik> getBrikQueue() {
        return brikQueue;
    }
    public static ArrayList<Felt> getFeltQueue() {
        return feltQueue;
    }
    public static void addToQueue(Brik brik){
        brikQueue.add(brik);
    }
    public static void addToQueue(Felt felt){
        feltQueue.add(felt);
    }
    public static void clearHandler(){
        aktivBrik=null;
        bold=null;
        brikQueue.clear();
        feltQueue.clear();
    }

    public static Felt getEndeFelt() {
        try {
            return feltQueue.getLast();
        } catch (Exception intetValgt){
            return aktivBrik.getObjPos();
        }
    }
    public static Felt getPenUlFelt() {
        try {
            return feltQueue.get(feltQueue.size()-2);
        } catch (Exception intetValgt){
            return aktivBrik.getObjPos();
        }
    }


    public static int[] getDirection(Felt fra, Felt til){
        int X,Y;
        X= Integer.compare(til.getX(), fra.getX());
        Y= Integer.compare(til.getY(), fra.getY());
        return new int[]{Y,X};
    }
    public static Felt getDirFelt(Felt fra, Felt til){
        return Bræt.getBræt()[til.getX()+getDirection(fra,til)[0]][til.getY()+getDirection(fra,til)[1]];
    }

    public static String[] getFeltStatus(Felt felt){
        /*
        0 = none
        1 = valgt felt
        2 = valgte brik
        3 = muligt felt
         */
        String[] status = {" ", " "};

        if (feltQueue.contains(felt)){
            status[0]="¤";
            status[1]="¤";
            return status;
        }
        if (aktivBrik==felt.getFeltObjekt().getObj()){
            status[0]="(";
            status[1]=")";
            return status;
        }
        if (aktivBrik.getBevægelse().contains(felt)){
            status[0]=">";
            status[1]=">";
        }
        return status;
    }

    public static void handleRyk(){
        /*
        Sikker info:
        - Aktiv brik
        - Felt den skal flyttes til

        Usikker info:
        - Indholdet af feltet der flyttes til
        - Indholdet af bagvedliggende retnings felter

        Rækkefølge:
        1. Fjern brik fra felt (Spilobjekt.objPos + Felt.spilObj)
        2. Få endefelt
        3. Tjek endefelts indhold
            (endePunkt) - Hvis tomt: ryk brik til felt
            (endePunkt) - Hvis bold: ryk brik til felt + pick-up bold

            - Hvis brik: Tilføj til brikQueue + Tjek retningsfelt
            (endePunkt) -Hvis retningsfelt tomt: ryk brikQueue
                -Hvis retningsfelt brik: Tilføj til brikQueue + Tjek retningsfelt
            (endePunkt) -Hvis retningsfelt bold: Flyt bold + ryk brikQueue

             -Hvis brik med bold: Tilføj til brikQueue + Tilføj bold til handler + Fjern bold fra brik + Tjek retningsfelt

        4. Clear handler

         */
        Felt endeFelt = getEndeFelt();
        Felt penUlFelt = getPenUlFelt();
        Felt dirFelt = getDirFelt(penUlFelt,endeFelt);
        ArrayList<Felt> tommeFelter = Bræt.getTommeGrænseFelter(endeFelt);
        Collections.shuffle(tommeFelter);

        switch (endeFelt.getFeltVærdi()){
            case 0:
                //Tomt felt
                aktivBrik.flyt(endeFelt);
                break;
            case 1:
                //Brik
                break;
            case 2:
                //Væltet brik
                Brik vBrik = (Brik) endeFelt.getFeltObjekt().getObj();
                vBrik.flyt(tommeFelter.getFirst());
                aktivBrik.flyt(endeFelt);
                break;
            case 3:
                //Brik med bold
                break;
            case 4:
                //Bold
                aktivBrik.flyt(endeFelt);
                aktivBrik.pickUpBold(bold);
                break;
            default:

        }

        clearHandler();
    }



}
