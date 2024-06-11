package tnin;

import engine.Application;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.xml.sax.SAXException;
import twiz.Level2Screen;
import twiz.Level3Screen;
import twiz.twizScreen;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    int index;
    protected App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) throws IOException, ParserConfigurationException, SAXException {
        super(title, windowSize, debugMode, fullscreen);
        this.windowSize = windowSize;
        testScreen beginningScreen = new testScreen("NinTest", windowSize, gameDimensions);
        beginningScreen.myApp = this;
        screens = new ArrayList<>();
        screens.add(beginningScreen);
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

