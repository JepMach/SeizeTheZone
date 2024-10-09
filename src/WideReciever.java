public class WideReciever extends Brik{


    public WideReciever(boolean team){
        this.name="WR";
        this.team=team;
    }
    @Override
    public void actionSelector(int action) {
        switch (action){
            case 1:
                tackle();
                break;
            case 2:
                pickUpBold();
                break;
            case 3:
                getHit();
                break;
            case 4:
                throwBold();
                break;
            case 5:
                catchBall();
                break;
            default:
                break;
        }
    }

    @Override
    void tackle(Brik brik) {

    }

    @Override
    void pickUpBold() {

    }

    @Override
    void throwBold() {

    }

    @Override
    public void getHit() {

    }

    @Override
    void push(Brik brik) {

    }
    @Override
    void dropBold() {

    }

    @Override
    public void move(Bræt bræt, Felt newFelt, int[] newPos) {
    }
    public void catchBall(){
        System.out.println("Ball caught");
    }
}
