package antn.chess.io;

import antn.chess.piece.properties.Color;
import antn.chess.board.Board;
import antn.chess.board.BoardConsoleRenderer;
import antn.chess.board.BoardFactory;
import antn.chess.piece.*;
import antn.chess.piece.properties.Coordinates;
import antn.chess.piece.properties.File;
import antn.chess.piece.properties.Move;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    private final Scanner scanner = new Scanner(System.in);

    public Coordinates input(){
        System.out.println("Enter coordinates for example A1");
        while (true){
            String line = scanner.nextLine();
            if(line.length() != 2){
                System.out.println("Invalid format");
                continue;
            }
            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);

            if(!Character.isLetter(fileChar)){
                System.out.println("Invalid format");
                continue;
            }
            if(!Character.isDigit(rankChar)){
                System.out.println("Invalid format");
                continue;
            }
            int rank = Character.getNumericValue(rankChar);
            if(rank < 1 || rank > 8){
                System.out.println("Invalid format");
                continue;
            }

            File file = File.fromChar(fileChar);

            if(file == null){
                System.out.println("Invalid format");
                continue;
            }


            return new Coordinates(file, rank);
        }
    }

    public Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {
        while (true) {
            Coordinates sourceCoordinates = inputPieceCoordinatesForColor(
                    color, board
            );

            Piece piece = board.getPiece(sourceCoordinates);
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            renderer.render(board, piece);

            Coordinates targetCoordinates = inputAvailableSquare(availableMoveSquares);

            Move move = new Move(sourceCoordinates, targetCoordinates);
            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Your king is under attack!");
                continue;
            }
            return move;
        }
    }
    public Coordinates inputPieceCoordinatesForColor(Color color, Board board){
        while (true){
            System.out.println("Enter coordinates for a piece to move");
            Coordinates coordinates = input();
            if(board.isSquareEmpty(coordinates)){
                System.out.println("Empty square");
                continue;
            }

            Piece piece = board.getPiece(coordinates);

            if(piece.getColor() != color){
                System.out.println("Wrong color");
                continue;
            }

            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if(availableMoveSquares.size() == 0){
                System.out.println("Blocked piece");
                continue;
            }
            return coordinates;
        }
    }
    public Coordinates inputAvailableSquare(Set<Coordinates> coordinates){
        while (true){
            System.out.println("Enter you move for selected piece");
            Coordinates input = input();
            if(!coordinates.contains(input)){
                System.out.println("Non-available square");
                continue;
            }
            return input;
        }
    }
    private boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copy = new BoardFactory().copy(board);
        copy.makeMove(move);
        Piece king = copy.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();
        return copy.isSquareAttackedByColor(king.getCoordinates(), color.opposite());
    }
}


