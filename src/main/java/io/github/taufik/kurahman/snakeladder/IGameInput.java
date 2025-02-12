package io.github.taufik.kurahman.snakeladder;

import java.util.List;

public interface IGameInput {
    public List<Snake> inputSnakes();
    public List<Ladder> inputLadders(List<Snake> snakes);
    public List<Player> inputPlayers();
}