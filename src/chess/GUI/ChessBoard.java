
package chess.GUI;

import chess.Board.BoardState;
import chess.Board.Move;
import chess.ChessPieces.ChessPiece;
import chess.ChessPieces.PieceColor;
import chess.ChessPieces.PieceType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Keeps track of the board visually and uses {@link BoardState} to perform underlying operations.
 * @author Brandon Selzer
 */
public class ChessBoard extends JPanel
{
    private final JLabel[][] squareLabels;
    private final BoardState boardState;
    
    private ArrayList<Move> validMoves;
    private boolean setToValidMoveColor = false;
    private boolean validMoveFlag = false;
    
    private final Color cream = new Color(255, 212, 111);
    private final Color brown = new Color(204, 102, 0);
    private final Color validMoveColor = Color.WHITE;
    private final int width = 75;
    private final int height = 75;
    private final int numRows = 8;
    private final int numColumns = 8;
    
    private int screenX = 0;
    private int screenY = 0;
    private int sourceX = 0;
    private int sourceY = 0;
    private int sourceRow = 0;
    private int sourceColumn = 0;
    private int lblX = 0;
    private int lblY = 0;
    private JLabel source;
    
    /**
     * Initializes the visual state of the board with an absolute layout. This includes:
     * <ul>
     * <li>The squares that the {@link ChessPiece}{@code s} are on using a two dimensional {@link JLabel} array.
     * <li>The {@link ChessPiece}{@code s} on the squares.
     * </ul>
     * @param boardState the overall state of the board. It performs the underlying operations when the user uses the mouse.
     */
    public ChessBoard(BoardState boardState)
    {
        squareLabels = new JLabel[numRows][numColumns];
        this.boardState = boardState;
        initialize();
    }

    private void initialize()
    {
        setLayout(null);
        setPreferredSize(new Dimension(numColumns * width, numRows * height));
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                JLabel lbl = new JLabel();
                lbl.setFont(new Font("TimesNewRoman", Font.PLAIN, (int)(height * .80)));
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setBounds(column * width, row * height, width, height);
                lbl.addMouseListener(new LabelMouseHandler());
                lbl.addMouseMotionListener(new LabelMouseMotionHandler());
                
                squareLabels[row][column] = lbl;
                add(lbl);
                
                if(boardState.getBoardStateSquare(row, column) != null)
                {
                    ChessPiece piece = boardState.getBoardStateSquare(row, column);
                    setTextToPiece(row, column, piece);
                }
            }
        }
        
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                
            }
        }
    }
    
    /**
     * Draws the squares of the board. If the user picks up a {@link ChessPiece} then the squares associated with valid moves change color.
     * @param g the {@link Graphics} object used to draw 
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                if(row % 2 == 0 && column % 2 == 0 || row % 2 == 1 && column % 2 == 1)
                {
                    g.setColor(cream);
                }
                else
                {
                    g.setColor(brown);
                }
                
                g.fillRect(column * width, row * height, width, height);
            }
        }
        
        if(setToValidMoveColor)
        {
            for(Move move: validMoves)
            {
                g.setColor(validMoveColor);
                g.fillRect(move.getToColumn() * width, move.getToRow() * height, width, height);
            }
        }
    }
    
    /**
     * Resets the first (source) square the user clicks, and any associated variables, to its default.
     */
    private void resetSource()
    {
        source.setLocation(sourceX, sourceY);
        source = null;
        sourceX = 0;
        sourceY = 0;
        sourceRow = 0;
        sourceColumn = 0;
    }
    
    /**
     * Resets the valid moves and any associated variables to its default.
     */
    private void resetValidMove()
    {
        validMoves = null;
        setToValidMoveColor = false;
        validMoveFlag = false;
    }
    
    /**
     * 
     * @param lbl the {@link JLabel} that the row and column are found for
     * @return an array where [0] is the row and [1] is the column of the given JLabel
     */
    private int[] findRowColumnByLabel(JLabel lbl)
    {
        int[] rowColumn = new int[2];
        
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                if(squareLabels[row][column] == lbl)
                {
                    rowColumn[0] = row;
                    rowColumn[1] = column;
                    return rowColumn;
                }
            }
        }
        
        return null; //Shouldn't ever get to this point.
    }
    
    /**
     * Sets the text of a {@link JLabel} found in the two dimensional {@link JLabel} array based on {@code piece}.
     * @param row the row of the {@link Jlabel}
     * @param column the column of the {2link JLabel}
     * @param piece the {@link ChessPiece} used to determine what Unicode symbol the text is set to
     */
    public void setTextToPiece(int row, int column, ChessPiece piece)
    {
        if(piece == null)
        {
            squareLabels[row][column].setText("");
            return;
        }
        
        PieceType type = piece.getPieceType();
        PieceColor color = piece.getPieceColor();
        
        if(color == PieceColor.BLACK)
        {
            switch(type) 
            {
                case BISHOP:
                    squareLabels[row][column].setText("\u265D");
                    break;  
                case KING:
                    squareLabels[row][column].setText("\u265A");
                    break;
                case KNIGHT:
                    squareLabels[row][column].setText("\u265E");
                    break;
                case PAWN:
                    squareLabels[row][column].setText("\u265F");
                    break;
                case QUEEN:
                    squareLabels[row][column].setText("\u265B");
                    break;
                case ROOK:
                    squareLabels[row][column].setText("\u265C");
                    break;
            } 
        } 
        else
        {
            switch(type) 
            {
                case BISHOP:
                    squareLabels[row][column].setText("\u2657");
                    break;  
                case KING:
                    squareLabels[row][column].setText("\u2654");
                    break;
                case KNIGHT:
                    squareLabels[row][column].setText("\u2658");
                    break;
                case PAWN:
                    squareLabels[row][column].setText("\u2659");
                    break;
                case QUEEN:
                    squareLabels[row][column].setText("\u2655");
                    break;
                case ROOK:
                    squareLabels[row][column].setText("\u2656");
                    break;
            }
        } 
    }
    
    /**
     * Handles mouse presses and mouse releases for the two dimensional {@link JLabel} array.
     */
    private class LabelMouseHandler extends MouseAdapter
    {
        /**
         * Invoked when the mouse has been released on a component.
         * <br>Assuming that there is a source square and there is a valid move, the move is performed by changing the text of the {@link JLabel}{@code s} affected.
         * @param me {@link MouseEvent} created from releasing the mouse on a square
         */
        @Override
        public void mouseReleased(MouseEvent me)
        {
            if(source != null)
            {   
                int toRow = (source.getY() + (me.getYOnScreen() - source.getLocationOnScreen().y)) / height;
                int toColumn = (source.getX() + (me.getXOnScreen() - source.getLocationOnScreen().x)) / width;
                Move validMove = null;
                
                //If it is null then the user didn't drag the piece, just pressed and released.
                if(validMoves != null)
                {
                    validMove = boardState.getValidMoveFromArrayList(toRow, toColumn, validMoves);
                }
                
                //Even if validMoves isn't null, validMove could still be null.
                if(validMove != null)
                {
                    ChessPiece sourcePiece = boardState.getBoardStateSquare(sourceRow, sourceColumn);
                    ChessPiece toPiece = boardState.getBoardStateSquare(toRow, toColumn);

                    boardState.addMoveToMoveList(validMove);
                    boardState.removePieceFromPieceSet(toPiece);
                    sourcePiece.setHasMoved(true);
                    
                    setTextToPiece(sourceRow, sourceColumn, null);
                    setTextToPiece(toRow, toColumn, sourcePiece);

                    boardState.setBoardStateSquare(sourceRow, sourceColumn, null);
                    boardState.setBoardStateSquare(toRow, toColumn, sourcePiece);
                    boardState.changeTurn();
                }
                
                resetSource();
            }
            
            resetValidMove(); //Need to do this so setToValidMoveColor is false.
            repaint();
        }
        
        /**
         * Invoked when a mouse button has been pressed on a component.
         * <br>Saves all the information related to the source {@link JLabel}.
         * <br>Initializes the centering for the {@link JLabel} in case the user drags it.
         * @param me {@link MouseEvent} created from pressing the mouse on a square
         */
        @Override
        public void mousePressed(MouseEvent me)
        {
            source = (JLabel)me.getSource();
            sourceX = source.getX();
            sourceY = source.getY();
            
            int[] rowColumn = findRowColumnByLabel(source);
            sourceRow = rowColumn[0];
            sourceColumn = rowColumn[1];
            
            if(boardState.getBoardStateSquare(sourceRow, sourceColumn) != null
                    && boardState.getTurn() == boardState.getBoardStateSquare(sourceRow, sourceColumn).getPieceColor())
            {
                screenX = me.getXOnScreen();
                screenY = me.getYOnScreen();
                
                //Since labels take up the an entire square, this makes it so that the label gets centered
                //onto the mouse cursor regardless of where the user decides to click inside a square.
                //Subtracting width/2 or height/2 provides this functionality.
                lblX = sourceX + (screenX - source.getLocationOnScreen().x) - width / 2;
                lblY = sourceY + (screenY - source.getLocationOnScreen().y) - height / 2;
            }
            else //Empty or opposite color square selected.
            {
                resetSource();
            }
        }
    }

    /**
     * Handles dragging the mouse for the two dimensional {@link JLabel} array.
     */
    private class LabelMouseMotionHandler extends MouseMotionAdapter
    {
        //https://stackoverflow.com/a/874424
        
        /**
         * Invoked when a mouse button is pressed on a component and then dragged.
         * <br>When the user starts dragging, the valid moves associated with the {@link ChessPiece} are colored.
         * <br>It always determines the new position in order to keep the {@link JLabel} centered.
         * @param me {@link MouseEvent} created from dragging the {@link JLabel}.
         */
        @Override
        public void mouseDragged(MouseEvent me)
        {
            if(source != null)
            {
                if(!validMoveFlag) //Only need to do this once per mouse cycle.
                {
                    validMoves = boardState.getValidMoves(sourceRow, sourceColumn);
                    validMoveFlag = true;
                    setToValidMoveColor = true;
                    repaint();
                }
                
                //Adds change to mouse position to label's position.
                int x = lblX + me.getXOnScreen() - screenX;
                int y = lblY + me.getYOnScreen() - screenY;
                source.setLocation(x, y);
            }
        }
    }
}
