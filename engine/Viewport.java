package engine;

import engine.UITools.UIElement;
import engine.support.Vec2d;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Viewport extends UIElement {
    public Viewport(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        scaleAdjust = 1.0;
        offsetX = 0.0;
        offsetY = 0.0;
    }

    public Double scaleAdjust;
    public Double offsetX;
    public Double offsetY;
    public Double manualOffsetX = 0.0;
    public Double manualOffsetY = 0.0;


    public Affine getAffine(){
        Affine viewportAdjust = new Affine();
        viewportAdjust.appendTranslation(offsetX, offsetY);
        viewportAdjust.appendScale(scaleAdjust, scaleAdjust);
        viewportAdjust.appendTranslation(manualOffsetX, manualOffsetY);
        return viewportAdjust;
    }

    public void affineAdjustTranslate(KeyEvent e){
        switch (e.getCharacter()) {
            case "i" -> manualOffsetY = manualOffsetY - 40.0;
            case "j" -> manualOffsetX = manualOffsetX - 40.0;
            case "k" -> manualOffsetY = manualOffsetY + 40.0;
            case "l" -> manualOffsetX = manualOffsetX + 40.0;
        }
    }

    public void affineAdjustScale(ScrollEvent scrollEvent, Vec2d mouseCoords, Vec2d windowSize){
        System.out.println("VIEWPORT SCALE ADJUSTING");
        if (scaleAdjust >=.5 && scaleAdjust <= 4.0) {
            if (this.scaleAdjust + scrollEvent.getDeltaY()* .0025 <= .5) {
                this.scaleAdjust = .5;
            } else if (this.scaleAdjust + scrollEvent.getDeltaY()* .0025 >= 4.0){
                this.scaleAdjust = 4.0;
            } else {
                this.scaleAdjust = this.scaleAdjust + scrollEvent.getDeltaY() * .0025;
                offsetX = (1600 - (1600 * scaleAdjust))/2;
                offsetY = (900 - (900 * scaleAdjust))/2;
            }
        }
    }
}
