package tic;

import engine.UITools.Button;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class PlayAgainButton extends Button {
    public PlayAgainButton(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);

    }

    @Override
    public void onTick(long nanosSincePreviousTick, Vec2d mouseCoords) {
        super.onTick(nanosSincePreviousTick, mouseCoords);
        if (this.location.x + screenLockOffset.x <= mouseCoords.x && mouseCoords.x <= this.location.x + screenLockOffset.x + this.dimensions.x) {
            if (this.location.y + screenLockOffset.y <= mouseCoords.y && mouseCoords.y <= this.location.y + screenLockOffset.y + this.dimensions.y) {
                /**
                 * If the button is being hovered
                 */
                this.onHover();
                System.out.println("playagainbutton IM BEING HOVERED");
            }
        }
        this.color = Color.color(.59, .71, .65);
    }

    @Override
    public int onClick(Vec2d coords) {
        if (this.location.x + screenLockOffset.x <= coords.x && coords.x <= this.location.x + screenLockOffset.x + this.dimensions.x) {
            if (this.location.y + screenLockOffset.y <= coords.y && coords.y <= this.location.y + screenLockOffset.y + this.dimensions.y) {
                System.out.println("playagainbutton clicked!");
                return 2;
            }
        }
        return 1;
    }

    @Override
    public void onHover() {
        this.color = Color.color(.39, .51, .45);
    }
}
