/**
 * The Road class represents a road connecting two towns in an undirected graph.
 * It implements the Comparable interface to allow for sorting based on source,
 * destination, and distance.
 */
public class Road implements Comparable<Road> {
    
    // Instance variables to store information about the road
    private Town source;
    private Town destination;
    private int degrees;
    private String name;

    /**
     * Constructs a new Road object with the specified source, destination,
     * distance, and name.
     *
     * @param source the starting town of the road
     * @param destination the ending town of the road
     * @param degrees the distance or weight of the road
     * @param name the name or identifier of the road
     */
    public Road(Town source, Town destination, int degrees, String name) {
        this.source = source;
        this.destination = destination;
        this.degrees = degrees;
        this.name = name;
    }

    /**
     * Gets the source town of the road.
     *
     * @return the source town
     */
    public Town getSource() {
        return source;
    }

    /**
     * Sets the source town of the road.
     *
     * @param source the new source town
     */
    public void setSource(Town source) {
        this.source = source;
    }

    /**
     * Gets the destination town of the road.
     *
     * @return the destination town
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Sets the destination town of the road.
     *
     * @param destination the new destination town
     */
    public void setDestination(Town destination) {
        this.destination = destination;
    }

    /**
     * Gets the distance or weight of the road.
     *
     * @return the distance or weight
     */
    public double getWeight() {
        return degrees;
    }

    /**
     * Sets the distance or weight of the road.
     *
     * @param degrees the new distance or weight
     */
    public void setWeight(int degrees) {
        this.degrees = degrees;
    }

    /**
     * Gets the name or identifier of the road.
     *
     * @return the name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name or identifier of the road.
     *
     * @param name the new name of the road
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares two roads based on source, destination, and distance.
     *
     * @param otherRoad the road to compare with
     * @return a negative integer, zero, or a positive integer as this road is
     *         less than, equal to, or greater than the specified road
     */
    @Override
    public int compareTo(Road otherRoad) {
        // Since this is an undirected graph, compare based on source, destination, and distance
        int sourceComparison = source.compareTo(otherRoad.source);
        if (sourceComparison == 0) {
            sourceComparison = destination.compareTo(otherRoad.destination);
        }

        if (sourceComparison == 0) {
            // If source and destination are the same, compare based on distance
            return Double.compare(degrees, otherRoad.degrees);
        }

        return sourceComparison;
    }

    /**
     * Returns a string representation of the road.
     *
     * @return a string representation of the road
     */
    @Override
    public String toString() {
        return "Road{" +
                "source=" + source +
                ", destination=" + destination +
                ", distance=" + degrees +
                ", name='" + name + '\'' +
                '}';
    }
}
