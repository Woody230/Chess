
package chess.GUI;

import chess.Board.BoardState;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 * The GUI for the chess game.
 * @author Brandon Selzer
 */
public class GUI extends JFrame
{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private ChessBoard board;
    private final BoardState boardState;
    
    /**
     * Initializes the board state that the {@link ChessBoard} will use.
     */
    public GUI()
    {
        boardState = new BoardState();
        initialize();
    }
    
    private void initialize()
    {
        setTitle("Chess");
        setLayout(new GridBagLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        initializeComponents();
        
        pack(); //Set size of this frame to the minimum required size to display all components.
        setLocationRelativeTo(null);
    }
    
    private void initializeComponents()
    {
        board = new ChessBoard(boardState);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(board, gbc);
    }
}
