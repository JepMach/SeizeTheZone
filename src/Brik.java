public class Brik {
    String navn;
    int [] brikPos = new int[2];
    Bold harBold;
    boolean boldHolder=false;
    boolean væltet=false;
    Spiller brikEjer;
    boolean hold;
    //True = rød, false = blå
    boolean brugtBevægelse;
    boolean brugtAction;
    int moveCost=1;


    public Brik(String navn){
        this.navn=navn;

    }

    public static void createSpillerBrik(Spiller spiller, String brikNavn){
        Brik nyBrik = new Brik(brikNavn);
        nyBrik.hold=spiller.hold;
        nyBrik.brikEjer=spiller;

        for (int i=0; i<spiller.spillerBrikker.length;i++){
            if (spiller.spillerBrikker[i]==null){
                spiller.spillerBrikker[i]=nyBrik;
                break;
            }
        }
    }

    /*------------- Getters og setter ---------------*/
    public void setPos(int y, int x) {
       this.brikPos[0]=y;
       this.brikPos[1]=x;
    }
    public int[] getBrikPos() {
        return brikPos;
    }
    public int getMoveCost(){
        return moveCost;
    }
    public void brugBevægelse() {
        this.brikEjer.brugAction(moveCost);
        this.brugtBevægelse = true;
    }
    public void brugAction() {
        this.brugtAction = true;
    }
    public void resetBrik(){
        this.brugtBevægelse=false;
        this.brugtAction=false;
    }
    public void væltBrik(){
        this.væltet=true;
    }
    public void rejsBrik(){
        this.væltet=false;
    }
    public void pickUpBold(Bold bold){
        this.harBold=bold;
        this.boldHolder=true;
        Bræt.bræt[this.getBrikPos()[0]][this.getBrikPos()[1]].fjernBold();
    }
    /*------------------------------------------------------*/

    public Felt[][] getBrikBevægelse(){
        Felt[][] brikBevægelse = new Felt[5][5];

        int y=-2;
        int x=-2;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                try {
                    brikBevægelse[i][j] = Bræt.bræt[this.getBrikPos()[0] + y][this.getBrikPos()[1] + x];
                } catch (Exception ignored){}
                x++;
                }

            x=-2;
            y++;
        }

        return brikBevægelse;

        /*
        brikBevægelse[4][0]=Bræt.bræt[this.pos.getPos()[0]-2][this.pos.getPos()[1]+2];
        brikBevægelse[4][1]=Bræt.bræt[this.pos.getPos()[0]-1][this.pos.getPos()[1]+2];
        brikBevægelse[4][2]=Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]+2];
        brikBevægelse[4][3]=Bræt.bræt[this.pos.getPos()[0]+1][this.pos.getPos()[1]+2];
        brikBevægelse[4][4]=Bræt.bræt[this.pos.getPos()[0]+2][this.pos.getPos()[1]+2];

        brikBevægelse[3][0]=Bræt.bræt[this.pos.getPos()[0]-2][this.pos.getPos()[1]+1];
        brikBevægelse[3][1]=Bræt.bræt[this.pos.getPos()[0]-1][this.pos.getPos()[1]+1];
        brikBevægelse[3][2]=Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]+1];
        brikBevægelse[3][3]=Bræt.bræt[this.pos.getPos()[0]+1][this.pos.getPos()[1]+1];
        brikBevægelse[3][4]=Bræt.bræt[this.pos.getPos()[0]+2][this.pos.getPos()[1]+1];

        brikBevægelse[2][0]=Bræt.bræt[this.pos.getPos()[0]-2][this.pos.getPos()[1]];
        brikBevægelse[2][1]=Bræt.bræt[this.pos.getPos()[0]-1][this.pos.getPos()[1]];
        //Brik pos brikBevægelse[2][2]
        brikBevægelse[2][3]=Bræt.bræt[this.pos.getPos()[0]+1][this.pos.getPos()[1]];
        brikBevægelse[2][4]=Bræt.bræt[this.pos.getPos()[0]+2][this.pos.getPos()[1]];

        brikBevægelse[1][0]=Bræt.bræt[this.pos.getPos()[0]-2][this.pos.getPos()[1]-1];
        brikBevægelse[1][1]=Bræt.bræt[this.pos.getPos()[0]-1][this.pos.getPos()[1]-1];
        brikBevægelse[1][2]=Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]-1];
        brikBevægelse[1][3]=Bræt.bræt[this.pos.getPos()[0]+1][this.pos.getPos()[1]-1];
        brikBevægelse[1][4]=Bræt.bræt[this.pos.getPos()[0]+2][this.pos.getPos()[1]-1];

        brikBevægelse[0][0]=Bræt.bræt[this.pos.getPos()[0]-2][this.pos.getPos()[1]-2];
        brikBevægelse[0][1]=Bræt.bræt[this.pos.getPos()[0]-1][this.pos.getPos()[1]-2];
        brikBevægelse[0][2]=Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]-2];
        brikBevægelse[0][3]=Bræt.bræt[this.pos.getPos()[0]+1][this.pos.getPos()[1]-2];
        brikBevægelse[0][4]=Bræt.bræt[this.pos.getPos()[0]+2][this.pos.getPos()[1]-2];
        */
    }

}
