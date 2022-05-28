package solution;
import Util.constants;
import solution.graph.Graph;
import Exception.SolutionException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class Solution {
    public static boolean[] processedNode = null;

    public static int getResult(String field, String creature){
        int numberShortestWay = 0;
        try {
            HashMap<Integer, HashMap<Integer, Integer>> graphHandel = Graph.initializationGraph(
                    field.toUpperCase(),
                    creature.toLowerCase(),
                    new FileReader(constants.FILE_MOVING_COST));
            numberShortestWay = getShortestWayToLastNode(graphHandel);
        } catch (SolutionException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return numberShortestWay;
    }

    private static int getShortestWayToLastNode(HashMap<Integer, HashMap<Integer, Integer>> graph) throws SolutionException {
        HashMap<Integer, Integer> tableCostsStartNode = createTableCosts(graph.get(0));
        processedNode = new boolean[constants.LENGTH_FIELD];
        int node = findLowestCost(tableCostsStartNode);
        while(node != constants.INVALID_FIND_NODE){
            int cost = tableCostsStartNode.get(node);
            HashMap<Integer, Integer> neighbors = graph.get(node);
            for (HashMap.Entry<Integer, Integer> entry : neighbors.entrySet()) {
                int nodeNeighbor = entry.getKey(),
                        costNeighbor = entry.getValue();
                int newCost = cost + costNeighbor;
                if(tableCostsStartNode.get(nodeNeighbor) > newCost){
                    tableCostsStartNode.put(nodeNeighbor, newCost);
                }
            }
            processedNode[node] = true;
            node = findLowestCost(tableCostsStartNode);
        }
        return tableCostsStartNode.get(constants.LENGTH_FIELD - 1);
    }

    public static HashMap<Integer, Integer> createTableCosts (HashMap<Integer, Integer> node){
        HashMap<Integer, Integer> tableCosts = new HashMap<>(node);
        for(int i = 1; i < constants.LENGTH_FIELD; i ++){
            tableCosts.putIfAbsent(i, Integer.MAX_VALUE);
        }
        return tableCosts;
    }

    public static int findLowestCost(HashMap<Integer, Integer> tableCosts) throws SolutionException {
        if(processedNode == null)throw new SolutionException("Список обработанных узлов не задан");
        int lowestCost = Integer.MAX_VALUE;
        int lowestNodeInTable = constants.INVALID_FIND_NODE;
        for (HashMap.Entry<Integer, Integer> entry : tableCosts.entrySet()) {
            int node = entry.getKey(),
                    cost = entry.getValue();
            if(node >= constants.LENGTH_FIELD)throw new SolutionException("Узел " + node + " не может быть обработан");
            if(cost < lowestCost && !processedNode[node]){
                lowestCost = cost;
                lowestNodeInTable = node;
            }
        }
        return lowestNodeInTable;
    }


}
