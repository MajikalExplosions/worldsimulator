
package worldsimulator;

import java.util.ArrayList;
import java.util.Random;
import worldsimulator.voronoi.Edge;
import worldsimulator.voronoi.Point;
import worldsimulator.voronoi.Stopwatch;
import worldsimulator.voronoi.Voronoi;
import worldsimulator.voronoi.StdDraw;

public class WorldSimulator {
    
    
    
    public static void main(String[] args) {
        World world = new World(new Random());
        
        if (! world.generate()) {
            System.out.println("Error generating world.");
            System.exit(100);
        }
        
        
        /*
        //Voronoi test, use to mess around with world gen
        int N = 100;
        Stopwatch s = new Stopwatch();

        ArrayList<Point> points = new ArrayList<>();

        Random gen = new Random();

        for (int i = 0; i < N; i++){
                double x = gen.nextDouble();
                double y = gen.nextDouble();
                points.add(new Point(x, y));
        }
        double start = s.elapsedTime();
        Voronoi diagram = new Voronoi (points);
        double stop = s.elapsedTime();

        //System.out.println(stop-start);

        // draw results
        StdDraw.setPenRadius(.005);
        for (Point p: points) {
                StdDraw.point(p.x, p.y);
        }
        StdDraw.setPenRadius(.002);
        for (Edge e: diagram.edges) {
                StdDraw.line(e.start.x, e.start.y, e.end.x, e.end.y);
        }
        */
    }
}
