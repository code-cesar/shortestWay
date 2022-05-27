package solution;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import Exception.SolutionException;


public class Solution {
    private static final String FILE_MOVING_COST = "movingCost.txt";
    private static final int AMOUNT_TERRAIN = 4;

    public static int getResult(String field, String creature){

        return 0;
    }

    private static HashMap<Character, Integer> getMovingCost(String creature) throws SolutionException {
        HashMap<Character, Integer> terrainAndCost = new HashMap<>();
        try {
            String lineFile;
            BufferedReader readBuf = new BufferedReader(new FileReader(FILE_MOVING_COST));
            while ((lineFile = readBuf.readLine()) != null) {
                String[] creatureAndTerrain = getParameters(lineFile, ":");
                if (!creature.equals(creatureAndTerrain[0])) continue;
                String[] costAndTerrain = getParameters(creatureAndTerrain[1], ",");
                terrainAndCost.put(costAndTerrain[0].charAt(0), Integer.parseInt(costAndTerrain[1]));
            }
            if (terrainAndCost.size() < AMOUNT_TERRAIN) throw new SolutionException("Количество местности должно быть " + AMOUNT_TERRAIN);
        }catch(IOException | NumberFormatException |  IndexOutOfBoundsException e){
            throw new SolutionException("Ошибка при чтении файла ", e.getCause());
        }

        return terrainAndCost;
    }

    private static String[] getParameters(String line, String delimiter) throws SolutionException {
        String[] parameters = line.split(delimiter, 2);
        if (parameters.length < 2)
            throw new SolutionException("В строке нет второго параметра");
        return parameters;
    }

}
