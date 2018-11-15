package pieces;

import board.Board;
import board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllience;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAllience){
        this.pieceAllience = pieceAllience;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
    }

    public Alliance getPieceAllience(){
        return pieceAllience;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
