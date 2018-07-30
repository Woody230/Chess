
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 *
 * @author Brandon Selzer
 */
public class Pawn extends ChessPiece
{
    /**
     * Initializes the {@link Pawn} {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public Pawn(PieceColor color) 
    {
        super(color);
        super.setPieceType(PieceType.PAWN);
    }

    /**
     * {@inheritDoc}
     * 
     * Valid moves:
     * <ul>
     * <li>One square at a time in the current direction.
     *      <ul>
     *      <li>UP if it is {@link PieceColor#WHITE}
     *      <li>DOWN if it is {@link PieceColor#BLACK}
     *      </ul>
     * <li>If {@link ChessPiece#hasMoved} is false, then it can move two squares in the current direction.
     * <li>En passant conditions:
     *      <ul>
     *      <li>The capturing pawn must be on its fifth rank.
     *      <li>The capturing pawn must be on an adjacent column and must have just moved two squares in a single move.
     *      <li>The capture can only be made on the move immediately after the opposing pawn moves the two squares.
     *      </ul>
     * </ul>
     */
    @Override
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState)
    {
        ArrayList<Move> validMoves = new ArrayList();
        PieceColor color = fromPiece.getPieceColor();
        
        int[] rows, columns;
        
        if(color == PieceColor.BLACK)
        {
            rows = new int[]{fromRow + 1, fromRow + 2, fromRow + 1, fromRow + 1};
            columns = new int[]{fromColumn, fromColumn, fromColumn - 1, fromColumn + 1};
        } 
        else
        {
            rows = new int[]{fromRow - 1, fromRow - 2, fromRow - 1, fromRow - 1};
            columns = new int[]{fromColumn, fromColumn, fromColumn - 1, fromColumn + 1};
        } 
        
        for(int i = 0; i < rows.length; i++)
        {
            if(rows[i] >= 0 && rows[i] < numRows && columns[i] >= 0 && columns[i] < numColumns)
            {
                switch(i)
                {
                    case 0: //Movement of 1 square.
                        if(boardState[rows[i]][columns[i]] == null)
                        {
                            validMoves.add(new Move(fromRow, fromColumn, rows[i], columns[i], fromPiece, boardState[rows[i]][columns[i]]));
                            break;
                        }
                        else
                        {
                            break;
                        }
                    case 1: //Movement of 2 squares.
                        if(!fromPiece.getHasMoved() && boardState[rows[i]][columns[i]] == null && boardState[rows[i + 1]][columns[i]] == null)
                        {
                            validMoves.add(new Move(fromRow, fromColumn, rows[i], columns[i], fromPiece, boardState[rows[i]][columns[i]]));
                            break;
                        }
                        else
                        {
                            break;
                        }
                    case 2: case 3: //Capture piece.
                        if(boardState[rows[i]][columns[i]] != null && boardState[rows[i]][columns[i]].getPieceColor() != color)
                        {
                            validMoves.add(new Move(fromRow, fromColumn, rows[i], columns[i], fromPiece, boardState[rows[i]][columns[i]]));
                            break;
                        }
                        else
                        {
                            break;
                        }
                }
            }
        }
        
        //Finish implementing en passant.
        //if(previousMoves.getLastMove() == opposite color Pawn now in same row && in left or right column && it moved 2 spaces)
        
        return validMoves;
    }
}
