package com.example.board;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

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
        sætGrafik((BrætTilstand) evt.getNewValue());
    }

    void sætGrafik(BrætTilstand brætTilstand){
        Brikker[][] brikPositioner = brætTilstand.bræt;
        ArrayList<ArrayList<Brikker>> spillerBrikker = brætTilstand.spillerBrikker;
        ArrayList<int[]> valgteFelter = brætTilstand.valgteFelter;
        int [][][] brætKoordinater = brætTilstand.brætKoordinater;

        for (int i=0; i<brikPositioner[1].length;i++){
            for (int j=0; j<brikPositioner.length;j++){

                String feltBillede;
                if (j==0 || j==brikPositioner.length-1){
                    feltBillede = "MålFelt";
                }
                else if ((j+i)%2==0){
                    feltBillede = "Grøn";
                }
                else {
                    feltBillede = "MørkeGrøn";
                }

                String valg=null;
                try {
                    if (valgteFelter.contains(brætKoordinater[j][i])) {
                        if (brætKoordinater[j][i] == valgteFelter.getFirst()) {
                            valg = "Brik";
                        } else {
                            valg = "Felt";
                        }
                    }
                }catch (Exception ignored){}
                finally {
                    if (valg==null) {
                        valg = "N/A";
                    }
                }

                if (brikPositioner[j][i]==null){
                    lavBillede(feltBillede,valg,"N/A","N/A","N/A",j*feltStørrelse,i*feltStørrelse);
                    continue;
                }

                String brikBillede;
                if (Objects.equals(brikPositioner[j][i].navn, "Bold")) {
                    brikBillede = "Bold";
                    lavBillede(feltBillede,valg,brikBillede,"N/A","N/A",j*feltStørrelse,i*feltStørrelse);
                    continue;
                } else {
                    brikBillede = brikPositioner[j][i].navn;
                }

                String holdFarve;
                if (spillerBrikker.getLast().contains(brikPositioner[j][i])){
                    holdFarve = "Blå";
                }
                else {
                    holdFarve = "Rød";
                }

                String brikStatus;
                if (brikPositioner[j][i].væltet){
                    brikStatus = "Væltet";
                }
                else if (brikPositioner[j][i].harBold){
                    brikStatus = "HarBold";
                } else {
                    brikStatus = "N/A";
                }
                lavBillede(feltBillede,valg,brikBillede,holdFarve,brikStatus,j*feltStørrelse,i*feltStørrelse);
                System.out.println(feltBillede+", "+valg+", "+brikBillede+", "+holdFarve+", "+brikStatus);
            }
        }

    }


    void lavBillede(String feltType, String inputStatus, String brikNavn, String holdFarve, String brikStatus, int x, int y){

        Image image = switch (feltType) {
            case "MålFelt" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\MålFelt.png");
            case "Grøn" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\GrøntFelt.png");
            case "MørkeGrøn" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\MørkeGrøntFelt.png");
            default -> null;
        };
        gC.drawImage(image,x,y, 60, 60);

        switch (inputStatus) {
            case "Felt":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\HighlightedTile.png");
                gC.drawImage(image,x,y, 60, 60);
                break;
            case "Brik":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\ValgtSpiller.png");
                gC.drawImage(image,x,y, 60, 60);
                break;
            default:
                break;
        };

        switch (brikNavn) {
            case "Bold":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\Bold.png");

                gC.drawImage(image,x,y, 60, 60);
                return;
            case "QuarterBack":
                System.out.println(holdFarve);
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\"+holdFarve+"QB.png");
                break;
            case "LineMan":
                System.out.println(holdFarve);
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\"+holdFarve+"LM.png");
                break;
            default:
                return;
        }
        if (Objects.equals(holdFarve, "Blå")){
            System.out.println(holdFarve);
            gC.drawImage(image,x,y, 60, 60);
        } else {
            System.out.println(holdFarve);
            gC.drawImage(image,x+60,y, -60, 60);
        }

        switch (brikStatus) {
            case "Væltet":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\Væltet.png");
                break;
            case "HarBold":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\SpillerBold.png");
                break;
            default:
                return;
        }
        gC.drawImage(image,x,y, 60, 60);

    }
}
