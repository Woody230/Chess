
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 *
 * @author Brandon Selzer
 */
public class Knight extends ChessPiece
{
    /**
     * Initializes the {@link Knight} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public Knight(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.KNIGHT);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>Two squares vertically and one square horizontally.
     * <li>Two squares horizontally and one square vertically.
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> validMoves = new ArrayList();
        PieceColor color = fromPiece.getPieceColor();
        
        int[] rows = {fromRow - 2, fromRow - 2, fromRow - 1, fromRow - 1, fromRow + 1, fromRow + 1, fromRow + 2, fromRow + 2};
        int[] columns = {fromColumn - 1, fromColumn + 1, fromColumn - 2, fromColumn + 2, fromColumn - 2, fromColumn + 2, fromColumn - 1, fromColumn + 1};
        
        for(int i = 0; i < rows.length; i++)
        {
            if(rows[i] >= 0 && rows[i] < numRows && columns[i] >= 0 && columns[i] < numColumns)
            {
                if(boardState[rows[i]][columns[i]] == null || boardState[rows[i]][columns[i]].getPieceColor() != color)
                {
                    validMoves.add(new Move(fromRow, fromColumn, rows[i], columns[i], fromPiece, boardState[rows[i]][columns[i]]));
                }
            }
        }
        
        return validMoves;
    }
}
