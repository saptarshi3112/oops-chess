package main.Utility;

import main.Board.Tile;

public class StateNode {

    Tile [][]val;
    StateNode prev, next;

    public StateNode(Tile[][] val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }

}
