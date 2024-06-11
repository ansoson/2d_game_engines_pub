package twiz;

import engine.Screen;
import engine.Systems.*;
import engine.mapTools.SituationalTileIndexer;
import engine.mapTools.Tile;
import engine.support.Vec2d;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import twiz.levels.Level2;
import twiz.levels.Level3;
import twiz.levels.WizGenerator;
import twiz.objects.MainChar;

import java.util.ArrayList;

public class Level3Screen extends Screen {
    public Level3Screen(String title, Vec2d windowSize, Double gameDimensions) {
        super(title, windowSize, gameDimensions);
        this.windowSize = windowSize;
        gameManager = new twizManager(0.0, 0.0, windowSize.x, windowSize.y, Color.color(.47, .64, .36));
        gameManager.myScreen = this;
        DrawSystem drawSystem = new DrawSystem(gameManager);
        gameManager.addSystem(drawSystem);
        TickSystem tickSystem = new TickSystem(gameManager);
        gameManager.addSystem(tickSystem);
        MouseUpdateSystem mouseUpdateSystem = new MouseUpdateSystem(gameManager);
        gameManager.addSystem(mouseUpdateSystem);
        CollisionSystem collisionSystem = new CollisionSystem(gameManager);
        gameManager.addSystem(collisionSystem);
        SpriteSystem spriteSystem = new SpriteSystem(gameManager);
        gameManager.addSystem(spriteSystem);
        KeyPressSystem keyPressSystem = new KeyPressSystem(gameManager);
        gameManager.addSystem(keyPressSystem);
        SpritesLoaded = false;
        MainChar mainChar = new MainChar(new Vec2d(160.0, 160.0), new Vec2d(80.0, 80.0), new Vec2d(0.0, 0.0), Color.BLACK);
        mainChar.addSystem(drawSystem, 4.0);
        mainChar.setMama(gameManager);
        mainChar.addSystem(collisionSystem, 3.0);
        mainChar.addSystem(spriteSystem, 3.0);
        mainChar.addSystem(keyPressSystem, 3.0);
        mainChar.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(mainChar);
        ArrayList<Tile> map = new WizGenerator().GenerateMap(new Level3(), gameManager);
        new SituationalTileIndexer().indexTiles(new Level3(), map);
        for (Tile tile: map){
            tile.situationalize();
        }
    }

    public void onTick(long nanosSincePreviousTick){
        if (!SpritesLoaded) {
            System.out.println("drawing sprites!");
            Image spritesheet = new Image("twiz/spritesheet/wiz_spritesheet.png");
            for (GameSystem system : gameManager.gameSystems) {
                if (system.getTag().equals("sprite")){
                    ((SpriteSystem) system).setSpriteLoaderSheet(spritesheet);
                }
            }
            SpritesLoaded = true;
        }
        super.onTick(nanosSincePreviousTick);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        super.onKeyPressed(e);
        for (GameSystem system : gameManager.gameSystems) {
            if (system.getTag().equals("keys")){
                ((KeyPressSystem) system).sendKeyDown(e);
            }
        }
    }

    public void onKeyLifted(KeyEvent e) {
        super.onKeyPressed(e);
        for (GameSystem system : gameManager.gameSystems) {
            if (system.getTag().equals("keys")){
                ((KeyPressSystem) system).sendKeyUp(e);
            }
        }
    }
}