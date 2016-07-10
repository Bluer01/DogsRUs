import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Animal class - for the animals in the kennel
 * 
 * @author Lynda Thomas and Chris Loftus?
 * @co-author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */

public class Animal implements Comparable<Animal> { // Implementing 'Comparable'
                                                    // lets me compare an animal
                                                    // with another one
    String id;
    String name;
    String type;
    String favFood;
    int foodPerDay;

    public ArrayList<Owner> originalOwners;

    public Animal() {
        name = "unknown animal";
    }

    /**
     * Constructor for the animal
     * 
     * @param identifier
     *            ID for the animal
     * @param n
     *            Name of the animal
     * @param owners
     *            Owners of the animal
     * @param food
     *            Favourite food of the animal
     * @param mealsPerDay
     *            Feeds per day for the animal
     */
    public Animal(String identifier, String n, ArrayList<Owner> owners, String food, int mealsPerDay) {
        this.id = identifier;
        this.name = n;
        this.originalOwners = new ArrayList<Owner>();
        for (Owner o : owners) {
            Owner copy = new Owner(o.getName(), o.getPhone());
            originalOwners.add(copy);
        this.favFood = food;
        this.foodPerDay = mealsPerDay;
        }
    }
    
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object) Necessary method to
     *      make sure that the sorting method in the KennelDemo class can sort
     *      properly, comparing the names and putting them alphabetically
     * 
     * @param compareAnimal
     *            The animal to compare it with
     * 
     * @return this.name.compareToIgnoreCase(compareName) Dictates the order
     *         dependent on the results
     */
    public int compareTo(Animal compareAnimal) {
        String compareName = ((Animal) compareAnimal).getName();

        return this.name.compareToIgnoreCase(compareName);
    }
    
    /**
     * Gets the ID of the animal
     * 
     * @return id The id value of the animal
     */
    public String getID() {
        return id;
    }
    
    /**
     * Setting the animal's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of the animal
     * 
     * @return name The name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Getting the animal type (dog, cat, bird)
     * 
     * @return type The type of animal
     */
    public String getType() {
        return type;
    }
    
    /**
     * Works with the changeSpecialConditionStatus method in the KennelDemo
     * class to get the right status
     * 
     * @param scan
     *            Takes the scanner from where it is called
     * 
     * @return newStatus The status that the user wants the condition status to
     *         be
     */
    public boolean specialConditionInput(Scanner scan) {
        boolean newStatus = KennelDemo.getBool("What would you like to change the status to?");
        return newStatus;
    }
    
    /**
     * What's its favourite food?
     * 
     * @param food
     *            The food it likes
     */
    public void setFavouriteFood(String food) {
        favFood = food;
    }

    /**
     * The food the animal likes to eat
     * 
     * @return The food
     */
    public String getFavouriteFood() {
        return favFood;
    }
    
    /**
     * Setting the feeds per day for the animal
     * 
     * @param feeds
     *            The number of feeds for the animal
     */
    public void setFeedsPerDay(int feeds) {
        foodPerDay = feeds;
    }

    /**
     * The number of feeds per day the animal is fed
     * 
     * @return The number of feeds per day
     */
    public int getFeedsPerDay() {
        return foodPerDay;
    }
    
    /**
     * Returns a copy of the original owners
     * 
     * @return A copy of the original owners as an array
     */
    public Owner[] getOriginalOwners() {
        Owner[] result = new Owner[originalOwners.size()];
        result = originalOwners.toArray(result);
        return result;
    }

    /**
     * Used for the generic information for saving animals
     * 
     * @param outfile
     *            The PrintWriter used for saving in the KennelDemo class
     */
    public void save(PrintWriter outfile) {
        outfile.println(id);
        outfile.println(name);
        outfile.println(type);
        Owner[] owners = getOriginalOwners();
        outfile.println(owners.length);
        for (Owner o : owners) {
            outfile.println(o.getName());
            outfile.println(o.getPhone());
        }
        outfile.println(foodPerDay);
        outfile.println(favFood);
    }

    /**
     * 
     * @param scan
     */
    public void load(Scanner scan) {
        name = scan.nextLine();
        scan.nextLine();
    }

    /**
     * Used for checking equality with the object parameter
     * 
     * @param obj
     *            Object for part of the equality check
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    /**
     * Returns the generic information of the animal
     * 
     * @return str.append().toString(); Constructs a string of the attributes of
     *         the object
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append("\n ID: " + id).append("\n Name: " + name).append("\n Original owner: " + originalOwners)
                .append("\n Favourite food: " + favFood).append("\n Food per day: " + foodPerDay).toString();
    }
    
}
