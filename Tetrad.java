/**
 * Tetrad class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;

public class Tetrad
{
    private Block[] blocks;

    public Tetrad(BoundedGrid<Block> grid)
    {
        blocks = new Block[4];
        for(int i = 0; i<4; i++){
            blocks[i] = new Block();
        }
        Color color = Color.BLACK;
        Location[] locs = new Location[4];
        int shape = (int)(Math.random()*7);
        if(shape == 0){
            locs[0] = new Location(0,3);
            locs[1] = new Location(0,4);
            locs[2] = new Location(0,5);
            locs[3] = new Location(0,6); //line
            color = Color.BLUE;
        }
        if(shape == 1){
            locs[0] = new Location(1,3);
            locs[1] = new Location(2,3);
            locs[2] = new Location(3,3);
            locs[3] = new Location(3,4); //L-Reverse
            color = Color.YELLOW;
        }
        if(shape == 2){
            locs[0] = new Location(1,4);
            locs[1] = new Location(2,4);
            locs[2] = new Location(3,4);
            locs[3] = new Location(3,3); //L
            color = Color.ORANGE;
        }
        if(shape == 3){
            locs[0] = new Location(2,3);
            locs[1] = new Location(3,3);
            locs[2] = new Location(3,2);
            locs[3] = new Location(4,2); //Z-Reverse
            color = Color.CYAN;
        }
        if(shape == 4){
            locs[0] = new Location(0,3);
            locs[1] = new Location(1,3);
            locs[2] = new Location(1,4);
            locs[3] = new Location(2,4); //Z 
            color = Color.GREEN;
        }
        if(shape == 5){
            locs[0] = new Location(0,3);
            locs[1] = new Location(1,3);
            locs[2] = new Location(2,3);
            locs[3] = new Location(1,4); //T
            color = Color.MAGENTA;
        }
        if(shape == 6){
            locs[0] = new Location(0,3);
            locs[1] = new Location(0,4);
            locs[2] = new Location(1,3);
            locs[3] = new Location(1,4); //Block
            color = Color.RED;
        }
        for(int i = 0; i < blocks.length; i++){
            blocks[i].setColor(color);
        }
        this.addToLocations(grid, locs);
    }

    //precondition:  blocks are not in any grid;
    //               blocks.length = locs.length = 4.
    //postcondition: The locations of blocks match locs,
    //               and blocks have been put in the grid.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i<locs.length; i++){
            blocks[i].putSelfInGrid(grid, locs[i]);
        }  // replace this line
    }

    //precondition:  Blocks are in the grid.
    //postcondition: Returns old locations of blocks;
    //               blocks have been removed from grid.
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[blocks.length];
        for(int i = 0; i<blocks.length; i++){
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;   // replace this line
    }

    //postcondition: Returns true if each of locs is
    //               valid (on the board) AND empty in
    //               grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid,
    Location[] locs)
    {
        for(Location l: locs){
            if(grid.isValid(l))
                if(grid.get(l)!=null)
                    return false;
        }
        return true;    // replace this line
    }

    //postcondition: Attempts to move this tetrad deltaRow
    //               rows down and deltaCol columns to the
    //               right, if those positions are valid
    //               and empty; returns true if successful
    //               and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        //Exercise 2.2    Insert code here to
        //              ask any block for its grid and store value
        //              remove the blocks (but save the locations)
        //              create an array of the new (possible) locations
        //              check if the new locations are empty
        //              replace the tetrad in the proper place (translated)
        //              return true if moved, false if not moved
        BoundedGrid<Block> g = blocks[0].getGrid();
        Location[] locs = new Location[blocks.length];
        for(int i = 0; i < blocks.length; i++){
            locs[i] = blocks[i].getLocation();
            if(locs[i]==null)
                return false;
            blocks[i].removeSelfFromGrid();
        }
        Location[] newLocs = new Location[blocks.length];
        for(int i = 0; i<blocks.length; i++){
            newLocs[i] = new Location(locs[i].getRow() + deltaRow, locs[i].getCol() + deltaCol);
        }
        boolean valid = true;
        for(int i = 0; i<blocks.length; i++){
            valid = valid && g.isValid(newLocs[i]);
        }
        if(!(areEmpty(g,newLocs)&&valid)){
            this.addToLocations(g, locs);
            return false;
        }
        this.addToLocations(g, newLocs);
        return true;   // replace this line
    }

    //postcondition: Attempts to rotate this tetrad
    //               clockwise by 90 degrees about its
    //               center, if the necessary positions
    //               are empty; returns true if successful
    //               and false otherwise.
    public boolean rotate()
    {
        //Exercise 3.0  Insert code here to
        //              ask any block for its grid and store value
        //              remove the blocks (but save the locations)
        //              check if the new locations are empty
        //              replace the tetrad in the proper place (rotated)

        BoundedGrid<Block> g = blocks[0].getGrid();
        Location[] locs = new Location[blocks.length];
        for(int i = 0; i < blocks.length; i++){
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        Location[] newLocs = new Location[blocks.length];
        for(int i = 0; i<blocks.length; i++){
            newLocs[i] = new Location(locs[1].getRow()-locs[1].getCol() + locs[i].getCol(), locs[1].getRow() +locs[1].getCol() - locs[i].getRow());
        }
        boolean valid = true;
        for(int i = 0; i<blocks.length; i++){
            valid = valid && g.isValid(newLocs[i]);
        }
        if(!areEmpty(g,newLocs)||!valid){
            this.addToLocations(g, locs);
            return false;
        }
        this.addToLocations(g, newLocs);
        return true;    // replace this line
    }
}
