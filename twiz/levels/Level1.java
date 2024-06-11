package twiz.levels;

import debugger.support.Vec2d;
import engine.mapTools.LevelFile;

public class Level1 extends LevelFile {


    public Level1() {
        levelDimsX = 10;
        levelDimsY = 6;
        tileSize = 80.0;
        levelString =  "1 1 1 1 1 1 1 1 1 1 " +
                        "1 0 0 0 0 0 0 0 0 1 " +
                        "1 0 2 0 0 0 0 0 0 1 " +
                        "1 0 0 0 0 0 0 3 0 1 " +
                        "1 0 0 0 0 0 0 0 0 1 " +
                        "1 1 1 1 1 1 1 1 1 1 ";
        splitString = levelString.split(" ");
    }

}
