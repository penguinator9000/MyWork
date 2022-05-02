
/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points,
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/

import java.util.*;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;



public class PointSET {
    // construct an empty set of points
    private SET<Point2D> TSet;
    private Iterator<Point2D> SetIterator;
    public PointSET() {
        TSet = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return TSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return TSet.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        TSet.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return TSet.contains(p);
    }


    // draw all of the points to standard draw
    public void draw() {
        if (!isEmpty()){
            SetIterator = TSet.iterator();
            if (size()>1){
                while (SetIterator.hasNext()){
                    Point2D Item = (Point2D)SetIterator.next();
                    Item.draw();
                }
            }
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> inRangeSet = new SET<Point2D>();
        if (!isEmpty()){
            SetIterator = TSet.iterator();
            if (size()>1){
                while (SetIterator.hasNext()){
                    Point2D Item = (Point2D)SetIterator.next();
                    if(rect.contains(Item)){inRangeSet.add(Item);}
                }
            }
        }
        return inRangeSet;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        if (!isEmpty()) {
            SetIterator = TSet.iterator();
            if (size() > 1) {
                while (SetIterator.hasNext()) {
                    Point2D Item = (Point2D) SetIterator.next();
                    if(nearestPoint==null){nearestPoint=Item;}
                    else if (p.distanceTo(Item)<p.distanceTo(nearestPoint)) {
                        nearestPoint=Item;
                    }
                }
            }
            return nearestPoint;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        PointSET PSet = new PointSET();

        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            PSet.insert(p);
        }
        System.out.println(PSet.nearest(new Point2D(0.5, 0.5)));

    }

}
