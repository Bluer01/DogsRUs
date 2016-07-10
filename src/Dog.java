import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.StringBuilder;

/**
 * To support an individual dog
 * 
 * @author Chris Loftus
 * @co-author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */
public class Dog extends Animal {
    private boolean likesBones;
    private boolean needsWalks;

    /**
     * Constructor for the dog
     * 
     * @param name
     *            The dog's name
     * @param owners
     *            A list of original owners: a copy is made
     * @param likeBones
     *            Does the dog like bones?
     * @param needsWalks
     *            DOes the dog need walks?
     * @param food
     *            The kind of food it eats
     * @param mealsPerDay
     *            Number of feeds per day
     */
    public Dog(String id, String n, ArrayList<Owner> owners, boolean likeBones, boolean needsWalks, String food,
            int mealsPerDay) {
        super(id, n, owners, food, mealsPerDay);
        this.type = "dog";
        this.likesBones = likeBones;
    }

    /**
     * Uses the results of the Animal superclass' input method and uses the
     * boolean for the sharesRun condition value. This has to be done twice for
     * dogs, as they have an additional condition
     */
    public void changeSpecialConditionStatus(Scanner scan) {
        System.out.println("Querying about needing a walk...");
        this.needsWalks = super.specialConditionInput(scan);
        System.out.println("\n Quering about liking bones...");
        this.likesBones = super.specialConditionInput(scan);
    }
    
    /**
     * Does the dog like bones?
     * 
     * @return true if it does
     */
    public boolean getLikesBones() {
        return likesBones;
    }

    /**
     * Does the dog need walks?
     * 
     * @return true if it does
     */
    public boolean getNeedsWalks() {
        return needsWalks;
    }
    
    /**
     * Calls the save method in the superclass (Animal) and then saves the
     * unique values from this class.
     */
    public void save(PrintWriter outfile, Dog d) {
        super.save(outfile);
        outfile.println(d.getLikesBones());
        outfile.println(d.getNeedsWalks());
    }
    
    /**
     * Equality check on the dog with the other object
     * 
     * @param The
     *            other dog to compare against.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dog other = (Dog) obj;
        if (likesBones != other.likesBones)
            return false;
        if (needsWalks != other.needsWalks)
            return false;
        return true;
    }

    /**
     * A basic implementation to just return all the data in string form
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append("\n Dog name: " + name).append(super.toString()).append("\n Likes bones?: " + likesBones)
                .append("\n Needs walks?: " + needsWalks).toString();
    }

}
