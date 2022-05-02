
/*************************************************************************
 *************************************************************************/

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Point2D;

public class KdTree {
    // construct an empty set of points
    private static class KdNode{
        private boolean alignment;// True for x False for y
        private KdNode left = null;
        private KdNode right = null;
        private Point2D point;
        private RectHV rect;

        private  void set_point(Point2D inp){
            point = inp;
        }
        private void set_left(KdNode inp){
            left = inp;
        }
        private void set_right(KdNode inp){
            right = inp;
        }
        private void set_flag(boolean inp){
            alignment = !inp;
        }
        private boolean rect_belong_right(RectHV rect){
            Point2D p =new Point2D(rect.xmax(),rect.ymax());
            return  belong_right(p);
        }
        private boolean rect_belong_left(RectHV rect){
            Point2D p =new Point2D(rect.xmin(),rect.ymin());
            return  !belong_right(p);
        }
        private boolean belong_right(Point2D p){
            if(alignment){
                if(point.x() <= p.x()){
                    return true;
                }
            }
            else{
                if(point.y() <= p.y()){
                    return true;
                }
            }
            return false;
        }
        private void set_rect(KdNode parent){
            double xmax, xmin, ymax, ymin;
            if (parent.alignment){
                if(parent.belong_right(point)){
                 xmax  = parent.rect.xmax() ;
                 xmin =  parent.point.x();
                }
                else{
                    xmax = parent.point.x();
                    xmin = parent.rect.xmin();
                }
                ymax = parent.rect.ymax();
                ymin = parent.rect.ymin();
            }
            else{
                if(parent.belong_right(point)){
                    ymax  = parent.rect.ymax() ;
                    ymin =  parent.point.y();
                }
                else{
                    ymax = parent.point.y();
                    ymin = parent.rect.ymin();
                }
                xmax = parent.rect.xmax();
                xmin = parent.rect.xmin();
            }
            rect = new RectHV(xmin,ymin,xmax,ymax);
        }


    }
    public KdTree() {
    }
        int size = 0;
        KdNode root = null;
        SET<Point2D> InRange = new SET<Point2D>();
    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }
    private void recursive_insert(Point2D p,KdNode currnode){
        if (currnode.point.equals(p)) return;
        if(currnode.belong_right(p)){
            if(currnode.right == null){
                KdNode node = new KdNode();
                node.set_point(p);
                node.set_flag(currnode.alignment);
                node.set_rect(currnode);
                currnode.set_right(node);
                size ++;
            }
            else{
                recursive_insert(p,currnode.right);
            }
        }
        else{
            if(currnode.left == null){
                KdNode node = new KdNode();
                node.set_point(p);
                node.set_flag(currnode.alignment);
                node.set_rect(currnode);
                currnode.set_left(node);
                size ++;
            }
            else{
                recursive_insert(p,currnode.left);
            }
        }
    }
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (isEmpty()){
            KdNode node = new KdNode();
            node.set_point(p);
            node.set_flag(true);
            node.rect = new RectHV(0,0,1,1);
            root = node;
            size ++;
        }
        else {
            recursive_insert(p,root);
        }


    };
    private boolean recursive_contains(Point2D p,KdNode currnode){
        if(currnode.point.equals(p)){
            return true;
        }
        if(currnode.belong_right(p)){
            if(currnode.right != null){
                return recursive_contains(p,currnode.right);
            }
        }
        else{
            if(currnode.left != null){
                return recursive_contains(p,currnode.left);
            }
        }
        return false;
    }
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if (!isEmpty()) {
            return recursive_contains(p,root);
        }
        return false;
    }

    // draw all of the points to standard draw
    public void draw() {

    }
    private void recrange(RectHV rect, KdNode currnode){
        if (currnode == null) return;
        else if (rect.contains(currnode.point)) {
            InRange.add(currnode.point);
            recrange(rect,currnode.left);
            recrange(rect,currnode.right);
        }
        else {
            if (currnode.rect_belong_right(rect)) {
                recrange(rect, currnode.right);
            }
            if (currnode.rect_belong_left(rect)){
                recrange(rect, currnode.left);
            }
        }
    }
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        InRange = new SET<Point2D>();
        recrange(rect, root);
        return InRange;
    }
    private Point2D recursive_nearest(Point2D p,Point2D nearest,KdNode currnode){
        if(currnode.point.distanceTo(p) < nearest.distanceTo(p)){
            nearest = currnode.point;
        }
        if (currnode.left != null && currnode.left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(nearest)) {
            nearest = recursive_nearest(p,nearest,currnode.left);
        }
        if (currnode.right != null && currnode.right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(nearest)) {
            nearest = recursive_nearest(p,nearest,currnode.right);
        }
        return nearest;
    }
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if(isEmpty())return null;
        return recursive_nearest(p,root.point,root);
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        KdTree kd = new KdTree();

        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kd.insert(p);
        }
        System.out.println(kd.range(new RectHV(0.5, 0.5,1,1)));




        /*
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        KdTree set = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();
        */

    }
}
