import java.util.Objects;

public class Felt {
    private final int posX;
    private final int posY;
    private SpilObjekt feltObjekt;

    public Felt(int xPos, int yPos){
        this.posX=xPos;
        this.posY=yPos;
    }
    public boolean isTomt(){
        return this.feltObjekt != null;
    }
    public SpilObjekt getFeltObjekt(){
        return this.feltObjekt;
    }
    public void fjernFeltObjekt(){
        this.feltObjekt=null;
    }
    public void setFeltObjekt(SpilObjekt obj){
        this.feltObjekt=obj;
    }
    public int getX() {
        return this.posX;
    }
    public int getY() {
        return this.posY;
    }

    public String getPrintVærdi(){
        String feltVærdi="    ";

        if (this.isTomt()){
            return feltVærdi;
        }
        if (Objects.equals(this.feltObjekt.getObjType(), "BRIK")){
            Brik brik = (Brik) this.feltObjekt.getObj();
            feltVærdi=" "+brik.navn+" ";
            if (brik.isVæltet()){
                feltVærdi="x"+brik.navn+"x";
                return feltVærdi;
            }
            if (!(brik.getBold() ==null)){
                feltVærdi=brik.navn+"<>";
                return feltVærdi;
            }
        } else {
            feltVærdi=" <> ";
        }
        return feltVærdi;
    }

    public int getFeltVærdi(){
        /*
        0 = tomt
        1 = brik
        2 = væltet brik
        3 = brik med bold
        4 = bold
         */
        int feltVærdi=0;

        if (this.isTomt()){
            return feltVærdi;
        }
        if (Objects.equals(this.feltObjekt.getObjType(), "BRIK")){
            Brik brik = (Brik) this.feltObjekt.getObj();
            feltVærdi=1;
            if (brik.isVæltet()){
                feltVærdi=2;
                return feltVærdi;
            }
            if (!(brik.getBold() ==null)){
                feltVærdi=3;
                return feltVærdi;
            }
        } else {
            feltVærdi=4;
        }
        return feltVærdi;
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



    public boolean isMålFelt() {
        return målFelt;
    }


}
