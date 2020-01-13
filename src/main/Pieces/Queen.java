package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return null;
    }

    @Override
    public ArrayList<Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {

        ArrayList <Index> moves = new ArrayList<>();

        return moves;
    }

}
