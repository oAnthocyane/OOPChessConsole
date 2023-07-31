package antn.chess.piece;

import antn.chess.board.Board;
import antn.chess.board.BoardUtils;
import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;

import java.util.List;

public abstract class LongRangePiece extends Piece {
    public LongRangePiece(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);
        if(result){
            result = isSquareAvailableForAttack(coordinates, board);
        }

        return result;
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        List<Coordinates> coordinatesBetween;
        if(this.coordinates.file() == coordinates.file())
            coordinatesBetween = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates);
        else if (this.coordinates.rank().equals(coordinates.rank()))
            coordinatesBetween = BoardUtils.getHorizontalCoordinatesBetween(this.coordinates, coordinates);
        else coordinatesBetween = BoardUtils.getDiagonalsCoordinatesBetween(this.coordinates, coordinates);
        for(Coordinates c : coordinatesBetween){
            if(!board.isSquareEmpty(c)){
                return false;
            }
        }

        return true;
    }
}
