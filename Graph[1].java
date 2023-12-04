import java.util.*;

// Graph class implementing GraphInterface with Town as vertices and Road as edges
public class Graph implements GraphInterface<Town, Road> {
    // Adjacency list to store the graph structure
    private Map<Town, List<Road>> adjacencyList;
    private Set<Town> towns;  // Set to store all vertices (Towns)
    private Set<Road> roads;  // Set to store all edges (Roads)
    private Map<Town, Road> shortestMap;  // Map to store shortest paths

    // Constructor to initialize the graph
    public Graph() 
    {
        adjacencyList = new HashMap<>();
        roads = new HashSet<Road>();
        towns = new HashSet<Town>();
    }

    // Get the edge between two towns
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) {
            return null;
        }

        // Check each road to find the matching one
        for (Road road : roads) 
        {
            return new Road(sourceVertex, destinationVertex, (int) road.getWeight(), road.getName()); 
        }

        return null;
    }

    // Add a new edge to the graph
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        // Check if source and destination vertices exist
        if (!adjacencyList.containsKey(sourceVertex) || !adjacencyList.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Source or destination vertices not found in the graph.");
        }

        // Create a new road
        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);

        // Add the road to the adjacency list
        List<Road> sourceEdges = adjacencyList.get(sourceVertex);
        sourceEdges.add(newRoad);

        return newRoad;
    }

    // Add a new vertex to the graph
    @Override
    public boolean addVertex(Town v) {
        // Check if the vertex already exists
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new ArrayList<>());
            return true;
        }
        return false;
    }

    // Check if an edge exists between two towns
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        Road road = getEdge(sourceVertex, destinationVertex);
        return road != null;
    }

    // Check if a vertex exists in the graph
    @Override
    public boolean containsVertex(Town v) {
        return adjacencyList.containsKey(v);
    }

    // Get the set of all edges in the graph
    @Override
    public Set<Road> edgeSet() {
        Set<Road> edges = new HashSet<>();
        for (List<Road> roads : adjacencyList.values()) {
            edges.addAll(roads);
        }
        return edges;
    }

    // Get the set of all edges connected to a specific vertex
    @Override
    public Set<Road> edgesOf(Town vertex) {
        List<Road> roads = adjacencyList.get(vertex);
        return new HashSet<>(roads);
    }

    // Remove an edge from the graph
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        List<Road> sourceEdges = adjacencyList.get(sourceVertex);
        Road roadToRemove = new Road(sourceVertex, destinationVertex, weight, description);

        // Remove the edge if found
        if (sourceEdges != null && sourceEdges.remove(roadToRemove)) {
            return roadToRemove;
        }

        return null;
    }

    // Remove a vertex from the graph
    @Override
    public boolean removeVertex(Town v) {
        List<Road> edgesToRemove = adjacencyList.remove(v);
        return edgesToRemove != null;
    }

    // Get the set of all vertices in the graph
    @Override
    public Set<Town> vertexSet() {
        return adjacencyList.keySet();
    }

    // Find the shortest path between two towns using Dijkstra's algorithm
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);

        // Reconstruct the path from destination to source
        List<Road> path = new ArrayList<>();
        Town currentVertex = destinationVertex;

        while (previousVertex.containsKey(currentVertex)) {
            Town previous = previousVertex.get(currentVertex);
            path.add(0, getEdge(previous, currentVertex));
            currentVertex = previous;
        }

        // Convert the path to a list of strings
        ArrayList<String> result = new ArrayList<>();
        for (Road road : path) {
            result.add(road.toString());  // Assuming Road has a toString method
        }

        return result;
    }

    // Dijkstra's algorithm to find the shortest paths from a source vertex
    private Map<Town, Integer> distance;
    private Map<Town, Town> previousVertex;

    @Override
    public void dijkstraShortestPath(Town sourceVertex) 
    {
        distance = new HashMap<>();
        previousVertex = new HashMap<>();

        PriorityQueue<Town> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        distance.put(sourceVertex, 0);
        priorityQueue.offer(sourceVertex);

        
    }
}
