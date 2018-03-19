
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 *
 * @author Brandon Selzer
 */
public class King extends ChessPiece
{
    /**
     * Initializes the {@link King} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public King(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.KING);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>One square in any direction that isn't in check.
     * <li>Castling conditions: 
     *      <ul>
     *      <li>The king and chosen rook are on the player's first rank.
     *      <li>Neither the king nor the chosen rook have previously moved.
     *      <li>There are no pieces between the king and the chosen rook.
     *      <li>The king is not currently in check.
     *      <li>The king does not pass through a square that is attacked by an enemy piece.
     *      <li>The king does not end up in check.
     *      </ul>
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> validMoves = new ArrayList();
        PieceColor color = fromPiece.getPieceColor();
        
        for(int row = fromRow - 1; row <= fromRow + 1; row++)
        {
            for(int column = fromColumn - 1; column <= fromColumn + 1; column++)
            {
                if(row >= 0 && row < numRows && column >= 0 && column < numColumns)
                {
                    if(boardState[row][column] == null 
                            || boardState[row][column].getPieceColor() != color)
                    {
                        validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                    }
                }//end if
            }//end loop
        }//end loop
        
        //Finish implementing castling.
        
        return validMoves;
    }//end method
}//end class
