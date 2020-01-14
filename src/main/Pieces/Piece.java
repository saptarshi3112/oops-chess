package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;
import java.util.ArrayList;

public abstract class Piece {

    private String name;
    private Team color;
    private String imgPath;
    private String category;

    protected static final int xAxis = 8;
    protected static final int yAxis = 8;

    protected static Boolean inRange(int x, int y) {
        return (x >= 0 && x < xAxis && y >= 0 && y < yAxis);
    }

    protected Boolean isEnemyKnight(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return tileBoard[x][y].isTileOccupied() && (!tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) && (tileBoard[x][y].getPiece().getCategory().equals("KNIGHT"));
    }

    protected Boolean isEnemyQueen (int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return (!tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) && (tileBoard[x][y].getPiece().getCategory().equals("QUEEN"));
    }

    protected Boolean isEnemyRook (int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return (!tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) && (tileBoard[x][y].getPiece().getCategory().equals("ROOK"));
    }

    protected Boolean isEnemyBishop (int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return (!tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) && (tileBoard[x][y].getPiece().getCategory().equals("BISHOP"));
    }

    public abstract ArrayList <Index> calculatePossibleMoves(int x, int y, Tile[][]tileBoard, Tile currentTile);

    public Piece(String name, String imgPath, Team color, String category) {
        this.name = name;
        this.imgPath = imgPath;
        this.color = color;
        this.category = category;
    }

    protected static Boolean getPieceBoolean(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        if  (inRange(x, y)) {
            Piece p = tileBoard[x][y].getPiece();
            if (p != null) {
                return !currentTile.getPiece().getColor().equals(p.getColor());
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getColor() {
        return color;
    }

    public void setColor(Team color) {
        this.color = color;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public abstract Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile);
}
