package io.github.taufik.kurahman.snakeladder;

public interface GameEventHandler {
    public void onGameStarted();
    public void onPlayerRolledTheDice(Player player, int diceValue);
    public void onPlayerWin(Player player);
}