package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;



public class KeyPressSystem extends GameSystem{
    public KeyPressSystem(GameManager mama) {
        super(mama);
        setTag("keys");
    }

    public void sendKeyDown(KeyEvent key) {
        for (Pair<Double, GameObject> object : this.getSystemObjects()){
            object.getValue().keyPressed(key);
        }
    }

    public void sendKeyUp(KeyEvent key) {
        for (Pair<Double, GameObject> object : this.getSystemObjects()){
            object.getValue().keyLifted(key);
        }
    }
}
