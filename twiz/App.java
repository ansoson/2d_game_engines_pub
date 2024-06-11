package twiz;

import engine.Application;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    int index;
    protected App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) throws IOException {
        super(title, windowSize, debugMode, fullscreen);
        this.windowSize = windowSize;
        twizScreen beginningScreen = new twizScreen("Wiz1", windowSize, gameDimensions);
        Level2Screen midScreen = new Level2Screen("Wizz", windowSize, gameDimensions);
        Level3Screen endScreen = new Level3Screen("Wizzz", windowSize, gameDimensions);
        beginningScreen.myApp = this;
        midScreen.myApp = this;
        endScreen.myApp = this;
        screens = new ArrayList<>();
        screens.add(beginningScreen);
        screens.add(midScreen);
        screens.add(endScreen);
        currentScreen = beginningScreen;
        index = 0;
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
    public void changeScreen(){
        if (index == 0) {
            currentScreen = screens.get(1);
            index++;
        } else if (index == 1) {
            currentScreen = screens.get(2);
            index++;
        }
    }

}

