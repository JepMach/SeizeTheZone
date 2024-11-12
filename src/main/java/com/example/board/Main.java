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
    private static final Bræt bane = new Bræt(16,BOARD_SIZE_X,BOARD_SIZE_Y);
    Button move = new Button("MOVE");
    private int selectedX = -1;
    private int selectedY = -1;
    private int rykX = 0;
    private int rykY = 0;
    private final Brik obj = new Brik("Borzoi",3,3);
    private final Bold bold = new Bold(5,7);

    @Override
    public void start(Stage primaryStage) {

        obj.setObjSprite("C:\\Users\\jeppe\\Desktop\\RUC\\Datalogi\\5.-Semester---Projekt-main\\5.-Semester---Projekt-main\\board1\\src\\main\\resources\\Sprites\\BasicPiece.png");
        grafik.getPiecePositions()[obj.getObjPos().getX()][obj.getObjPos().getY()] = true;

        bold.setObjSprite("C:\\Users\\jeppe\\Desktop\\RUC\\Datalogi\\5.-Semester---Projekt-main\\5.-Semester---Projekt-main\\board1\\src\\main\\resources\\Sprites\\Bold.png");
        grafik.getPiecePositions()[bold.getObjPos().getX()][bold.getObjPos().getY()] = true;


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
                obj.setObjSprite("C:\\Users\\jeppe\\Desktop\\RUC\\Datalogi\\5.-Semester---Projekt-main\\5.-Semester---Projekt-main\\board1\\src\\main\\resources\\Sprites\\HighlightedPiece.png");
                selectedX = X;
                selectedY = Y;
                rykX = X;
                rykY = Y;

            }
        } else {
            if(Bræt.getGrænseFelter(Bræt.getBræt()[rykX][rykY]).contains(Bræt.getBræt()[X][Y])) {
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
        obj.flyt(Bræt.getBræt()[rykX][rykY]);
        grafik.getPiecePositions()[rykX][rykY] = true;
        obj.setObjSprite("C:\\Users\\jeppe\\Desktop\\RUC\\Datalogi\\5.-Semester---Projekt-main\\5.-Semester---Projekt-main\\board1\\src\\main\\resources\\Sprites\\BasicPiece.png");
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
