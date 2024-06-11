package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import engine.support.Vec2d;
import javafx.util.Pair;

public class ResizeSystem extends GameSystem{
    public ResizeSystem(GameManager mama) {
        super(mama);
        setTag("resizes");
    }

    public void onResize(Vec2d newDims, Vec2d newSize){
        for (Pair<Double, GameObject> object : this.getSystemObjects()){
                object.getValue().onResize(newDims, newSize);
        }
    }
}
