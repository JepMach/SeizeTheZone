public class Felt {
    int[] pos = new int[2];
    boolean målFelt;
    Brik brikPåFelt;
    Bold boldPåFelt;

    public Felt(int yPos, int xPos){
        this.pos[0]=yPos;
        this.pos[1]=xPos;

    }

    public void fjernBrik(){
        this.brikPåFelt=null;
    }
    public void setBrikPåFelt(Brik brik){
        brik.setPos(this.pos[0],this.pos[1]);
        this.brikPåFelt=brik;
    }
    public Brik getBrikPåFelt(){
        return brikPåFelt;
    }
    public boolean getFeltTomt(){
        return boldPåFelt == null;
    }
    public void fjernBold(){
        this.boldPåFelt=null;
    }
    public void setBoldPåFelt(Bold bold){
        bold.setBoldFelt(this);
        this.boldPåFelt=bold;
    }
    public Bold getBoldPåFelt() {
        return boldPåFelt;
    }

    public int[] getPos() {
        return pos;
    }

    public boolean isMålFelt() {
        return målFelt;
    }

    public int getFeltVærdi(){
        /*
        -1 = mål felt
        0 = tomt felt
        1 = bold på felt
        2 = brik på felt
        3 = brik med bold på felt
        */
        int feltVærdi=0;
        if (this.getFeltTomt()){
            if (this.isMålFelt()){
                feltVærdi+=-1;
                return feltVærdi;
            }
            return feltVærdi;
        }
        if (!(this.getBoldPåFelt()==null)){
            feltVærdi++;
            return feltVærdi;
        }
        if (!(this.getBrikPåFelt()==null)){
            feltVærdi+=2;
            if (this.getBrikPåFelt().isBoldHolder()){
                feltVærdi++;
            }
        }
        return feltVærdi;
    }
}
