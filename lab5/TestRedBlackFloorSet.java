import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet aSet = new AListFloorSet();
        RedBlackFloorSet rSet = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i ++) {
            double val = StdRandom.uniform(-5000, 5000);
            aSet.add(val);
            rSet.add(val);
        }
        for (int i = 0; i < 100000; i++) {
            double val2 = StdRandom.uniform(-5000, 5000);
            assertEquals(aSet.floor(val2), rSet.floor(val2), 0.000001);
        }

    }
}
