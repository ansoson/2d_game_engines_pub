package twiz.levels;

import engine.mapTools.LevelFile;

public class Level2 extends LevelFile {

    public Level2() {
            levelDimsX = 20;
            levelDimsY = 11;
            tileSize = 80.0;
            levelString =  "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 " +
                    "1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 1 " +
                    "1 0 2 0 0 0 0 0 0 0 1 1 1 0 0 0 0 3 0 1 " +
                    "1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 1 " +
                    "1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 1 " +
                    "1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 " +
                    "1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 " +
                    "1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 " +
                    "1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 " +
                    "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 " +
                    "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ";
            splitString = levelString.split(" ");
    }


}
