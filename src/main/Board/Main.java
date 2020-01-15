package main.Board;

import main.Pieces.*;
import main.Utility.Index;
import main.Utility.StateManager;
import main.Utility.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class Main extends JFrame implements KeyListener, MouseListener {

    private static StateManager stateManager = new StateManager();

    public static Knight bk1, bk2, wk1, wk2;
    public static Rook br1, br2, wr1, wr2;
    public static Bishop bb1, bb2, wb1, wb2;
    public static Queen bq1, wq1;
    public static King bkg1, wkg1;
    public static Pawn[] blackPawn = new Pawn[8], whitePawn = new Pawn[8];

    private static Tile [][]tiles = new Tile[8][8];
    private static Tile currentTile = null, prev = null;
    private static ArrayList<Index> markedTiles = new ArrayList<>();

    private static Player p1, p2, currentPlayer;

    private static Index bk1Position;
    private static Index wk1Position;

    Main() {
        this.setTitle("OopsChess");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = null;

                if (i == 0) {
                    if (j == 0) p = br1;
                    if (j == 1) p = bk1;
                    if (j == 2) p = bb1;
                    if (j == 3) p = bq1;
                    if (j == 4) {
                        p = bkg1;
                        bk1Position = new Index(i, j);
                    }
                    if (j == 5) p = bb2;
                    if (j == 6) p = bk2;
                    if (j == 7) p = br2;
                } if (i == 7) {
                    if (j == 0) p = wr1;
                    if (j == 1) p = wk1;
                    if (j == 2) p = wb1;
                    if (j == 3) p = wq1;
                    if (j == 4) {
                        p = wkg1;
                        wk1Position = new Index(i, j);
                    }
                    if (j == 5) p = wb2;
                    if (j == 6) p = wk2;
                    if (j == 7) p = wr2;
                }

                Tile t = new Tile(i, j, p);
                tiles[i][j] = t;

                panel.add(t);
                t.addMouseListener(this);
            }
        }

        this.setFocusable(true);
        this.addKeyListener(this);
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static void falseTiles () {
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
            tiles[idx.getX()][idx.getY()] = tiles[idx.getX()][idx.getY()];
            tiles[idx.getX()][idx.getY()].repaint();
        }
    }

    private void tileMarker() {
        falseTiles();
        String category = currentTile.getPiece().getCategory();
        int x = currentTile.getXCoordinate(), y = currentTile.getYCoordinate();
        currentTile.setSelectedTile(true);
        markedTiles.clear();
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
            case ("QUEEN"):
                Queen queen = (Queen) currentTile.getPiece();
                markedTiles = queen.calculatePossibleMoves(x, y, tiles, currentTile);
                makeColorTiles(markedTiles);
                break;
            case ("KING"):
                King king = (King) currentTile.getPiece();
                markedTiles = king.calculatePossibleMoves(x, y, tiles, currentTile);
                makeColorTiles(markedTiles);
            default:
                break;
        }
        prev = currentTile;
    }

    private static void swapPlayer() {
        if (currentPlayer.equals(p1)) {
            currentPlayer = p2;
        } else {
            currentPlayer = p1;
        }
    }

    private static Boolean canKingMove() {
        if (currentPlayer.getTeam().equals(Team.BLACK)) {
            return !bk1.calculatePossibleMoves(bk1Position.getX(), bk1Position.getY(), tiles, currentTile).isEmpty();
        } else {
            return !wk1.calculatePossibleMoves(wk1Position.getX(), wk1Position.getY(), tiles, currentTile).isEmpty();
        }
    }

    public static void main(String[] args) {

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

        bq1 = new Queen("BQ1", "Black_Queen.png", Team.BLACK, "QUEEN");
        wq1 = new Queen("WQ1", "White_Queen.png", Team.WHITE, "QUEEN");

        bkg1 = new King("BKG1", "Black_King.png", Team.BLACK, "KING");
        wkg1 = new King("WKG1", "White_King.png", Team.WHITE, "KING");

        new Main();

        p1 = new Player("Bubai", Team.WHITE);
        p2 = new Player("Saptarshi", Team.BLACK);

        currentPlayer = p1;

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        currentTile = (Tile)mouseEvent.getSource();

        // If the tile is occupied and the piece is of the same color.
        // or the tile is blank. for movement
        // or to attack enemy tile. when the prev is not null.

        if (currentTile.isTileOccupied() && prev == null) {
            // first move //
            tileMarker();
        } else if (currentTile.isTileOccupied() && prev != null && currentTile.getPiece().getColor().equals(prev.getPiece().getColor())) {
            // swap moves //
            tileMarker();
        } else {

            if (!markedTiles.isEmpty()) {
                for (Index idx : markedTiles) {
                    Integer xMark = idx.getX(), yMark = idx.getY();
                    if (xMark.equals(currentTile.getXCoordinate()) && yMark.equals(currentTile.getYCoordinate())) {
                        if (prev != null && prev.isTileOccupied()) {

                            if (prev.isTileOccupied() && prev.getPiece().getCategory().equals("KING")) {
                                if (prev.getPiece().getColor().equals(Team.BLACK)) {
                                    bk1Position.setX(xMark);
                                    bk1Position.setY(yMark);
                                } else {
                                    wk1Position.setX(xMark);
                                    wk1Position.setY(yMark);
                                }
                            }

                            Tile blank = tiles[xMark][yMark];
                            blank.changePiece(prev.getPiece());
                            tiles[xMark][yMark] = blank;
                            prev.removePiece();
                            prev = null;
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
