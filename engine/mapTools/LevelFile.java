package engine.mapTools;

import debugger.support.Vec2d;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class LevelFile {

    public boolean autoMC = true;
    protected int levelDimsX = 5;
    protected int levelDimsY = 5;
    protected Double tileSize = 160.0;
    protected String levelString =  "1 1 1 1 1 " +
                                    "1 0 0 0 1 " +
                                    "1 0 2 0 1 " +
                                    "1 0 0 0 1 " +
                                    "1 1 1 1 1";
    protected String[] splitString = levelString.split(" ");

    public LevelFile(){
    }

    public LevelFile(File textFile) throws IOException {
        levelString = Files.readString(textFile.toPath(), StandardCharsets.UTF_8);
        String[] splitStringTemp = levelString.split(" ");
        levelDimsX = Integer.parseInt(splitStringTemp[0]);
        levelDimsY = Integer.parseInt(splitStringTemp[1]);
        tileSize = Double.parseDouble(splitStringTemp[2]);
        splitString = Arrays.copyOfRange(splitStringTemp, 3, splitStringTemp.length);
    }

}
