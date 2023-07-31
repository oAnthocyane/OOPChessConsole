package antn.chess.board;

import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.File;
import antn.chess.piece.properties.Move;
import antn.chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    private final HashMap<Coordinates, Piece> pieces = new HashMap<>();

    private final String startingFen;

    private final List<Move> moves = new ArrayList<>();

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPiece(Coordinates coordinates, Piece piece){
        piece.setCoordinates(coordinates);
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates){
        pieces.remove(coordinates);
    }

    public void makeMove(Move move){
        Piece piece = getPiece(move.getFrom());
        removePiece(move.getFrom());
        setPiece(move.getTo(), piece);

        moves.add(move);
    }

    public void setupDefaultPiecePosition(){
        // set pawns
        for(File file : File.values()){
            setPiece(new Coordinates(file, 2), new Pawn(Color.WHITE, new Coordinates(file, 2)));
            setPiece(new Coordinates(file, 7), new Pawn(Color.BLACK, new Coordinates(file, 7)));
        }

        // set rook
        setPiece(new Coordinates(File.A, 1), new Rook(Color.WHITE, new Coordinates(File.A, 1)));
        setPiece(new Coordinates(File.H, 1), new Rook(Color.WHITE, new Coordinates(File.H, 1)));
        setPiece(new Coordinates(File.A, 8), new Rook(Color.BLACK, new Coordinates(File.A, 8)));
        setPiece(new Coordinates(File.H, 8), new Rook(Color.BLACK, new Coordinates(File.H, 8)));

        // set Knight
        setPiece(new Coordinates(File.B, 1), new Knight(Color.WHITE, new Coordinates(File.B, 1)));
        setPiece(new Coordinates(File.G, 1), new Knight(Color.WHITE, new Coordinates(File.G, 1)));
        setPiece(new Coordinates(File.B, 8), new Knight(Color.BLACK, new Coordinates(File.B, 8)));
        setPiece(new Coordinates(File.G, 8), new Knight(Color.BLACK, new Coordinates(File.G, 8)));

        // set Bishop
        setPiece(new Coordinates(File.C, 1), new Bishop(Color.WHITE, new Coordinates(File.C, 1)));
        setPiece(new Coordinates(File.F, 1), new Bishop(Color.WHITE, new Coordinates(File.F, 1)));
        setPiece(new Coordinates(File.C, 8), new Bishop(Color.BLACK, new Coordinates(File.C, 8)));
        setPiece(new Coordinates(File.F, 8), new Bishop(Color.BLACK, new Coordinates(File.F, 8)));

        // set Queen
        setPiece(new Coordinates(File.D, 1), new Queen(Color.WHITE, new Coordinates(File.D, 8)));
        setPiece(new Coordinates(File.D, 8), new Queen(Color.BLACK, new Coordinates(File.D, 8)));

        // set King
        setPiece(new Coordinates(File.E, 1), new King(Color.WHITE, new Coordinates(File.E, 1)));
        setPiece(new Coordinates(File.E, 8), new King(Color.BLACK, new Coordinates(File.E, 8)));


    }

    public boolean isSquareEmpty(Coordinates coordinates){
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates){
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates){
        return (coordinates.file().ordinal() + 1 + coordinates.rank()) % 2 == 0;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);
        for(Piece piece : pieces){
            Set<Coordinates> attackedSquares = piece.getAvailableAttackedSquares(this);

            if(attackedSquares.contains(coordinates)) return true;
        }
        return false;
    }

    public List<Piece> getPiecesByColor(Color color) {
        return pieces.values().stream().filter(piece -> piece.getColor() == color).toList();
    }

    public String getStartingFen() {
        return startingFen;
    }

    public List<Move> getMoves() {
        return moves;
    }
}
