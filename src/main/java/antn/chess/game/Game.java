package antn.chess.game;

import antn.chess.piece.properties.Color;
import antn.chess.io.InputCoordinates;
import antn.chess.piece.properties.Move;
import antn.chess.board.Board;
import antn.chess.board.BoardConsoleRenderer;
import java.util.List;

public class Game {

    private final Board board;

    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameChecker(),
            new CheckmateGameStateChecker()
    );

    private final InputCoordinates inputCoordinates = new InputCoordinates();

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop(){
        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING){

            if(colorToMove == Color.WHITE){
                System.out.println("White to move");
            }
            else {
                System.out.println("Black to move");

            }
            renderer.render(board);
            Move move = inputCoordinates.inputMove(board, colorToMove, renderer);
            board.makeMove(move);

            // pass move
            colorToMove = colorToMove.opposite();
            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);
        System.out.println("Game ended with state: " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if(state != GameState.ONGOING) return state;
        }
        return GameState.ONGOING;
    }
}
