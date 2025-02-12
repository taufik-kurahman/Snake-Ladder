package io.github.taufik.kurahman.snakeladder;

import java.util.*;

public class Game implements GameEventHandler {
    private IGameInput gameInput;
    private IDice dice;
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean hasWinner;

    public Game(int boardCells, IGameInput gameInput, IDice dice) {
        this.gameInput = gameInput;
        this.dice = dice;
        board = new Board(boardCells);
        currentPlayerIndex = 0;
        hasWinner = false;
    }

    public void start() {
        List<Snake> snakes = gameInput.inputSnakes();
        List<Ladder> ladders = gameInput.inputLadders(snakes);
        board.setPositionMap(snakes, ladders);
        players = gameInput.inputPlayers();
        onGameStarted();
    }

    @Override
    public void onGameStarted() {
        while (!hasWinner) {
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.rollTheDice(dice, this);
        }
    }

    @Override
    public void onPlayerRolledTheDice(Player player, int diceValue) {
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
                onPlayerWin(player);
            }
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    @Override
    public void onPlayerWin(Player player) {
        System.out.println(player.getName() + " wins the game");
        hasWinner = true;
    }
}