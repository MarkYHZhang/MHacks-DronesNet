package dronesimulation;
/**
 * Created by mark on 25/03/17.
 */
import java.util.Random;

public class InfoGenerator {

    private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk" };
    private static String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak" };
    private static String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur" };

    private static Random rand = new Random();

    static String generateName() {
        return Beginning[rand.nextInt(Beginning.length)] +
                Middle[rand.nextInt(Middle.length)]+
                End[rand.nextInt(End.length)];
    }


    private static String[] model = {"DJI Phantom 4", "DJI Inspire 1", "DJI Phantom 3 Pro", "DJI Phantom 4 Pro", "DJI Phantom",
    "DJI Mavic Pro", "DJI Phantom 3 Advanced", "Yuneec Typhoon H Pro", "Parrot Bebop 2 FPV", "Parrot Disco FPV"};
    static String generateModel(){
        return model[rand.nextInt(model.length)];
    }

}