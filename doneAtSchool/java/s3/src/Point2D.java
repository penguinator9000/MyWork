import edu.princeton.cs.algs4.StdDraw;
//all taken from algs4
public class Point2D implements Comparable<Point2D> {
    private final double x;    // x coordinate
    private final double y;    // y coordinate
    // construct the point (x, y)
    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinates must be finite");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        if (x == 0.0) this.x = 0.0;  // convert -0.0 to +0.0
        else          this.x = x;

        if (y == 0.0) this.y = 0.0;  // convert -0.0 to +0.0
        else          this.y = y;
    }

    public double x() { // x-coordinate
        return x;
    }

    public double y() {// y-coordinate
        return y;
    }

    // Euclidean distance between two points
    public double distanceTo(Point2D that){
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    // square of Euclidean distance between two points
    public double distanceSquaredTo(Point2D that){
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx*dx + dy*dy;
    }
    // for use in an ordered symbol table
    public int compareTo(Point2D that){
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }
    // does this point equal that?
    public boolean equals(Object other){
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }
    // draw to standard draw
    public void draw(){
        StdDraw.point(x, y);
    }
    // string representation
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
