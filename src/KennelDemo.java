import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @co-author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */
public class KennelDemo {
    private String filename; // Holds the name of the file
    private Kennel kennel; // Holds the kennel
    private static Scanner scan; // So we can read from keyboard

    /**
     * Starting the application
     */
    private KennelDemo() {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information: ");
        filename = scan.next();
        kennel = new Kennel();
    }

    /**
     * initialise() method runs from the main and reads from a file
     */
    private void initialise() {
        System.out.println("Using file " + filename);

        try (FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                Scanner infile = new Scanner(br)) {

            // Setting up the kennel details
            String kennelName = infile.nextLine();
            int kennelSize = infile.nextInt();
            infile.nextLine();
            kennel.setCapacity(kennelSize);

            int numAnimals = infile.nextInt();
            infile.nextLine();
            kennel.setName(kennelName);

            // Setting up the animals in the kennel
            for (int i = 0; i < numAnimals; i++) {
                String id = infile.nextLine();
                String animalName = infile.nextLine();
                String animalType = infile.nextLine();

                int numOwners = infile.nextInt();
                infile.nextLine();
                ArrayList<Owner> owners = new ArrayList<>();
                for (int oCount = 0; oCount < numOwners; oCount++) {
                    String name = infile.nextLine();
                    String phone = infile.nextLine();
                    Owner owner = new Owner(name, phone);
                    owners.add(owner);
                }
                int feedsPerDay = infile.nextInt();
                infile.nextLine();
                String favFood = infile.nextLine();

                /*
                 * Different animal types have different special conditions to
                 * consider in addition to the different class that you're
                 * making an object of, so the switch statement is useful to
                 * deal with those assignments
                 */
                switch (animalType) {
                case "dog":
                    boolean likesBones = infile.nextBoolean();
                    infile.nextLine();
                    boolean needsWalks = infile.nextBoolean();
                    if (infile.hasNextLine()) { // Needed to prevent the
                                                // application from crashing if
                                                // it
                        infile.nextLine(); // tries to take the next line from
                                           // the end of the file
                    }
                    Dog dog = new Dog(id, animalName, owners, likesBones, needsWalks, favFood, feedsPerDay);
                    kennel.addAnimal(dog);
                    break;
                case "cat":
                    boolean sharesRun = infile.nextBoolean();
                    if (infile.hasNextLine()) {
                        infile.nextLine();
                    }
                    Cat cat = new Cat(id, animalName, owners, sharesRun, favFood, feedsPerDay);
                    kennel.addAnimal(cat);
                    break;
                case "bird":
                    boolean wingsClipped = infile.nextBoolean();
                    if (infile.hasNextLine()) {
                        infile.nextLine();
                    }
                    Bird bird = new Bird(id, animalName, owners, wingsClipped, favFood, feedsPerDay);
                    kennel.addAnimal(bird);
                    break;
                }

                /*
                 * Used to sort the animals (by name, alphabetically) in the
                 * kennel (requires setting the Animal class up as comparable
                 * with appropriate compareTo() method for specified field.
                 */
                Collections.sort(kennel.animals);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + " does not exist. Assuming first use and an empty file."
                    + " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /**
     * The main menu method
     */
    private void runMenu() {
        String response;
        do {
            printMenu();
            scan = new Scanner(System.in);
            response = getString("What would you like to do?: ").toUpperCase();
            switch (response) {
            case "1":
                runAnimalMenu();
                break;
            case "2":
                setKennelName();
                break;
            case "3":
                setKennelCapacity();
                break;
            case "Q":
                break;
            default:
                System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    /**
     * Prints the main menu options
     */
    private void printMenu() {
        System.out.println("1 - Manage animals");
        System.out.println("2 - Set up Kennel name");
        System.out.println("3 - Set kennel capacity");
        System.out.println("q - Quit");
    }

    /**
     * Runs the 'manage animals' menu
     */
    private void runAnimalMenu() {
        String response;
        do {
            printAnimalMenu();
            scan = new Scanner(System.in);
            response = getString("What would you like to do?: ").toUpperCase();
            switch (response) {
            case "1":
                admitAnimal();
                Collections.sort(kennel.animals); // Need to sort animals again
                                                  // after admitting a new one
                break;
            case "2":
                printAll();
                break;
            case "3":
                kennel.obtainAnAnimalType();
                break;
            case "4":
                kennel.obtainTypeWithRecognisedAttribute();
                break;
            case "5":
                searchForAnimal();
                break;
            case "6":
                runAnimalInfoMenu();
                break;
            case "7":
                removeAnimal();
                break;
            }
        } while (!(response.equals("B")));
    }

    /**
     * Prints the 'manage animals' menu
     */
    private void printAnimalMenu() {
        System.out.println("1 - Admit new animal");
        System.out.println("2 - Print all animals");
        System.out.println("3 - Print all of specific animal type");
        System.out.println("4 - Print all of type with recognised attribute " + "(likes bones, shares run, etc.)");
        System.out.println("5 - Search for an animal");
        System.out.println("6 - Change animal info");
        System.out.println("7 - Remove an animal");
        System.out.println("B - Back");
    }

    /**
     * Used to set the name of the kennel
     */
    private void setKennelName() {
        String kennelName = getString("Enter desired kennel name: ");
        kennel.setName(kennelName);
        System.out.println("Kennel name is now: " + kennel.getName());
    }

    /**
     * Used to set the amount of animals that the kennel can support
     */
    private void setKennelCapacity() {
        int max = getInt("Enter max number of animals: ");
        if (!(kennel.getNumOfAnimals() > max)) {
            kennel.setCapacity(max);
            System.out.println("Kennel capacity is now: " + kennel.getCapacity());
        } else {
            System.out.println("Kennel capacity cannot be " + "fewer than the number of animals in the kennel; "
                    + "try again with more appropriate value");
        }
    }

    /**
     * Goes through the process of admitting a new animal
     */
    private void admitAnimal() {
        boolean uniqueID = false;
        String id;
        boolean correctType;
        correctType = false;
        Animal newAnimal = null;
        boolean specialCondition = false;
        // Used for an additional special condition so far only used for dogs
        // (needing walks)
        boolean specialCondition2 = false;
        String name = getStringOnlyAlpha("What is the animal's name?");

        do {
            id = getStringOnlyNum("What is " + name + "'s ID?");
            if (null == findAnimal(id)) {
                uniqueID = true;
            } else {
                System.out.println("ID not available; please provide unique ID");
            }
        } while (false == uniqueID);

        ArrayList<Owner> owners = getOwners(); // Uses a separate method for
                                               // getting the owner(s)
        String fav;
        fav = getStringOnlyAlpha("What is " + name + "'s favourite food?");
        int numTimes;

        /*
         * do-while loop for the feeds Ensures a sensible number
         */
        do {
            numTimes = getInt("How many times is " + name
                    + " fed a day? (as a number, and between 1 and 5 feeds per day)");
            if (!(numTimes >= 1 && numTimes <= 5)) {
                System.out.println("Feeds must be between at least 1 and at most 5; "
                        + "please insert a value of either or between those 2 values.");
            }
        } while ((!(numTimes >= 1 && numTimes <= 5)));

        /*
         * A loop to make sure that the user puts in the correct type for an
         * animal, and goes through until they put a correct one in. It then
         * sends the information to the admitAnimalType method for assigning the
         * boolean to the special condition, and makes the new animal with the
         * information gathered
         */
        do {
            String type = getStringOnlyAlpha("What type of animal is " + name + "? (dog, cat, bird)").toLowerCase();
            switch (type) {
            case "dog":
                correctType = true;
                specialCondition = admitAnimalType(type, name);
                // Needed for the additional condition (needing walks)
                specialCondition2 = admitAnimalType2(type, name);
                newAnimal = new Dog(id, name, owners, specialCondition, specialCondition2, fav, numTimes);
                break;
            case "cat":
                correctType = true;
                specialCondition = admitAnimalType(type, name);
                newAnimal = new Cat(id, name, owners, specialCondition, fav, numTimes);
                break;
            case "bird":
                correctType = true;
                specialCondition = admitAnimalType(type, name);
                newAnimal = new Bird(id, name, owners, specialCondition, fav, numTimes);
                break;
            default:
                System.out.println("Sorry, we don't take those animals; please choose dog, cat, or bird.");
            }
        } while (!correctType);
        kennel.addAnimal(newAnimal);

        System.out.println("Welcome to the kennel, " + name + "!");

        // Animals need to be sorted again after adding a new animal
        Collections.sort(kennel.animals);
    }

    /**
     * Handles each animal type's special conditions for the admitAdnimal
     * method's specialCondition variable, for admitting the animal correctly
     */
    private boolean admitAnimalType(String type, String name) {
        boolean specialCondition = false;
        switch (type) {
        case "dog":
            String likesBones;
            likesBones = getStringOnlyAlpha("Does " + name + " like bones? (Y/N)").toUpperCase();
            if (likesBones.equals("Y") || likesBones.equals("N")) {
                if (likesBones.equals("Y")) {
                    specialCondition = true;
                }
                break;
            } else {
                System.err.println("Please provide a 'Y' or 'N' answer");
                admitAnimalType(type, name);
            }
        case "cat":
            String sharesRun;
            sharesRun = getStringOnlyAlpha("Does " + name + " share a run? (Y/N)").toUpperCase();
            if (sharesRun.equals("Y") || sharesRun.equals("N")) {
                if (sharesRun.equals("Y")) {
                    specialCondition = true;
                }
                break;
            } else {
                System.err.println("Please provide a 'Y' or 'N' answer");
                admitAnimalType(type, name);
            }
        case "bird":
            String wingsClipped;
            wingsClipped = getStringOnlyAlpha("Are " + name + "'s wings clipped? (Y/N)").toUpperCase();
            if (wingsClipped.equals("Y") || wingsClipped.equals("N")) {
                if (wingsClipped.equals("Y")) {
                    specialCondition = true;
                }
                break;
            } else {
                System.err.println("Please provide a 'Y' or 'N' answer");
                admitAnimalType(type, name);
            }
        }
        return specialCondition;
    }

    /**
     * Needed, so far, for the dog's 2nd potential special condition of needing
     * walks
     */
    private boolean admitAnimalType2(String type, String name) {
        boolean specialCondition2 = false;
        switch (type) {
        case "dog":
            String needsWalks;
            needsWalks = getStringOnlyAlpha("Does " + name + " need walks? (Y/N)").toUpperCase();
            if (needsWalks.equals("Y") || needsWalks.equals("N")) {
                if (needsWalks.equals("Y")) {
                    specialCondition2 = true;
                }
                break;
            } else {
                System.err.println("Please provide a 'Y' or 'N' answer");
                admitAnimalType(type, name);
            }
        }
        return specialCondition2;
    }

    /**
     * printAll() method runs from the main and prints status
     */
    private void printAll() {
        System.out.println(kennel);
    }

    /**
     * Asks for the animal ID to get the animal, then prints its information
     */
    private void searchForAnimal() {
        String id = getStringOnlyNum("Please provide animal ID: ");
        Animal animal = kennel.searchAnimals(id);
        if (animal != null) {
            System.out.println(animal.toString());
        } else {
            System.out.println("Could not find animal with ID of: " + id);
        }
    }

    /**
     * Runs the animal information menu, used for asking the user what they want
     * to change about an animal
     */
    private void runAnimalInfoMenu() {
        String response;
        String animalID;

        // Passed to the methods in the do-while loop
        animalID = getStringOnlyNum("Which animal (ID)?");
        if (null == findAnimal(animalID)) {
            System.out.println("Could not find animal with ID of: " + animalID);
        } else {
            do {
                Animal animal;
                printChangeAnimalInfoMenu(animalID);
                scan = new Scanner(System.in);
                response = getString("What would you like to change about " + findAnimal(animalID).getName() + "? \n")
                        .toUpperCase();
                animal = kennel.searchAnimals(animalID);
                switch (response) {
                case "1":
                    changeAnimalName(animalID);
                    break;
                case "2":
                    changeSpecialConditionStatus(scan, animal);
                    break;
                case "3":
                    changeAnimalFeedsPerDay(animalID);
                    break;
                case "4":
                    changeAnimalFavFood(animalID);
                    break;
                case "B":
                    break;
                default:
                    System.err.println("Input not valid; please try again");
                }
            } while (!(response.equals("B")));
        }
    }

    /**
     * Prints the menu for changing animal information
     * 
     * @param animalID
     *            Allows me to get the name of the animal to use in a string
     */
    private void printChangeAnimalInfoMenu(String animalID) {
        // I use findAnimal so that I can personalise the output to the animal
        System.out.println("1 - Name");
        System.out.println("2 - Special condition status(es)");
        System.out.println("3 - Feeds per day");
        System.out.println("4 - Favourite food");
        System.out.println("b - Back");
    }

    /**
     * Gets the ID of the animal that the user wants removed, and then calls the
     * removeAnimal method in the kennel
     */
    private void removeAnimal() {
        String animalToBeRemoved;
        animalToBeRemoved = getStringOnlyNum("Please enter ID of animal to be removed: ");
        kennel.removeAnimal(animalToBeRemoved);
    }

    /**
     * Changes the animal's name (per user input)
     * 
     * @param animal
     *            Provides the animal for the findAnimal method
     */
    private void changeAnimalName(String animal) {
        String newName = getStringOnlyAlpha("What would you like " + findAnimal(animal).getName()
                + "'s new name to be?");
        findAnimal(animal).setName(newName);
        Collections.sort(kennel.animals); // New animal name makes sorting again
                                          // necessary
    }

    /**
     * Takes the scanner and animal, so that it can use them in the function in
     * the animal type's respective class method and change the special
     * conditions
     * 
     * @param scan
     *            Used to pass into the changeSpecialConditionStatus method
     * @param animal
     *            The animal that you're using the method of
     */
    private void changeSpecialConditionStatus(Scanner scan, Animal animal) {
        switch (animal.getType()) {
        case "dog":
            ((Dog) animal).changeSpecialConditionStatus(scan); // Casting is
                                                               // required for
                                                               // the specific
            break; // subclass of the animal here
        case "cat":
            ((Cat) animal).changeSpecialConditionStatus(scan);
            break;
        case "bird":
            ((Bird) animal).changeSpecialConditionStatus(scan);
            break;
        }
    };

    /**
     * Used to change the amount of feeds per day of an animal
     * 
     * @param animal
     *            Animal to change the feeds per day off
     */
    private void changeAnimalFeedsPerDay(String animal) {
        int newFeedsPerDay;
        do {
            newFeedsPerDay = getInt("What is " + findAnimal(animal).getName() + "'s new number of feeds per day?");
            if (!(newFeedsPerDay >= 1 && newFeedsPerDay <= 5)) {
                System.out.println("Feeds must be between at least 1 and at most 5; "
                        + "please insert a value of either or between those 2 values.");
            }
            // A window for the allowed feeds amount to what seems appropriate
        } while ((!(newFeedsPerDay >= 1 && newFeedsPerDay <= 5)));
        findAnimal(animal).setFeedsPerDay(newFeedsPerDay);
    }

    /**
     * Used to change a particular animal's favourite food
     * 
     * @param animal
     *            The animal to change the favourite food of
     */
    private void changeAnimalFavFood(String animal) {
        String newFavFood = getStringOnlyAlpha("What is " + findAnimal(animal).getName() + "'s new favourite food?");
        findAnimal(animal).setFavouriteFood(newFavFood);
    }

    /**
     * Used to find an animal by its ID
     * 
     * @param toFind
     *            The animal to find from the id string
     * @return foundAnimal The animal that has been found
     */
    private Animal findAnimal(String toFind) {
        Animal foundAnimal = null;
        for (Animal a : kennel.animals) {
            if (a.id.equals(toFind)) {
                foundAnimal = a;
                break;
            }
        }
        return foundAnimal;
    }

    /**
     * getOwners sorts out the owners part of admitting animals. It takes input
     * from the user to find out the owner's name and phone number and adds it
     * to an owners array for the animal
     */
    private ArrayList<Owner> getOwners() {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        String answer = "";
        do {
            answer = "";
            String ownName = getStringOnlyAlpha("Please enter the owner's name and phone number on separate "
                    + "lines (type the name, press enter, and then type the next input): ");
            String ownPhone = getStringOnlyNum("");
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            do {
                answer = getString("Another owner? (Y/N)").toUpperCase();
                if (!(answer.equals("Y") || answer.equals("N"))) {
                    System.err.println("Please provide a 'Y' or 'N' answer \n");
                }
            } while (!(answer.equals("Y") || answer.equals("N")));
        } while (!answer.equals("N"));

        return owners;
    }

    /**
     * getString is used for ensuring that the user doesn't insert a blank
     * value, and gets rid of the unnecessary whitespace before and after the
     * string.
     */
    public static String getString(String message) {
        boolean correct = false;
        String result = "";
        do {
            System.out.println(message);
            result = scan.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Please input non-blank value");
            } else {
                correct = true;
            }
        } while (!correct);
        return result;
    }

    public static String getStringOnlyNum(String message) {
        boolean correct = false;
        String result = "";
        do {
            System.out.println(message);
            result = scan.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Please input non-blank value");
            } else if (false == containsOnlyNum(result)) {
                System.err.println("Incorrect input; no non-integer characters allowed");
            } else {
                correct = true;
            }
        } while (!correct);
        return result;
    }

    public static String getStringOnlyAlpha(String message) {
        boolean correct = false;
        String result = "";
        do {
            System.out.println(message);
            result = scan.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Please input non-blank value");
            } else if (false == containsOnlyAlpha(result)) {
                System.err.println("Incorrect input; no non-english alphabet characters allowed");
            } else {
                correct = true;
            }
        } while (!correct);
        return result;
    }

    /**
     * Ensures that the user doesn't use a negative value or non-integer value
     */
    public static int getInt(String message) {
        boolean correct = false;
        int result = 0;
        do {
            System.out.println(message);
            try {
                result = scan.nextInt();
                if (result < 0) {
                    System.out.println("Please provide a non-negative value.");
                } else {
                    scan.nextLine();
                    correct = true;
                }
            } catch (InputMismatchException ime) {
                System.err.println("Please enter an number");
                scan.nextLine();
            }
        } while (!correct);
        return result;
    }

    /**
     * Helps to handle the boolean inputs, by handling the exception and asking
     * the user again for the correct value
     */
    public static boolean getBool(String message) {
        boolean correct = false;
        boolean result = false;
        do {
            System.out.println(message);
            try {
                result = scan.nextBoolean();
            } catch (InputMismatchException ime) {
                System.err.println("Please enter true or false");
                scan.nextLine();
            }
            scan.nextLine();
            correct = true;
        } while (!correct);
        return result;
    }

    public static boolean containsOnlyAlpha(String input) {
        if (input.matches("[a-zA-Z ]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsOnlyNum(String input) {
        if (input.matches(("[0-9]+"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * save() method runs from the main and writes back to file
     */
    private void save() {
        try (FileWriter fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter outfile = new PrintWriter(bw);) {

            outfile.println(kennel.getName());
            outfile.println(kennel.getCapacity());
            outfile.println(kennel.getNumOfAnimals());

            /*
             * Loops through the kennel's animals, checking the animal
             * subclass/type, and then uses the appropriate save method from the
             * subclass
             */
            for (Animal a : kennel.animals) {
                if (a instanceof Dog) {
                    ((Dog) a).save(outfile, (Dog) a);
                } else if (a instanceof Cat) {
                    ((Cat) a).save(outfile, (Cat) a);
                } else if (a instanceof Bird) {
                    ((Bird) a).save(outfile, (Bird) a);
                }
            }
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + filename);
        }
    }

    /**
     * Main running method of the program
     * 
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("**********Welcome***********");
        KennelDemo demo = new KennelDemo();
        demo.initialise();
        demo.runMenu();
        demo.printAll();
        demo.save();
        System.out.println("***********Thanks for visiting**********");
    }
}
