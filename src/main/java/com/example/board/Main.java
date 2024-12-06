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

        spillerBrikker1.add(new Brikker("QuarterBack"));
        spillerBrikker1.add(new Brikker("LineMan"));
        spillerBrikker2.add(new Brikker("QuarterBack"));
        spillerBrikker2.add(new Brikker("LineMan"));

        brætBrikker.add(spillerBrikker1);
        brætBrikker.add(spillerBrikker2);

        testBræt = new BrætTilstand(brætBrikker,18,8,7,7);
        grafik = new Grafik(testBræt, 60);
        testBræt.addPropertyChangeListener(grafik);
        grafik.sætGrafik(testBræt);
        grafik.vindue.setOnMouseClicked(event -> handleMouseClick(event, testBræt));
        aktion.getItems().addAll("Move Here", "Cancel");
        aktion.setOnAction(e -> feldthandler());
        Scene scene = new Scene(grafik.vindue, 18 * grafik.feltStørrelse, 8 * grafik.feltStørrelse);
        primaryStage.setTitle("Seize The Zone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleMouseClick(MouseEvent event, BrætTilstand testBræt) {
        int X = (int) (event.getX() / grafik.feltStørrelse);
        int Y = (int) (event.getY() / grafik.feltStørrelse);
        if (ValgtX == -1 && ValgtY == -1) {
            if (!(testBræt.bræt[X][Y]==null)){
                ValgtX = X;
                ValgtY = Y;
                testBræt.valgteFelter.push(testBræt.brætKoordinater[X][Y]);
                testBræt.opdaterValgteFelter();
            }
        }
        else {
            int[] sidstefeldt = testBræt.valgteFelter.getLast();
            int lastX = sidstefeldt[0];
            int lastY = sidstefeldt[1];
            if (erfeldttæt(X, Y, lastX, lastY)) {
                if (!erfeldtvalgt(X, Y)) {
                    testBræt.valgteFelter.push(testBræt.brætKoordinater[X][Y]);
                }
                else {
                    while (!testBræt.valgteFelter.isEmpty()) {
                        int[] top = testBræt.valgteFelter.peek();
                        if (top[0] == X && top[1] == Y) {
                            break;
                        }
                        testBræt.valgteFelter.pop();
                    }
                }
                aktion.setLayoutX(X * grafik.feltStørrelse );
                aktion.setLayoutY(Y * grafik.feltStørrelse + grafik.feltStørrelse-30);
                aktion.setPrefSize(grafik.feltStørrelse, grafik.feltStørrelse-45);

                if (!grafik.vindue.getChildren().contains(aktion)) {
                    grafik.vindue.getChildren().add(aktion);
                }
                testBræt.opdaterValgteFelter();
            }
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
                resetvalg();
                break;
            default:
                break;
        }
        //reseter comboboxen så den samme ting kan vælges to gange i træk.
        aktion.setValue(null);
        resetvalg();
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
        testBræt.valgteFelter.clear();
        testBræt.opdaterValgteFelter();
        grafik.vindue.getChildren().remove(aktion);

    }
    public static void main(String[] args) {
        launch(args);
    }
}