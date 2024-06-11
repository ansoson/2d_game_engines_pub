package twiz.objects;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class nextFloorTrigger extends GameObject {
    public nextFloorTrigger(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.hasTrigger = true;
    }

    public void onTrigger(){
        myMama.myScreen.myApp.changeScreen();
    }
}
