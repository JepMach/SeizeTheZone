public class QuarterBack extends Brik {


    public QuarterBack(boolean team){
        this.name="QB";
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
            default:
                break;
        }
    }

    @Override
    void tackle(Brik brik) {
        System.out.println("Tackled");
    }

    @Override
    void pickUpBold() {
        System.out.println("Ball picked up");
    }

    @Override
    public void getHit() {
        System.out.println("Hit");
    }

    @Override
    void push(Brik brik) {

    }
    @Override
    void dropBold() {

    }

    @Override
    public void move(Bræt bræt, Felt newFelt, int[] newPos) {
        this.pos=newPos;
        if(this.pos==newFelt.pos && newFelt.occupied){
            LogicHandler handleCollision = new LogicHandler(/*bræt*/);
            handleCollision.addBrikker(this);
            handleCollision.addFelter(newFelt);
        }

    }
    @Override
    public void throwBold(){
        System.out.println("Ball thrown");
    }


}
