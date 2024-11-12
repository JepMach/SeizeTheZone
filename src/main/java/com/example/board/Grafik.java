package com.example.board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class Grafik {
    public static final int BOARD_SIZE_X = 18;
    public static final int BOARD_SIZE_Y = 8;
    public static final int CELL_SIZE = 60;
    private final boolean[][] piecePositions = new boolean[BOARD_SIZE_X][BOARD_SIZE_Y];
    private final boolean[][] highTile = new boolean[BOARD_SIZE_X][BOARD_SIZE_Y];
    private final Image highlightedTile = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\Datalogi\\5.-Semester---Projekt-main\\5.-Semester---Projekt-main\\board1\\src\\main\\resources\\Sprites\\HighlightedTile.png", CELL_SIZE,CELL_SIZE, true, true);
    private final Canvas canvas = new Canvas(BOARD_SIZE_X * CELL_SIZE, BOARD_SIZE_Y * CELL_SIZE);
    private final GraphicsContext gC = canvas.getGraphicsContext2D();
    private final Pane pane = new Pane(canvas);
    private ComboBox<String> actionBox;
    ArrayList<String> actions = new ArrayList<>();

    public Grafik(){
    }
    public boolean[][] getPiecePositions() {
        return piecePositions;
    }
    public boolean[][] getHighTile(){
        return highTile;
    }
    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getgC() {
        return gC;
    }

    public Pane getPane() {
        return pane;
    }
    public ComboBox<String> getActionBox(){
        return actionBox;
    }

    public void printBoard() {
        for (int y = 0; y < BOARD_SIZE_Y; y++) {
            for (int x = 0; x < BOARD_SIZE_X; x++) {
                if (getHighTile()[x][y]) {
                  gC.drawImage(highlightedTile,x * CELL_SIZE, y * CELL_SIZE);
                } else {
                    gC.drawImage(Bræt.getBræt()[x][y].getFeltSprite(), x * CELL_SIZE, y * CELL_SIZE);
                }
                if (piecePositions[x][y]) {
                    gC.drawImage(Bræt.getBræt()[x][y].getFeltObjekt().getObjSprite(), x * CELL_SIZE, y * CELL_SIZE);
                }
            }
        }
    }

    public ArrayList<String> getActions(){
        actions.add("Act1");
        actions.add("Act2");
        actions.add("Act3");
        return actions;
    }
    public void createActionBox(int x, int y, ArrayList<String> actions){
        actionBox.getItems().addAll(actions);
        actionBox.setLayoutX(x);
        actionBox.setLayoutY(y);
        pane.getChildren().add(actionBox);
    }
    public void removeActionBox(){
        pane.getChildren().remove(actionBox);
    }
}
