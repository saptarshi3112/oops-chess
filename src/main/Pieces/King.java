package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class King extends Piece {
    public King(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    private Boolean checkTileBishopOrQueen(int itrX, int itrY, Tile[][] tileBoard, Tile currentTile) {
        if (inRange(itrX, itrY)) {
            return tileBoard[itrX][itrY].isTileOccupied() && (isEnemyQueen(itrX, itrY, tileBoard, currentTile) || isEnemyBishop(itrX, itrY, tileBoard, currentTile));
        } return false;
    }

    private Boolean checkTileRookOrQueen(int itrX, int itrY, Tile[][] tileBoard, Tile currentTile) {
        if (inRange(itrX, itrY)) {
            return tileBoard[itrX][itrY].isTileOccupied() && (isEnemyQueen(itrX, itrY, tileBoard, currentTile) || isEnemyRook(itrX, itrY, tileBoard, currentTile));
        }
        return false;
    }

    // Method to check to if the block is under attack by any of the pieces.
    // if not only then the king can move to that block
    private Boolean ifBlockUnderCheck(int x, int y, Tile[][] tileBoard, Tile currentTile) {

        // Check all the eight sides to see if the block can be attacked by a piece.

        // Check for a knight.
        int []X = {x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
        int []Y = {y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};

        for (int i = 0; i < 8; i++) {
            int xValue = X[i], yValue = Y[i];
            if (inRange(xValue, yValue)) {
                if (tileBoard[xValue][yValue].isTileOccupied() && isEnemyKnight(xValue, yValue, tileBoard, currentTile)) {
                    return true;
                }
            }
        }

        // Check for a Bishop or a queen.

        // Bottom right x++ y++
        int itrX = x, itrY = y;
        while (itrX < xAxis && itrY < yAxis) {
            itrX++; itrY++;
            if (inRange(itrX, itrY)) {
                if (checkTileBishopOrQueen(itrX, itrY, tileBoard, currentTile)) {
                    return true;
                }
            }
        }

        // Top right x-- y++
        itrX = x; itrY = y;
        while (itrX >= 0 && itrY < yAxis) {
            itrX--; itrY++;
            if (inRange(itrX, itrY)) {
                if(checkTileBishopOrQueen(itrX, itrY, tileBoard, currentTile)) {
                    return true;
                }
            }
        }

        // Bottom left x++ y--
        itrX = x; itrY = y;
        while (itrX < xAxis && itrY >= 0) {
            itrX++; itrY--;
            if (inRange(itrX, itrY)) {
                if (checkTileBishopOrQueen(itrX, itrY, tileBoard, currentTile)) {
                    return true;
                }
            }
        }

        // Top left x-- y--
        itrX = x; itrY = y;
        while (itrX >= 0 && itrY >= 0) {
            itrX--; itrY--;
            if (inRange(itrX, itrY)) {
                if (checkTileBishopOrQueen(itrX, itrY, tileBoard, currentTile)) {
                    return true;
                }
            }
        }

        // Check for a rook or queen attack.
        itrX = x; itrY = y;

        // left
        while (itrY >= 0) {
            if (inRange(x, itrY) && checkTileRookOrQueen(x, itrY, tileBoard, currentTile)) {
                return true;
            }
            itrY--;
        }

        // right
        while (itrY < yAxis) {
            if (inRange(x, itrY) && checkTileRookOrQueen(x, itrY, tileBoard, currentTile)) {
                return true;
            }
            itrY++;
        }

        // top
        while (itrX >= 0) {
            if (inRange(itrX, y) && checkTileRookOrQueen(itrX, y, tileBoard, currentTile)) {
                return true;
            }
            itrX--;
        }


        // bottom
        while (itrX < xAxis) {
            if (inRange(itrX, y) && checkTileRookOrQueen(itrX, y, tileBoard, currentTile)) {
                return true;
            }
            itrX++;
        }


        return false;

    }

    // Method to check if the piece can in that block or not.
    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        if (inRange(x, y)) {
            if (tileBoard[x][y].isTileOccupied()) {
                // if there is a friend piece. false else if enemy then
                if (tileBoard[x][y].getPiece().getColor().equals(currentTile.getPiece().getColor())) {
                    return false;
                } else {
                    // Enemy under block after kill
                    return !ifBlockUnderCheck(x, y, tileBoard, currentTile);
                }
            } else {
                // when target tile is empty. // check if it can be attacked by a piece.
                return !ifBlockUnderCheck(x, y, tileBoard, currentTile);
            }
        } else {
            return false;
        }
    }

    public Boolean isChecked(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return calculatePossibleMoves(x, y, tileBoard, currentTile).size() == 0;
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
