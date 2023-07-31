package antn.chess.piece.properties;

public enum Color {
    WHITE,
    BLACK;

    public Color opposite(){
        return this == WHITE ? BLACK : WHITE;
    }
}
