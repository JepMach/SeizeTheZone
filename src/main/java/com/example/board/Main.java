package com.example.board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.util.ArrayList;

public class Main extends Application {
    static ArrayList<Brikker> spillerBrikker1 = new ArrayList<>();
    static ArrayList<Brikker> spillerBrikker2 = new ArrayList<>(){};
    static ArrayList<ArrayList<Brikker>> brætBrikker = new ArrayList<>(){};
    BrætTilstand testBræt;
    Grafik grafik;
    private int ValgtX = -1;
    private int ValgtY = -1;
    ComboBox<String> aktion = new ComboBox<>();
    @Override
    public void start(Stage primaryStage) {

        spillerBrikker1.add(new Brikker("QB"));
        spillerBrikker1.add(new Brikker("LM"));
        spillerBrikker2.add(new Brikker("QB"));
        spillerBrikker2.add(new Brikker("LM"));

        brætBrikker.add(spillerBrikker1);
        brætBrikker.add(spillerBrikker2);

        testBræt = new BrætTilstand(brætBrikker,18,8,7,7);
        grafik = new Grafik(testBræt, 60);
        testBræt.addPropertyChangeListener(grafik);
        grafik.sætGrafik(testBræt);

        grafik.vindue.getChildren().add(aktion);
        aktion.setLayoutX(testBræt.brætKoordinater.length * grafik.feltStørrelse - (3 * grafik.feltStørrelse));
        aktion.setLayoutY(testBræt.brætKoordinater[0].length * grafik.feltStørrelse + grafik.feltStørrelse/4);
        aktion.setPrefSize(grafik.feltStørrelse*2, grafik.feltStørrelse/3);
        aktion.setVisible(false);
        aktion.getItems().addAll("Move Here", "Cancel");
        aktion.setOnAction(e -> feldthandler());
        grafik.canvas.setOnMouseClicked(event -> handleMouseClick(event, testBræt));

        Scene scene = new Scene(grafik.vindue, testBræt.brætKoordinater.length * grafik.feltStørrelse, testBræt.brætKoordinater[0].length * grafik.feltStørrelse+grafik.feltStørrelse);
        primaryStage.setTitle("Seize The Zone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleMouseClick(MouseEvent event, BrætTilstand testBræt) {
        int X = (int) (event.getX() / grafik.feltStørrelse);
        int Y = (int) (event.getY() / grafik.feltStørrelse);
        try {
            if (ValgtX == -1 && ValgtY == -1) {
                if (!(testBræt.bræt[X][Y] == null)) {
                    ValgtX = X;
                    ValgtY = Y;
                    aktion.setValue(null);
                    aktion.setVisible(true);
                    testBræt.opdaterValgteFelter(testBræt.brætKoordinater[X][Y]);
                }
            } else {
                int[] sidstefeldt = testBræt.valgteFelter.getLast();
                int lastX = sidstefeldt[0];
                int lastY = sidstefeldt[1];
                if (erfeldttæt(X, Y, lastX, lastY)) {
                    testBræt.opdaterValgteFelter(testBræt.brætKoordinater[X][Y]);
                }
            }
        } catch (Exception e){
            System.out.println("Klik");
        }
    }
    private void feldthandler() {
        String action = aktion.getValue();
        if (action == null) {
            return;
        }
        switch (action) {
            case "Move Here":
                int[] sidstefeldt = testBræt.valgteFelter.getLast();
                int[] startFelt = testBræt.brætKoordinater[ValgtX][ValgtY];
                testBræt.opdaterBrætTest(startFelt,sidstefeldt);
                break;
            case "Cancel":
                testBræt.opdaterValgteFelter("clear");
                break;
            default:
                break;
        }
        resetvalg();

        //reseter comboboxen så den samme ting kan vælges to gange i træk.
    }
    private boolean erfeldtvalgt(int x, int y) {
        for (int[] square : testBræt.valgteFelter) {
            if (square[0] == x && square[1] == y) {
                return true;
            }
        }
        return false;
    }
    private boolean erfeldttæt(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }

    private void resetvalg() {
        ValgtX = -1;
        ValgtY = -1;
        aktion.setVisible(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}