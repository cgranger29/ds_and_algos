package Maze;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        System.out.println("Currently checking point (" + x + ", " + y + ")");
        int maxRow = maze.getNRows() - 1;
        int maxCol = maze.getNCols() - 1;

        //base cases
        if((x < 0 || x > maxRow)){
            System.out.println("x is out of range");
            return false;
        }
        if ((y < 0 || y > maxCol)){
            System.out.println("y is out of range");
            return false;
        }
        if(maze.getColor(x, y) == TEMPORARY || maze.getColor(x, y) == NON_BACKGROUND){
            System.out.println("point is either blocked or already was visited");
            return false;

        } else if (maze.getColor(x, y) == PATH){
            System.out.println("Point is part of a valid path");
            return true;
        }else if (maze.getColor(x, y) == BACKGROUND){
            
            maze.recolor(x, y, TEMPORARY);
        }
        if (x == maxRow && y == maxCol){
            maze.recolor(x,y,PATH);
            return true;
        }
        
        //recursive logic
        
        //check left
        boolean left = findMazePath(x - 1, y);
        if (left){
            maze.recolor(x,y,PATH);
        }
        //check right
        boolean right = findMazePath(x + 1, y);
        if(right){
            maze.recolor(x,y,PATH);
        }
        //check up
        boolean up = findMazePath(x, y + 1);
        if(up){
            maze.recolor(x,y,PATH);
        }
        //check down
        boolean down = findMazePath(x, y - 1);
        if(down){
            maze.recolor(x,y,PATH);
        }

        if (left || right || up || down){
            System.out.println("Path found!!!");
            return true;
        }
    	

        return false;
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    
    // ADD METHOD FOR PROBLEM 3 HERE
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
