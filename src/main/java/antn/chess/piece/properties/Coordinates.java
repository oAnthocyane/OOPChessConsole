package antn.chess.piece.properties;

import java.util.Objects;

public record Coordinates(File file, Integer rank) {

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift()], this.rank + shift.rankShift());
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift();
        int r = rank + shift.rankShift();

        if (f < 0 || f > 7) return false;
        if (r < 1 || r > 8) return false;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return file == that.file && Objects.equals(rank, that.rank);
    }

}
