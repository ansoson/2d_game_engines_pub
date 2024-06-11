package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import javafx.util.Pair;

public class TickSystem extends GameSystem{

    public TickSystem(GameManager mama){
        super(mama);
        setTag("ticks");
    }

    public void onTick(long n){
        for (Pair<Double, GameObject> object : this.getSystemObjects()){
            object.getValue().tick(n);
        }
    }

}
