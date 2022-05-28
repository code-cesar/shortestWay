package solution.graph;
import Exception.SolutionException;
import Util.constants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    @Test
    void delimiter() throws SolutionException {
        String[] testHandR = Graph.getParameters("H:R", ":");
        assertEquals("H",testHandR[0]);
        assertEquals("R",testHandR[1]);

        testHandR = Graph.getParameters("H,R", ",");
        assertEquals("H",testHandR[0]);
        assertEquals("R",testHandR[1]);
    }

    @Test
    void negativeNoneTwoParametrSolutionException() {
        assertThrows(SolutionException.class, () -> {
            Graph.getParameters("H:", ",");
        });
    }

    @Test
    void getMovingCostLine() throws IOException, SolutionException {
        String initialStr = "Human:S,1\n" +
                "Human:T,2\n" +
                "Human:X,3\n" +
                "Human:A,4\n";
        Reader readBuf = new StringReader(initialStr);
        HashMap<Character, Integer> terrainAndCost = Graph.getCostMoving(readBuf, "Human");
        assertTrue(terrainAndCost.containsKey('S'));
        assertTrue(terrainAndCost.containsKey('T'));
        assertTrue(terrainAndCost.containsKey('X'));
        assertTrue(terrainAndCost.containsKey('A'));

        assertEquals(1,terrainAndCost.get('S'));
        assertEquals(2,terrainAndCost.get('T'));
        assertEquals(3,terrainAndCost.get('X'));
        assertEquals(4,terrainAndCost.get('A'));
        readBuf.close();
    }

    @Test
    void negativeNoneAmountTerrain() throws IOException {
        String initialStr = "Human:S,1\n" +
                "Human:T,2\n" +
                "Human:X,3\n";
        Reader readBuf = new StringReader(initialStr);
        assertThrows(SolutionException.class, () -> {
            Graph.getCostMoving(readBuf, "Human");
        });
        readBuf.close();
    }

    @Test
    void getGraph() throws IOException, SolutionException {
        String initialStr = "Human:S,1\n" +
                "Human:T,2\n" +
                "Human:W,3\n" +
                "Human:P,4\n";

        Reader readBuf = new StringReader(initialStr);
        HashMap<Integer, HashMap<Integer, Integer>> graphHandel = Graph.initializationGraph(
                "STWSWTPPTPTTPWPP",
                "Human",
                readBuf
        );
        assertEquals(constants.LENGTH_FIELD,graphHandel.size());
        for(int i = 0; i < constants.LENGTH_FIELD; i ++){
            assertTrue(graphHandel.containsKey(i));
        }
        readBuf.close();
    }

    @Test
    void negativeGetGraphLowLength() throws IOException {

        String initialStr = "Human:S,1\n" +
                "Human:T,2\n" +
                "Human:W,3\n" +
                "Human:P,4\n";

        Reader readBuf = new StringReader(initialStr);

        assertThrows(SolutionException.class, () -> {
            Graph.initializationGraph(
                    "STWSWTPTPTTPWPP",
                    "",
                    readBuf
            );
        });
        readBuf.close();
    }





}