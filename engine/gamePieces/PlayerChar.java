package engine.gamePieces;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class PlayerChar extends GameObject {
    public PlayerChar(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, Color.YELLOW);
    }

    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        System.out.println("COLLIDDINGGGGGGGGGGG playerchar");
        return super.onCollision(collidedWith, MTV);
    }
}
