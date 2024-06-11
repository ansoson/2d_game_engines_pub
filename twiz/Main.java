package twiz;

import engine.support.FXApplication;
import engine.support.FXFrontEnd;
import engine.support.Vec2d;

import java.io.IOException;

/**
 * Here is your main class. You should not have to edit this
 * class at all unless you want to change your window size
 * or turn off the debugging information.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Vec2d openingSize = new Vec2d(1600, 900);
        FXFrontEnd app = new App("Wiz",openingSize, true, false);
        FXApplication application = new FXApplication();
        application.begin(app);
    }

}
