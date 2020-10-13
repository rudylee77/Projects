package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public Point nearest(double x, double y) {
        Point nearest = points.get(0);
        double distance = distance(nearest.getX(), nearest.getY(), x, y);
        for (int i = 1; i < points.size(); i++) {
            Point next = points.get(i);
            double cmp = distance(next.getX(), next.getY(), x, y);
            if (distance > cmp) {
                nearest = next;
                distance = cmp;
            }
        }
        return nearest;
    }
}
