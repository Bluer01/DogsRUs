import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.StringBuilder;

/**
 * To support an individual bird
 * 
 * @author Daniel Bloor
 * @version 1.8 (20th April 2015)
 */
public class Bird extends Animal {

    private boolean wingsClipped;

    /**
     * Constructor for the bird
     * 
     * @param name
     *            The bird's name
     * @param owners
     *            A list of original owners: a copy is made
     * @param wingsClipped
     *            Are the bird's wings clipped?
     * @param food
     *            The kind of food it eats
     * @param mealsPerDay
     *            Number of feeds per day
     */
    public Bird(String id, String n, ArrayList<Owner> owners, boolean wingsClipped, String food, int mealsPerDay) {
        super(id, n, owners, food, mealsPerDay);
        this.type = "bird";
        this.wingsClipped = wingsClipped;
    }
    
    /**
     * Uses the results of the Animal superclass' input method and uses the
     * boolean for the sharesRun condition value
     */
    public void changeSpecialConditionStatus(Scanner scan) {
        this.wingsClipped = super.specialConditionInput(scan);
    }
    
    /**
     * Does the bird have clipped wings?
     * 
     * @return true if it does
     */
    public boolean getWingsClipped() {
        return wingsClipped;
    }

    /**
     * Calls the save method in the superclass (Animal) and then saves the
     * unique values from this class.
     * 
     * @param outfile
     *               The file to save to
     * @param b
     *         The bird that you're saving
     */
    public void save(PrintWriter outfile, Bird b) {
        super.save(outfile);
        outfile.println(b.getWingsClipped());
    }
    
    /**
     * Equality check on the bird with the other object
     * 
     * @param The
     *            other bird to compare against.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bird other = (Bird) obj;
        if (wingsClipped != other.wingsClipped)
            return false;
        return true;
    }

    /**
     * A basic implementation to just return all the data in string form
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append("\n Bird name: " + name).append(super.toString()).append("\n wings clipped?: " + wingsClipped).toString();
    }

}
