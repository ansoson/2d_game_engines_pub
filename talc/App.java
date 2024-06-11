package talc;

import engine.Application;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;

public class App extends Application {
    protected App(String title) {
        super(title);
    }

    protected App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
        super(title, windowSize, debugMode, fullscreen);
        this.windowSize = windowSize;
        AlcScreen beginningScreen = new AlcScreen("Alchemy", windowSize, gameDimensions);
        screens = new ArrayList<>();
        screens.add(beginningScreen);
        currentScreen = beginningScreen;
    }

    protected void onTick(long nanosSincePreviousTick) {
        super.onTick(nanosSincePreviousTick);
    }

    @Override
    protected void onResize(Vec2d newSize) {
        super.onResize(newSize);
    }

    protected void onDraw(GraphicsContext g) {
        super.onDraw(g);
        currentScreen.onDraw(g);
    }

    protected void onMousePressed(MouseEvent e) {
        currentScreen.onMousePressed(e, mouseCoords);
    }

    protected void onMouseReleased(MouseEvent e) {
        currentScreen.onMouseReleased(e, mouseCoords);
    }

    @Override
    protected void onMouseDragged(MouseEvent e) {
        mouseCoords = new Vec2d(e.getX(), e.getY());
        currentScreen.onMouseDragged(e, mouseCoords);
    }

    @Override
    protected void onMouseMoved(MouseEvent e) {
        mouseCoords = new Vec2d(e.getX(), e.getY());
        currentScreen.onMouseMoved(e, mouseCoords);
    }

    protected void onKeyTyped(KeyEvent e) {
        currentScreen.onKeyTyped(e);
    }

    protected void onMouseWheelMoved(ScrollEvent scrollEvent){
        currentScreen.onMouseWheelMoved(scrollEvent);
    }

}
