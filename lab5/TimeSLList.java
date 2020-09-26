import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        List<Integer> testVals = new ArrayList<Integer>();
        List<Double> times =  new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();
        testVals.add(1000);
        testVals.add(2000);
        testVals.add(4000);
        testVals.add(8000);
        testVals.add(16000);
        testVals.add(32000);
        testVals.add(64000);
        testVals.add(128000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        opCounts.add(10000);
        for (int i = 0; i < testVals.size(); i++) {
            int val = testVals.get(i);
            int val2 = 10000;
            SLList<Integer> sList = new SLList();
            while (val != 0) {
                sList.addLast(val);
                val--;
            }
            Stopwatch sw = new Stopwatch();
            while (val2 != 0) {
                sList.getLast();;
                val2--;
            }
            times.add(sw.elapsedTime());
        }
        printTimingTable(testVals, times, opCounts);
    }

}
