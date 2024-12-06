package com.example.board;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (Objects.equals(evt.getPropertyName(), "ValgteFelter")){
            opdaterInputGrafik((BrætTilstand) evt.getNewValue());
        } else {
            //opdaterBrikGrafik((BrætTilstand) evt.getNewValue());
            sætGrafik((BrætTilstand) evt.getNewValue());
        }
    }

    void opdaterInputGrafik(BrætTilstand brætTilstand){
        ArrayList<int[]> valgteFelter = brætTilstand.valgteFelter;
        int [][][] brætKoordinater = brætTilstand.brætKoordinater;
        Image image;
        for (int[] felter: valgteFelter){
            if (brætKoordinater[felter[0]][felter[1]] == valgteFelter.getFirst()) {
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\ValgtSpiller.png");
                gC.drawImage(image,felter[0]*feltStørrelse,felter[1]*feltStørrelse, feltStørrelse, feltStørrelse);
            } else {
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\HighlightedTile.png");
                gC.drawImage(image,felter[0]*feltStørrelse,felter[1]*feltStørrelse, feltStørrelse, feltStørrelse);
            }
        }
    }

    void sætGrafik(BrætTilstand brætTilstand){
        ArrayList<ArrayList<Brikker>> spillerBrikker = brætTilstand.spillerBrikker;
        Brikker[][] brikPositioner = brætTilstand.bræt;
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
                feltGrafik(feltBillede,j*feltStørrelse,i*feltStørrelse);
                if (brikPositioner[j][i]==null){
                    continue;
                }
                String brikBillede;
                if (Objects.equals(brikPositioner[j][i].navn, "Bold")) {
                    brikBillede = "Bold";
                    brikGrafik(brikBillede, "N/A", "N/A", j * feltStørrelse, i * feltStørrelse);
                    continue;
                }

                brikBillede = brikPositioner[j][i].navn;
                String holdFarve;
                if (spillerBrikker.getLast().contains(brikPositioner[j][i])) {
                    holdFarve = "Blå";
                } else {
                    holdFarve = "Rød";
                }
                String brikStatus;
                if (brikPositioner[j][i].væltet) {
                    brikStatus = "Væltet";
                } else if (brikPositioner[j][i].harBold) {
                    brikStatus = "HarBold";
                } else {
                    brikStatus = "N/A";
                }
                brikGrafik(brikBillede, holdFarve, brikStatus, j * feltStørrelse, i * feltStørrelse);
            }
        }

    }

    void feltGrafik(String feltType, int x, int y){
        Image image = switch (feltType) {
            case "MålFelt" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\MålFelt.png");
            case "Grøn" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\GrøntFelt.png");
            case "MørkeGrøn" -> new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\MørkeGrøntFelt.png");
            default -> null;
        };
        gC.drawImage(image,x,y, feltStørrelse, feltStørrelse);
    }
    void brikGrafik(String brikNavn, String holdFarve, String brikStatus, int x, int y){
        Image image;
        switch (brikNavn) {
            case "Bold":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\Bold.png");
                gC.drawImage(image,x,y, feltStørrelse, feltStørrelse);
                return;
            case "QuarterBack":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\"+holdFarve+"QB.png");
                break;
            case "LineMan":
                image = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\SeizeTheZone\\STZBilleder\\"+holdFarve+"LM.png");
                break;
            default:
                return;
        }
        if (Objects.equals(holdFarve, "Blå")){
            gC.drawImage(image,x,y, feltStørrelse, feltStørrelse);
        } else {
            gC.drawImage(image,x+feltStørrelse,y, -feltStørrelse, feltStørrelse);
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
        gC.drawImage(image,x,y, feltStørrelse, feltStørrelse);
    }
}
