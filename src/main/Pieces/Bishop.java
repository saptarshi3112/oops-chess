package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;


public class Bishop extends Piece {

    public Bishop(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return getaBoolean(x, y, tileBoard, currentTile);
    }

    @Override
    public ArrayList<Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        ArrayList <Index> moves = new ArrayList<>();

        int xAxis = 8, yAxis = 8;

        // bottom right x++ y++
        int xItr = x, yItr = y;
        while (xItr < xAxis && yItr < yAxis) {
            if (isSafe(xItr+1, yItr+1, tileBoard, currentTile)) {
                moves.add(new Index(xItr+1, yItr+1));
                if (tileBoard[xItr+1][yItr+1].isTileOccupied() && !tileBoard[xItr+1][yItr+1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    moves.add(new Index(xItr+1, yItr+1));
                    break;
                }
            } else {
                break;
            } xItr++; yItr++;
        }

        // top right x-- y++
        xItr = x; yItr = y;
        while (xItr >= 0 && yItr < yAxis) {
            if (isSafe(xItr-1, yItr+1, tileBoard, currentTile)) {
                moves.add(new Index(xItr-1, yItr+1));
                if (tileBoard[xItr-1][yItr+1].isTileOccupied() && !tileBoard[xItr-1][yItr+1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    moves.add(new Index(xItr-1, yItr+1));
                    break;
                }
            } else {
                break;
            } xItr--; yItr++;
        }

        // bottom left x++ y--
        xItr = x; yItr = y;
        while (xItr < xAxis && yItr >= 0) {
            if (isSafe(xItr+1, yItr-1, tileBoard, currentTile)) {
                moves.add(new Index(xItr+1, yItr-1));
                if (tileBoard[xItr+1][yItr-1].isTileOccupied() && !tileBoard[xItr+1][yItr-1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    moves.add(new Index(xItr+1, yItr-1));
                    break;
                }
            } else {
                break;
            } xItr++; yItr--;
        }

        // top left x-- y--
        xItr = x; yItr = y;
        while (xItr >= 0 && yItr >= 0) {
            if (isSafe(xItr-1, yItr-1, tileBoard, currentTile)) {
                moves.add(new Index(xItr-1, yItr-1));
                if (tileBoard[xItr-1][yItr-1].isTileOccupied() && !tileBoard[xItr-1][yItr-1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    moves.add(new Index(xItr-1, yItr-1));
                    break;
                }
            } else {
                break;
            } xItr--; yItr--;
        }

        return moves;
    }
}

