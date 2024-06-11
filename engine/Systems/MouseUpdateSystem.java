package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import engine.support.Vec2d;
import javafx.util.Pair;

public class MouseUpdateSystem extends GameSystem {
    public MouseUpdateSystem(GameManager mama){
        super(mama);
        setTag("mouse");
    }

    public void mouseManage(boolean mousePressed, boolean mouseReleased, Vec2d mouseCoords){
        try{
            for (Pair<Double, GameObject> object : this.getSystemObjects()){
                if(!object.getValue().DEAD){
                    object.getValue().mouseManage(mousePressed, mouseReleased, mouseCoords);
                }
            }
        }
        catch (Exception e){
            System.out.println("weird line numbers are likely diverged error, address at some point");
        }

    }

}
