import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Fast {
    private static boolean  checkTmp(ArrayList<Point[]> lines, Point[] points){
        String comparepoints ="";
        for(int j= 0;j<points.length;j++){
            comparepoints += points[j];
        }
        for(int i = 0; i < lines.size();i++){
            Point[] line = lines.get(i);
            String compare1 ="";
            for(int j= 0;j<line.length;j++){
                compare1 +=line[j];
            }
            if(compare1.equals(comparepoints)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        In in = new In();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        Point[] p2 = points.clone();
        ArrayList<Point[]> linedpoints = new ArrayList<Point[]>();

        for(int i = 0; i < p2.length; i++){
            Arrays.sort(points, p2[i].SLOPE_ORDER);
            ArrayList<Point> temp = new ArrayList<Point>();
            temp.add(p2[i]);
            /*
            StdOut.println("Slope shit compared to\n"+p2[i]);
            for(int x = 0;points.length > x;x++){
                StdOut.print(p2[i].slopeTo(points[x]));
                StdOut.println(" "+points[x]);
            }
            StdOut.println("----------------\n");
            */
            for(int j = 0; j < points.length ; j++){
                if((p2[i].slopeTo(points[j]) == p2[i].slopeTo(temp.get(temp.size()-1))) || temp.size()<2) {
                    temp.add(points[j]);
                }
                else {
                        if (temp.size()>3){
                            Collections.sort(temp);
                            Point[] exporter = new Point[temp.size()];
                            for(int tm = 0; tm < temp.size();tm++){
                                exporter[tm] = temp.get(tm);
                            }
                            if (checkTmp(linedpoints,exporter)) linedpoints.add(exporter);
                        }
                        temp.clear();
                        temp.add(p2[i]);
                        temp.add(points[j]);
                }
            }
            if (temp.size()>3){
                Collections.sort(temp);
                Point[] exporter = new Point[temp.size()];
                for(int tm = 0; tm < temp.size();tm++){
                    exporter[tm] = temp.get(tm);
                }
                if (checkTmp(linedpoints,exporter)) linedpoints.add(exporter);
            }
        }
        Collections.sort(linedpoints,new Comparator<Point[]>() {
                    public int compare(Point[] p1, Point[] p2) {
                        int comp = p1[0].compareTo(p2[0]);
                        if(comp == 0) {
                            comp = Double.compare(p1[0].slopeTo(p1[1]),p2[0].slopeTo(p2[1]));
                        }
                        return comp;
                    }
                });
        for(int i = 0; linedpoints.size() > i; i++ ){
            Point[] curline = linedpoints.get(i);
            String outstr = curline[0].toString();
            for( int j =1; curline.length >j;j++){
                    outstr += " -> "+curline[j];
            }
            StdOut.println(outstr);
        }
   }
}

