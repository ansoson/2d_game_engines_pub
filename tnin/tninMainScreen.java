package tnin;

import engine.GameObject;
import engine.Screen;
import engine.Systems.*;
import engine.support.Vec2d;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tnin.objects.*;

import java.io.IOException;

public class tninMainScreen extends Screen {
    boolean quickfix = false;
    int Waiting = 0;
    public tninMainScreen(String title, Vec2d windowSize, Double gameDimensions) throws IOException {
        super(title, windowSize, gameDimensions);
        this.windowSize = windowSize;
        gameManager = new tninManager(0.0, 0.0, windowSize.x, windowSize.y, Color.color(.47, .64, .36));
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
        GameObject obj1 = new circleObject(new Vec2d(300, 150), new Vec2d(200, 200), new Vec2d(0.0, 0.0), Color.BLACK);
        obj1.addSystem(drawSystem, 4.0);
        obj1.setMama(gameManager);
        obj1.addSystem(collisionSystem, 3.0);
        obj1.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(obj1);
        GameObject obj2 = new squareObject(new Vec2d(550, 150), new Vec2d(150, 150), new Vec2d(0.0, 0.0), Color.BLACK);
        obj2.addSystem(drawSystem, 4.0);
        obj2.setMama(gameManager);
        obj2.addSystem(collisionSystem, 3.0);
        obj2.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(obj2);
        Vec2d[] vp = { new Vec2d(900, 200),  new Vec2d(950, 300), new Vec2d(1050, 300),new Vec2d(1100, 200), new Vec2d(1000, 100)};
        GameObject obj3 = new PolygonObject(new Vec2d(900, 150), new Vec2d(100, 100), new Vec2d(0.0, 0.0), Color.BLACK, vp);
        obj3.addSystem(drawSystem, 4.0);
        obj3.setMama(gameManager);
        obj3.addSystem(collisionSystem, 3.0);
        obj3.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(obj3);
        MainNin mainChar = new MainNin(new Vec2d(160.0, 650.0), new Vec2d(80.0, 80.0), new Vec2d(0.0, 0.0), Color.BLACK);
        mainChar.addSystem(drawSystem, 4.0);
        mainChar.setMama(gameManager);
        mainChar.addSystem(collisionSystem, 3.0);
        mainChar.addSystem(spriteSystem, 3.0);
        mainChar.addSystem(keyPressSystem, 3.0);
        mainChar.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(mainChar);
        GameObject floor = new Floor(new Vec2d(80.0, 800.0), new Vec2d(1440, 200), new Vec2d(0.0, 0.0), Color.BLACK);
        floor.addSystem(drawSystem, 4.0);
        floor.setMama(gameManager);
        floor.addSystem(collisionSystem, 3.0);
        floor.addSystem(tickSystem, 3.0);
        gameManager.gameObjects.add(floor);
    }

    public void onTick(long nanosSincePreviousTick){

        if (!SpritesLoaded) {
            System.out.println("drawing sprites!");
            Image spritesheet = new Image("tnin/spritesheet/nin_spritesheet.png");
            for (GameSystem system : gameManager.gameSystems) {
                if (system.getTag().equals("sprite")){
                    ((SpriteSystem) system).setSpriteLoaderSheet(spritesheet);
                }
            }
            SpritesLoaded = true;
        } else {
            if (Waiting > 120) {
                super.onTick(nanosSincePreviousTick);
            }
            Waiting++;
        }
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
