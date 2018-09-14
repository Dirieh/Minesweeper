
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Dirieh Mahdi Ali, University of Ottawa
 */
//Name: Dirieh Mahdi Ali
//ID: 300017745
//ITI-1121 Section: C
public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE
    private boolean covered;
    private boolean mined;
    private int neighbooringMines;
    private boolean wasClicked;
    private int x;
    private int y;


    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){

    // ADD YOU CODE HERE
    this.x = x;
    this.y = y;
    this.covered = false;
    this.mined = false;
    this.neighbooringMines = 0;
    this.wasClicked = false;

    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){

    // ADD YOU CODE HERE
        return this.x;

    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

    // ADD YOU CODE HERE
        return this.y;
    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

    // ADD YOU CODE HERE
        this.mined = true;


    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

    // ADD YOU CODE HERE
        return this.mined;

    }


    /**
     * Setter for covered
     */
    public void uncover() {

    // ADD YOU CODE HERE
        this.covered = true;

    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
        return this.covered;

    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE
        this.wasClicked = true;

    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

    // ADD YOU CODE HERE
        return this.wasClicked;

    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighbooring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE
        this.neighbooringMines = neighbooringMines;

    }

    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {

    // ADD YOU CODE HERE
        return this.neighbooringMines;

    }

 }
