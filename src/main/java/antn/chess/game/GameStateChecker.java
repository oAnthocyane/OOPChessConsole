package antn.chess.game;

import antn.chess.piece.properties.Color;
import antn.chess.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
