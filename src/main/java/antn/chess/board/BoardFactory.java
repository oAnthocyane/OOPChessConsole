package antn.chess.board;

import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.File;
import antn.chess.piece.properties.Move;
import antn.chess.piece.factory.PieceFactory;

public class BoardFactory {

    private final PieceFactory pieceFactory = new PieceFactory();

    public Board fromFen(String fen){

        Board board = new Board(fen);

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];
        String[] fenRows = piecePositions.split("/");
        for (int i = 0; i < fenRows.length; i++) {
            String row = fenRows[i];
            int rank = 8 - i;
            int fileIndex = 0;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);
                if(Character.isDigit(fenChar)){
                    fileIndex += Character.getNumericValue(fenChar);
                }else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);
                    board.setPiece(coordinates, pieceFactory.fromFenChar(fenChar, coordinates));
                    fileIndex++;
                }
            }
        }
        return board;
    }

    public Board copy(Board source){
        Board clone = fromFen(source.getStartingFen());
        for(Move move : source.getMoves()){
            clone.makeMove(move);
        }

        return clone;
    }
}
