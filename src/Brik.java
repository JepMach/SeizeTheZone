public class Brik {
    String navn;
    Felt pos;
    Bold harBold;
    boolean væltet;
    Spiller brikEjer;
    boolean hold;
    //True = rød, false = blå
    boolean brugtBevægelse;
    boolean brugtAction;


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

    public Felt getPos() {
        return pos;
    }

    public void setPos(Felt pos) {
        this.pos = pos;
        Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]].brikPåFelt=this;
        Bræt.bræt[this.pos.getPos()[0]][this.pos.getPos()[1]].feltTomt=false;
    }

    public void brugBevægelse(boolean brugtBevægelse) {
        this.brugtBevægelse = brugtBevægelse;
    }

    public void brugAction(boolean brugtAction) {
        this.brugtAction = brugtAction;
    }
    /*------------------------------------------------------*/

    public Felt[][] getBrikBevægelse(){
        Felt[][] brikBevægelse = new Felt[5][5];

        int x=-2;
        int y=-2;

        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                try {
                    brikBevægelse[i][j] = Bræt.bræt[this.pos.getPos()[0] + x][this.pos.getPos()[1] + y];
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
