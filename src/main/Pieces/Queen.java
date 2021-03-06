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
        return getPieceBoolean(x, y, tileBoard, currentTile);
    }

    @Override
    public ArrayList<Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        ArrayList <Index> moves = new ArrayList<>();

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
