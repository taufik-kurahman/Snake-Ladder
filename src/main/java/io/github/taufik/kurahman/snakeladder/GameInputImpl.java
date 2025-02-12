package io.github.taufik.kurahman.snakeladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameInputImpl implements IGameInput {
    private int boardCells;
    Scanner scanner = new Scanner(System.in);

    public GameInputImpl(int boardCells) {
        this.boardCells = boardCells;
    }

    private int getValidInteger(String label) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                LogUtil.printInvalidInteger(label);
                scanner.next();
            }
        }
    }

    @Override
    public List<Snake> inputSnakes() {
        List<Snake> snakes = new ArrayList<>();
        Set<Integer> usedHeads = new HashSet<>();

        int snakesSize = getValidInteger("Snakes Size");

        for (int i = 0; i < snakesSize; i++) {
            int head, tail;

            while (true) {
                if (!scanner.hasNextInt()) {
                    LogUtil.printInvalidInteger("Snake Head");
                    scanner.next();
                    continue;
                }
                head = scanner.nextInt();

                if (!scanner.hasNextInt()) {
                    LogUtil.printInvalidInteger("Snake Tail");
                    scanner.next();
                    continue;
                }
                tail = scanner.nextInt();

                if (head == this.boardCells) {
                    System.out.println("Snake Head can't be equals to board cells.");
                } else if (usedHeads.contains(head)) {
                    System.out.println("Snake Head already exists.");
                } else if (tail == head) {
                    System.out.println("Snake Tail can't be equals to Head.");
                } else if (tail > head) {
                    System.out.println("Snake Tail must be less than Head.");
                } else {
                    usedHeads.add(head);
                    break;
                }
            }

            snakes.add(new Snake(head, tail));
        }

        return snakes;
    }

    @Override
    public List<Ladder> inputLadders(List<Snake> snakes) {
        List<Ladder> ladders = new ArrayList<>();
        Set<Integer> usedStart = new HashSet<>();
        Set<Integer> snakeHeads = new HashSet<>();
        for (Snake snake : snakes) {
            snakeHeads.add(snake.getHead());
        }

        int laddersSize = getValidInteger("Ladders size");

        for (int i = 0; i < laddersSize; i++) {
            int start, end;

            while (true) {
                if (!scanner.hasNextInt()) {
                    LogUtil.printInvalidInteger("Ladder Start");
                    scanner.next();
                    continue;
                }
                start = scanner.nextInt();

                if (!scanner.hasNextInt()) {
                    LogUtil.printInvalidInteger("Ladder End");
                    scanner.next();
                    continue;
                }
                end = scanner.nextInt();

                if (start == this.boardCells) {
                    System.out.println("Ladder Start can't be equals to Board Cells.");
                } else if (snakeHeads.contains(start)) {
                    System.out.println("Ladder Start conflict with Snake Head.");
                } else if (usedStart.contains(start)) {
                    System.out.println("Ladder Start already exists.");
                } else if (end == start) {
                    System.out.println("Ladder End can't be equals to Ladder Start.");
                } else if (end < start) {
                    System.out.println("Ladder End must be greater than Ladder Start.");
                } else if (snakes.contains(new Snake(end, start))) {
                    System.out.println("Ladder will causing infinite loop.");
                } else {
                    usedStart.add(start);
                    break;
                }
            }

            ladders.add(new Ladder(start, end));
        }

        return ladders;
    }

    @Override
    public List<Player> inputPlayers() {
        List<Player> players = new ArrayList<>();
        int playersSize = getValidInteger("Players Size");
        scanner.nextLine();

        for (int i = 0; i < playersSize; i++) {
            String name = scanner.nextLine().trim();
            players.add(new Player(name));
        }

        return players;
    }
}