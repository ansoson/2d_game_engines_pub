package tnin;

import engine.GameManager;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import twiz.TwizViewport;

public class tninManager extends GameManager {
    /**
     * This class is the main thing that manages sizing for each screen, and will contain the specialized rules for
     * every game
     *
     * @param x
     * @param y
     * @param width
     * @param length
     * @param color
     */
    protected tninManager(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        this.viewport = new TwizViewport(x, y, width, length, color);
    }
    public void onTick(long nanosSincePreviousTick) {
        super.onTick(nanosSincePreviousTick);
    }

    public void viewPortManageScale(ScrollEvent scrollEvent){
        this.viewport.affineAdjustScale(scrollEvent, mouseCoords, myScreen.windowSize);
    }


}