import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * To model a Kennel - a collection of animals
 * 
 * @author Lynda Thomas and Chris Loftus
 * @co-author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */

public class Kennel {
    private String name;
    ArrayList<Animal> animals;
    private int nextFreeLocation;
    private int capacity;

    /**
     * Creates a kennel with a default size 20
     * 
     * @param maxNoDogs
     *            The capacity of the kennel
     */
    public Kennel() {
        this(20);
    }

    /**
     * Create a kennel
     * 
     * @param maxNoDogs
     *            The capacity of the kennel
     */
    public Kennel(int maxNoAnimals) {
        nextFreeLocation = 0; // no animals in collection at start
        capacity = maxNoAnimals;

        // Might change to make it dynamic
        animals = new ArrayList<Animal>(capacity);
    }

    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the kennel e.g. "DogsRUs"
     * 
     * @param theName
     */
    public void setName(String theName) {
        name = theName;
    }
    
    /**
     * This method gets the value for the name attribute. The purpose of the
     * attribute is: The name of the Kennel e.g. "DogsRUs"
     * 
     * @return String The name of the kennel
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method returns the number of dogs in a kennel
     * 
     * @return int Current number of dogs in the kennel
     */
    public int getNumOfAnimals() {
        return nextFreeLocation;
    }

    /**
     * Set the size of the kennel
     * 
     * @param capacity
     *            The max dogs we can house
     */
    public void setCapacity(int capacity) {
        if (capacity < (animals.size())) {
            System.out.println("Sorry, reducing the " + "current capacity would require " 
                                + "evicting an animal.");
        } else {
            this.capacity = capacity;
        }
    }
    
    /**
     * Maximum capacity of the kennels
     * 
     * @return The max size of the kennel
     */
    public int getCapacity() {
        return capacity;
    }
        
    /**
     * This method is the last part of admitting animals and/or loading them. It
     * checks capacity, and if there is space then it adds the animal to the
     * animals arraylist and puts the next location to put one a space after
     */
    public void addAnimal(Animal theAnimal) {
        if (nextFreeLocation >= capacity) {
            System.out.println("Sorry, kennel is full - cannot add");
            return;
        }
        animals.add(theAnimal);
        nextFreeLocation++;
    }


     // possible issue of printing everything, or just one...?
    public void obtainAnAnimalType() {
        boolean correctType = false;
        do {
            String animalType = KennelDemo.getString("What type of animal would you like the print?").toLowerCase();

            for (Animal a : animals) {
                switch (animalType) {
                case "bird":
                    correctType = true;
                    if (a instanceof Bird) {
                        System.out.println(a.toString());
                    }
                    break;
                case "dog":
                    correctType = true;
                    if (a instanceof Dog) {
                        System.out.println(a.toString());
                    }
                    break;
                case "cat":
                    correctType = true;
                    if (a instanceof Cat) {
                        System.out.println(a.toString());
                    }
                    break;
                default:
                    System.err.println("Please provide appropriate type of animal (dog, cat, bird)");
                }
            }
        } while (!correctType);
    }

    /**
     * Only returns those dogs who like bones
     * 
     * @return An array of dogs of the correct size. If no dogs like bones then
     *         returns an empty array (size 0)
     */
    public void obtainDogsWhoLikeBones() {
        System.out.println("Dogs that like bones: ");
        for (Animal d : animals) {
            if (d instanceof Dog) {
                if (((Dog) d).getLikesBones()) {
                    System.out.println(d);
                }
            }
        }
    }

    public void obtainDogsWhoNeedWalks() {
        System.out.println("Dogs who need walks: \n");
        for (Animal d : animals) {
            if (d instanceof Dog) {
                if (((Dog) d).getNeedsWalks()) {
                    System.out.println(d);
                }
            }
        }
    }

    public void obtainCatsSharingRun() {
        System.out.println("Cats sharing run: \n");
        for (Animal c : animals) {
            if (c instanceof Cat) {
                if (((Cat) c).getSharesRun()) {
                    System.out.println(c);
                }
            }
        }
    }

    public void obtainBirdsWingsClipped() {
        System.out.println("Birds wings clipped: \n");
        for (Animal b : animals) {
            if (b instanceof Bird) {
                if (((Bird) b).getWingsClipped()) {
                    System.out.println(b);
                }
            }
        }
    }

    /**
     * This method prints all of the animals with a specific special condition.
     * For cats and birds, this will automatically go for sharing runs or
     * clipped wings, but dogs require additional input to verify whether the
     * user requires liking bones or needing walks.
     */
    public void obtainTypeWithRecognisedAttribute() {
        boolean correctType = false;
        Scanner scan = new Scanner(System.in);
        do {
            String animalType = KennelDemo.getString("What type of animal?: ").toLowerCase();
            switch (animalType) {
            case "dog":
                correctType = true;
                int which;
                do {
                    which = KennelDemo
                            .getInt("Would you like the print the dogs who like bones ('1'), "
                                    + "or the dogs who need walks ('2')? (Please type 1 or 2): ");
                    if (1 == which) {
                        obtainDogsWhoLikeBones();
                    } else if (2 == which) {
                        obtainDogsWhoNeedWalks();
                    }
                } while (!((1 == which) || (2 == which)));
                break;
            case "cat":
                correctType = true;
                obtainCatsSharingRun();
                break;
            case "bird":
                correctType = true;
                obtainBirdsWingsClipped();
                break;
            default:
                System.err.println("Please provide appropriate type of animal (dog, cat, bird");
            }
        } while (!correctType);
    }

    /**
     * Searches for an animal by its id, and returns the Animal object
     */
    public Animal searchAnimals(String id) {
        Animal result = null;
        for (Animal a : animals) {
            if (a.getID().equals(id)) {
                result = a;
            }
        }
        return result;
    }

    /**
     * This method finds the animal that the user wants to remove by looping
     * through the animals arraylist and matching the id values
     */
    public void removeAnimal(String animal) {
        Animal which = null;
        for (Animal a : animals) {
            if (a.getClass() != null) {
                if (animal.equals(a.id)) {
                    which = a;
                }
            }
        }
            if (which != null) {
                animals.remove(which);
                System.out.println("Removed animal with ID of: " + animal);
                nextFreeLocation--;
            } else
                System.err.println("Cannot remove; animal not in kennel");
    }
    

    /**
     * @return String showing all the information in the kennel
     */
    public String toString() {
        StringBuilder results = new StringBuilder("Data in Kennel '" + name + "' is: \n");
        for (Animal a : animals) {
            results.append(a + "\n");
        }
        return results.toString();
    }

}
