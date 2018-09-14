import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Dirieh Mahdi Ali, University of Ottawa
 */
//Name: Dirieh Mahdi Ali
//ID: 300017745
//ITI-1121 Section: C
public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private DotButton[][] board;
    private javax.swing.JLabel nbreOfStepsLabel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
        this.gameModel = gameModel;

        JFrame myFrame = new JFrame(); //create new JFrame instance with Title
        myFrame.setTitle("MineSweeper it -- the ITI 1121 version");
        int width = gameModel.getWidth();
        int heigth = gameModel.getHeigth();

        myFrame.setSize(width*28 + 30,heigth*28 + 85); 
        // ^ the icons are 28*28 pixels, so i size the window accordingly, and i add an arbitrary padding on the sides that seems to work for all (reasonable) board dimensions.
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        nbreOfStepsLabel = new JLabel("Number of steps: ");

        
        GridBagConstraints c = new GridBagConstraints();
        //c.gridwidth = width;
        //c.gridheight = heigth;
        c.fill = GridBagConstraints.BOTH;

        JPanel panel = new JPanel(new GridBagLayout());///add layout to the newly created panel
       // panel.
        Dimension d = new Dimension(28,28);//the size of each button, so that it fits the icons (28*28 pixels)
        board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()];//create/setup board of DotButton objects
        for (int i = 0; i < gameModel.getWidth(); i++){//fill the board
            for (int j = 0; j < gameModel.getHeigth(); j++){
                c.gridx = i;
                c.gridy = j;
                board[i][j] = new DotButton(i, j, getIcon(i,j));
                board[i][j].setPreferredSize(d);
                panel.add(board[i][j], c);


                //action listener setup
                board[i][j].addActionListener(gameController);
                board[i][j].setActionCommand("DotButton");
            }
        }

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);
        reset.setActionCommand("Reset");

        JButton quit = new JButton("Quit");
        quit.addActionListener(gameController);
        quit.setActionCommand("Quit");

        panel2.add(nbreOfStepsLabel, BorderLayout.CENTER);
        panel2.add(reset, BorderLayout.CENTER);
        panel2.add(quit, BorderLayout.CENTER);

        myFrame.add(panel);
        myFrame.add(panel2, BorderLayout.SOUTH);

        update();
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);//stops the user from resizing board. this ensures proper and clean view of the whole board


    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
        for (int i = 0; i < gameModel.getWidth(); i++){
            for (int j = 0; j < gameModel.getHeigth(); j++){
                board[i][j].setIconNumber(getIcon(i,j));
            }
        }

        this.nbreOfStepsLabel.setText("Number of steps: " + gameModel.getNumberOfSteps());
        this.repaint();


    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
    
    // ADD YOU CODE HERE
        String canonical_board = "";
        if(gameModel.isCovered(i,j) == false){
            return 11;
        }
        else if (gameModel.isMined(i,j) == true){
            if (gameModel.hasBeenClicked(i,j) == true){
                return 10;
            }
            else{
                return 9;
            }
        }
        else if (gameModel.getNeighbooringMines(i,j) != 0){
            return gameModel.getNeighbooringMines(i,j);
        }
        else{
            return 0;
        }

    }


}
