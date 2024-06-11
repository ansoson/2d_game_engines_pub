package engine;

import engine.support.FXFrontEnd;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;

/**
 * This is your main Application class that you will contain your
 * 'draws' and 'ticks'. This class is also used for controlling
 * user input.
 */
public class Application extends FXFrontEnd {

  public ArrayList<Screen> screens;
  public Screen currentScreen;
  public Vec2d windowSize;
  public Double gameDimensions;
  protected Double incomingSizeShiftX = 1.0;
  protected Double incomingSizeShiftY = 1.0;
  protected Vec2d mouseCoords = new Vec2d(0.0, 0.0);
  protected Application(String title) {
    super(title);
  }
  protected Application(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
    super(title, windowSize, debugMode, fullscreen);
    this.windowSize = windowSize;
    this.gameDimensions = windowSize.x/windowSize.y;
  }

  /**
   * Called periodically and used to update the state of your game.
   * @param nanosSincePreviousTick	approximate number of nanoseconds since the previous call
   */
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    currentScreen.gameManager.mouseCoords = mouseCoords;
    currentScreen.onTick(nanosSincePreviousTick);
  }

  /**
   * Called after onTick().
   */
  @Override
  protected void onLateTick() {
    // Don't worry about this method until you need it. (It'll be covered in class.)
  }

  /**
   *  Called periodically and meant to draw graphical components.
   * @param g		a {@link GraphicsContext} object used for drawing.
   */
  @Override
  protected void onDraw(GraphicsContext g) {
    g.clearRect(0, 0, windowSize.x, windowSize.y);
    incomingSizeShiftX = 1.0;
    incomingSizeShiftY = 1.0;
  }

  /**
   * Called when a key is typed.
   * @param e		an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyTyped(KeyEvent e) {
    if (e.getCode() == KeyCode.ESCAPE){
      shutdown();
      System.out.println("ESCAPING");
    }
    currentScreen.onKeyTyped(e);
    System.out.println(e.getCharacter());
  }

  /**
   * Called when a key is pressed.
   * @param e		an FX {@link KeyEvent} representing the input event.
   */
  @Override
  public void onKeyPressed(KeyEvent e) {
    System.out.println("KEY PRESSED");
    if (e.getCode() == KeyCode.ESCAPE){
      shutdown();
      System.out.println("ESCAPING");
    }
    currentScreen.onKeyPressed(e);
  }

  /**
   * Called when a key is released.
   * @param e		an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyReleased(KeyEvent e) {
    if (e.getCode() == KeyCode.ESCAPE){
      shutdown();
      System.out.println("ESCAPING");
    }
    currentScreen.onKeyLifted(e);
  }

  /**
   * Called when the mouse is clicked.
   * @param e		an FX {@link MouseEvent} representing the input event.
   */
  @Override
  public void onMouseClicked(MouseEvent e) {
    currentScreen.onMouseClicked(e, mouseCoords);
  }

  /**
   * Called when the mouse is pressed.
   * @param e		an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMousePressed(MouseEvent e) {
    mouseCoords = new Vec2d(e.getX(), e.getY());
  }

  /**
   * Called when the mouse is released.
   * @param e		an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseReleased(MouseEvent e) {
    mouseCoords = new Vec2d(e.getX(), e.getY());
  }

  /**
   * Called when the mouse is dragged.
   * @param e		an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseDragged(MouseEvent e) {
    mouseCoords = new Vec2d(e.getX(), e.getY());
  }

  /**
   * Called when the mouse is moved.
   * @param e		an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseMoved(MouseEvent e) {
    mouseCoords = new Vec2d(e.getX(), e.getY());
  }

  /**
   * Called when the mouse wheel is moved.
   * @param e		an FX {@link ScrollEvent} representing the input event.
   */
  @Override
  protected void onMouseWheelMoved(ScrollEvent e) {
    currentScreen.onMouseWheelMoved(e);
  }

  /**
   * Called when the window's focus is changed.
   * @param newVal	a boolean representing the new focus state
   */
  @Override
  protected void onFocusChanged(boolean newVal) {

  }

  /**
   * Called when the window is resized.
   * @param newSize	the new size of the drawing area.
   */
  @Override
  protected void onResize(Vec2d newSize) {
    //store the new size somewhere
    currentScreen.onResize(windowSize, newSize, gameDimensions);
    windowSize = newSize;
  }

  /**
   * Called when the app is shutdown.
   */
  @Override
  protected void onShutdown() {

  }

  /**
   * Called when the app is starting up.s
   */
  @Override
  protected void onStartup() {
    System.out.println("bing bong");
  }

  protected void itsJoever(int result){
  }

  public void onRestart() {}
  public void changeScreen(){}

}


