package engine.mapTools;

import engine.GameObject;

import java.util.ArrayList;

public class SituationalTileIndexer {

    public SituationalTileIndexer(){}

    public void indexTiles(LevelFile levelFile, ArrayList<Tile> tiles){
        int index = 0;
        int tilesX = levelFile.levelDimsX;
        for (Tile tile: tiles) {
            //top left
            if (index - tilesX -1 >= 0) {
                tile.eightSquare.add(tiles.get(index - tilesX - 1));
            } else {
                tile.eightSquare.add(null);
            }
            //top middle
            if (index - tilesX >= 0) {
                tile.eightSquare.add(tiles.get(index - tilesX));
            } else {
                tile.eightSquare.add(null);
            }
            //top right
            if (index - tilesX + 1 >= 0) {
                tile.eightSquare.add(tiles.get(index - tilesX + 1));
            } else {
                tile.eightSquare.add(null);
            }
            //left
            if (index - 1 >= 0) {
                tile.eightSquare.add(tiles.get(index - 1));
            } else {
                tile.eightSquare.add(null);
            }
            //right
            if (index + 1 < tiles.size()) {
                tile.eightSquare.add(tiles.get(index + 1));
            } else {
                tile.eightSquare.add(null);
            }
            //bottom left
            if (index + tilesX - 1 < tiles.size()) {
                tile.eightSquare.add(tiles.get(index + tilesX - 1));
            } else {
                tile.eightSquare.add(null);
            }
            //bottom middle
            if (index + tilesX < tiles.size()) {
                tile.eightSquare.add(tiles.get(index + tilesX));
            } else {
                tile.eightSquare.add(null);
            }
            //bottom right
            if (index + tilesX + 1 < tiles.size()) {
                tile.eightSquare.add(tiles.get(index + tilesX + 1));
            } else {
                tile.eightSquare.add(null);
            }
            index++;
        }
    }

}
