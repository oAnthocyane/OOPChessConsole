package antn.chess.piece;

import antn.chess.board.Board;
import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

abstract public class Piece {
    protected Coordinates coordinates;

    protected Color color;

    public Set<Coordinates> getAvailableMoveSquares(Board board){
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift : getPieceMoves()) {
            if(coordinates.canShift(shift)){
                Coordinates newCoordinates = coordinates.shift(shift);

                if(isSquareAvailableForMove(newCoordinates, board)){
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).getColor() != color;
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();


    public Piece(Color color, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = color;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Set<Coordinates> getAvailableAttackedSquares(Board board) {
        Set<CoordinatesShift> pieceAttacks = getPieceAttacks();
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift pieceAttack : pieceAttacks){
            if(coordinates.canShift(pieceAttack)){
                Coordinates shiftedCoordinates = coordinates.shift(pieceAttack);

                if(isSquareAvailableForAttack(shiftedCoordinates, board)){
                    result.add(shiftedCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates shiftedCoordinates, Board board) {
        return true;
    }

    protected Set<CoordinatesShift> getPieceAttacks() {
        return getPieceMoves();
    }
}
