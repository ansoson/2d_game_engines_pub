package talc;

import engine.GameManager;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class AlcMama extends GameManager {
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
    protected AlcMama(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        this.viewport = new AlcViewport(x, y, width, length, color);
    }

    @Override
    public void onTick(long nanosSincePreviousTick) {
        super.onTick(nanosSincePreviousTick);
    }

    public void viewPortManageScale(ScrollEvent scrollEvent){
        this.viewport.affineAdjustScale(scrollEvent, mouseCoords, myScreen.windowSize);
    }
}
