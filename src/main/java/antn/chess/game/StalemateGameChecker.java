package antn.chess.game;

import antn.chess.piece.properties.Color;
import antn.chess.board.Board;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.Piece;

import java.util.List;
import java.util.Set;

public class StalemateGameChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color){
        List<Piece> pieces = board.getPiecesByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            if(availableMoveSquares.size() > 0){
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
