package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> mapPQ;
    private List<Vertex> solution = new ArrayList<>();
    private HashSet<Vertex> visited;
    private HashMap<Vertex, Double> distance;
    private HashMap<Vertex, Vertex> edges;
    private SolverOutcome outcome = SolverOutcome.UNSOLVABLE;
    private double solutionWeight = 0;
    private int numStates = 0;
    private double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        mapPQ = new DoubleMapPQ<>();
        visited = new HashSet<>();
        distance = new HashMap<>();
        edges = new HashMap<>();
        mapPQ.add(start, input.estimatedDistanceToGoal(start, end));
        distance.put(start, 0.0);
        while (mapPQ.size() > 0) {
            Vertex smallest = mapPQ.removeSmallest();
            visited.add(smallest);
            numStates++;
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(smallest);
            for (WeightedEdge<Vertex> v : neighbors) {
                relax(v, input, end);
            }
            if (smallest.equals(end) && sw.elapsedTime() < timeout) {
                solutionWeight = distance.get(end);
                outcome = SolverOutcome.SOLVED;
                solution.add(smallest);
                while (!smallest.equals(start)) {
                    smallest = edges.get(smallest);
                    solution.add(smallest);
                }
                Collections.reverse(solution);
                break;
            } else if (sw.elapsedTime() >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
        }
        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> v, AStarGraph<Vertex> input, Vertex end) {
        Vertex from = v.from();
        Vertex to = v.to();
        double totalDist = distance.get(from) + v.weight();
        double priority = totalDist + input.estimatedDistanceToGoal(to, end);
        if (!distance.containsKey(to)) {
            distance.put(to, totalDist);
            edges.put(to, from);
            mapPQ.add(to, priority);
        }
        if (totalDist < distance.get(to)) {
            distance.replace(to, totalDist);
            edges.replace(to, from);
            if (!mapPQ.contains(to)) {
                mapPQ.add(to, priority);
            } else {
                mapPQ.changePriority(to, priority);
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStates;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
