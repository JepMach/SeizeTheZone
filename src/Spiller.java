public class Spiller {
    Brik[] spillerBrikker = new Brik[3];
    int mål;
    boolean activePlayer;
    boolean angriber;
    boolean hold;
    //true = rød, false = blå
    static final int totalActionPoints=1;
    int actionPoints = totalActionPoints;
    int downs=0;


    public Spiller(boolean hold){
        this.hold=hold;
    }

    public void vælgBrik(int y, int x){
        Bræt.vælgBrikPåFelt(y,x);
    }
    public void vælgFelt(int y, int x){
        if (!(Bræt.getValgteBrik()==null)){
                Bræt.vælgFelt(y, x);
        } else {
            if (!Bræt.bræt[y][x].getFeltTomt() && Bræt.bræt[y][x].getBrikPåFelt().hold==this.hold) {
                vælgBrik(y, x);
            } else {
                System.out.println("Ikke brikejer");
            }
        }
        testKontroller();
    }

    public void ryk(){
        if (this.actionPoints-Bræt.getValgteBrik().getMoveCost()<0){
            System.out.println(" ");
            System.out.println("Insufficient action points");
            System.out.println(" ");
            Bræt.clearValg();
            testKontroller();
        } else {
            Bræt.getValgteBrik().brugBevægelse();
            Bræt.flytBrik(Bræt.getValgteBrik(), Bræt.getEndeFelt());
            testKontroller();
        }
    }





    /*------------- Getters og setter ---------------*/
    public Brik[] getSpillerBrikker() {
        return this.spillerBrikker;
    }
    public int getMål() {
        return this.mål;
    }
    public boolean isAngriber() {
        return this.angriber;
    }
    public boolean hold() {
        return this.hold;
    }
    public int getDowns() {
        return downs;
    }
    public void setAngriber(boolean angriber) {
        this.angriber = angriber;
    }
    public void setActivePlayer(){
        this.activePlayer=true;
        this.resetActionPoints();
        this.testKontroller();
    }
    public void endTurn(){
        this.activePlayer=false;
        for (Brik brik: this.spillerBrikker){
            brik.resetBrik();
        }
        Bræt.clearValg();
    }
    public int getActionPoints() {
        return this.actionPoints;
    }
    public void brugAction(int action) {
        this.actionPoints = this.actionPoints -action;
    }
    public void resetActionPoints(){
        this.actionPoints =totalActionPoints;
    }
    public void setDowns(int downs) {
        this.downs = downs;
    }

    public String getHoldNavn(){
        String hold;
        if (this.hold){
            hold="Rød";
        } else {
            hold="Blå";
        }
        return hold;
    }
    /*------------------------------------------------------*/


    /*---------------- Brik bevægelse ------------------------*/
    public void testKontroller(){
        Bræt.printBoard();
        boolean Int;

        System.out.println(" ");
        System.out.println("Tur "+Bræt.getTurer());
        System.out.println("Aktiv spiller: "+this.getHoldNavn());
        System.out.println("Available action points: "+this.getActionPoints());
        System.out.println("Enter r for move, c for clear, e for end turn");
        System.out.print("enter Y: ");
        while (!Main.input.hasNextInt()) {
            switch (Main.input.next().charAt(0)) {
                case 'r':
                    ryk();
                    return;
                case 'c':
                    Bræt.clearValg();
                    testKontroller();
                    return;
                case 'e':
                    endTurn();
                    return;
                default:
                   break;
            }
        }
        int y = Main.input.nextInt();
        System.out.print("Enter X: ");
        while (!Main.input.hasNextInt()) Main.input.next();
        int x = Main.input.nextInt();
        vælgFelt(y,x);

    }
}
