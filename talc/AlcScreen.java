package talc;

import engine.GameObject;
import engine.Screen;
import engine.Systems.*;
import engine.mapTools.LevelFile;
import engine.mapTools.MapGenerator;
import engine.support.Vec2d;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import talc.objects.AlcObject;
import talc.objects.GarbageBin;
import talc.objects.Tier1Flower;
import talc.objects.Tier2Flower;

public class AlcScreen extends Screen {
    public AlcScreen(String title) {
        super(title);
    }

    boolean SpritesLoaded;

    public AlcScreen(String title, Vec2d windowSize, Double gameDimensions) {
        super(title, windowSize, gameDimensions);
        this.windowSize = windowSize;
        gameManager = new AlcMama(0.0, 0.0, windowSize.x, windowSize.y, Color.color(.80,.86,.84));
        gameManager.myScreen = this;
        DrawSystem drawSystem = new DrawSystem(gameManager);
        gameManager.addSystem(drawSystem);
        TickSystem tickSystem = new TickSystem(gameManager);
        gameManager.addSystem(tickSystem);
        MouseUpdateSystem mouseUpdateSystem = new MouseUpdateSystem(gameManager);
        gameManager.addSystem(mouseUpdateSystem);
        AlcCollision collisionSystem = new AlcCollision(gameManager);
        gameManager.addSystem(collisionSystem);
        SpriteSystem spriteSystem = new SpriteSystem(gameManager);
        gameManager.addSystem(spriteSystem);
        GameObject testObj = new GameObject(new Vec2d(90, 90), new Vec2d(350, 95), new Vec2d(0, 0), Color.color(.65, .60, .52));
        testObj.addSystem(drawSystem, 2.0);
        GarbageBin trash = new GarbageBin(new Vec2d(450, 100), new Vec2d(75, 75), new Vec2d(0, 0), Color.BLUE);
        trash.setMama(gameManager);
        trash.addSystem(drawSystem, 2.0);
        trash.addSystem(collisionSystem, 3.0);
        trash.addSystem(spriteSystem, 3.0);
        gameManager.gameObjects.add(trash);
        gameManager.gameObjects.add(testObj);
        Tier1Flower PinkFlower = new Tier1Flower(new Vec2d(100, 100), new Vec2d(75, 75), new Vec2d(0, 0), Color.PINK);
        PinkFlower.addSystem(drawSystem, 3.0);
        PinkFlower.setMama(gameManager);
        PinkFlower.addSystem(mouseUpdateSystem, 3.0);
        PinkFlower.addSystem(collisionSystem, 3.0);
        PinkFlower.addSystem(spriteSystem, 3.0);
        PinkFlower.isPink = true;
        gameManager.gameObjects.add(PinkFlower);
        Tier1Flower BlueFlower = new Tier1Flower(new Vec2d(185, 100), new Vec2d(75, 75), new Vec2d(0, 0), Color.BLUE, new Vec2d(140.0, 0.0), new Vec2d(70.0, 70.0));
        BlueFlower.addSystem(drawSystem, 3.0);
        BlueFlower.setMama(gameManager);
        BlueFlower.addSystem(mouseUpdateSystem, 3.0);
        BlueFlower.addSystem(collisionSystem, 3.0);
        BlueFlower.addSystem(spriteSystem, 3.0);
        BlueFlower.isBlue = true;
        gameManager.gameObjects.add(BlueFlower);
        Tier1Flower OrangeFlower = new Tier1Flower(new Vec2d(270, 100), new Vec2d(75, 75), new Vec2d(0, 0), Color.BLUE, new Vec2d(70.0, 0.0), new Vec2d(70.0, 70.0));
        OrangeFlower.addSystem(drawSystem, 3.0);
        OrangeFlower.setMama(gameManager);
        OrangeFlower.addSystem(mouseUpdateSystem, 3.0);
        OrangeFlower.addSystem(collisionSystem, 3.0);
        OrangeFlower.addSystem(spriteSystem, 3.0);
        OrangeFlower.isOrange = true;
        gameManager.gameObjects.add(OrangeFlower);
        Tier1Flower PurpleFlower = new Tier1Flower(new Vec2d(355, 100), new Vec2d(75, 75), new Vec2d(0, 0), Color.BLUE, new Vec2d(210.0, 0.0), new Vec2d(70.0, 70.0));
        PurpleFlower.addSystem(drawSystem, 3.0);
        PurpleFlower.setMama(gameManager);
        PurpleFlower.addSystem(mouseUpdateSystem, 3.0);
        PurpleFlower.addSystem(collisionSystem, 3.0);
        PurpleFlower.addSystem(spriteSystem, 3.0);
        PurpleFlower.isPurple = true;
        gameManager.gameObjects.add(PurpleFlower);
        SpritesLoaded = false;
    }
@Override
    public void onTick(long nanosSincePreviousTick){
        if (!SpritesLoaded) {
            System.out.println("drawing sprites!");
            Image spritesheet = new Image("talc/SpriteSheets/flowerspritesheet2.png");
            for (GameSystem system : gameManager.gameSystems) {
                if (system.getTag().equals("sprite")){
                    ((SpriteSystem) system).setSpriteLoaderSheet(spritesheet);
                }
            }
            SpritesLoaded = true;
        }
        super.onTick(nanosSincePreviousTick);
    }
}
