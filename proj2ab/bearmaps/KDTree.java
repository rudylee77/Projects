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

        public KDNode(boolean xOry, Point parent) {
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
            KDNodeHelper(root, points.get(i), root.xOry);
        }
    }

    private void KDNodeHelper(KDNode root, Point cmp, boolean xOry) {
        if (xOry) {
            if (root.parent.getX() > cmp.getX()) {
                if (root.left == null) {
                    root.left = new KDNode(!xOry, cmp);
                } else {
                    KDNodeHelper(root.left, cmp, root.left.xOry);
                }
            } else {
                if (root.right == null) {
                    root.right = new KDNode(!xOry, cmp);
                } else {
                    KDNodeHelper(root.right, cmp, root.left.xOry);
                }
            }
        } else {
            if (root.parent.getY() > cmp.getY()) {
                if (root.left == null) {
                    root.left = new KDNode(!xOry, cmp);
                } else {
                    KDNodeHelper(root.left, cmp, root.left.xOry);
                }
            } else {
                if (root.right == null) {
                    root.right = new KDNode(!xOry, cmp);
                } else {
                    KDNodeHelper(root.right, cmp, root.left.xOry);
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
        if (n == null) {
            return best;
        }
        if (distance(n.x, n.y, goal.getX(), goal.getY()) < distance(best.x, best.y, goal.getX(), goal.getY())) {
            best = n;
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
        if (badSide != null && badSide.xOry) {
            if (Math.abs(best.x - goal.getX()) > Math.abs(n.x - goal.getX())) {
                best = nearest(badSide, goal, best);
            }
        } else if (badSide != null && !badSide.xOry) {
            if (Math.abs(best.y - goal.getY()) > Math.abs(n.y - goal.getY())) {
                best = nearest(badSide, goal, best);
            }
        }
        return best;
    }

    public Point nearest(double x, double y) {
        return (nearest(root, new Point(x, y), root)).parent;
    }
}
