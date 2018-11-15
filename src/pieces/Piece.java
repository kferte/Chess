package pieces;

import Alliance;
import board.Board;
import board.Move;

import java.util.List;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllience;

    Piece(final int piecePosition, final Alliance pieceAllience){
        this.pieceAllience = pieceAllience;
        this.piecePosition = piecePosition;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);
}
