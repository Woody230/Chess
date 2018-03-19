
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
        PieceColor color = fromPiece.getPieceColor();
        int row = fromRow - 1;
        int column = fromColumn + 1;
        
        while(row >= 0 && column < numColumns) //Check 1st quadrant.
        {
            if(boardState[row][column] == null) //Empty space
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
            }
            else if(boardState[row][column].getPieceColor() != color) //Enemy
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                break; 
            }
            else 
            {
                break;
            }
            
            //Move along diagonal.
            row--;
            column++;
        }//end loop
        
        row = fromRow - 1;
        column = fromColumn - 1;
        
        while(row >= 0 && column >= 0) //Check 2nd quadrant.
        {
            if(boardState[row][column] == null) //Empty space
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
            }
            else if(boardState[row][column].getPieceColor() != color) //Enemy
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                break;
            }
            else
            {
                break;
            }
            
            //Move along diagonal.
            row--;
            column--;
        }//end loop
        
        row = fromRow + 1;
        column = fromColumn - 1;
        
        while(row < numRows && column >= 0) //Check 3rd quadrant.
        {
            if(boardState[row][column] == null) //Empty space
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
            }
            else if(boardState[row][column].getPieceColor() != color) //Enemy
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                break;
            }
            else
            {
                break;
            }
            
            //Move along diagonal.
            row++;
            column--;
        }//end loop
        
        row = fromRow + 1;
        column = fromColumn + 1;
        
        while(row < numRows && column < numColumns) //Check 4th quadrant.
        {
            if(boardState[row][column] == null) //Empty space
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
            }
            else if(boardState[row][column].getPieceColor() != color) //Enemy
            {
                validMoves.add(new Move(fromRow, fromColumn, row, column, fromPiece, boardState[row][column]));
                break;
            }
            else
            {
                break;
            }
            
            //Move along diagonal.
            row++;
            column++;
        }//end loop
        
        return validMoves;
    }//end method
}//end class
