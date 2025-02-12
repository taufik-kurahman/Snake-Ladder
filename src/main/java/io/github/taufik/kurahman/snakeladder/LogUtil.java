package io.github.taufik.kurahman.snakeladder;

public final class LogUtil {
    private LogUtil() {}

    public static void printPlayerMove(String playerName, int diceValue, int from, int to) {
        System.out.println(playerName + " rolled a " + diceValue + " and moved from " + from + " to " + to);
    }

    public static void printInvalidInteger(String label) {
        System.out.println("Invalid " + label + " input! Please enter only numbers.");
    }
}