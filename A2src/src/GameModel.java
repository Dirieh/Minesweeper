import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Dirieh Mahdi Ali, University of Ottawa
 */

//Name: Dirieh Mahdi Ali
//ID: 300017745
//ITI-1121 Section: C
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
    private java.util.Random generator = new java.util.Random();
    private int heigthOfGame;
    private DotInfo[][] model;
    private int numberOfMines;
    private int numberofsteps;
    private int widthOfGame;


    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {

    // ADD YOU CODE HERE
        this.widthOfGame = width;
        this.heigthOfGame = heigth; 
        this.numberOfMines = numberOfMines;
        this.numberofsteps = 0;
        model = new DotInfo[this.widthOfGame][this.heigthOfGame];
        reset();
    }



    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
        //resetting the state of each tile so that they are all hidden again
        this.numberofsteps = 0;
        //update mine placements randomly
        for (int i = 0; i < this.widthOfGame; i++){
            for (int j =0; j < this.heigthOfGame; j++){
                model[i][j] = new DotInfo(i,j);
            }
        }
        for (int i=0; i < this.numberOfMines; i++){
            int posx = generator.nextInt(this.widthOfGame);
            int posy = generator.nextInt(this.heigthOfGame);
            while (model[posx][posy].isMined() == true){
            posx = generator.nextInt(this.widthOfGame);
            posy = generator.nextInt(this.heigthOfGame);
            }
            model[posx][posy].setMined();
        }
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){

    // ADD YOU CODE HERE
        return this.heigthOfGame;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){

    // ADD YOU CODE HERE
        return this.widthOfGame;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        return model[i][j].isMined();
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){

    // ADD YOU CODE HERE
        return model[i][j].hasBeenClicked();
    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
  public boolean isBlank(int i, int j){

    // ADD YOU CODE HERE
    int right_incr, left_incr, top_incr, bot_incr;
    //the following if-statements are to check if the inquiry point is at one of the boarders
    //that would cause an index error for my implementation, so i make sure i increment properly.
    if (model[i][j].isMined() == true){
        return false;
    }
    if (i == this.widthOfGame-1){
        right_incr = 0;
    }
    else{
        right_incr = 1;
    }
    if (i == 0){
        left_incr = 0;
    }
    else{
        left_incr = 1;
    }
    if (j == this.heigthOfGame-1){
        bot_incr = 0;
    }
    else{
        bot_incr = 1;
    }
    if (j == 0){
        top_incr = 0;
    }
    else{
        top_incr = 1;
    }


    //now we check the surroundings of the tile selected
    if (isMined(i - left_incr,j - top_incr)== false && isMined(i,j - top_incr) == false && isMined(i + right_incr,j - top_incr) == false 
        && isMined(i - left_incr,j)== false && isMined(i + right_incr,j) == false
        && isMined(i - left_incr,j + bot_incr) == false && isMined(i,j + bot_incr) == false && isMined(i + right_incr,j + bot_incr) == false){
        return true;
    }

    return false;

  }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){

    // ADD YOU CODE HERE
        return model[i][j].isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
    int minecount = 0;
    int posx = 0;
    int posy = 0;
    for (int k = -1; k <= 1; k++){
        for (int n = -1; n <= 1; n++){
            posx = i + k;
            posy = j + n;
            if (posx >= 0 && posx <= this.widthOfGame-1 && posy >=0 && posy <= this.heigthOfGame-1){
                if (isMined(posx,posy) == true){
                    minecount++;
                }
            }

        }
    }

    return minecount;
    }

    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){

    // ADD YOU CODE HERE
        model[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
    // ADD YOU CODE HERE
        step();
        model[i][j].click();
    }
     /**
     * Uncover all remaining covered dot
     */   
     public void uncoverAll(){

    // ADD YOU CODE HERE
        for (int i =0; i<this.widthOfGame; i++){
            for (int j = 0; j<this.heigthOfGame ;j++){
                model[i][j].uncover();
            }
        }

     }



    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){

    // ADD YOU CODE HERE
        return numberofsteps;

    }



    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j){

    // ADD YOU CODE HERE
        return model[i][j];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
   public void step(){

    // ADD YOU CODE HERE
    this.numberofsteps++;

   }

   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
   public boolean isFinished(){

    // ADD YOU CODE HERE
    boolean flag = false;
    for (int i =0; i<this.widthOfGame; i++){
        for (int j =0; j<this.heigthOfGame; j++) {
            if (model[i][j].isMined() == false  && model[i][j].isCovered() == false){
                return false;
            }
            
        }
    }
    return true;

   }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
   public String toString(){

    // ADD YOU CODE HERE
    String canonical_board = "";
    for (int i =0; i<this.widthOfGame; i++){
        for (int j=0; j<this.heigthOfGame; j++){
            if(isCovered(i,j) == false){
                canonical_board += "X";
            }
            else if (isMined(i,j) == true){
                if (hasBeenClicked(i,j) == true){
                    canonical_board += "M";
                }
                else{
                    canonical_board += "m";
                }
            }
            else if (getNeighbooringMines(i,j) != 0){
                canonical_board += getNeighbooringMines(i,j);
            }
            else if (isBlank(i,j) == true){
                canonical_board += "B";
            }
            canonical_board += "\t";
        }
        canonical_board += "\n";

    }
    return canonical_board;
   }
}
