package main.Pieces;

import main.Board.Tile;

import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        if  ((x >= 0 && x < 8) && (y >= 0 && y < 8)) {
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

    @Override
    public ArrayList <Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {

        ArrayList <Index> moves = new ArrayList<>();
        int xAxis = 8, yAxis = 8;

        // right
        int itr = y;
        while (itr < yAxis) {
            if (isSafe(x, itr+1, tileBoard, currentTile)) {
                moves.add(new Index(x, itr+1));
                if (tileBoard[x][itr+1].isTileOccupied() && !tileBoard[x][itr+1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    break;
                }
            } else {
                break;
            } itr++;
        }

        // bottom
        itr = x;
        while (itr < xAxis) {
            if (isSafe(itr+1, y, tileBoard, currentTile)) {
                moves.add(new Index(itr+1, y));
                if (tileBoard[itr+1][y].isTileOccupied() && !tileBoard[itr+1][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    break;
                }
            } else {
                break;
            }
            itr++;
        }

        // left
        itr = y;
        while (itr >= 0) {
            if (isSafe(x, itr-1, tileBoard, currentTile)) {
                moves.add(new Index(x, itr-1));
                // if there is enemy piece break;
                if (tileBoard[x][itr-1].isTileOccupied() && !tileBoard[x][itr-1].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    break;
                }
            } else {
                break;
            }
            itr--;
        }

        // top
        itr = x;
        while (itr >= 0) {
            if (isSafe(itr-1, y, tileBoard, currentTile)) {
                moves.add(new Index(itr-1, y));
                if (tileBoard[itr-1][y].isTileOccupied() && !tileBoard[itr-1][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    break;
                }
            } else {
                break;
            }
            itr--;
        }

        return moves;
    }
}
