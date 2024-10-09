public class Bold {
    int[] pos= new int[2];
    Brik heldBy;

    public Bold(){

    }

    public void move(int x, int y){
        this.pos[0]=x;
        this.pos[1]=y;
    }
    public void dropped(){

    }
}
