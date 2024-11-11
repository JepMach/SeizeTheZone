package com.example.board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

import static com.example.board.Grafik.CELL_SIZE;
import static com.example.board.Grafik.BOARD_SIZE_X;
import static com.example.board.Grafik.BOARD_SIZE_Y;

public class Main extends Application {

    public static final Grafik grafik = new Grafik();
    private static Bræt bane = new Bræt(16,BOARD_SIZE_X,BOARD_SIZE_Y);
    Button move = new Button("MOVE");
    private int selectedX = -1;
    private int selectedY = -1;
    private int rykX = 0;
    private int rykY = 0;

    @Override
    public void start(Stage primaryStage) {

        grafik.getPiecePositions()[3][3] = true;

        grafik.printBoard();


        grafik.getPane().setOnMouseClicked(event -> handleMouseClick(event, grafik.getgC()));
        move.setOnAction(e -> handleRyk(grafik.getgC()));

        Scene scene = new Scene(grafik.getPane(), BOARD_SIZE_X * CELL_SIZE, BOARD_SIZE_Y * CELL_SIZE);
        primaryStage.setTitle("Board test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMouseClick(MouseEvent event, GraphicsContext gc) {
        int X = (int) (event.getX() / CELL_SIZE);
        int Y = (int) (event.getY() / CELL_SIZE);
        if (selectedX == -1 && selectedY == -1) {
            if (grafik.getPiecePositions()[X][Y]) {
                grafik.setImage(grafik.getHighlightedPiece());
                selectedX = X;
                selectedY = Y;
                rykX = X;
                rykY = Y;

            }
        } else {
            if(Bræt.getGrænseFelter(bane.getBræt()[rykX][rykY]).contains(bane.getBræt()[X][Y])) {
                grafik.getHighTile()[X][Y] = true;
                rykX = X;
                rykY = Y;
                if (!grafik.getPane().getChildren().contains(move)){
                    grafik.getPane().getChildren().add(move);
                }
            }



        }
        grafik.printBoard();
    }
    private void handleRyk(GraphicsContext gc){
        grafik.getPiecePositions()[selectedX][selectedY] = false;
        grafik.getPiecePositions()[rykX][rykY] = true;
        grafik.setImage(grafik.getBasicPieceImage());
        grafik.getPane().getChildren().remove(move);

        for (int i = 0; i < grafik.getHighTile().length; ++i) {
            Arrays.fill(grafik.getHighTile()[i], false);
        }

        selectedX = -1;
        selectedY = -1;
        rykX = 0;
        rykY = 0;

        grafik.printBoard();
    }


    public static void main(String[] args) {
        launch(args);
    }
}