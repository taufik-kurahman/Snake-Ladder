package io.github.taufik.kurahman.snakeladder;

import java.util.*;

public class Game {
    private IGameInput gameInput;
    private IDice dice;
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean hasWinner;
    private final GameEventHandler eventHandler;

    public Game(int boardCells, IGameInput gameInput, IDice dice) {
        this.gameInput = gameInput;
        this.dice = dice;
        board = new Board(boardCells);
        currentPlayerIndex = 0;
        hasWinner = false;
        eventHandler = new GameEventHandler() {
            @Override
            public void onGameStarted() {
                Game.this.onGameStarted();
            }

            @Override
            public void onPlayerRolledTheDice(Player player, int diceValue) {
                Game.this.onPlayerRolledTheDice(player, diceValue);
            }

            @Override
            public void onPlayerWin(Player player) {
                Game.this.onPlayerWin(player);
            }
        };
    }

    public void start() {
        List<Snake> snakes = gameInput.inputSnakes();
        List<Ladder> ladders = gameInput.inputLadders(snakes);
        board.setPositionMap(snakes, ladders);
        players = gameInput.inputPlayers();
        eventHandler.onGameStarted();
    }

    private void onGameStarted() {
        while (!hasWinner) {
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.rollTheDice(dice, eventHandler);
        }
    }

    private void onPlayerRolledTheDice(Player player, int diceValue) {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + diceValue;

        if (newPosition > board.getCells()) {
            LogUtil.printPlayerMove(player.getName(), diceValue, currentPosition, currentPosition);
        } else {
            int finalPosition = newPosition;
            Map<Integer, Integer> positionMap = board.getPositionMap();
            if (positionMap.containsKey(newPosition)) {
                finalPosition = positionMap.get(newPosition);
            }
            player.setPosition(finalPosition);
            LogUtil.printPlayerMove(player.getName(), diceValue, currentPosition, finalPosition);

            if (finalPosition == board.getCells()) {
                eventHandler.onPlayerWin(player);
            }
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private void onPlayerWin(Player player) {
        System.out.println(player.getName() + " wins the game");
        hasWinner = true;
    }
}