package main.Pieces;

import main.Board.Tile;

import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        if (x >= 0 && x < xAxis && y >= 0 && y < yAxis) {
            if (tileBoard[x][y].isTileOccupied() && tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                return false;
            } else if (tileBoard[x][y].isTileOccupied() && !tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public ArrayList <Index> calculatePossibleMoves(int x, int y, Tile [][]tileBoard, Tile currentTile) {

        ArrayList <Index> moves = new ArrayList<>();

        int []xMoves = {x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
        int []yMoves = {y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};

        for (int i = 0; i < 8; i++) {
            if (this.isSafe(xMoves[i], yMoves[i], tileBoard, currentTile)) {
                moves.add(new Index(xMoves[i], yMoves[i]));
            }
        }

        return moves;
    }

}
