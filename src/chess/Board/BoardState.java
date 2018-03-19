
package chess.Board;

import chess.ChessPieces.ChessPiece;
import chess.ChessPieces.PieceColor;
import chess.ChessPieces.PieceSet;
import java.util.ArrayList;

/**
 * Keeps track of the overall state of the board. This includes:
 * <ul>
 * <li>The squares that the {@link ChessPiece}{@code s} are on using a two dimensional {@link ChessPiece} array.
 * <li>The black and white piece sets using {@link PieceSet}.
 * <li>The moves that haven taken place throughout the game using {@link MoveList}.
 * <li>The current turn using {@link PieceColor}.
 * </ul>
 * 
 * @author Brandon Selzer
 */
public class BoardState 
{
    private final ChessPiece[][] boardState;
    private final PieceSet blackSet;
    private final PieceSet whiteSet;
    private final MoveList moveList;
    private PieceColor turn = PieceColor.WHITE;
    
    private final int numRows = 8;
    private final int numColumns = 8;
    
    /**
     * Initializes the board state. This includes:
     * <ul>
     * <li>The two dimensional {@link ChessPiece} array.
     * <li>The black and white piece sets using {@link PieceSet}.
     * <li>{@link MoveList}.
     * </ul>
     */
    public BoardState()
    {
        boardState = new ChessPiece[numRows][numColumns];
        blackSet = new PieceSet(PieceColor.BLACK);
        whiteSet = new PieceSet(PieceColor.WHITE);
        moveList = new MoveList();
        
        initialize();
    }//end constructor
    
    /**
     * Initializes the board using each {@link PieceSet} in the order that each {@link ChessPiece} will be placed from top left to bottom right.
     */
    private void initialize()
    {
        int index = 0;
        
        for(int row = 0; row <= 1; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                boardState[row][column] = PieceSet.INITIAL_BLACK_PIECE_LIST.get(index);
                index++;
            }//end loop
        }//end loop
        
        index = 0;
        
        for(int row = numRows - 2; row <= numRows - 1; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                boardState[row][column] = PieceSet.INITIAL_WHITE_PIECE_LIST.get(index);
                index++;
            }//end loop
        }//end loop
    }//end method
    
    public PieceColor getTurn()
    {
        return turn;
    }
    
    public MoveList getMoveList()
    {
        return moveList;
    }
    
    /**
     * 
     * @param row the row of the board state
     * @param column the column of the board state
     * @return a {@link ChessPiece} at the {@code row} and {@code column} of the board state, or null if there isn't any
     */
    public ChessPiece getBoardStateSquare(int row, int column)
    {
        return boardState[row][column];
    }
    
    /**
     * Changes the game's turn. If it is {@link PieceColor#WHITE} then it changes to {@link PieceColor#BLACK}. If it is {@link PieceColor#BLACK} then it changes to {@link PieceColor#WHITE}.
     */
    public void changeTurn()
    {
        if(turn == PieceColor.WHITE)
        {
            turn = PieceColor.BLACK;
        }
        else
        {
            turn = PieceColor.WHITE;
        }
    }//end method
    
    /**
     * Saves {@code piece} to the {@code row} and {@code column} of the board state.
     * @param row the row of the board state 
     * @param column the column of the board state
     * @param piece the {@link ChessPiece} to be saved
     */
    public void setBoardStateSquare(int row, int column, ChessPiece piece)
    {
        boardState[row][column] = piece;
    }
    
    /**
     * Invokes {@link MoveList#addMove(chess.Board.Move)} in order to add the move to the {@link MoveList}.
     * @param move the move added to the {@link MoveList}
     */
    public void addMoveToMoveList(Move move)
    {
        moveList.addMove(move);
    }
    
    /**
     * Removes {@code piece} from the {@link PieceSet} of the same {@link PieceColor}.
     * @param piece the {@link ChessPiece} to be removed.
     */
    public void removePieceFromPieceSet(ChessPiece piece)
    {
        if(piece == null)
        {
            return;
        }
        else if(piece.getPieceColor() == PieceColor.WHITE)
        {
            whiteSet.removePiece(piece.getPieceType());
        }
        else
        {
            blackSet.removePiece(piece.getPieceType());
        }
    }//end method
    
    /**
     * 
     * @param fromRow the row the {@link ChessPiece} is currently at
     * @param fromColumn the column the {@link ChessPiece} is currently at
     * @param toRow the row the {@link ChessPiece} wants to move to
     * @param toColumn the column the {@link ChessPiece} wants to move to
     * @return a {@link Move} representing the action of going from {@code fromRow} and {@code fromColumn} to {@code toRow} and {@code toColumn}, or null if the move isn't valid
     */
    /*
    public Move getValidMove(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        ArrayList<Move> validMoves = boardState[fromRow][fromColumn].getValidMoves(fromRow, fromColumn, getBoardStateSquare(fromRow, fromColumn), numRows, numColumns, boardState);
        
        for(Move move: validMoves)
        {
            if(move.getToRow() == toRow && move.getToColumn() == toColumn)
            {
                return move;
            }
        }//end loop
        
        return null;
    }//end method
    */
    
    /**
     * 
     * @param toRow the row the {@link ChessPiece} wants to move to
     * @param toColumn the column the {@link ChessPiece} wants to move to
     * @param validMoves an {@link ArrayList} containing all of the possible moves from an origin square
     * @return a {@link Move} representing the action of going from an origin square to {@code toRow} and {@code toColumn}, or null if the move isn't valid
     */
    public Move getValidMoveFromArrayList(int toRow, int toColumn, ArrayList<Move> validMoves)
    {
        for(Move move: validMoves)
        {
            if(move.getToRow() == toRow && move.getToColumn() == toColumn)
            {
                return move;
            }
        }//end loop
        
        return null;
    }//end method
    
    /**
     * 
     * @param fromRow the row the {@link ChessPiece} is currently at
     * @param fromColumn the column the {@link ChessPiece} is currently at
     * @return an {@link ArrayList} containing all of the possible moves originating from {@code fromRow} and {@code fromColumn}
     */
    public ArrayList<Move> getValidMoves(int fromRow, int fromColumn)
    {
        return boardState[fromRow][fromColumn].getValidMoves(fromRow, fromColumn, getBoardStateSquare(fromRow, fromColumn), numRows, numColumns, boardState);
    }//end method
}//end class
