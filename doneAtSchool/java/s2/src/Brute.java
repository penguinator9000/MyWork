import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.Arrays;

public class Brute {

    private static String StringPointsToLineStr(Point p1,Point p2,Point p3,Point p4){
        return p1.toString()+" -> "+p2.toString()+" -> "+p3.toString()+" -> "+p4.toString();
    }

    public static String RunBrute(Point[] PointList) {
        String outPut = "";
        Arrays.sort(PointList);
        for(int a = 0; a<PointList.length;a++){
            for(int b = a+1; b<PointList.length;b++){
                for(int c = b+1; c<PointList.length;c++){
                    for(int d = c+1; d<PointList.length;d++){
                        if(PointList[a].slopeTo(PointList[b])==PointList[b].slopeTo(PointList[c])&&PointList[b].slopeTo(PointList[c])==PointList[c].slopeTo(PointList[d])){
                            outPut += StringPointsToLineStr(PointList[a],PointList[b],PointList[c],PointList[d])+"\n";
                        }
                    }
                }
            }
        }
        return outPut;
    }

    public static void main(String[] args) {
        In in = new In();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
    }
}
