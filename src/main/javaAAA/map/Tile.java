package map;

/**
 * Created by mark on 25/03/17.
 */
public class Tile {

    private int height = 0;

    private int type; //0 everywhere, 1 national park, 2 prohibited airspace, 3 airport

    public Tile(int type, int height){
        this.type = type;
        this.height = height;
    }


    public boolean isBuilding() {
        return height>0;
    }

    public boolean isRoad(){
        return height == 0;
    }

    public int getHeight() {
        return height;
    }

    public int getType() {
        return type;
    }
}
