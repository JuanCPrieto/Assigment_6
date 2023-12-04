import java.util.ArrayList;
import java.util.List;

/**
 * Represents a town with a name and a list of adjacent towns.
 * Implements the Comparable interface for sorting purposes.
 */
public class Town implements Comparable<Town> {

    private String name;
    private List<Town> adjacentTowns;

    // Constructors

    /**
     * Constructs a Town object with the given name and an empty list of adjacent towns.
     * @param name The name of the town.
     */
    public Town(String name) {
        this.name = name;
        this.adjacentTowns = new ArrayList<>();
    }

    /**
     * Constructs a Town object by copying the properties of another Town.
     * @param templateTown The town to be used as a template for copying properties.
     */
    public Town(Town templateTown) {
        this.name = templateTown.getName();
        this.adjacentTowns = templateTown.getAdjacentTowns();
    }

    // Getter and Setter for name

    /**
     * Gets the name of the town.
     * @return The name of the town.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the town.
     * @param name The new name for the town.
     */
    public void setName(String name) {
        this.name = name;
    }

    // Getter for adjacentTowns

    /**
     * Gets the list of adjacent towns.
     * @return The list of adjacent towns.
     */
    public List<Town> getAdjacentTowns() {
        return adjacentTowns;
    }

    /**
     * Adds an adjacent town to the list.
     * @param town The town to be added as adjacent.
     */
    public void addAdjacentTown(Town town) {
        adjacentTowns.add(town);
    }

    /**
     * Removes an adjacent town from the list.
     * @param town The town to be removed from the list of adjacent towns.
     */
    public void removeAdjacentTown(Town town) {
        adjacentTowns.remove(town);
    }

    // toString method to represent the Town object as a string

    /**
     * Returns a string representation of the Town object.
     * @return A string representation of the Town object.
     */
    @Override
    public String toString() {
        return "Town{" +
                "name='" + name + '\'' +
                ", adjacentTowns=" + adjacentTowns +
                '}';
    }

    // Implementation of the compareTo method from the Comparable interface

    /**
     * Compares this town with another town based on their names.
     * @param otherTown The town to be compared with.
     * @return A negative integer, zero, or a positive integer if this town is less than, equal to, or greater than the other town.
     */
    @Override
    public int compareTo(Town otherTown) {
        return this.name.compareTo(otherTown.getName());
    }

    // Implementation of the hashCode method

    /**
     * Generates a hash code for the Town object based on its name.
     * @return The hash code for the Town object.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    // Implementation of the equals method

    /**
     * Checks if this town is equal to another object.
     * Two towns are considered equal if they have the same name.
     * @param obj The object to be compared with.
     * @return True if the towns are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Town otherTown = (Town) obj;
        return this.name.equals(otherTown.getName());
    }
}