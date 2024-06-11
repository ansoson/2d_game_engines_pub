package engine.mapTools;

import engine.GameManager;
import engine.GameObject;
import engine.Systems.GameSystem;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class MapGenerator {

    public MapGenerator(){
    }

    public ArrayList<Tile> GenerateMap(LevelFile level, GameManager manager) {
        ArrayList<Tile> returnList = new ArrayList<>();
        int i = 0;
        double xPos = 0.0;
        double yPos = -1.0;
        double dOrd = 2.0;
        for (String s : level.splitString) {
                dOrd = 2.0;
            if (i % level.levelDimsX == 0) {
                xPos = 0.0;
                yPos = yPos + 1.0;
                Tile newTile = createNewTile(s, xPos, yPos, level.tileSize, manager);
                newTile.myMama = manager;
                if (Objects.equals(s, "1")) {
                    dOrd = 3.0;
                }
                for (GameSystem system : manager.gameSystems) {
                    if (system.getTag().equals("draws")) {
                        system.addObject(newTile, dOrd);
                    }
                    if (system.getTag().equals("tick")){
                        system.addObject(newTile, dOrd);
                    }
                    if (system.getTag().equals("collision")) {
                        system.addObject(newTile, dOrd);
                    }
                    if (system.getTag().equals("sprite")) {
                        system.addObject(newTile, dOrd);
                    }
                }
                returnList.add(newTile);
                xPos = xPos + 1.0;
            } else {
                Tile newTile = createNewTile(s, xPos, yPos, level.tileSize, manager);
                newTile.myMama = manager;
                if (Objects.equals(s, "1")) {
                    dOrd = 3.0;
                }
                for (GameSystem system : manager.gameSystems) {
                    if (system.getTag().equals("draws")) {
                        system.addObject(newTile, dOrd);
                    }
                    if (system.getTag().equals("tick")){
                        system.addObject(newTile, dOrd);
                    }
                    if (system.getTag().equals("collision")) {
                        system.addObject(newTile, dOrd);
                    }
                }
                xPos++;
                returnList.add(newTile);
            }
            i++;
        }
        return returnList;
    }

    protected Tile createNewTile(String s, double xPos, double yPos, double tileSize, GameManager manager) {
        Tile newTile = new FloorTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.tileType = 0;
        if (Objects.equals(s, "1")) {
            newTile = new WallTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.collisionHitbox.makeUnmoving();
            newTile.tileType = 1;
        }
        if (Objects.equals(s, "2")) {
            newTile = new SpawnTile(new Vec2d(xPos * tileSize, yPos * tileSize), new Vec2d(tileSize, tileSize), new Vec2d(0.0, 0.0), Color.BLACK);
            newTile.tileType = 2;
        }
        return newTile;
    }

}
