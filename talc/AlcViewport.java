package talc;

import engine.Viewport;
import engine.support.Vec2d;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class AlcViewport extends Viewport {
    public AlcViewport(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        scaleAdjust = 1.0;
        offsetX = 0.0;
        offsetY = 0.0;
    }

    public Double scaleAdjust;
    public Double offsetX;
    public Double offsetY;

    public Affine getAffine(){
        Affine viewportAdjust = new Affine();
        viewportAdjust.appendScale(scaleAdjust, scaleAdjust);
        viewportAdjust.appendTranslation(offsetX, offsetY);
        return viewportAdjust;
    }

    public void affineAdjustTranslate(KeyEvent e){
        System.out.println("VIEWPORT printing teehee");
        if (e.getCharacter().equals("w")){
            offsetY = offsetY - 40.0 * scaleAdjust;
        } else if (e.getCharacter().equals("a")){
            offsetX = offsetX - 40.0 * scaleAdjust;
        } else if (e.getCharacter().equals("s")){
            offsetY = offsetY + 40.0 * scaleAdjust;
        } else if (e.getCharacter().equals("d")){
            offsetX = offsetX + 40.0 * scaleAdjust;
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
                offsetX = this.offsetX - scrollEvent.getDeltaY() * .025 * windowSize.x * .05;
                offsetY = this.offsetY - scrollEvent.getDeltaY() * .025 * windowSize.y * .05;
            }
        }
    }
}
