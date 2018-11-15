package pieces;

import board.Board;
import board.BoardUtils;
import board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8};

    Pawn(int piecePosition, Alliance pieceAllience) {
        super(piecePosition, pieceAllience);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinates = this.piecePosition + (this.getPieceAllience().getDirection() * currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)){
                continue;
            }

            if(currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinates).isTileOccupied()){
                legalMoves.add(new Move.PawnMove(board, this, candidateDestinationCoordinates));
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
