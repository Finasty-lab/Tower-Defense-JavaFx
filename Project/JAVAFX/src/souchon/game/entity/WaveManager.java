package souchon.game.entity;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class of entity to represent a manager of Wave.
 * It is represented by an lvl, an idWave, a finnish as well as an currentWave, a {@link Wave} list and a {@link String} list orders.
 */
public class WaveManager {

    private final ArrayList<String> waveOrders = new ArrayList<>();
    private final List<Wave> waves;
    private Wave currentWave;
    private boolean finnish;
    private int lvl = 0;
    private int idWave;

    /**
     * Constructor of {@link WaveManager}
     *
     * @param lvl Actual game level of {@link WaveManager}
     */
    public WaveManager(int lvl) {
        idWave = 1;
        waves = new ArrayList<>();
        this.lvl = lvl;
        parseFile(this.lvl);
        currentWave = waves.get(idWave - 1);
    }

    /**
     * Constructor of {@link WaveManager} without parameters
     */
    public WaveManager() {
        idWave = 1;
        waves = new ArrayList<>();
        parseFile(this.lvl);
        currentWave = waves.get(idWave - 1);
    }


    public ArrayList<String> getWaveOrders() {
        return waveOrders;
    }

    /**
     * Method to copy the waveOrder
     *
     * @return {@link ArrayList<String>} Copy of the waveOrder
     */
    public ArrayList<String> getCopyWaveOrders() {
        ArrayList<String> copy;
        copy = (ArrayList<String>) waveOrders.clone();
        return copy;
    }

    /**
     * Display all the order in the {@link WaveManager}
     */
    public void displayWaveOrders() {
        for (String s : waveOrders) {
            System.out.println(s);
        }
    }

    /**
     * Method to move on to the next wave.
     * If we reach the end of the wave list, we indicate that the game is finished by 'finnish = true'.
     */
    public void nextWave() {
        idWave++;
        if (idWave - 1 == waves.size())
            finnish = true;
        else
            currentWave = waves.get(idWave - 1);
    }

    /**
     * Method to indicate whether True or False all the waves have finished spawning enemies
     *
     * @return true if finnish, false else
     */
    public boolean isFinnish() {
        return finnish;
    }


    /**
     * Get the first enemy of the current wave
     *
     * @return the {@link Enemy} at the top of the list of the current wave
     */
    public Enemy spawnEnemy() {
        return currentWave.getEnemy();
    }


    /**
     * Add an order for the waves.
     *
     * @param order Order to add
     * @param value Length of pixel where the order is applied.
     */
    public void addWaveOrder(String order, int value) {
        waveOrders.add(order);
        waveOrders.add(String.valueOf(value));
    }

    /**
     * Method to indicate whether True or False the current wave has finished spawning enemies
     *
     * @return true if finnish, false else
     */
    public boolean spawnFinnish() {
        return currentWave.isSpawnFinnish();
    }

    /**
     * Method for parsing the game wave configuration file
     */
    public void parseFile(int level) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("resource/json/waves.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject root = (JSONObject) obj;
            JSONArray wavesArray = (JSONArray) root.get("waves");

            for (var o : wavesArray) {
                JSONObject waveJSON = (JSONObject) o;
                waves.add(new Wave(waveJSON, level));
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
