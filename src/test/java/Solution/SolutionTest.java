package Solution;

import Exception.SolutionException;
import Util.constants;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void createTestTableCost(){
        HashMap<Integer, Integer> node = new HashMap<>();
        node.put(1, 5);
        node.put(2,6);
        HashMap<Integer, Integer> tableCost = Solution.createTableCosts(node);
        assertEquals(constants.LENGTH_FIELD - 1,tableCost.size());
        for(int i = 1; i < constants.LENGTH_FIELD; i ++){
            assertTrue(tableCost.containsKey(i));
        }
        assertEquals(5,tableCost.get(1));
        assertEquals(6,tableCost.get(2));
    }

    @Test
    void findLowestCost() throws SolutionException {
        HashMap<Integer, Integer> node = new HashMap<>();
        node.put(1, 5);
        node.put(2, 6);
        HashMap<Integer, Integer> tableCost = Solution.createTableCosts(node);
        Solution.processedNode = new boolean[constants.LENGTH_FIELD];
        assertEquals(1,  Solution.findLowestCost(tableCost));
        Solution.processedNode[1] = true;
        assertEquals(2,  Solution.findLowestCost(tableCost));

    }

    @Test
    void negativeNoneInitProcessedNode()  {
        HashMap<Integer, Integer> node = new HashMap<>();
        node.put(1, 5);
        node.put(2, 6);
        HashMap<Integer, Integer> tableCost = Solution.createTableCosts(node);
        Solution.processedNode = null;
        assertThrows(SolutionException.class, () -> {
            Solution.findLowestCost(tableCost);
        });
    }

    @Test
    void negativeBigNode()  {
        HashMap<Integer, Integer> node = new HashMap<>();
        node.put(1, 5);
        node.put(888888, 6);
        HashMap<Integer, Integer> tableCost = Solution.createTableCosts(node);
        Solution.processedNode = new boolean[constants.LENGTH_FIELD];
        assertThrows(SolutionException.class, () -> {
            Solution.findLowestCost(tableCost);
        });
    }

    @Test
    void testGetResult(){
        assertEquals(10,  Solution.getResult("STWSWTPPTPTTPWPP", "Human"));
        assertEquals(15,  Solution.getResult("STWSWTPPTPTTPWPP", "Swamper"));
        assertEquals(12,  Solution.getResult("STWSWTPPTPTTPWPP", "Woodman"));
    }



}