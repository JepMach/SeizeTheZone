import java.util.ArrayList;
import java.util.Arrays;

public class Spiller {
    Brik[] spillerBrikker = new Brik[3];
    int mål;
    boolean angriber;
    boolean hold;
    //true = rød, false = blå
    int totalActionPoints=7;
    int actionTæller;
    int downs=0;
    Brik valgteBrik;
    ArrayList<Felt> muligeFelter = new ArrayList<>();
    ArrayList<Felt> valgteFelter = new ArrayList<>();




    public Spiller(boolean hold){
        this.hold=hold;
    }

    /*------------- Getters og setter ---------------*/
    public Brik[] getSpillerBrikker() {
        return spillerBrikker;
    }
    public int getMål() {
        return mål;
    }
    public boolean isAngriber() {
        return angriber;
    }
    public boolean hold() {
        return hold;
    }
    public int getActionTæller() {
        return actionTæller;
    }
    public int getDowns() {
        return downs;
    }
    public void setAngriber(boolean angriber) {
        this.angriber = angriber;
    }
    public void setActionTæller(int actionTæller) {
        this.actionTæller = actionTæller;
    }
    public void resetActionTæller(){
        this.actionTæller=totalActionPoints;
    }
    public void setDowns(int downs) {
        this.downs = downs;
    }
    /*------------------------------------------------------*/


    /*---------------- Brik bevægelse ------------------------*/
    public void vælgFelt(Felt valgtFelt){

        if (this.muligeFelter.contains(valgtFelt)) {
            if (this.valgteFelter.contains(valgtFelt)) {
                this.valgteFelter.remove(valgtFelt);
                System.out.println(" ");
                System.out.println("Fjernet felt");
                System.out.println(" ");
            } else {
                this.valgteFelter.add(valgtFelt);
                System.out.println(" ");
                System.out.println("Tilføjet felt");
                System.out.println(" ");
                if (!(valgtFelt.brikPåFelt ==null)){
                    this.muligeFelter.clear();
                    this.muligeFelter.add(valgtFelt);
                    this.muligeFelter.add(this.valgteBrik.pos);
                }
            }
        } else {
            System.out.println(" ");
            System.out.println("Ulovligt felt");
            System.out.println(" ");
        }

    }
    public Felt getEndeFelt() {
        return this.valgteFelter.get(valgteFelter.size()-1);
    }
    public void vælgBrik(Brik brik) {
        this.muligeFelter.clear();
        this.valgteFelter.clear();
        this.valgteBrik=brik;
        for (Felt[] array : brik.getBrikBevægelse()) {
            this.muligeFelter.addAll(Arrays.asList(array));
        }
    }
    public void rykBrik(){
        try {
            if (!(this.valgteFelter ==null) && !(this.getEndeFelt() ==this.valgteBrik.pos)) {
                for (Felt tFelt : this.valgteFelter) {
                    this.valgteBrik.pos.feltTomt = true;
                    this.valgteBrik.pos.brikPåFelt = null;
                    this.valgteBrik.setPos(tFelt);
                }
                this.muligeFelter.clear();
                this.valgteFelter.clear();
                this.valgteBrik = null;
            }

        } catch (Exception noMove){
                        System.out.println(" ");
                        System.out.println("Brik står allerede på dette felt");
                        System.out.println(" ");
                        this.testKontroller();
                    }
    }
    public void testKontroller(){
        Bræt.printBoard();
        if (this.valgteBrik==null){
            System.out.println("Vælg brik: tast 1 til "+this.spillerBrikker.length);
            char move = Main.input.next().charAt(0);
            updateValgteBrik(move);
        } else {
            System.out.println("Valgte brik: "+this.valgteBrik.navn);
            System.out.println("Vælg anden brik = r");
            System.out.println("Ryk brik valgte felter = j");
            System.out.println("Vælg felt");
            char move = Main.input.next().charAt(0);
            updateValgteFelter(move);
        }
    }
    private void updateValgteBrik(char move){
        switch (move){
            case '1':
                this.vælgBrik(this.spillerBrikker[0]);
                break;
            case '2':
                this.vælgBrik(this.spillerBrikker[1]);
                break;
            case '3':
                this.vælgBrik(this.spillerBrikker[2]);
                break;
            default:
                System.out.println(" ");
                System.out.println("Kan ikke læse input");
                System.out.println(" ");
                break;
        }
        this.testKontroller();
    }
    private void updateValgteFelter(char move){

        try {
            boolean fraVælg=false;
            boolean ryk=false;
            int[] startFelt;
            if (this.valgteFelter.isEmpty()) {
                startFelt = this.valgteBrik.pos.getPos();
            } else {
                startFelt = getEndeFelt().getPos();
            }
            switch (move) {

                case 'r':
                    this.valgteBrik = null;
                    this.testKontroller();
                    break;
                case 'q':
                    this.vælgFelt(Bræt.bræt[startFelt[0] - 1][startFelt[1] - 1]);
                    break;
                case 'w':
                    this.vælgFelt(Bræt.bræt[startFelt[0] - 1][startFelt[1]]);
                    break;
                case 'e':
                    this.vælgFelt(Bræt.bræt[startFelt[0] - 1][startFelt[1] + 1]);
                    break;
                case 'a':
                    this.vælgFelt(Bræt.bræt[startFelt[0]][startFelt[1] - 1]);
                    break;
                case 'd':
                    this.vælgFelt(Bræt.bræt[startFelt[0]][startFelt[1] + 1]);
                    break;
                case 'z':
                    this.vælgFelt(Bræt.bræt[startFelt[0] + 1][startFelt[1] - 1]);
                    break;
                case 'x':
                    this.vælgFelt(Bræt.bræt[startFelt[0] + 1][startFelt[1]]);
                    break;
                case 'c':
                    this.vælgFelt(Bræt.bræt[startFelt[0] + 1][startFelt[1] + 1]);
                    break;
                case 'j':
                    this.rykBrik();
                    ryk=true;
                    break;
                case 's':
                    this.vælgFelt(Bræt.bræt[startFelt[0]][startFelt[1]]);
                    fraVælg=true;
                    break;
                default:
                    System.out.println(" ");
                    System.out.println("Kan ikke læse input");
                    System.out.println(" ");
                    break;
            }

        if (!ryk) {
            if (fraVælg || this.getEndeFelt() == this.valgteBrik.pos) {
                this.vælgBrik(valgteBrik);
            } else {
                startFelt = getEndeFelt().getPos();

                ArrayList<Felt> sletteListe = new ArrayList<>();
                for (Felt felt : this.muligeFelter) {
                    for (int xy = 0; xy < 2; xy++) {
                        if (felt.getPos()[xy] > startFelt[xy] + 1 || felt.getPos()[xy] < startFelt[xy] - 1) {
                            sletteListe.add(felt);
                        }
                    }
                }
                this.muligeFelter.removeAll(sletteListe);
            }
        }

        } catch (Exception outOfBounds) {
            System.out.println(" ");
            System.out.println("Felt out of bounds");
            System.out.println(" ");
        }
        this.testKontroller();
    }
    /*------------------------------------------------------*/


}
