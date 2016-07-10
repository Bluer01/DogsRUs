/**
 * Used for the owner of the animals
 * 
 * @author Lynda Thomas and Chris Loftus
 * @co-author Daniel Bloor
 * @version 1.8 (19th April 2015)
 */
public class Owner {
    private String name;
    private String phone;

    /**
     * Constructor for the owner
     * 
     * @param n
     *            Name of the owner
     * @param p
     *            Phone number of the owner
     */
    public Owner(String n, String p) {
        name = n;
        phone = p;
    }

    /**
     * Sets the name of the owner
     * 
     * @param name
     *            The name of the owner
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of the owner
     * 
     * @return name The name of the owner
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the owner
     * 
     * @param phone
     *            The phone number of the owner
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the phone number of the owner
     * 
     * @return phone The phone number of the owner
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Equality check on the owner with the other object
     * 
     * @param The
     *            other owner to compare against.
     */
    @Override
    public boolean equals(Object obj) { 
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Owner other = (Owner) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    /**
     * Builds and returns a string for printing owner information
     * 
     * @return str.append().toString() The string with appended variables
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append(name).append(" ").append(phone).toString();
    }

}
