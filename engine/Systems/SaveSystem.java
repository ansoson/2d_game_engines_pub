package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import javafx.util.Pair;

public class SaveSystem extends GameSystem{
    public SaveSystem(GameManager mama) {
        super(mama);
        setTag("save");
    }

    public void save(long n){
        for (Pair<Double, GameObject> object : this.getSystemObjects()){
//            object.getValue().save(n);
        }
    }

}
