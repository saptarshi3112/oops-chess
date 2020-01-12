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
import java.beans.BeanInfo;
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
        br2 = new Rook("BR2", "Black_Rook.png", Team.BLACK, "ROOK");

        wr1 = new Rook("WR1", "White_Rook.png", Team.WHITE, "ROOK");
        wr2 = new Rook("WR2", "White_Rook.png", Team.WHITE, "ROOK");

        bb1 = new Bishop("BB1", "Black_Bishop.png", Team.BLACK, "BISHOP");
        bb2 = new Bishop("BB2", "Black_Bishop.png", Team.BLACK, "BISHOP");

        wb1 = new Bishop("WB1", "White_Bishop.png", Team.WHITE, "BISHOP");
        wb2 = new Bishop("WB2", "White_Bishop.png", Team.WHITE, "BISHOP");

        this.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = null;

                if (i == 0) {
                    if (j == 0) p = br1;
                    if (j == 1) p = bk1;
                    if (j == 2) p = bb1;
                    if (j == 5) p = bb2;
                    if (j == 6) p = bk2;
                    if (j == 7) p = br2;
                } if (i == 7) {
                    if (j == 0) p = wr1;
                    if (j == 1) p = wk1;
                    if (j == 2) p = wb1;
                    if (j == 5) p = wb2;
                    if (j == 6) p = wk2;
                    if (j == 7) p = wr2;
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
                default:
                    break;
            }
            prev = currentTile;
        } else {
            if (!markedTiles.isEmpty()) {
                for (Index idx : markedTiles) {
                    Integer xMark = idx.getX(), yMark = idx.getY();
                    if (xMark.equals(currentTile.getXCoordinate()) && yMark.equals(currentTile.getYCoordinate())) {
                        if (prev.isTileOccupied()) {
                            Tile blank = tiles[xMark][yMark];
                            blank.changePiece(prev.getPiece());
                            prev.removePiece();
                            falseTiles();
                        }
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