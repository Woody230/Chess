
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 *
 * @author Brandon Selzer
 */
public class Queen extends ChessPiece
{
    /**
     * Initializes the {@link Queen} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public Queen(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.QUEEN);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>Any square in any direction.
     *      <ul>
     *      <li>The {@link Queen} can not move past any other {@link ChessPiece}.
     *      </ul>
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> diagonalMoves = new Bishop(fromPiece.getPieceColor()).getValidMoves(fromRow, fromColumn, fromPiece, numRows, numColumns, boardState);
        ArrayList<Move> straightMoves = new Rook(fromPiece.getPieceColor()).getValidMoves(fromRow, fromColumn, fromPiece, numRows, numColumns, boardState);
        
        ArrayList<Move> validMoves = new ArrayList();
        validMoves.addAll(diagonalMoves);
        validMoves.addAll(straightMoves);
        return validMoves;
    }//end method
}//end class
