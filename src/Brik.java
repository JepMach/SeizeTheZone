public abstract class Brik {
    String name;
    int[] pos;
    Bold holdingBall;
    boolean knockedDown;
    boolean team;
    //True = red, false = blue



    public Brik(){

    }

    public abstract void actionSelector(int action);
    abstract void tackle(Brik brik);
    abstract void pickUpBold();
    abstract void throwBold();
    public abstract void getHit();
    abstract void push(Brik brik);
    abstract void dropBold();
    public abstract void move(Bræt bræt, Felt newFelt, int[] newPos);



}
