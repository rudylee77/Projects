import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        List<Integer> testVals = new ArrayList<Integer>();
        List<Double> times =  new ArrayList<Double>();
        testVals.add(1000);
        testVals.add(2000);
        testVals.add(4000);
        testVals.add(8000);
        testVals.add(16000);
        testVals.add(32000);
        testVals.add(64000);
        testVals.add(128000);
        for (int i = 0; i < testVals.size(); i++) {
            Stopwatch sw = new Stopwatch();
            int val = testVals.get(i);
            AList<Integer> aList = new AList();
            while (val != 0) {
                aList.addLast(val);
                val--;
            }
            times.add(sw.elapsedTime());
        }
        printTimingTable(testVals, times, testVals);
    }


}
