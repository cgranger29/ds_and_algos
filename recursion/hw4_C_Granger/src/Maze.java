package Maze;
import java.util.*;

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

    //left original problem 1 implementation here. Wasnt sure if it was required after refactor for problem 3.
    public boolean findMazePath(int x, int y) {
        /* LEAVING ORIGINAL PROBLEM 1 HERE. 
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
        */

        ArrayList<PairInt> result = findMazePathMin(x, y);

        if (result.size() == 0 || result == null){
            return false;
        } else{
            for (PairInt pair: result){
                maze.recolor(pair.getX(), pair.getY(), PATH);
            }
            return true;
        }


    }

    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        int maxRow = maze.getNRows() - 1;
        int maxCol = maze.getNCols() - 1;

        trace.push(new PairInt(x, y));

        //base cases
        if((x < 0 || y > maxRow)){
            trace.pop();
            return;
        }
        if ((y < 0 || x > maxCol)){
            trace.pop();
            return;
        }

        if(maze.getColor(x, y) == PATH || maze.getColor(x, y) == NON_BACKGROUND){
            trace.pop();
            return;

        } 

        if (maze.getColor(x, y) == BACKGROUND){
            
            maze.recolor(x, y, PATH);
        }
        if (y == maxRow &&  x== maxCol){
            ArrayList<PairInt> resultToAdd = new ArrayList<>();
            PairInt addPair = new PairInt(x, y);
            

            for(PairInt pair: trace){
                resultToAdd.add(pair);
            }
            result.add(resultToAdd);
            trace.pop();
            maze.recolor(x, y, BACKGROUND);
            return;
        }

        //check left
        findMazePathStackBased(x - 1, y, result, trace);

        //check right
        findMazePathStackBased(x + 1, y, result, trace);

        //check up
        findMazePathStackBased(x, y - 1, result, trace);

        //check down
        findMazePathStackBased(x, y + 1, result, trace);

        maze.recolor(x, y, BACKGROUND);
        trace.pop();
        return;

    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();  
        Stack<PairInt> trace = new Stack <>();
        findMazePathStackBased(0 ,0 , result , trace);
        return result; 
    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<ArrayList<PairInt>> result =  findAllMazePaths(x, y);
        
        Integer min_len = null;
        ArrayList<PairInt> res = new ArrayList<>();
        if (result.size() == 0){
            return res;
        }
        for(ArrayList<PairInt> pair: result){
            if (min_len == null || pair.size() < min_len){
                min_len = pair.size();
                res = pair;
            }
        }
        System.out.println("SHORTEST PATH IS " + res);
        return res;
    }
    
    public class PairInt{
        // only constructor and getters used for implementation but added other methods since they were in the UML.
        private int x;
        private int y;

        public PairInt(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public void setX(int x){
            this.x = x;
        }

        public void setY(int y){
            this.y = y;
        }

        public boolean equals(Object p){
            //requirements unclear for this method. This wasnt required in my implementation
            return false;
        }

        public String toString(){
            return "(" + this.x + ", " + this.y + ")";
        }

        public PairInt copy(){
            PairInt newCopy = new PairInt(this.x, this.y);
            return newCopy;
        }
    }
    

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
