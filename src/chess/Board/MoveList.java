
package chess.Board;

import java.util.ArrayList;

/**
 *  Used to keep a record of all the actions taken throughout the game.
 *  <br> Uses an {@link java.util.ArrayList} to store each {@link Move}.
 * 
 * @author Brandon Selzer
 */
public class MoveList 
{
    private final ArrayList<Move> moves;
    
    /**
     * Creates an {@link ArrayList} of {@link Move}.
     */
    public MoveList()
    {
        moves = new ArrayList();
    }
    
    /**
     * Adds a {@link Move} to the {@link ArrayList}.
     * @param move the {@link Move} to be added
     */
    public void addMove(Move move)
    {
        moves.add(move);
    }
    
    /**
     * Removes the last move in the {@link ArrayList}.
     */
    public void removeLastMove()
    {
        moves.remove(moves.size() - 1);
    }
    
    public ArrayList<Move> getMoves()
    {
        return moves;
    }
}
