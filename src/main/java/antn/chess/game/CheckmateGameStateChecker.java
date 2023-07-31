package antn.chess.game;

import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.Move;
import antn.chess.board.Board;
import antn.chess.board.BoardFactory;
import antn.chess.piece.King;
import antn.chess.piece.Piece;

import java.util.List;
import java.util.Set;

public class CheckmateGameStateChecker extends GameStateChecker{

    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);
        Piece king = pieces.stream().filter(piece -> piece instanceof King).findFirst().get();

        if(!board.isSquareAttackedByColor(king.getCoordinates(), color.opposite())){
            return GameState.ONGOING;
        }
        for (Piece piece : pieces) {
            Set<Coordinates> availableMovesSquares = piece.getAvailableMoveSquares(board);

            for(Coordinates coordinates : availableMovesSquares){
                Board clone = new BoardFactory().copy(board);
                clone.makeMove(new Move(piece.getCoordinates(), coordinates));
                Piece clonedKing = clone.getPiecesByColor(color)
                        .stream().filter(p -> p instanceof King).findFirst().get();
                if(!clone.isSquareAttackedByColor(king.getCoordinates(), color.opposite())){
                    return GameState.ONGOING;
                }
            }
        }
        if(color == Color.WHITE){
            return GameState.CHECKMATE_TO_WHITE_KING;
        }else {
            return GameState.CHECKMATE_TO_BLACK_KING;
        }
    }
}
