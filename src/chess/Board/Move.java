
package chess.Board;

import chess.ChessPieces.ChessPiece;

/**
 * Used to keep a record of a single action taken during the game. This includes:
 * <ul>
 * <li>The originating row and column of the {@link ChessPiece} being moved.
 * <li>The terminating row and column of the {@link ChessPiece} being moved.
 * <li>The moves that haven taken place throughout the game using {@link MoveList}.
 * <li>The current turn using {@link PieceColor}.
 * </ul>
 * 
 * @see MoveList
 * 
 * @author Brandon Selzer
 */
public class Move 
{
    private int fromRow;
    private int fromColumn;
    private int toRow;
    private int toColumn;
    private ChessPiece pieceMoved;
    private ChessPiece pieceCaptured;
    
    /*
    public Move(int fromRow, int fromColumn, int toRow, int toColumn, ChessPiece pieceMoved)
    {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
        this.pieceMoved = pieceMoved;
    }//end constructor
    */
    
    /**
     * Creates a new {@link Move}.
     * @param fromRow the row {@code pieceMoved} is currently at
     * @param fromColumn the column {@code pieceMoved} is currently at
     * @param toRow the row {@code pieceMoved} wants to move to
     * @param toColumn the column {@code pieceMoved} wants to move to
     * @param pieceMoved the {@link ChessPiece} that is being moved
     * @param pieceCaptured the {@link ChessPiece} that will be captured, or null if there is no {@link ChessPiece} at {@code toRow} and {@code toColumn}
     */
    public Move(int fromRow, int fromColumn, int toRow, int toColumn, ChessPiece pieceMoved, ChessPiece pieceCaptured)
    {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
        this.pieceMoved = pieceMoved;
        this.pieceCaptured = pieceCaptured;
    }//end constructor

    public int getFromRow() 
    {
        return fromRow;
    }

    public int getFromColumn() 
    {
        return fromColumn;
    }

    public int getToRow() 
    {
        return toRow;
    }

    public int getToColumn() 
    {
        return toColumn;
    }

    public ChessPiece getPieceMoved() 
    {
        return pieceMoved;
    }

    public ChessPiece getPieceCaptured() 
    {
        return pieceCaptured;
    }

    public void setFromRow(int fromRow) 
    {
        this.fromRow = fromRow;
    }

    public void setFromColumn(int fromColumn) 
    {
        this.fromColumn = fromColumn;
    }

    public void setToRow(int toRow) 
    {
        this.toRow = toRow;
    }

    public void setToColumn(int toColumn) 
    {
        this.toColumn = toColumn;
    }

    public void setPieceMoved(ChessPiece pieceMoved) 
    {
        this.pieceMoved = pieceMoved;
    }

    public void setPieceCaptured(ChessPiece pieceCaptured) 
    {
        this.pieceCaptured = pieceCaptured;
    }
}//end class
