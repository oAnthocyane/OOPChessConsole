package antn.chess.piece;

import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.CoordinatesShift;

import java.util.Set;

public class Bishop extends LongRangePiece implements BishopMoving{

    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getBishopMoves();
    }


}
