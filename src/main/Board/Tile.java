package main.Board;

import main.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class Tile extends JPanel {

    // X and Y coordinate.
    Integer xCoordinate, yCoordinate;

    // Icon of the piece.
    private JLabel logo;

    // Piece set in the tile.
    private Piece piece;

    // Boolean values to decide the color of the tile.
    private Boolean hasNextMove = false;
    private Boolean isSelectedTile = false;
    private Boolean opponentTile = false;

    public Tile(int x, int y, Piece p) {

        // coordinates of the block.
        this.xCoordinate = x;
        this.yCoordinate = y;

        // border layout.
        this.setLayout(new BorderLayout());

        // border of color black.
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        // set color of tiles to black or white.
        if ((x+y)%2 == 0) {
            setBackground(Color.WHITE);
        } else {
            setBackground(Color.GRAY);
        }

        // set the piece.
        this.setPiece(p);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(this.hasNextMove)
            this.setBackground(Color.YELLOW);
        else if (this.isSelectedTile)
            this.setBackground(Color.GREEN);
        else if (this.opponentTile)
            this.setBackground(Color.RED);
        else {
            if ((this.xCoordinate+this.yCoordinate)%2 == 0) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.GRAY);
            }
        }
    }

    public Boolean getSelectedTile() {
        return isSelectedTile;
    }

    public void setSelectedTile(Boolean selectedTile) {
        isSelectedTile = selectedTile;
    }

    public JLabel getLogo() {
        return logo;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        // if the piece is not null, then it will contain an icon.
        // else not .
        if (piece != null) {
            this.piece = piece;
            ImageIcon img = new ImageIcon(this.getClass().getResource(piece.getImgPath()));
            logo = new JLabel(img);
            this.logo.setHorizontalAlignment(JLabel.CENTER);
        } else {
            this.piece = null;
            logo = new JLabel();
        }

        // add the logo to the tile.
        this.add(logo);
    }

    public void changePiece(Piece p) {
        this.piece = p;
        ImageIcon img = new ImageIcon(this.getClass().getResource(piece.getImgPath()));
        this.logo.setIcon(img);
        this.logo.setHorizontalAlignment(JLabel.CENTER);
    }

    public void removePiece() {
        this.piece = null;
        this.logo.setIcon(null);
    }

    public void pawnPromotion () {

    }

    public Boolean getHasNextMove() {
        return hasNextMove;
    }

    public void setHasNextMove(Boolean hasNextMove) {
        this.hasNextMove = hasNextMove;
    }

    public void setLogo(JLabel logo) {
        this.logo = logo;
    }

    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Boolean isTileOccupied() {
        return this.piece != null;
    }

    public Boolean getOpponentTile() {
        return opponentTile;
    }

    public void setOpponentTile(Boolean underCheck) {
        this.opponentTile = underCheck;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
}
