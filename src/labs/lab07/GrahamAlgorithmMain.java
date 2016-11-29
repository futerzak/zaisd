package labs.lab07;

import java.util.Arrays;
import java.util.Stack;

public class GrahamAlgorithmMain {

    private Stack<Point2D> hull = new Stack<>();

    public GrahamAlgorithmMain(Point2D[] pts) {


        int N = pts.length;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = pts[i];
        }
        Arrays.sort(points);

        Arrays.sort(points, 1, N, points[0].POLAR_ORDER);

        hull.push(points[0]);
        int k1;
        for (k1 = 1; k1 < N; k1++) {
            if (!points[0].equals(points[k1])) {
                break;
            }
        }
        if (k1 == N) {
            return;
        }

        int k2;
        for (k2 = k1 + 1; k2 < N; k2++) {
            if (Point2D.isLeft(points[0], points[k1], points[k2]) != 0) {
                break;
            }
        }
        hull.push(points[k2 - 1]);

        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.isLeft(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

        assert isConvex();
    }

    public Iterable<Point2D> hull() {
        Stack<Point2D> s = new Stack<>();
        for (Point2D p : hull) {
            s.push(p);
        }
        return s;
    }

    private boolean isConvex() {
        int N = hull.size();
        if (N <= 2) {
            return true;
        }

        Point2D[] points = new Point2D[N];
        int n = 0;
        for (Point2D p : hull()) {
            points[n++] = p;
        }

        for (int i = 0; i < N; i++) {
            if (Point2D
                    .isLeft(points[i], points[(i + 1) % N], points[(i + 2) % N]) <= 0) {
                return false;
            }
        }
        return true;
    }
}