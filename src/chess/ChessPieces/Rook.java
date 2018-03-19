
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 *
 * @author Brandon Selzer
 */
public class Rook extends ChessPiece
{
    /**
     * Initializes the {@link Rook} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public Rook(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.ROOK);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>Any square along a straight line.
     *      <ul>
     *      <li>The {@link Rook} can not move past any other {@link ChessPiece}.
     *      </ul>
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> validMoves = new ArrayList();
        PieceColor color = fromPiece.getPieceColor();
        int row = fromRow - 1;
        int column = fromColumn;
        
        while(row >= 0) //Check up.
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
            
            row--;
        }//end loop
        
        row = fromRow;
        column = fromColumn - 1;
        
        while(column >= 0) //Check left.
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
            
            column--;
        }//end loop
        
        row = fromRow + 1;
        column = fromColumn;
        
        while(row < numRows) //Check down.
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
            
            row++;
        }//end loop
        
        row = fromRow;
        column = fromColumn + 1;
        
        while(column < numColumns) //Check right.
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

            column++;
        }//end loop
        
        return validMoves;
    }//end method
}//end class
