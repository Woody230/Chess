
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 * 
 * @author Brandon Selzer
 */
public class Bishop extends ChessPiece
{
    /**
     * Initializes the {@link Bishop} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public Bishop(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.BISHOP);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>Any square along the diagonals in any direction. 
     *      <ul>
     *      <li>The {@link Bishop} can not move past any other {@link ChessPiece}.
     *      </ul>
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> validMoves = new ArrayList();
        PieceColor fromPieceColor = fromPiece.getPieceColor();
        int row, column;
        
        for(int i = 0; i < 4; i++)
        {
            switch(i)
            {
                case 0: //1st quadrant.
                    row = fromRow - 1;
                    column = fromColumn + 1;
                    break;
                case 1: //2nd quadrant.
                    row = fromRow - 1;
                    column = fromColumn - 1;
                    break;
                case 2: //3rd quadrant.
                    row = fromRow + 1;
                    column = fromColumn - 1;
                    break;
                default: //4th quadrant.
                    row = fromRow + 1;
                    column = fromColumn + 1;
            }
            
            while(row >= 0 && row < numRows && column >= 0 && column < numColumns)
            {
                if(boardState[row][column] == null) //Empty space
                {
                    validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                }
                else if(boardState[row][column].getPieceColor() != fromPieceColor) //Enemy
                {
                    validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                    break; 
                }
                else 
                {
                    break;
                }
            
                switch(i)
                {
                    case 0: //1st quadrant.
                        row--;
                        column++;
                        break;
                    case 1: //2nd quadrant.
                        row--;
                        column--;
                        break;
                    case 2: //3rd quadrant.
                        row++;
                        column--;
                        break;
                    default: //4th quadrant.
                        row++;
                        column++;
                }
            }
        }
        
        return validMoves;
    }
}
