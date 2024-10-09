public class LogicHandler {
    Brik actingBrik;
    Brik reactingBrik;
    Felt felt;
    Bold bold;
    Bræt bræt;


    public LogicHandler(/*Bræt bræt*/){
        //this.bræt=bræt;
    }

    /*____________________________________________________*/
    public void brik2Brik(Brik aBrik, Brik rBrik){
        this.actingBrik=aBrik;
        this.reactingBrik=rBrik;

        if(!aBrik.team==rBrik.team) {
            aBrik.tackle(rBrik);
            //tackle logic in Brik subclass
            rBrik.getHit();
            //get hit logic in Brik subclass
            if(!(rBrik.holdingBall ==null)){
                this.bold=rBrik.holdingBall;
                rBrik.dropBold();
                this.bold.dropped();
            }
        } else {
            aBrik.push(rBrik);
            //pushed logic in Brik subclass
        }

    }
    /*____________________________________________________*/
    public void brik2Bold(Bold bold, Brik brik){
        this.bold=bold;
        this.actingBrik=brik;

        bold.heldBy=brik;
        brik.holdingBall=bold;
    }

    /*____________________________________________________*/


    /*___________________Handle object____________________*/
    public void handleObjectBrik(Brik brikAction, int action){
                brikAction.actionSelector(action);
    }
    /*____________________________________________________*/
    public void handleObjectFelt(){
    }
    /*____________________________________________________*/
    public void handleObejctBold(){
    }
    /*____________________________________________________*/


    /*________________Handle interaction__________________*/
    private void handleInteractionBrikBold(){
    }
    /*____________________________________________________*/
    private void handleInteractionBrikFelt(){
    }
    /*____________________________________________________*/
    private void handleInteractionBrikFeltBold(){
    }
    /*____________________________________________________*/
    private void handleInteractionFeltBold(){
    }
    /*____________________________________________________*/
    private void handelInteraction(){
    }
    /*____________________________________________________*/


}
