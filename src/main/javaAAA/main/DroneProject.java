package main;

import dronesimulation.DroneManager;
import map.MapGenerator;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import static spark.Spark.init;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

/**
 * Created by mark on 25/03/17.
 */
public class DroneProject {

    private static DroneManager dw;
    public static int[][] map;
    private static String mjson;

    public static void main(String[] args) {
        MapGenerator mg = new MapGenerator();
        mg.generate();
        map = mg.getArray();
        mjson = mg.toString();
        System.out.println(mjson);

        dw = new DroneManager();
        staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
        staticFiles.expireTime(600);
        webSocket("/map", main.UserHandler.class);
        init();
    }


    //Sends a message from one user to all users, along with a list of current usernames
    public static void initPacket(Session session) {
        try {
            JSONObject s = new JSONObject().put("drones", dw.getParsedDroneInfo()).put("init",1).put("map",mjson);

            session.getRemote().sendString(String.valueOf(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(Session session, int id) {
        try {
            session.getRemote().sendString(dw.getDrone(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
