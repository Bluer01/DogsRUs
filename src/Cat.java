import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.StringBuilder;

/**
 * To support an individual cat
 * 
 * @author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */
public class Cat extends Animal {

    private boolean sharesRun;

    /**
     * Constructor for the cat
     * 
     * @param name
     *            The cat's name
     * @param owners
     *            A list of original owners: a copy is made
     * @param sharesRun
     *            Is the cat allowed to share the run?
     * @param food
     *            The kind of food it eats
     * @param mealsPerDay
     *            Number of feeds per day
     */
    public Cat(String id, String name, ArrayList<Owner> owners, boolean sharesRun, String food, int mealsPerDay) {
        super(id, name, owners, food, mealsPerDay);
        this.type = "cat";
        this.sharesRun = sharesRun;
    }

    /**
     * Uses the results of the Animal superclass' input method and uses the
     * boolean for the sharesRun condition value
     */
    public void changeSpecialConditionStatus(Scanner scan) {
        this.sharesRun = super.specialConditionInput(scan);
    }

    /**
     * Can the cat share the run?
     * 
     * @return true if it does
     */
    public boolean getSharesRun() {
        return sharesRun;
    }
    
    /**
     * Calls the save method in the superclass (Animal) and then saves the
     * unique values from this class.
     */
    public void save(PrintWriter outfile, Cat c) {
        super.save(outfile);
        outfile.println(c.getSharesRun());
    }
    
    /**
     * Equality check on the cat with the other object
     * 
     * @param The
     *            other cat to compare against.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cat other = (Cat) obj;
        if (sharesRun != other.sharesRun)
            return false;
        return true;
    }

    /**
     * A basic implementation to just return all the data in string form
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append("\n Cat name: " + name).append(super.toString()).append("\n Shares the run?: " + sharesRun)
                .toString();
    }

}
