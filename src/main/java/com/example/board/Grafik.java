package com.example.board;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;
public class Grafik implements PropertyChangeListener{

    Canvas canvas;
    GraphicsContext gC;
    Pane vindue;
    int feltStørrelse;

    public Grafik(BrætTilstand bræt, int feltStørrelse){
        int[] brætStørrelse = new int[]{bræt.bræt.length,bræt.bræt[0].length};
        this.feltStørrelse=feltStørrelse;
        canvas = new Canvas(brætStørrelse[0] * feltStørrelse, brætStørrelse[1] * feltStørrelse);
        gC = canvas.getGraphicsContext2D();
        vindue = new Pane(canvas);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BrætTilstand brætTilstand = (BrætTilstand) evt.getNewValue();
        BrætTilstand orgBræt = (BrætTilstand) evt.getOldValue();

        switch (evt.getPropertyName()){
            case "TilføjFelter":
                for (int[] felt: brætTilstand.valgteFelter){
                    getValgteGrafik(brætTilstand,felt[0],felt[1]);
                }
                break;
            case "FjernFelter":
                for (int[] felt: orgBræt.valgteFelter){
                    getValgteGrafik(brætTilstand,felt[0],felt[1]);
                }
                break;
            case "FlytBrikker":
                for (int[] felt: orgBræt.valgteFelter){
                    getValgteGrafik(brætTilstand,felt[0],felt[1]);
                }
                for (int[] felt: brætTilstand.brikKoordinater){
                    getBasicGrafik(brætTilstand,felt[0],felt[1]);
                }
                break;
        }
    }
    void sætGrafik(BrætTilstand brætTilstand){
        Brikker[][] brikPositioner = brætTilstand.bræt;
        for (int i=0; i<brikPositioner[1].length;i++){
            for (int j=0; j<brikPositioner.length;j++){
                getBasicGrafik(brætTilstand,j,i);
            }
        }
    }
    void getBasicGrafik(BrætTilstand brætTilstand, int x, int y){
        Image image = getFeltBillede(brætTilstand, x, y);
        gC.drawImage(image,x*feltStørrelse,y*feltStørrelse, feltStørrelse, feltStørrelse);
        if (brætTilstand.bræt[x][y]==null)return;

        image = getBrikBillede(brætTilstand,x,y);
        if (brætTilstand.spillerBrikker.getLast().contains(brætTilstand.bræt[x][y])) {
            gC.drawImage(image,x*feltStørrelse,y*feltStørrelse, feltStørrelse, feltStørrelse);
        } else {
            gC.drawImage(image,x*feltStørrelse+feltStørrelse,y*feltStørrelse, -feltStørrelse, feltStørrelse);
        }

        image = getStatusBillede(brætTilstand,x,y);
        try {
            gC.drawImage(image,x*feltStørrelse,y*feltStørrelse, feltStørrelse, feltStørrelse);
        } catch (Exception ignored){}
    }
    private Image getFeltBillede(BrætTilstand brætTilstand, int x, int y) {
        String feltBillede;
        if (x ==0 || x == brætTilstand.bræt.length-1){
            feltBillede = "MålFelt";
        }
        else if ((x + y)%2==0){
            feltBillede = "Grøn";
        }
        else {
            feltBillede = "MørkeGrøn";
        }
        return switch (feltBillede) {
            case "MålFelt" -> newImage("C:STZBilleder/MålFelt.png");
            case "Grøn" -> newImage("C:STZBilleder/GrøntFelt.png");
            case "MørkeGrøn" -> newImage("C:STZBilleder/MørkeGrøntFelt.png");
            default -> null;
        };
    }
    private Image getBrikBillede(BrætTilstand brætTilstand, int x, int y){
        String brikBillede = brætTilstand.bræt[x][y].navn;
        String holdFarve;
        if (brætTilstand.spillerBrikker.getLast().contains(brætTilstand.bræt[x][y])) {
            holdFarve = "Blå";
        } else {
            holdFarve = "Rød";
        }
        if (Objects.equals(brætTilstand.bræt[x][y].navn, "Bold")) {
            return newImage("C:STZBilleder/Bold.png");
        } else {
            return newImage("C:STZBilleder/"+holdFarve+brikBillede+".png");
        }
    }
    private Image getStatusBillede(BrætTilstand brætTilstand, int x, int y){
        String brikStatus;
        if (brætTilstand.bræt[x][y].væltet) {
            brikStatus = "Væltet";
        } else if (brætTilstand.bræt[x][y].harBold) {
            brikStatus = "HarBold";
        } else {
            brikStatus = "N/A";
        }
        return switch (brikStatus) {
            case "Væltet" -> newImage("C:STZBilleder/Væltet.png");
            case "HarBold" -> newImage("C:STZBilleder/SpillerBold.png");
            default -> null;
        };
    }
    void getValgteGrafik(BrætTilstand brætTilstand, int x, int y){
        getBasicGrafik(brætTilstand,x,y);
        if (!brætTilstand.valgteFelter.contains(brætTilstand.brætKoordinater[x][y])){
            return;
        }
        String feltFyldt;
        if (brætTilstand.bræt[x][y]==null){
            feltFyldt="Tomt";
        } else {
            feltFyldt="Brik";
        }
        Image image = switch (feltFyldt) {
            case "Brik" -> newImage("C:STZBilleder\\ValgtSpiller.png");
            case "Tomt" -> newImage("C:STZBilleder\\HighlightedTile.png");
            default -> null;
        };
        gC.drawImage(image,x*feltStørrelse,y*feltStørrelse, feltStørrelse, feltStørrelse);
    }
    HashMap<String,Image> imgMap = new HashMap<>();
    Image newImage(String s){
        if(imgMap.containsKey(s)){return imgMap.get(s);}
        Image img=new Image(s);
        imgMap.put(s,img);
        System.out.println("Load "+s);
        return img;
    }
    
}
