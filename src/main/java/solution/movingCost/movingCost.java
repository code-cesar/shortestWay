package solution.movingCost;

import Exception.SolutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class movingCost {
    private static final int AMOUNT_TERRAIN = 4;

    public static HashMap<Character, Integer> get(Reader fileFlow, String creature) throws SolutionException {
        HashMap<Character, Integer> terrainAndCost = new HashMap<>();
        BufferedReader readBuf = null;
        try {
            String lineFile;
            readBuf = new BufferedReader(fileFlow);
            while ((lineFile = readBuf.readLine()) != null) {
                String[] creatureAndTerrain = getParameters(lineFile, ":");
                if (!creature.equals(creatureAndTerrain[0])) continue;
                String[] costAndTerrain = getParameters(creatureAndTerrain[1], ",");
                terrainAndCost.put(costAndTerrain[0].charAt(0), Integer.parseInt(costAndTerrain[1]));
            }
            if (terrainAndCost.size() < AMOUNT_TERRAIN || terrainAndCost.size()  > AMOUNT_TERRAIN)
                throw new SolutionException("Количество местности должно быть " + AMOUNT_TERRAIN);
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
