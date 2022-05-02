package s2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final int x, y;

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and another point to standard drawing
    public void drawTo(Point other) {
        StdDraw.line(this.x, this.y, other.x, other.y);
    }

    // slope between this point and another point
    public double slopeTo(Point other) {
        double Mupper = other.y - this.y;
        double Mlower = other.x - this.x;
        if (Mupper == 0.0) {
            if (Mlower == 0.0) {
                return Double.NEGATIVE_INFINITY;
            }
            return Mupper;
        }
        else if (Mlower == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        double result = Mupper / Mlower;
        return result;
    }

    //Is this points y smaller than the other and breaking ties by x-coordinates
    public int compareTo(Point other) {
        int comp = Integer.compare(this.y, other.y);
        if (comp == 0) {
            comp = Integer.compare(this.x, other.x);
        }
        return comp;
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            return Double.compare((slopeTo(a)),(slopeTo(b)));
        }
    }
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}
