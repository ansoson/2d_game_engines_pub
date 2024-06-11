package engine;

import engine.Systems.*;
import engine.UITools.UIElement;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GameManager {
    protected Vec2d location = null;
    protected Vec2d dimensions = null;
    protected Color BGColor;
    public Double squareStartX = 0.0;
    public Double squareStartY = 0.0;
    public ArrayList<UIElement> uiChildren;
    public Vec2d mouseCoords = new Vec2d(0.0, 0.0);
    public Screen myScreen;
    public ArrayList<GameSystem> gameSystems;
    public ArrayList<GameObject> gameObjects;
    public Viewport viewport;
    public boolean itsJoever = false;

    public ArrayList<GameObject> onDeathRow;
    public GameSystem collisions;

    /**
     * This class is the main thing that manages sizing for each screen, and will contain the specialized rules for
     * every game
     * @param x
     * @param y
     * @param width
     * @param length
     */
    protected GameManager(Double x, Double y, Double width, Double length, Color color) {
        this.location = new Vec2d(x, y);
        this.dimensions = new Vec2d(width, length);
        this.BGColor = color;
        this.gameSystems = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.viewport = new Viewport(x, y, width, length, color);
        uiChildren = new ArrayList<>();
        onDeathRow = new ArrayList<>();
    }

    public void onTick(long nanosSincePreviousTick) {
        for (UIElement element : uiChildren) {
            element.onTick(nanosSincePreviousTick, mouseCoords);
        }
        for (GameSystem system : gameSystems){
            if (system.getTag().equals("collision")){
                try{
                    ((CollisionSystem) system).runCollisions();
                }
                catch(ConcurrentModificationException e){
                    System.out.println("oopsiedoodles");
                }
            }
            if (system.getTag().equals("ticks")){
                ((TickSystem) system).onTick(nanosSincePreviousTick);
            }
        }
    };
    protected void onDraw(GraphicsContext g) {
        g.setFill(BGColor);
        g.setTransform(new Affine());
        g.fillRect(this.location.x - dimensions.x * .5, this.location.y - dimensions.y *.5,  dimensions.x * 2, dimensions.y * 2);
        for (GameSystem system : gameSystems){
            if (system.getTag().equals("draws")){
                ((DrawSystem) system).onDraw(g);
            }
        }
        for (UIElement element : uiChildren) {
            element.onDraw(g);
        }
    };

    /**
     * This function should establish resize rules for the game
     */
    protected void onResize(Vec2d resizeDims, Vec2d newSize) {
        Vec2d resizedOffset = applyResizeOffsetRules(resizeDims, newSize);
        Vec2d resizedScale = applyResizeScaleRules(resizeDims, newSize);
//        this.dimensions = newSize;
        for (UIElement child : uiChildren) {
            child.onResize(resizedScale, resizedOffset);
        }
    }

    protected Vec2d applyResizeScaleRules(Vec2d resizeDims, Vec2d newSize) {
        Vec2d resizeReturn;
        if (newSize.x >= newSize.y * myScreen.gameDimensions) {
            squareStartX = (newSize.x - newSize.y * myScreen.gameDimensions)/2.0;
            squareStartY = 0.0;
            this.location = new Vec2d(squareStartX, squareStartY);
            resizeReturn = new Vec2d((newSize.x - 2.0 * squareStartX)/this.dimensions.x, newSize.y/this.dimensions.y);
            this.dimensions = new Vec2d(newSize.x - 2.0 * squareStartX, newSize.y);
        }
        else {
            squareStartY = (newSize.y * myScreen.gameDimensions - newSize.x)/2.0;
            squareStartX = 0.0;
            this.location = new Vec2d(squareStartX, squareStartY);
            resizeReturn = new Vec2d(newSize.x/this.dimensions.x, (newSize.y - squareStartY * 2.0)/this.dimensions.x);
            this.dimensions = new Vec2d(newSize.x, newSize.y - squareStartY * 2.0);
        }
        System.out.println(dimensions + "   " + location);
        return resizeReturn;
    }

    protected Vec2d applyResizeOffsetRules(Vec2d resizeDims, Vec2d newSize){
        Vec2d resizeReturn;
        if (newSize.x >= newSize.y) {
            resizeReturn = new Vec2d((newSize.x - newSize.y)/2 - squareStartX, 0.0 - squareStartY);
        }
        else {
            resizeReturn = new Vec2d(0.0 - squareStartX, (newSize.y - newSize.x)/2 - squareStartY);
        }
        System.out.println(resizeReturn);
        return resizeReturn;
    }

    public void onClick(Vec2d coords) {
        for (UIElement child : uiChildren) {
            child.onClick(coords);
        }
    }
    public void turnShiftNoTick() {
    }
    public void addSystem(GameSystem gameSystem){
        this.gameSystems.add(gameSystem);
    }
    public void mouseManage(boolean mousePressed, boolean mouseReleased, Vec2d mouseCoords){
        for (GameSystem system : gameSystems){
            if (system.getTag().equals("mouse")){
                ((MouseUpdateSystem) system).mouseManage(mousePressed, mouseReleased, mouseCoords);
            }
        }
    }
    public void viewPortManageTranslate(KeyEvent e){
        System.out.println("viewportmanaging bigmama");
        this.viewport.affineAdjustTranslate(e);
    }

    public void viewPortManageScale(ScrollEvent scrollEvent){
    }

    public void changeViewport(int adjustmentX, int adjustmentY) {
        this.viewport.manualOffsetY = this.viewport.manualOffsetY + adjustmentY;
        this.viewport.manualOffsetX = this.viewport.manualOffsetX + adjustmentX;
    }

    public void maxZoomOut(){
        this.viewport.scaleAdjust = .5;
        this.viewport.manualOffsetY = 800.0;
        this.viewport.manualOffsetX = 225.0;
    }

}
