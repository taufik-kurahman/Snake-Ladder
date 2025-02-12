package io.github.taufik.kurahman.snakeladder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameTest {
    @Mock
    private IDice dice;

    @Mock
    private IGameInput gameInput;

    @Mock 
    private GameEventHandler eventHandler;

    private Game game;

    private static final int BOARD_CELLS = GameConfig.BOARD_CELLS;

    @BeforeEach
    void setUp() {
        game = spy(new Game(BOARD_CELLS, gameInput, dice));
    }

    @Test
    void playerWinsWhenReachingBoardCells() {
        List<Snake> snakes = List.of(new Snake(6, 1));
        Ladder ladder = new Ladder(5, BOARD_CELLS);
        List<Ladder> ladders = List.of(ladder);
        Player player = new Player("Test");
        List<Player> players = List.of(player);
        int diceValue = ladder.getEnd();

        when(gameInput.inputSnakes()).thenReturn(snakes);
        when(gameInput.inputLadders(any())).thenReturn(ladders);
        when(gameInput.inputPlayers()).thenReturn(players);
        when(dice.roll()).thenReturn(diceValue);

        game.start();

        verify(game).onGameStarted();
        verify(game).onPlayerRolledTheDice(player, diceValue);
        verify(game).onPlayerWin(player);

        assertEquals(BOARD_CELLS, player.getPosition());
    }
}