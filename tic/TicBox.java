package tic;

import engine.UITools.UIElement;
import engine.UITools.BasicCircle;
import engine.UITools.Button;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class TicBox extends Button {

    protected boolean XOrO = true;
    protected Color hoverColor2 = Color.color(.90, .75, .55);
    protected boolean selfDestructed = false;


    public TicBox(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        BasicCircle hovering = new BasicCircle(this.location.x, this.location.y, this.dimensions.x, this.dimensions.y, disappearColor);
        hovering.screenLockOffset = this.screenLockOffset;
        this.children.add(hovering);
    }


    public void onTick(long nanosSincePreviousTick, Vec2d mouseCoords) {
        super.onTick(nanosSincePreviousTick, mouseCoords);
            if (this.location.x + screenLockOffset.x <= mouseCoords.x && mouseCoords.x <= this.location.x + screenLockOffset.x + this.dimensions.x) {
                if (this.location.y + screenLockOffset.y <= mouseCoords.y && mouseCoords.y <= this.location.y + screenLockOffset.y + this.dimensions.y) {
                    /**
                     * If the button is being hovered
                     */
                    this.onHover();
                } else {
                    this.children.get(0).color = disappearColor;
                    this.children.get(1).color = disappearColor;
                }
            } else {
                this.children.get(0).color = disappearColor;
                this.children.get(1).color = disappearColor;
            }
    }

    public void onHover() {
        if (!selfDestructed){
            if (XOrO) {
                this.children.get(0).color = hoverColor;
                this.children.get(1).color = disappearColor;
            } else {
                this.children.get(1).color = hoverColor2;
                this.children.get(0).color = disappearColor;
            }
        }
    }

    @Override
    public int onClick(Vec2d mouseCoords) {

        if (selfDestructed) {
            return 0;
        }
        if (this.location.x + screenLockOffset.x <= mouseCoords.x && mouseCoords.x <= this.location.x + screenLockOffset.x + this.dimensions.x) {
            if (this.location.y + screenLockOffset.y <= mouseCoords.y && mouseCoords.y <= this.location.y + screenLockOffset.y + this.dimensions.y) {
                if (XOrO) {
                    this.children.get(0).color = hoverColor;
                } else {
                    this.children.get(1).color = hoverColor2;
                }
                this.selfDestruct();
                return 1;
            } else {
                return 0;
            }
        } return 0;
    }

    public void selfDestruct() {

        if (XOrO) {
            hoverColor2 =hoverColor;
            disappearColor = hoverColor;
            this.children.remove(1);
            this.children.add(this.children.get(0));
            selfDestructed = true;
        } else {
            hoverColor =hoverColor2;
            disappearColor = hoverColor2;
            this.children.remove(0);
            this.children.add(this.children.get(0));
            selfDestructed = true;
        }

    }

    public void onResize(Vec2d resizeDims, Vec2d resizeOffset) {
        this.location = new Vec2d(this.location.x * resizeDims.x, this.location.y * resizeDims.y);
        this.screenLockOffset = new Vec2d(this.screenLockOffset.x + resizeOffset.x, this.screenLockOffset.y + resizeOffset.y);
        this.dimensions = new Vec2d(this.dimensions.x * resizeDims.x, this.dimensions.y * resizeDims.y);

        if (selfDestructed) {
            this.children.get(0).onResize(resizeDims, resizeOffset);
        } else {
            for (UIElement element : children) {
                element.onResize(resizeDims, resizeOffset);
            }
        }

    };



}
