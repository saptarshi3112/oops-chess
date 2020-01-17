package main.Pieces;

import main.Board.Tile;
import main.Utility.Index;
import main.Utility.Team;

import java.util.ArrayList;

public class Pawn extends Piece {

    private Boolean hasMoved = false;
    private Boolean isOnFifthBlock = false;
    private Boolean canEnPassant = true;

    private Boolean hasMovedTwice = false;
    private Boolean firstMove = false;

    private Boolean madeTwoBlockLeap = false;

    private Index previousCoordinate;

    public Pawn(String name, String imgPath, Team color, String category) {
        super(name, imgPath, color, category);
    }

    private Boolean checkEnPassantMoveCondition() {

        // check if any of the previously made a two square move on left or right of the adjacent move.

        return false;
    }

    @Override
    public ArrayList<Index> calculatePossibleMoves(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        ArrayList <Index> moves = new ArrayList<>();

        int xCoordinate = currentTile.getXCoordinate(), yCoordinate = currentTile.getYCoordinate();

        if (super.getColor().equals(Team.BLACK)) {
            if (inRange(xCoordinate + 2, yCoordinate)) {
                if (!tileBoard[xCoordinate + 2][yCoordinate].isTileOccupied()
                        || (tileBoard[xCoordinate + 2][yCoordinate].isTileOccupied()
                        && !tileBoard[xCoordinate + 2][yCoordinate].getPiece().getColor().equals(currentTile.getPiece().getColor()))) {
                    moves.add(new Index(xCoordinate + 2, yCoordinate));
                    moves.add(new Index(xCoordinate + 1, yCoordinate));
                }
            }
        }

        return moves;
    }

    @Override
    public Boolean isSafe(int x, int y, Tile[][] tileBoard, Tile currentTile) {
        return null;
    }

    public Boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(Boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Boolean getOnFifthBlock() {
        return isOnFifthBlock;
    }

    public void setOnFifthBlock(Boolean onFifthBlock) {
        isOnFifthBlock = onFifthBlock;
    }

    public Boolean getFirstMove() {
        return this.firstMove;
    }

    public void setFirstMove(Boolean firstMove) {
        this.firstMove = firstMove;
    }

    public Boolean getCanEnPassant() {
        return canEnPassant;
    }

    public void setCanEnPassant(Boolean canEnPassant) {
        this.canEnPassant = canEnPassant;
    }

    public Boolean getHasMovedTwice() {
        return hasMovedTwice;
    }

    public void setHasMovedTwice(Boolean hasMovedTwice) {
        this.hasMovedTwice = hasMovedTwice;
    }

    public Index getPreviousCoordinate() {
        return previousCoordinate;
    }

    public void setPreviousCoordinate(Index previousCoordinate) {
        this.previousCoordinate = previousCoordinate;
    }

}
