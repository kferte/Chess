package pieces;

import board.Board;
import board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllience;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAllience){
        this.pieceType = pieceType;
        this.pieceAllience = pieceAllience;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return pieceAllience;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType{

        PAWN("P"){
            @Override
            public boolean isKing(){
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();
    }
}
