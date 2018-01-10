package dronesimulation;

import main.DroneProject;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

import map.MapGenerator;
import twilio.SmsSender;

/**
 * Created by mark on 25/03/17.
 */
public class Drone {

    private static int ID = 0;
    private double x, y, height;
    private double ex, ey;
    private int id;
    private String name = InfoGenerator.generateName();
    private int[][] map = DroneProject.map;
    private ArrayList<Point> loc = new ArrayList<>();

    private boolean visited;

    public Drone(int x, int y, int height, int ex, int ey){
        this.x = x;
        this.y = y;
        this.height = height;
        this.ex = ex;
        this.ey = ey;
        id = ID++;
        findPath(x,y,ex,ey);
        if (id==0){
            loc = new ArrayList<>();
            loc.add(new Point(x,y));
            loc.add(new Point(MapGenerator.m1.x,MapGenerator.m1.y));
        }
    }

    public void findPath(int startR, int startC, int r, int c){

        //initialize the path history 2d array
        Point[][] pathHistory = new Point[map.length][map[0].length];

        //north south east west direction vectors
        Point[] direction = {new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};

        //initialize starting point
        Point start = new Point(startR,startC);
        pathHistory[startR][startC] = start;

        //the BFS queue
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(start);

        //while queue not empty
        while (!queue.isEmpty()){
            //gets the current point
            Point cur = queue.poll();
            //try all four directions
            for (Point dirPnt : direction) {
                int rLoc = cur.x + dirPnt.x;
                int cLoc = cur.y + dirPnt.y;
                //if in map and is valid block to go and not visit
                if (rLoc >= 0 && rLoc < map.length && cLoc >= 0 && cLoc < map[0].length && map[rLoc][cLoc] >=0 && map[rLoc][cLoc] < height && pathHistory[rLoc][cLoc] == null) {
                    //append this to the queue
                    queue.add(new Point(rLoc, cLoc));
                    //store in path history
                    pathHistory[rLoc][cLoc] = cur;
                }
            }
        }

        //backtrace the path histroy
        Point cur = pathHistory[r][c];
        while (true){
            if (cur.x==startR&&cur.y==startC) break;
            loc.add(new Point(cur.x,cur.y));
            cur = pathHistory[cur.x][cur.y];
        }
        Collections.reverse(loc);
    }

    int ind = 0, dir = 1;
    public void move(){
        if (ind==loc.size()){
            ind = loc.size()-1;
            dir=-1;
        }
        else if (ind==-1){
            ind = 0;
            dir=1;
        }
        Point p = loc.get(ind);
        ind+=dir;
        x = p.x;
        y = p.y;


        if (!visited&&intersects(MapGenerator.s1.x, MapGenerator.e1.x, x, MapGenerator.s1.y, MapGenerator.e1.y, y)){
            SmsSender.sendToSimon();
            System.out.println("Went into a restricted air zone: airport");
            visited = true;
        }
        else if (!visited&&intersects(MapGenerator.s2.x, MapGenerator.e2.x, x, MapGenerator.s2.y, MapGenerator.e2.y, y)){
            SmsSender.sendToSimon();
            System.out.println("Went into a restricted air zone: nature");
            visited=true;
        }

    }
    public boolean intersects(double ax, double bx, double x, double ay, double by, double y){
        if (x >= ax && x <= bx) {
            if (y >= ay && x <= by){
                return true;
            }
            if (y <= ay && x >= by){
                return true;
            }
        }
        if (x <= ax && x >= bx) {
            if (y >= ay && x <= by){
                return true;
            }
            if (y <= ay && x >= by){
                return true;
            }
        }
        return false;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
