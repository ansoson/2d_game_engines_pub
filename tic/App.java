package tic;

import engine.Application;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


/**
 * This is your Tic-Tac-Toe top-level, App class.
 * This class will contain every other object in your game.
 */
public class App extends Application {

  public App(String title) {
    super(title);
  }

  public App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
    super(title, windowSize, debugMode, fullscreen);
    this.windowSize = windowSize;
    GameScreen beginningScreen = new GameScreen("Tic-Tac-Toe", windowSize, gameDimensions);
    screens = new ArrayList<>();
    screens.add(beginningScreen);
    currentScreen = beginningScreen;
  }

  public WinnerScreen resultScreen;

  protected void onTick(long nanosSincePreviousTick) {
    super.onTick(nanosSincePreviousTick);
    if (resultScreen != null){
      resultScreen.gameManager.mouseCoords = mouseCoords;
      resultScreen.onTick(nanosSincePreviousTick);
    }
  }

  @Override
  protected void onDraw(GraphicsContext g) {
    super.onDraw(g);
    currentScreen.onDraw(g);
    if (resultScreen != null){
      resultScreen.onDraw(g);
    }
  }

  protected void onResize(Vec2d newSize) {
    currentScreen.onResize(windowSize, newSize, gameDimensions);
    if (resultScreen != null){
      resultScreen.onResize(windowSize, newSize, gameDimensions);
    }
    windowSize = newSize;
  }

  public void onMouseClicked(MouseEvent e) {
    if (resultScreen != null){
      resultScreen.onMouseClicked(e);
    } else {
      currentScreen.onMouseClicked(e, mouseCoords);
    }
  }

  protected void onStartup() {
    currentScreen.myApp = this;
  }

  protected void itsJoever(int result){
    resultScreen = new WinnerScreen("Tic Winna", windowSize, gameDimensions, result);
    resultScreen.myApp = this;
    resultScreen.onStartup();
  }

  @Override
  public void onRestart() {
    currentScreen = new GameScreen("Tic-Tac-Toe", windowSize, gameDimensions);
    currentScreen.myApp = this;
    currentScreen.onStartup();
    resultScreen = null;
  }
}

