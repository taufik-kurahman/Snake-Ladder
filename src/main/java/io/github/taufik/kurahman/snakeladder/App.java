package io.github.taufik.kurahman.snakeladder;

public class App {
    public static void main(String[] args) {
        int boardCells = GameConfig.BOARD_CELLS;
        IGameInput gameInput = new GameInputImpl(boardCells);
        IDice dice = new DiceImpl(GameConfig.DICE_COUNT, GameConfig.DICE_MAX_VALUE);
        Game game = new Game(boardCells, gameInput, dice);
        game.start();
    }
}