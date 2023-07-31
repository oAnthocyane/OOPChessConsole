package antn.chess.piece;


import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.CoordinatesShift;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece{
    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return new HashSet<>(Arrays.asList(
                        new CoordinatesShift(1, 2),
                        new CoordinatesShift(2, 1),
                        new CoordinatesShift(1, -2),
                        new CoordinatesShift(2, -1),
                        new CoordinatesShift(-1, -2),
                        new CoordinatesShift(-2, -1),
                        new CoordinatesShift(-1, 2),
                        new CoordinatesShift(-2, 1)
                )
        );
    }

    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
}
