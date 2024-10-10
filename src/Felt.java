public class Felt {
    int[] pos = new int[2];
    boolean feltTomt;
    boolean målFelt;
    Brik brikPåFelt;
    Bold boldPåFelt;

    public Felt(int xPos, int yPos){
        this.pos[0]=xPos;
        this.pos[1]=yPos;
    }

    public boolean getFeltTomt(){
            return feltTomt;
    }
    public Brik getBrikPåFelt(){
        return brikPåFelt;
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
