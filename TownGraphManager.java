import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

    private Graph graph;

    public TownGraphManager() {
        this.graph = new Graph();
    }

    // Method to populate the graph from a text file
    public void populateTownGraph(File selectedFile) {
        try {
            Scanner scanner = new Scanner(selectedFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse the line and add towns and roads to the graph
                // Example: town1, town2, 10, RoadName
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String town1 = parts[0];
                    String town2 = parts[1];
                    int weight = Integer.parseInt(parts[2]);
                    String roadName = parts[3];
                    addRoad(town1, town2, weight, roadName);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a road between two towns with the given weight and road name.
     *
     * @param town1     The name of the first town.
     * @param town2     The name of the second town.
     * @param weight    The weight (distance) of the road.
     * @param roadName  The name of the road.
     * @return          True if the road was successfully added, false otherwise.
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        // Ensure that both towns exist in the graph
        if (!graph.containsVertex(source) || !graph.containsVertex(destination)) {
            return false;
        }

        // Delegate to the graph's addEdge method
        return graph.addEdge(source, destination, weight, roadName) != null;
    }

    /**
     * Gets the name of the road connecting two towns.
     *
     * @param town1 The name of the first town.
     * @param town2 The name of the second town.
     * @return      The name of the road, or null if no road exists between the towns.
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        Road road = graph.getEdge(source, destination);

        if (road != null) {
            return road.getName();
        }

        return null;
    }

    /**
     * Adds a town to the graph.
     *
     * @param town The name of the town to be added.
     * @return     True if the town was successfully added, false otherwise.
     */
    @Override
    public boolean addTown(String town) {
        Town newTown = new Town(town);
        return graph.addVertex(newTown);
    }

    /**
     * Gets a town with the given name from the graph.
     *
     * @param name The name of the town to retrieve.
     * @return     The Town object with the specified name, or null if not found.
     */
    @Override
    public Town getTown(String name) {
        return graph.vertexSet().stream()
                .filter(town -> town.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if a town with the given name exists in the graph.
     *
     * @param name The name of the town to check.
     * @return     True if the town exists, false otherwise.
     */
    @Override
    public boolean containsTown(String name) {
        return graph.containsVertex(new Town(name));
    }

    /**
     * Checks if a road connection exists between two towns.
     *
     * @param town1 The name of the first town.
     * @param town2 The name of the second town.
     * @return      True if a road connection exists, false otherwise.
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    /**
     * Gets a list of all road names in the graph, sorted alphabetically.
     *
     * @return An ArrayList containing all road names.
     */
    @Override
    public ArrayList<String> allRoads() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadNames = new ArrayList<>();

        for (Road road : roads) {
            roadNames.add(road.getName());
        }

        roadNames.sort(String::compareTo);
        return roadNames;
    }

    /**
     * Deletes a road connection between two towns with the specified road name.
     *
     * @param town1    The name of the first town.
     * @param town2    The name of the second town.
     * @param roadName The name of the road to delete.
     * @return         True if the road connection was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        // Delegate to the graph's removeEdge method
        Road removedRoad = graph.removeEdge(source, destination, -1, roadName);

        return removedRoad != null;
    }

    /**
     * Deletes a town from the graph.
     *
     * @param town The name of the town to delete.
     * @return     True if the town was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteTown(String town) {
        Town townToRemove = new Town(town);

        // Delegate to the graph's removeVertex method
        return graph.removeVertex(townToRemove);
    }

    /**
     * Gets a list of all town names in the graph, sorted alphabetically.
     *
     * @return An ArrayList containing all town names.
     */
    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = graph.vertexSet();
        ArrayList<String> townNames = new ArrayList<>();

        for (Town town : towns) {
            townNames.add(town.getName());
        }

        townNames.sort(String::compareTo);
        return townNames;
    }

    /**
     * Gets the shortest path between two towns in the graph.
     *
     * @param town1 The name of the source town.
     * @param town2 The name of the destination town.
     * @return      An ArrayList containing the names of towns in the shortest path.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        // Delegate to the graph's shortestPath method
        return graph.shortestPath(source, destination);
    }
}
