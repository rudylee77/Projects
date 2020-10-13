package bearmaps;
import java.util.List;

public class KDTree {
    private static class KDNode {
        private double x;
        private double y;
        private boolean xOry;
        private Point parent;
        private KDNode left;
        private KDNode right;

        private KDNode(boolean xOry, Point parent) {
            this.x = parent.getX();
            this.y = parent.getY();
            this.xOry = xOry;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

    private List<Point> points;
    private KDNode root;

    public KDTree(List<Point> points) {
        this.points = points;
        Point first = points.get(0);
        root = new KDNode(true, first);
        for (int i = 1; i < points.size(); i++) {
            KdNodeHelper(root, points.get(i));
        }
    }

    private void KdNodeHelper(KDNode og, Point cmp) {
        if (og.xOry) {
            if (og.parent.getX() > cmp.getX()) {
                if (og.left == null) {
                    og.left = new KDNode(!og.xOry, cmp);
                } else {
                    KdNodeHelper(og.left, cmp);
                }
            } else {
                if (og.right == null) {
                    og.right = new KDNode(!og.xOry, cmp);
                } else {
                    KdNodeHelper(og.right, cmp);
                }
            }
        } else {
            if (og.parent.getY() > cmp.getY()) {
                if (og.left == null) {
                    og.left = new KDNode(!og.xOry, cmp);
                } else {
                    KdNodeHelper(og.left, cmp);
                }
            } else {
                if (og.right == null) {
                    og.right = new KDNode(!og.xOry, cmp);
                } else {
                    KdNodeHelper(og.right, cmp);
                }
            }
        }
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    private KDNode nearest(KDNode n, Point goal, KDNode best) {
        KDNode goodSide = null;
        KDNode badSide = null;
        double distance = distance(best.x, best.y, goal.getX(), goal.getY());
        if (n == null) {
            return best;
        }
        if (distance(n.x, n.y, goal.getX(), goal.getY()) < distance) {
            best = n;
            distance = distance(n.x, n.y, goal.getX(), goal.getY());
        }
        if (n.xOry) {
            if (goal.getX() < n.parent.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if (goal.getY() < n.parent.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        best = nearest(goodSide, goal, best);
        if (badSide != null) {
            if (n.xOry) {
                if (distance > Math.abs(n.x - goal.getX())) {
                    best = nearest(badSide, goal, best);
                }
            } else {
                if (distance > Math.abs(n.y - goal.getY())) {
                    best = nearest(badSide, goal, best);
                }
            }
        }
        return best;
    }

    public Point nearest(double x, double y) {
        return (nearest(root, new Point(x, y), root)).parent;
    }
}
