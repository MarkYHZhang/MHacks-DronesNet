package dronesimulation;

import map.MapGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;
import main.DroneProject;

/**
 * Created by mark on 25/03/17.
 */
public class DroneManager{

    // EDIT THIS NUMBER BASED ON WHICH SIM YOU WANT TO RUN
    public static int simNumber = 0; // 0 is normal, 1 is go to nature base, 2 is go to airplane base, 3 is go over government base


    private static final int NUMBEROFDRONES = 100;
    private Random rand = new Random();
    private static Drone[] drones = new Drone[NUMBEROFDRONES];
    public DroneManager(){
        for (int i = 0; i < NUMBEROFDRONES; i++) {
            boolean isRestrictedTile = true;
            int x, y, height;
            do{
                x = rand.nextInt(MapGenerator.MAP_X);
                y = rand.nextInt(MapGenerator.MAP_Y);
                if (DroneProject.map[x][y] >= 0) {
                    isRestrictedTile = false;
                }
            }while (isRestrictedTile);

            if (DroneProject.map[x][y] == 0) {
                height = rand.nextInt(20)+1;
            }
            else{
                height = rand.nextInt(5)+1 + DroneProject.map[x][y];
            }

            int ex, ey;
            isRestrictedTile = true;
            do{
                ex = rand.nextInt(MapGenerator.MAP_X);
                ey = rand.nextInt(MapGenerator.MAP_Y);
                if (DroneProject.map[ex][ey] >= 0 && DroneProject.map[ex][ey] < height) {
                    isRestrictedTile = false;
                }
            }while (isRestrictedTile);
//            ex = MapGenerator.m1.x;
//            ey = MapGenerator.m1.y;

            Drone cur;
            if (i==0) cur = new Drone(x, y, 63, ex, ey);
            else cur = new Drone(x,y,height,ex,ey);

            drones[i]=cur;
        }
    }
    public static void moveAll(){
        for (Drone drone : drones) {
            drone.move();
        }
    }

    public static void move(int id){
        drones[id].move();

    }

    public String getDrone(int id){
        JSONObject object = new JSONObject();
        object.put("init", 0);
        object.put("name", drones[id].getName());
        object.put("id", drones[id].getId());
        object.put("x", drones[id].getX() - MapGenerator.MAP_X/2);
        object.put("y", drones[id].getHeight());
        object.put("z", drones[id].getY() - MapGenerator.MAP_Y/2);
        return object.toString();
    }

    public String getParsedDroneInfo(){
        JSONArray arr = new JSONArray();
        for (Drone drone : drones) {
            JSONObject droneInfo = new JSONObject();
            droneInfo.put("name", drone.getName());
            droneInfo.put("id", drone.getId());
            droneInfo.put("x", drone.getX() - MapGenerator.MAP_X/2);
            droneInfo.put("y", drone.getHeight());
            droneInfo.put("z", drone.getY() - MapGenerator.MAP_Y/2);
            arr.put(droneInfo);
        }
        return arr.toString();
    }

//    // AIRPORT DRONE
//    private static final int NUMBEROFDRONES = 1;
//    private Random rand = new Random();
//    private static Drone[] airportDrone = new Drone[NUMBEROFDRONES];
//    public DroneManager(){
//            boolean isRestrictedTile = true;
//            int x, y, height;
//            do{
//                x = rand.nextInt(MapGenerator.MAP_X);
//                y = rand.nextInt(MapGenerator.MAP_Y);
//                if (DroneProject.map[x][y] >= 0) {
//                    isRestrictedTile = false;
//                }
//            }while (isRestrictedTile);
//
//            if (DroneProject.map[x][y] == 0) {
//                height = rand.nextInt(20)+1;
//            }
//            else{
//                height = rand.nextInt(5)+1 + DroneProject.map[x][y];
//            }
//
//            int ex, ey;
//            ex = MapGenerator.m1.x;
//            ey = MapGenerator.m1.y;
//
//            Drone cur = new Drone(x, y, height, ex, ey);
//
//            airportDrone[0]=cur;
//    }
//
//    public static void move(int id){
//        airportDrone[id].move();
//        System.out.println(airportDrone[id].getX() + " " + airportDrone[id].getY());;
//
//    }
//
//    public String getDrone(int id){
//        JSONObject object = new JSONObject();
//        object.put("init", 0);
//        object.put("name", airportDrone[id].getName());
//        object.put("id", airportDrone[id].getId());
//        object.put("x", airportDrone[id].getX() - MapGenerator.MAP_X/2);
//        object.put("y", airportDrone[id].getHeight());
//        object.put("z", airportDrone[id].getY() - MapGenerator.MAP_Y/2);
//        return object.toString();
//    }
//
//    public String getParsedDroneInfo() {
//        JSONArray arr = new JSONArray();
//        for (Drone drone : airportDrone) {
//            JSONObject droneInfo = new JSONObject();
//            droneInfo.put("name", drone.getName());
//            droneInfo.put("id", drone.getId());
//            droneInfo.put("x", drone.getX() - MapGenerator.MAP_X / 2);
//            droneInfo.put("y", drone.getHeight());
//            droneInfo.put("z", drone.getY() - MapGenerator.MAP_Y / 2);
//            arr.put(droneInfo);
//        }
//        return arr.toString();
//    }

//    // NATURE DRONE
//    private static final int NUMBEROFDRONES = 1;
//    private Random rand = new Random();
//    private static Drone[] airportDrone = new Drone[NUMBEROFDRONES];
//    public DroneManager(){
//        boolean isRestrictedTile = true;
//        int x, y, height;
//        do{
//            x = rand.nextInt(MapGenerator.MAP_X);
//            y = rand.nextInt(MapGenerator.MAP_Y);
//            if (DroneProject.map[x][y] >= 0) {
//                isRestrictedTile = false;
//            }
//        }while (isRestrictedTile);
//
//        if (DroneProject.map[x][y] == 0) {
//            height = rand.nextInt(20)+1;
//        }
//        else{
//            height = rand.nextInt(5)+1 + DroneProject.map[x][y];
//        }
//
//        int ex, ey;
//        ex = MapGenerator.m2.x;
//        ey = MapGenerator.m2.y;
//
//        Drone cur = new Drone(x, y, height, ex, ey);
//
//        airportDrone[0]=cur;
//    }
//    public static void moveAll(){
//        for (Drone drone : airportDrone) {
//            drone.move();
//        }
//    }
//
//    public static void move(int id){
//        airportDrone[id].move();
//
//    }
//
//    public String getDrone(int id){
//        JSONObject object = new JSONObject();
//        object.put("init", 0);
//        object.put("name", airportDrone[id].getName());
//        object.put("id", airportDrone[id].getId());
//        object.put("x", airportDrone[id].getX() - MapGenerator.MAP_X/2);
//        object.put("y", airportDrone[id].getHeight());
//        object.put("z", airportDrone[id].getY() - MapGenerator.MAP_Y/2);
//        return object.toString();
//    }
//
//    public String getParsedDroneInfo(){
//        JSONArray arr = new JSONArray();
//        for (Drone drone : airportDrone) {
//            JSONObject droneInfo = new JSONObject();
//            droneInfo.put("name", drone.getName());
//            droneInfo.put("id", drone.getId());
//            droneInfo.put("x", drone.getX() - MapGenerator.MAP_X/2);
//            droneInfo.put("y", drone.getHeight());
//            droneInfo.put("z", drone.getY() - MapGenerator.MAP_Y/2);
//            arr.put(droneInfo);
//        }
//        return arr.toString();
//    }

}
