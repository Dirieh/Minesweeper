import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Dirieh Mahdi Ali, University of Ottawa
 */

//Name: Dirieh Mahdi Ali
//ID: 300017745
//ITI-1121 Section: C
public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private GameView gameView;


    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
        gameModel = new GameModel(width,height, numberOfMines);
        gameView = new GameView(gameModel, this);
    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE
        String action = e.getActionCommand();
        if (action.equals("DotButton")){
            DotButton button = (DotButton) e.getSource();
            play(button.getColumn(), button.getRow());

        }
        else if (action.equals("Reset")){
            reset();
        }
        else if (action.equals("Quit")){
            System.exit(0);
        }



    }

    /**
     * resets the game
     */
    private void reset(){
    // ADD YOU CODE HERE
        gameModel.reset();
        gameView.update();

    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
        if (gameModel.isCovered(width,heigth) == false){//if uncovered
        //check if covered
            gameModel.uncover(width, heigth);
            gameModel.click(width,heigth);
            if (gameModel.isMined(width,heigth) == true){//if it is mined
                //either quit game or replay
                gameModel.uncoverAll();
                gameView.update();
                Object[] options = {"Quit", "Play Again"};
                int n = JOptionPane.showOptionDialog(null, "Aouch, you lost in " + gameModel.getNumberOfSteps() + " steps!\nWould you like to play again?", "Boom!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (n==0){
                    //if pressed quit, close application
                    System.exit(0);
                }
                else{
                    //if user presses play again OR the X button to close, the game is reset.
                    reset();
                }


            }
            else if (gameModel.isBlank(width, heigth) == true){            
                //engage flooding algo
                clearZone(gameModel.get(width,heigth));
                gameView.update();
            }
            else{
                //uncover tile and show the number of surrounding mines
                //gameModel.model[width][heigth].setNeighbooringMines(getNeighbooringMines(width,heigth));
                gameModel.get(width,heigth).setNeighbooringMines(gameModel.getNeighbooringMines(width,heigth));
                gameView.update();
                


            }

        }

        if (gameModel.isFinished() == true){
            Object[] options = {"Quit", "Play Again"};
            int n = JOptionPane.showOptionDialog(null, "Congratulations, you won in " + gameModel.getNumberOfSteps() + " steps!\nWould you like to play again?", "Won", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (n==0){
                //if pressed quit, close application
                System.exit(0);
            }
            else{
                //if user presses play again OR the X button to close, the game is reset.
                reset();
            }
        }
    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
    // ADD YOU CODE HERE
        GenericArrayStack<DotInfo> stack = new GenericArrayStack<DotInfo>(gameModel.getHeigth() * gameModel.getWidth());
        stack.push(initialDot);
        while (stack.isEmpty() == false){
            DotInfo d = stack.pop();

            int posx = 0;
            int posy = 0;
            for (int k = -1; k <= 1; k++){
                for (int n = -1; n <= 1; n++){
                    posx = d.getX() + k;
                    posy = d.getY() + n;
                    if (posx >= 0 && posx <= gameModel.getWidth()-1 && posy >=0 && posy <= gameModel.getHeigth()-1){
                        DotInfo i = gameModel.get(posx,posy);
                        if (i.isCovered() == false){
                            i.uncover();
                            if (gameModel.isBlank(posx,posy) == true){
                                stack.push(i);
                            }//end if
                        }//end if
                    }//end if 

                }//end for
            }//end for 
        }//end while
    }



}
