package tic;

import engine.support.FXApplication;
import engine.support.FXFrontEnd;
import engine.support.Vec2d;

/**
 * Here is your main class. You should not have to edit this
 * class at all unless you want to change your window size
 * or turn off the debugging information.
 */
public class Main {

    public static void main(String[] args) {
        Vec2d openingSize = new Vec2d(800, 800);
        FXFrontEnd app = new App("Tic-Tac-Toe",openingSize, true, false);
        FXApplication application = new FXApplication();
        application.begin(app);
    }
}
