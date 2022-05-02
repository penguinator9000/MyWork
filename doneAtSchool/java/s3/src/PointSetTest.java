import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.PipedOutputStream;

public class PointSetTest {
    public static void main(String[] args) {
        String filename = args[0];
        int T =10;
        In in = new In(filename);

        PointSET PSet = new PointSET();
        KdTree KdT = new KdTree();

        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            PSet.insert(p);
            KdT.insert(p);
        }
        Point2D[] RandPointList = new Point2D[T];
        for (int i=0;i<T;i++){
            double x = StdRandom.uniform(0.0,1.0);
            double y = StdRandom.uniform(0.0,1.0);
            RandPointList[i]=new Point2D(x,y);
        }

        double totalTimePset = 0;
        double totalTimeKdT = 0;
        Stopwatch perWatch;
        double time;
        for (int i=0;i<T;i++){
            perWatch = new Stopwatch();
            PSet.nearest(RandPointList[i]);
            time = perWatch.elapsedTime();
            totalTimePset += time;

            perWatch = new Stopwatch();
            KdT.nearest(RandPointList[i]);
            time = perWatch.elapsedTime();
            totalTimeKdT += time;
        }

        System.out.print("PointSET:");
        System.out.println(totalTimePset/T);
        System.out.print("Kd-Tree:");
        System.out.println(totalTimeKdT/T);
    }
}
