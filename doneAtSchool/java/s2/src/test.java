import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
public class test {
    public static void main(String[] args) {
        int[] Nlist = {150, 200, 300, 400, 800, 1600, 3200, 6400, 12800};
        int T = 5;
        for (int N : Nlist) {
            Point[] points = new Point[N];
            double totalTime = 0;
            for (int q = 0; q < T; q++) {
                for (int i = 0; i < N; i++) {
                    int x = StdRandom.uniform(N), y = StdRandom.uniform(N);
                    points[i] = new Point(x, y);
                }
                Stopwatch perWatch = new Stopwatch();
                //Fast.main(points);
                double time = perWatch.elapsedTime();
                totalTime += time;
            }
            System.out.println(N);
            System.out.println((double)Math.round(totalTime*10 / T)/ 10);
            System.out.println("————————————————");
        }
    }
}
