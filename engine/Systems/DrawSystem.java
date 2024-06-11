package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class DrawSystem extends GameSystem{

    public DrawSystem(GameManager mama){
        super(mama);
        setTag("draws");
        keyList = new ArrayList<>();
    }

    protected ArrayList<Double> keyList;

    public void addObject(GameObject obj, Double additional){
        systemObjects.add(new Pair<>(additional, obj));
        if (!keyList.contains(additional)){
            keyList.add(additional);
            Collections.sort(keyList);
        }
    }

    public void onDraw(GraphicsContext g){
        g.setTransform(myMama.viewport.getAffine());
        g.setImageSmoothing(false);
        for (Double drawLayer : keyList) {
            for (Pair<Double, GameObject> object : this.getSystemObjects()){
                if (Objects.equals(object.getKey(), drawLayer) && !object.getValue().DEAD) {
                    object.getValue().draw(g);
                }
            }
        }

    }

}
