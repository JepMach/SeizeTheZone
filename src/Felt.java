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
        return brikPåFelt == null;
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
}
