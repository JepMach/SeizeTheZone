package com.example.board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    static ArrayList<Brikker> spillerBrikker1 = new ArrayList<>();
    static ArrayList<Brikker> spillerBrikker2 = new ArrayList<>(){};
    static ArrayList<ArrayList<Brikker>> brætBrikker = new ArrayList<>(){};
    private int ValgtX = -1;
    private int ValgtY = -1;

    @Override
    public void start(Stage primaryStage) {

        spillerBrikker1.add(new Brikker("QuarterBack"));
        spillerBrikker1.add(new Brikker("LineMan"));
        spillerBrikker2.add(new Brikker("QuarterBack"));
        spillerBrikker2.add(new Brikker("LineMan"));

        brætBrikker.add(spillerBrikker1);
        brætBrikker.add(spillerBrikker2);



        BrætTilstand testBræt = new BrætTilstand(brætBrikker,18,8,7,7);
        Grafik grafik = new Grafik(testBræt, 60);
        testBræt.addPropertyChangeListener(grafik);
        grafik.sætGrafik(testBræt);
        grafik.vindue.setOnMouseClicked(event -> handleMouseClick(event, testBræt));



        Scene scene = new Scene(grafik.vindue, 18 * 60, 8 * 60);
        primaryStage.setTitle("Seize The Zone");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(spillerBrikker1.getFirst().navn);
    }

    public void handleMouseClick(MouseEvent event, BrætTilstand testBræt) {
        int X = (int) (event.getX() / 60);
        int Y = (int) (event.getY() / 60);


        if (ValgtX == -1 && ValgtY == -1) {
            if (!(testBræt.bræt[X][Y] ==null)) {
                ValgtX = X;
                ValgtY = Y;
                testBræt.opdaterValgteFelter(testBræt.brætKoordinater[X][Y]);
            }
        }
        else {
            if(!(testBræt.bræt[X][Y] ==testBræt.bræt[testBræt.valgteFelter.getFirst()[0]][testBræt.valgteFelter.getFirst()[1]])){
                testBræt.opdaterBrætTest(new int[]{ValgtX,ValgtY},new int[]{X,Y});
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
