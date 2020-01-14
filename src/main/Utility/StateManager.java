package main.Utility;

import main.Board.Tile;

public class StateManager {
    private StateNode head;
    private StateNode lastIndex;

    public StateManager() {
        this.head = null;
    }

    public StateNode getHead() {
        return head;
    }

    public void setHead(StateNode head) {
        this.head = head;
    }

    public void insert(Tile [][]state) {
        StateNode new_node = new StateNode(state);
        new_node.next = null;

        if (head == null) {
            new_node.prev = null;
            head = new_node;
            this.lastIndex = new_node;
            return;
        }

        this.lastIndex.next = new_node;
        new_node.prev = this.lastIndex;
    }

    public Integer getSize() {
        StateNode n = this.head;
        Integer size = 0;
        while (n != null) {
            size += 1;
            n = n.next;
        }
        return size;
    }

    public boolean isFirstMove() {
        return false;
    }

    public Tile[][] getPreviousMove() {
        if (lastIndex.prev != null) {
            System.out.println(lastIndex.prev);
            return lastIndex.prev.val;
        }
        else {
            return null;
        }
    }

}
