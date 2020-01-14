package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class King extends Piece {
    public King(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    private Boolean underCheck(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return false;
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (tileBoard[x][y].isTileOccupied() && tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                return false;
            } else {
                return  true;
            }
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        ArrayList <Index> moves = new ArrayList<>();

        // diagonals
        if (isSafe(x+1, y+1, tileBoard, currentTile)) {
            moves.add(new Index(x+1, y+1));
        } if (isSafe(x-1, y+1, tileBoard, currentTile)) {
            moves.add(new Index(x-1, y+1));
        } if (isSafe(x-1, y-1, tileBoard, currentTile)) {
            moves.add(new Index(x-1, y-1));
        } if (isSafe(x+1, y-1, tileBoard, currentTile)) {
            moves.add(new Index(x+1, y-1));
        }

        // top
        if (isSafe(x-1, y, tileBoard, currentTile)) {
            moves.add(new Index(x-1, y));
        }

        // bottom
        if (isSafe(x+1, y, tileBoard, currentTile)) {
            moves.add(new Index(x+1, y));
        }

        // right
        if (isSafe(x, y+1, tileBoard, currentTile)) {
            moves.add(new Index(x, y+1));
        }

        // left
        if (isSafe(x, y-1, tileBoard, currentTile)) {
            moves.add(new Index(x, y-1));
        }

        return moves;
    }
}
