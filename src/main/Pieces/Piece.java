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

    public abstract ArrayList <Index> calculatePossibleMoves(int x, int y, Tile[][]tileBoard, Tile currentTile);

    public Piece(String name, String imgPath, Team color, String category) {
        this.name = name;
        this.imgPath = imgPath;
        this.color = color;
        this.category = category;
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
