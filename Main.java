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
import java.util.Stack;

import static com.example.board.Grafik.CELL_SIZE;
import static com.example.board.Grafik.BOARD_SIZE_X;
import static com.example.board.Grafik.BOARD_SIZE_Y;

public class Main extends Application {

    public static final Grafik grafik = new Grafik();
    private static final Bræt bane = new Bræt(16, BOARD_SIZE_X, BOARD_SIZE_Y);


    ComboBox<String> aktion = new ComboBox<>();



    private int ValgtX = -1;
    private int ValgtY = -1;


    private final Stack<int[]> valgtfelt = new Stack<>();


    private final Brik obj = new Brik("Borzoi", 3, 3);
    private final Bold bold = new Bold(5, 7);

    @Override
    public void start(Stage primaryStage) {

        obj.setObjSprite("C:\\Users\\esben\\Desktop\\SeizeTheZone-Ver-0.2-Nyeste-\\SeizeTheZone-Ver-0.2-Nyeste-\\Sprites\\BasicPiece.png");
        grafik.getPiecePositions()[obj.getObjPos().getX()][obj.getObjPos().getY()] = true;

        bold.setObjSprite("C:\\Users\\esben\\Desktop\\SeizeTheZone-Ver-0.2-Nyeste-\\SeizeTheZone-Ver-0.2-Nyeste-\\Sprites\\Bold.png");
        grafik.getPiecePositions()[bold.getObjPos().getX()][bold.getObjPos().getY()] = true;

        grafik.printBoard();
        grafik.getPane().setOnMouseClicked(event -> handleMouseClick(event, grafik.getgC()));


        aktion.getItems().addAll("Move Here", "Cancel");
        aktion.setOnAction(e -> feldthandler());

        Scene scene = new Scene(grafik.getPane(), BOARD_SIZE_X * CELL_SIZE, BOARD_SIZE_Y * CELL_SIZE);
        primaryStage.setTitle("Seize The Zone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleMouseClick(MouseEvent event, GraphicsContext gc) {
        int X = (int) (event.getX() / CELL_SIZE);
        int Y = (int) (event.getY() / CELL_SIZE);


        if (ValgtX == -1 && ValgtY == -1) {
            if (grafik.getPiecePositions()[X][Y]) {

                //obj.setObjSprite("C:\\Users\\esben\\Desktop\\SeizeTheZone-Ver-0.2-Nyeste-\\SeizeTheZone-Ver-0.2-Nyeste-\\Sprites\\HighlightedPiece.png");
                ValgtX = X;
                ValgtY = Y;


                highlightfeldt(X, Y);
                valgtfelt.push(new int[]{X, Y});

                grafik.printBoard();
            }

        }

        else {
            int[] sidstefeldt = valgtfelt.get(valgtfelt.size() - 1);
            int lastX = sidstefeldt[0];
            int lastY = sidstefeldt[1];



            if (erfeldttæt(X, Y, lastX, lastY)) {
                if (!erfeldtvalgt(X, Y)) {
                    valgtfelt.push(new int[]{X, Y});
                    highlightfeldt(X, Y);

                }
                else {

                    while (!valgtfelt.isEmpty()) {
                        int[] top = valgtfelt.peek();
                        if (top[0] == X && top[1] == Y) {
                            break;
                        }
                        grafik.getHighTile()[X][Y] = false;
                        valgtfelt.pop();
                        System.out.println(valgtfelt.size() - 1);

                    }
                }

                aktion.setLayoutX(X * CELL_SIZE );
                aktion.setLayoutY(Y * CELL_SIZE + CELL_SIZE/3);
                aktion.setPrefSize(CELL_SIZE, CELL_SIZE/3);

                if (!grafik.getPane().getChildren().contains(aktion)) {
                    grafik.getPane().getChildren().add(aktion);
                }

                grafik.printBoard();
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
                int[] sidstefeldt = valgtfelt.get(valgtfelt.size() - 1);
                int targetX = sidstefeldt[0];
                int targetY = sidstefeldt[1];

                grafik.getPiecePositions()[ValgtX][ValgtY] = false;
                obj.flyt(Bræt.getBræt()[targetX][targetY]);
                grafik.getPiecePositions()[targetX][targetY] = true;
                obj.setObjSprite("C:\\Users\\esben\\Desktop\\SeizeTheZone-Ver-0.2-Nyeste-\\SeizeTheZone-Ver-0.2-Nyeste-\\Sprites\\BasicPiece.png");

                break;


            case "Cancel":

                resetvalg();
                break;

            default:
                break;
        }

        //reseter comboboxen så den samme ting kan vælges to gange i træk.
        aktion.setValue(null);
        grafik.getPane().getChildren().remove(aktion);
        resetvalg();
        grafik.printBoard();
    }

    private void highlightfeldt(int x, int y) {
        grafik.getHighTile()[x][y] = true;
    }

    private boolean erfeldtvalgt(int x, int y) {
        for (int[] square : valgtfelt) {
            if (square[0] == x && square[1] == y) {
                return true;
            }
        }
        return false;
    }

    private boolean erfeldttæt(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < BOARD_SIZE_X && y >= 0 && y < BOARD_SIZE_Y;
    }

    private void resetvalg() {

        for (int i = 0; i < grafik.getHighTile().length; ++i) {
            Arrays.fill(grafik.getHighTile()[i], false);
        }

        ValgtX = -1;
        ValgtY = -1;
        valgtfelt.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
