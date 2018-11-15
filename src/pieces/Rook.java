package pieces;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -8, -1, 1, 8};

    Rook(int piecePosition, Alliance pieceAllience) {
        super(piecePosition, pieceAllience);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;

            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
                    break;
                }

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAtDestinationAllience = pieceAtDestination.getPieceAllience();

                        if(this.pieceAllience != pieceAtDestinationAllience) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && candidateOffset == -1;
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && candidateOffset == 1;
    }
}
