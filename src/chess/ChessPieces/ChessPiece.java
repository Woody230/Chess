
package chess.ChessPieces;

import chess.Board.Move;
import java.util.ArrayList;

/**
 * 
 * @author Brandon Selzer
 */
public abstract class ChessPiece 
{
    private PieceType type;
    private final PieceColor color;
    private boolean hasMoved;
    
    /**
     * Initializes the {@link ChessPiece}.
     * @param color the {@link PieceColor} of a {@link ChessPiece}
     */
    public ChessPiece(PieceColor color)
    {
        this.color = color;
        this.hasMoved = false;
    }
    
    public PieceType getPieceType()
    {
        return type;
    }
    
    public PieceColor getPieceColor()
    {
        return color;
    }
    
    public boolean getHasMoved()
    {
        return hasMoved;
    }
    
    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved = hasMoved;
    }

    protected void setPieceType(PieceType type)
    {
        this.type = type;
    }

    /**
     * 
     * @param fromRow the row the {@code fromPiece} is currently at
     * @param fromColumn the column {@code fromPiece} is currently at
     * @param fromPiece the {@link ChessPiece} that wants to move
     * @param numRows the number of rows of the {@code boardState}
     * @param numColumns the number of columns of the {@code boardState}
     * @param boardState the two dimensional {@link ChessPiece} array representation of the board
     * @return an {@link ArrayList} containing all of the valid moves {@code fromPiece} can do from {@code fromRow} and {@code fromColumn}
     */
    public abstract ArrayList<Move> getValidMoves(int fromRow, int fromColumn, ChessPiece fromPiece, int numRows, int numColumns, ChessPiece[][] boardState);
}//end class
