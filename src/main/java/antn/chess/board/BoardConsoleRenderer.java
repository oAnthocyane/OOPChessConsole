package antn.chess.board;

import antn.chess.piece.properties.Color;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.File;
import antn.chess.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";
    public void render(Board board, Piece pieceToMove){
        Set<Coordinates> availableMovesSquares = emptySet();
        if(pieceToMove != null){
            availableMovesSquares = pieceToMove.getAvailableMoveSquares(board);
        }
        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for(File file : File.values()){
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighlight = availableMovesSquares.contains(coordinates);
                if(board.isSquareEmpty(coordinates)) line += getSpriteForEmptySquare(new Coordinates(file, rank), isHighlight);
                else line += getPieceSprite(board.getPiece(coordinates), isHighlight);
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public void render(Board board){
        render(board, null);
    }
    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighlight){
        // format = background color + font color + text
        String result = sprite;

        if(pieceColor == Color.WHITE) result = ANSI_WHITE_PIECE_COLOR + result;
        else result = ANSI_BLACK_PIECE_COLOR + result;
        if(isHighlight) result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        else if(isSquareDark) result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        else result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        return result;
    }
    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighlight){
        return colorizeSprite("   ", Color.WHITE, Board.isSquareDark(coordinates), isHighlight) ;
    }

    public String getPieceSprite(Piece piece, boolean isHighlight){
        return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.getColor(),
                Board.isSquareDark(piece.getCoordinates()), isHighlight);
    }
    private String selectUnicodeSpriteForPiece(Piece piece) {
        return switch (piece.getClass().getSimpleName()) {
            case "Pawn" -> "♟︎";
            case "Knight" -> "♞";
            case "Bishop" -> "♝";
            case "Rook" -> "♜";
            case "Queen" -> "♛";
            case "King" -> "♚";
            default -> "";
        };

    }


}
