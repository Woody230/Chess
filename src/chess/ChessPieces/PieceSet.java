
package chess.ChessPieces;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * A collection of the {@link ChessPiece}{@code s} for a particular {@link PieceColor}. It doesn't keep track of each piece's position on the board.
 * @author Brandon Selzer
 */
public class PieceSet 
{
    private final ArrayList<ChessPiece> pieceList;
    private final PieceColor color;
    
    public final static ArrayList<ChessPiece> INITIAL_BLACK_PIECE_LIST = new ArrayList()
    {{
        add(new Rook(PieceColor.BLACK));
        add(new Knight(PieceColor.BLACK));
        add(new Bishop(PieceColor.BLACK));
        add(new Queen(PieceColor.BLACK));
        add(new King(PieceColor.BLACK));
        add(new Bishop(PieceColor.BLACK));
        add(new Knight(PieceColor.BLACK));
        add(new Rook(PieceColor.BLACK));
        
        for(int i = 0; i < 8; i++)
        {
            add(new Pawn(PieceColor.BLACK));
        }
    }};//end variable declaration
            
    public final static ArrayList<ChessPiece> INITIAL_WHITE_PIECE_LIST = new ArrayList()
    {{
        for(int i = 0; i < 8; i++)
        {
            add(new Pawn(PieceColor.WHITE));
        }
        
        add(new Rook(PieceColor.WHITE));
        add(new Knight(PieceColor.WHITE));
        add(new Bishop(PieceColor.WHITE));
        add(new Queen(PieceColor.WHITE));
        add(new King(PieceColor.WHITE));
        add(new Bishop(PieceColor.WHITE));
        add(new Knight(PieceColor.WHITE));
        add(new Rook(PieceColor.WHITE));
    }};//end variable declaration
    
    /**
     * Initializes a collection of pieces based on {@code color}.
     * @param color the {@link PieceColor} of the pieces of the set
     */
    public PieceSet(PieceColor color)
    {
        this.color = color;
        
        if(color == PieceColor.WHITE)
        {
            pieceList = INITIAL_WHITE_PIECE_LIST;
        }
        else
        {
            pieceList = INITIAL_BLACK_PIECE_LIST;
        }
    }//end constructor
    
    /**
     * Removes a {@link ChessPiece} from the set based on the {@code type} of piece to be removed.
     * @param type the type of the piece to be removed
     */
    public void removePiece(PieceType type)
    {
        //Temporary game resolution.
        if(type == PieceType.KING)
        {
            String victoryColor;
            
            if(this.color == PieceColor.WHITE)
            {
                victoryColor = "Black";
            }
            else
            {
                victoryColor = "White";
            }
            
            JOptionPane.showMessageDialog(null, victoryColor + " wins");
            System.exit(0);
        }//end if 
        
        for(ChessPiece piece: pieceList)
        {
            if(piece.getPieceType() == type)
            {
                pieceList.remove(piece);
                return;
            }//end if
        }//end loop
    }//end method
}//end class
