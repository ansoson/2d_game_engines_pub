package twiz.levels;

import engine.GameManager;
import engine.Systems.GameSystem;
import engine.mapTools.*;
import engine.support.Vec2d;
import javafx.scene.paint.Color;
import twiz.objects.*;

import java.util.Objects;

public class WizGenerator extends MapGenerator {

    protected Tile createNewTile(String s, double xPos, double yPos, double tileSize, GameManager manager) {
        Tile newTile = new GrassTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
        newTile.tileType = 0;
        if (Objects.equals(s, "1")) {
            System.out.println("MADE A WALLTILE mapgenerator");
            newTile = new TreeTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.collisionHitbox.makeUnmoving();
            newTile.tileType = 1;
        }
        if (Objects.equals(s, "2")) {
            newTile = new SlimeSpawnerTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.tileType = 2;
        }
        if (Objects.equals(s, "3")) {
            newTile = new NextFloorTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.tileType = 3;
            nextFloorTrigger trigger = new nextFloorTrigger(new Vec2d(xPos * tileSize + tileSize*.4, yPos * tileSize + tileSize*.4), new Vec2d(tileSize * .2, tileSize * .2), new Vec2d(0.0, 0.0), Color.BLACK);
            manager.gameObjects.add(trigger);
            for (GameSystem system : manager.gameSystems) {
                if (system.getTag().equals("collision")) {
                    system.addObject(trigger, 3.0);
                }
            }
            trigger.myMama = manager;
        }
        return newTile;
    }

}
