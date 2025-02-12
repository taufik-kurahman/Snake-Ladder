package io.github.taufik.kurahman.snakeladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int cells;
    private Map<Integer, Integer> positionMap;

    public Board(int cells) {
        this.cells = cells;
        this.positionMap = new HashMap<>();
    }

    public int getCells() {
        return cells;
    }

    public void setPositionMap(List<Snake> snakes, List<Ladder> ladders) {
        for (Snake snake : snakes) {
            positionMap.put(snake.getHead(), snake.getTail());
        }

        for (Ladder ladder : ladders) {
            positionMap.put(ladder.getStart(), ladder.getEnd());
        }
    }

    public Map<Integer, Integer> getPositionMap() {
        return positionMap;
    }
}