package Solution.Graph;

import Exception.SolutionException;
import Util.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class Graph {

    public static HashMap<Integer, HashMap<Integer, Integer>> initializationGraph(String field, String creature, Reader fileFlow) throws SolutionException {
        if(field.length() != constants.LENGTH_FIELD)throw new SolutionException("Игровое поле должно быть размером: " + constants.LENGTH_FIELD);
        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>();
        HashMap<Character, Integer> terrainAndCost = getCostMoving(fileFlow, creature);
        for(int i = 0; i < constants.LENGTH_FIELD; i ++) {
            HashMap<Integer, Integer> neighbours = new HashMap<>();
            if(i < constants.LENGTH_FIELD - 1) {
                int rightNeighbour = i + 1;
                if (rightNeighbour % 4 != 0){
                    neighbours.put(rightNeighbour, terrainAndCost.get(field.charAt(rightNeighbour)));
                }
                int leftNeighbour = i - 1;
                if (i % 4 != 0 && leftNeighbour != 0){
                    neighbours.put(leftNeighbour, terrainAndCost.get(field.charAt(leftNeighbour)));
                }
                int upstairsNeighbour = i + 4;
                if (upstairsNeighbour < constants.LENGTH_FIELD){
                    neighbours.put(upstairsNeighbour, terrainAndCost.get(field.charAt(upstairsNeighbour)));
                }
                int bottomNeighbour = i - 4;
                if (bottomNeighbour > -1 && bottomNeighbour != 0){
                    neighbours.put(bottomNeighbour, terrainAndCost.get(field.charAt(bottomNeighbour)));
                }
            }
            graph.put(i, neighbours);
        }
        return graph;
    }

    public static HashMap<Character, Integer> getCostMoving(Reader fileFlow, String creature) throws SolutionException {
        HashMap<Character, Integer> terrainAndCost = new HashMap<>();
        BufferedReader readBuf = null;
        creature = creature.toLowerCase();
        try {
            String lineFile;
            readBuf = new BufferedReader(fileFlow);
            while ((lineFile = readBuf.readLine()) != null) {
                String[] creatureAndTerrain = getParameters(lineFile, ":");
                if (!creature.equals(creatureAndTerrain[0].toLowerCase())) continue;
                String[] costAndTerrain = getParameters(creatureAndTerrain[1], ",");
                terrainAndCost.put(costAndTerrain[0].toUpperCase().charAt(0), Integer.parseInt(costAndTerrain[1]));
            }
            if (terrainAndCost.size() != constants.AMOUNT_TERRAIN)
                throw new SolutionException("Количество местности должно быть " + constants.AMOUNT_TERRAIN);
        }catch(IOException | NumberFormatException e){
            throw new SolutionException("Ошибка при чтении файла ", e.getCause());
        }finally {
            try {
                if(readBuf != null)readBuf.close();
                if(fileFlow != null)fileFlow.close();
            } catch (IOException e) {
                throw new SolutionException("Ошибка при закрытии потока ", e.getCause());
            }
        }
        return terrainAndCost;
    }

    public static String[] getParameters(String line, String delimiter) throws SolutionException {
        String[] parameters = line.split(delimiter, 2);
        if (parameters.length < 2)
            throw new SolutionException("В строке нет второго параметра");
        return parameters;
    }

}
