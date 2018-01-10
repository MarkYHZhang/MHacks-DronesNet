package map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mark on 25/03/17.
 */
public class MapGenerator {

    public static final int MAP_X = 200;
    public static final int MAP_Y = 200;
    public static Point s1, m1, e1, s2, m2, e2;

    public static Tile[][] map = new Tile[MAP_X+1][MAP_Y+1];

    private ArrayList<String> section = new ArrayList<>();

    public MapGenerator(){
        for (int i = 0; i <= MAP_X; i++) {
            for (int j = 0; j <= MAP_Y; j++) {
                map[i][j] = new Tile(0,0);
            }
        }
    }

    public void generate(){
        int hor = 20;
        int ver = 10;
        for (int i = 1; i <= MAP_Y; i++) {
            if (i%hor == 0){
                for (int j = 1 ; j <= MAP_X; j++){
                    map[j][i] = new Tile(0,-2);
                }
                section.add(0+","+i+","+(MAP_X-1)+","+i+",0,0");
            }
        }
        for (int i = 1; i <= MAP_X; i++){
            if (i%ver == 0){
                Arrays.fill(map[i],new Tile(0,-2));
                section.add(i+","+0+","+i+","+(MAP_Y-1)+",0,0");
            }
        }

        // Air base
        s1 = null;
        e1 = null;
        for (int i = MAP_X-ver+1; i < MAP_X; i++) {
            for (int j = 0; j < hor*3; j++) {
                map[i][j] = new Tile(3,-1);
                if (s1==null) s1 = new Point(i,j);
                else e1 = new Point(i,j);
            }
        }
        section.add(s1.x+","+s1.y+","+e1.x+","+e1.y+","+3+","+(-1));
        m1 = new Point((e1.x + s1.x)/2, (e1.y + s1.y)/2);

        // Park
        s2 = null;
        e2 = null;
        for (int i = MAP_X-6*ver+1; i < MAP_X-4*ver; i++) {
            if (i!=MAP_X-5*ver) {
                for (int j = hor + 1; j < hor * 3; j++) {
                    map[i][j] = new Tile(1, -1);
                    if (s2==null) s2 = new Point(i,j);
                    else e2 = new Point(i,j);
                }
            }
        }
        section.add(s2.x+","+s2.y+","+e2.x+","+e2.y+","+1+","+(-1));
        m2 = new Point((e2.x + s2.x)/2, (e2.y + s2.y)/2);

        for (int i = 1; i < MAP_X; i++) {
            for (int j = 1; j < MAP_Y; j++) {
                if (map[i][j].isRoad()&&map[i][j-1].isRoad()&&map[i-1][j-1].isRoad()&&map[i-1][j].isRoad()&&map[i-1][j+1].isRoad()) {
                    int width = (int) (Math.random() * 3 + 4);
                    int length = (int) (Math.random() * 3 + 4);
                    int type = ((int)(Math.random()*50))==0 ? 2 : 0;
                    double chance = (Math.random())*100.0;
                    int height = 0;
                    if (chance>25&&chance<75)height = 4 + (int)(Math.random()*2);
                    else if (chance>93&&chance<95)height= 7 + (int)(Math.random()*3);
                    else if (chance>99.7)height=25 + (int)(Math.random()*5);
                    if (width >= 2 && length >= 2) height += (int) (Math.random() * 30 + 5);
                    else height += (int) (Math.random() * 20 + 1);
                    Point start = null, end = null;
                    for (int k = i; k < i + width; k++) {
                        if (k<MAP_X&&map[k][0].getHeight()==-2)break;
                        for (int l = j; l < j + length; l++) {
                            if (k<MAP_X&&l<MAP_Y&&map[k][l].isRoad()){
                                if (map[0][l].getHeight()==-2)break;
                                if (k==0){
                                    if (map[k-1][l+1].isRoad()){
                                        map[k][l] = new Tile(type, height);
                                        if (start==null) start = new Point(k,l);
                                        else end = new Point(k,l);
                                    }
                                }else {
                                    map[k][l] = new Tile(type, height);
                                    if (start==null) start = new Point(k,l);
                                    else end = new Point(k,l);
                                }
                            }
                        }
                    }
                    section.add(start.x+","+start.y+","+end.x+","+end.y+","+type+","+height);
                }
            }
        }

        for (int i = 1; i <= MAP_Y; i++) {
            if (i%hor == 0){
                for (int j = 1 ; j <= MAP_X; j++){
                    map[j][i] = new Tile(0,0);
                }
            }
        }
        for (int i = 1; i <= MAP_X; i++){
            if (i%ver == 0){
                Arrays.fill(map[i],new Tile(0,0));
            }
        }
    }

    public int[][] getArray(){
        int[][] heightMap = new int[MAP_X+1][MAP_Y+1];
        for (int i = 1; i <= MAP_X; i++) {
            for (int j = 1; j <= MAP_Y; j++) {
                heightMap[i][j] = map[i][j].getHeight();
            }
        }
        return heightMap;
    }

    @Override
    public String toString() {
        JSONArray arr = new JSONArray();
        for (String s : section) {
            String[] info = s.split(",");
            JSONObject obj = new JSONObject();
            obj.put("x1", Integer.parseInt(info[0]) - MapGenerator.MAP_X/2);
            obj.put("z1", Integer.parseInt(info[1]) - MapGenerator.MAP_Y/2);
            obj.put("x2", Integer.parseInt(info[2]) - MapGenerator.MAP_X/2);
            obj.put("z2", Integer.parseInt(info[3]) - MapGenerator.MAP_Y/2);
            obj.put("type", Integer.parseInt(info[4]));
            obj.put("height", Integer.parseInt(info[5]));
            arr.put(obj);
        }
        return arr.toString();
    }
}
