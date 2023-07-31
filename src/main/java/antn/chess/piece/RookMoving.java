package antn.chess.piece;

import antn.chess.piece.properties.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface RookMoving {
    default Set<CoordinatesShift> getRookMoves(){
        Set<CoordinatesShift> result = new HashSet<>();

        // left to right
        for (int i = -7; i <= 7; i++) {
            if(i == 0) continue;

            result.add(new CoordinatesShift(i, 0));
        }


        // top to bottom
        for (int i = -7; i <= 7; i++) {
            if(i == 0) continue;

            result.add(new CoordinatesShift(0, i));
        }
        return result;
    }
}
