package pieces;

import board.Board;
import board.BoardUtils;
import board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16};

    Pawn(int piecePosition, final Alliance pieceAllience) {
        super(piecePosition, pieceAllience);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinates = this.piecePosition + (this.getPieceAllience().getDirection() * currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)){
                continue;
            }

            if(currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinates).isTileOccupied()){
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinates));
            } else if(currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition]) && this.getPieceAllience().isBlack() ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition]) && this.getPieceAllience().isWhite()){
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAllience.getDirection() * 8);

                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinates).isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinates));
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
