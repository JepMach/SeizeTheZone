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
    private final Image basicPieceImage = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\BP5\\Billeder\\BasicPiece.png", CELL_SIZE, CELL_SIZE, true, true);
    private final Image highlightedPiece = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\BP5\\Billeder\\HighlightedPiece.png", CELL_SIZE,CELL_SIZE,true,true);
    private final Image highlightedTile = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\BP5\\Billeder\\HighlightedTile.png", CELL_SIZE,CELL_SIZE,true,true);
    private final Image grøntFelt = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\BP5\\Billeder\\GrøntFelt.png", CELL_SIZE,CELL_SIZE,true,true);
    private final Image mørkeGrøntFelt = new Image("C:\\Users\\jeppe\\Desktop\\RUC\\BP5\\Billeder\\MørkeGrøntFelt.png", CELL_SIZE,CELL_SIZE,true,true);
    private Image image = basicPieceImage;
    private final Canvas canvas = new Canvas(BOARD_SIZE_X * CELL_SIZE, BOARD_SIZE_Y * CELL_SIZE);
    private final GraphicsContext gC = canvas.getGraphicsContext2D();
    private final Pane pane = new Pane(canvas);
    private ComboBox<String> actionBox;
    ArrayList<String> actions = new ArrayList<>();

    public Grafik(){
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public boolean[][] getPiecePositions() {
        return piecePositions;
    }
    public boolean[][] getHighTile(){
        return highTile;
    }

    public Image getBasicPieceImage() {
        return basicPieceImage;
    }

    public Image getHighlightedPiece() {
        return highlightedPiece;
    }

    public Image getHighlightedTile() {
        return highlightedTile;
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

                if ((x + y) % 2 == 0) {
                    createTile("green", x, y);
                } else {
                    createTile("darkgreen", x, y);
                }
                if (piecePositions[x][y]) {
                    gC.drawImage(image, x * CELL_SIZE, y * CELL_SIZE);
                } else if (getHighTile()[x][y]) {
                    gC.drawImage(highlightedTile, x * CELL_SIZE, y * CELL_SIZE);
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
    public void createTile(String type, int x, int y){
        if (Objects.equals(type, "green")) {
            gC.drawImage(grøntFelt, x * CELL_SIZE, y * CELL_SIZE);
        } else if (Objects.equals(type,"darkgreen")){
            gC.drawImage(mørkeGrøntFelt, x * CELL_SIZE, y * CELL_SIZE);
        }
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
