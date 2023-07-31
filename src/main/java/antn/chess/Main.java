package antn.chess;

import antn.chess.board.Board;
import antn.chess.board.BoardFactory;
import antn.chess.game.Game;

public class Main {
    public static void main(String[] args) {
        Board board = new BoardFactory().fromFen(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        );

        Game game = new Game(board);
        game.gameLoop();
    }
}