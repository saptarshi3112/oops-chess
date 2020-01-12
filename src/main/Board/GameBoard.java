package main.Board;

import main.Pieces.Bishop;
import main.Pieces.Knight;
import main.Pieces.Piece;
import main.Pieces.Rook;
import main.Utility.Index;
import main.Utility.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameBoard extends JPanel implements MouseListener, ActionListener {

    public static Knight bk1, bk2, wk1, wk2;
    public static Rook br1, br2, wr1, wr2;
    public static Bishop bb1, bb2, wb1, wb2;

    private static Tile [][]tiles = new Tile[8][8];
    private static Tile currentTile = null, prev = null;
    private static ArrayList<Index> markedTiles = new ArrayList<>();

    GameBoard() {

        bk1 = new Knight("BK1", "Black_Knight.png", Team.BLACK, "KNIGHT");
        bk2 = new Knight("BK2", "Black_Knight.png", Team.BLACK, "KNIGHT");

        wk1 = new Knight("WK1", "White_Knight.png", Team.WHITE, "KNIGHT");
        wk2 = new Knight("WK2", "White_Knight.png", Team.WHITE, "KNIGHT");

        br1 = new Rook("BR1", "Black_Rook.png", Team.BLACK, "ROOK");
        wr1 = new Rook("WR1", "White_Rook.png", Team.WHITE, "ROOK");

        bb1 = new Bishop("BB1", "Black_Bishop.png", Team.BLACK, "BISHOP");

        this.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = null;

                if ((i == 0 && j == 1) || (i == 0 && j == 6)) {
                    p = bk1;
                } else if ((i == 7 && j == 1) || (i == 7 && j == 6)) {
                    p = wk1;
                } else if ((i == 0 && j == 0) || (i == 0 && j == 7)) {
                    p = br1;
                } else if ((i == 7 && j == 0) || (i == 7 && j == 7)) {
                    p = wr1;
                } else if ((i == 0 && j == 2) || (i == 0 && j == 5)) {
                    p = bb1;
                }


                Tile t = new Tile(i, j, p);
                tiles[i][j] = t;

                this.add(t);
                t.addMouseListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private static void falseTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setHasNextMove(false);
                tiles[i][j].setSelectedTile(false);
                tiles[i][j].setOpponentTile(false);
                tiles[i][j].repaint();
            }
        }
    }

    private void makeColorTiles (ArrayList <Index> list) {
        for(Index idx : list) {
            if (tiles[idx.getX()][idx.getY()].isTileOccupied()) {
                tiles[idx.getX()][idx.getY()].setOpponentTile(true);
            } else {
                tiles[idx.getX()][idx.getY()].setHasNextMove(true);
            }
            tiles[idx.getX()][idx.getY()].repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        // check if prev is not null. if prev is not null it is an attack move.
        // else if prev is null then it is an attack move.

        currentTile = (Tile)mouseEvent.getSource();
        if (currentTile.isTileOccupied()) {
            falseTiles();
            String category = currentTile.getPiece().getCategory();
            int x = currentTile.getXCoordinate(), y = currentTile.getYCoordinate();
            currentTile.setSelectedTile(true);

            switch (category) {
                case ("KNIGHT"):
                    Knight knight = (Knight) currentTile.getPiece();
                    markedTiles = knight.calculatePossibleMoves(x, y, tiles, currentTile);
                    makeColorTiles(markedTiles);
                    break;
                case ("BISHOP"):
                    Bishop bishop = (Bishop) currentTile.getPiece();
                    markedTiles = bishop.calculatePossibleMoves(x, y, tiles, currentTile);
                    makeColorTiles(markedTiles);
                    break;
                case ("ROOK"):
                    Rook rook = (Rook) currentTile.getPiece();
                    markedTiles = rook.calculatePossibleMoves(x, y, tiles, currentTile);
                    makeColorTiles(markedTiles);
                    break;
                case ("PAWN"):
                    System.out.println("pawn");
                    break;
                case ("QUEEN"):
                    System.out.println("Queen");
                    break;
                case ("KING"):
                    System.out.println("King");
                    break;
                default:
                    break;
            }
        } else {
            if (!markedTiles.isEmpty()) {
                for (Index idx : markedTiles) {
                    Integer xMark = idx.getX(), yMark = idx.getY();
                    if (xMark.equals(currentTile.getXCoordinate()) && yMark.equals(currentTile.getYCoordinate())) {
                        Tile blank = tiles[xMark][yMark];
                        System.out.println(blank.getXCoordinate() + "  " + blank.getYCoordinate());
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
