package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import javafx.util.Pair;

import java.util.ArrayList;

public class GameSystem {

    private String tag;
    protected ArrayList<Pair<Double, GameObject>> systemObjects;
    protected GameManager myMama;

    public GameSystem(GameManager mama){
        tag = "NONSPECIFICSYSTEM";
        systemObjects = new ArrayList<>();
        myMama = mama;
    }

    public String getTag(){
        return this.tag;
    }

    protected void setTag(String newTag){
        this.tag = newTag;
    }

    public ArrayList<Pair<Double, GameObject>> getSystemObjects(){
        return this.systemObjects;
    }

    public void addObject(GameObject obj, Double additional){
        systemObjects.add(new Pair<>(additional, obj));
    }
    public void removeObject(GameObject obj){
        for (Pair<Double, GameObject> pair : systemObjects){
            if (pair.getValue() == obj){
                systemObjects.remove(pair);
            }
        }
    }



}
