package engine;

import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class Screen{

    public GameManager gameManager;
    public Application myApp;
    public double gameTime = 10;
    public Vec2d windowSize;
    public Double gameDimensions;
    protected boolean SpritesLoaded;

    public Screen(String title) {
        gameManager = new GameManager(100.0, 100.0, 100.0, 100.0, Color.color(.80,.86,.84));
    }

    public Screen(String title, Vec2d windowSize, Double gameDimensions) {
        gameManager = new GameManager(0.0, 0.0, windowSize.x, windowSize.y, Color.color(.80,.86,.84));
        this.gameDimensions = gameDimensions;
        SpritesLoaded = false;
    }
    public void onTick(long nanosSincePreviousTick){
        gameManager.onTick(nanosSincePreviousTick);
    }
    public void onDraw(GraphicsContext g){
        gameManager.onDraw(g);
    }
    public void onResize(Vec2d currentSize, Vec2d newSize, Double gameDims) {
//        super.onResize(newSize);
        gameManager.onResize(currentSize, newSize);
        this.windowSize = newSize;
    }
    public void onMousePressed(MouseEvent e, Vec2d mouseCoords) {
        System.out.println(e.getX());
        gameManager.mouseManage(true, false, mouseCoords);
    }
    public void onMouseReleased(MouseEvent e, Vec2d mouseCoords) {
        gameManager.mouseManage(false, true, mouseCoords);
    }
    public void onMouseDragged(MouseEvent e, Vec2d mouseCoords) {
//        mouseCoords = new Vec2d(e.getX(), e.getY());
        gameManager.mouseManage(false, false, mouseCoords);
    }
    public void onMouseMoved(MouseEvent e, Vec2d mouseCoords) {
//        mouseCoords = new Vec2d(e.getX(), e.getY());
        gameManager.mouseManage(false, false, mouseCoords);
    }
    public void onMouseClicked(MouseEvent e, Vec2d mouseCoords){
    }
    public void onKeyTyped(KeyEvent e) {
        gameManager.viewPortManageTranslate(e);
    }
    public void onKeyPressed(KeyEvent e) {}
    public void onKeyLifted(KeyEvent e) {}
    public void onMouseWheelMoved(ScrollEvent e) {
        gameManager.viewPortManageScale(e);
    }
    public void itsJoever(int result){
        myApp.itsJoever(result);
    }
    public void onStartup(){
        gameManager.myScreen = this;
        onResize(windowSize, windowSize, gameDimensions);
    }











}
