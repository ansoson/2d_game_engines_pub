package twiz.objects;

import engine.Systems.GameSystem;
import engine.Systems.SpriteSystem;
import engine.mapTools.WallTile;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TreeTile extends WallTile {
    public TreeTile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.spriteCoords = new Vec2d(160.0, 320.0);
        this.spriteDims = new Vec2d(160.0, 160.0);
    }

    public void situationalize(){
        beenSituationalized = true;
        int binaryPattern = 111111111;
        if (eightSquare.get(0) != null && eightSquare.get(0).tileType != 1) {
            binaryPattern = binaryPattern - 10000000;
        }
        if (eightSquare.get(1) != null && eightSquare.get(1).tileType != 1) {
            binaryPattern = binaryPattern - 1000000;
        }
        if (eightSquare.get(2) != null && eightSquare.get(2).tileType != 1) {
            binaryPattern = binaryPattern - 100000;
        }
        if (eightSquare.get(3) != null && eightSquare.get(3).tileType != 1) {
            binaryPattern = binaryPattern - 10000;
        }
        if (eightSquare.get(4) != null && eightSquare.get(4).tileType != 1) {
            binaryPattern = binaryPattern - 1000;
        }
        if (eightSquare.get(5) != null && eightSquare.get(5).tileType != 1) {
            binaryPattern = binaryPattern - 100;
        }
        if (eightSquare.get(6) != null && eightSquare.get(6).tileType != 1) {
            binaryPattern = binaryPattern - 10;
        }
        if (eightSquare.get(7) != null && eightSquare.get(7).tileType != 1) {
            binaryPattern = binaryPattern - 1;
        }

        System.out.println(binaryPattern);
        switch (binaryPattern) {
            case 100000000 -> this.spriteCoords = new Vec2d(160.0, 320.0);
            case 111111110 -> this.spriteCoords = new Vec2d(640.0, 480.0);
            case 111111100, 111111000, 111111001 -> this.spriteCoords = new Vec2d(160.0, 480.0);
            case 101101011, 111101011, 101101111, 111101111 -> this.spriteCoords = new Vec2d(320.0, 320.0);
            case 101111111 -> this.spriteCoords = new Vec2d(640.0, 320.0);
            case 111011111 -> this.spriteCoords = new Vec2d(800.0, 320.0);
            case 100011111, 100111111, 110011111 -> this.spriteCoords = new Vec2d(320.0, 480.0);
            case 111010110, 111110110, 111010111, 111110111 -> this.spriteCoords = new Vec2d(480.0, 480.0);
            case 111111011 -> this.spriteCoords = new Vec2d(800.0, 480.0);
            case 111010000, 111110000, 111010100, 111110100 -> this.spriteCoords = new Vec2d(1120, 320.0);
            case 100010110, 110010110, 100010111, 110010111 -> this.spriteCoords = new Vec2d(1120, 480.0);
            case 101101000, 111101000, 101101001, 111101001 -> this.spriteCoords = new Vec2d(960, 320.0);
            case 100001011, 100101011, 100001111, 100101111 -> this.spriteCoords = new Vec2d(960, 480.0);
            default -> this.spriteCoords = new Vec2d(480.0, 320.0);
        }
    }

    @Override
    public void draw(GraphicsContext g) {
        for (GameSystem system : myMama.gameSystems) {
            if (system.getTag().equals("sprite")) {
                ((SpriteSystem) system).drawSprite(g,spriteCoords.x, spriteCoords.y, spriteDims.x, spriteDims.y,
                        this.getTransform().getLocation().x,
                        this.getTransform().getLocation().y,
                        this.getTransform().getDimensions().x,
                        this.getTransform().getDimensions().y);
            }
        }
    }

}
