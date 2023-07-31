package antn.chess.piece;

import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.CoordinatesShift;

import java.util.Set;

public class Queen extends LongRangePiece implements BishopMoving, RookMoving{
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());

        return moves;
    }
}
