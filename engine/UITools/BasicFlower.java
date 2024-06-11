package engine.UITools;

import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicFlower extends UIElement {

    private boolean petal1 = true;
    private boolean petal2 = true;
    private boolean petal3 = true;
    private boolean petal4 = true;

    private Color secretColor;

    public BasicFlower(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        this.color = color;
        this.secretColor = color;
        this.children.add(new BasicBox(x+ width*.33, y, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.66, y+ length*.33, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.33, y + length*.66, width *.33, length*.33, color));
        this.children.add(new BasicBox(x, y +length*.33, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.33, y + length*.33, width *.33, length*.33, Color.color(.92, .88, .60)));
    }

    public BasicFlower(Double x, Double y, Double width, Double length, Color color, Vec2d offset) {
        super(x, y, width, length, color);
        this.color = color;
        this.secretColor = color;
        this.children.add(new BasicBox(x+ width*.33, y, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.66, y+ length*.33, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.33, y + length*.66, width *.33, length*.33, color));
        this.children.add(new BasicBox(x, y +length*.33, width *.33, length*.33, color));
        this.children.add(new BasicBox(x+ width*.33, y + length*.33, width *.33, length*.33, Color.color(.92, .88, .60)));
        this.screenLockOffset = offset;
    }

    @Override
    public void onDraw(GraphicsContext g) {

        if(petal1){
            this.children.get(0).color = color;
            this.children.get(0).onDraw(g);
        }
        if(petal2){
            this.children.get(1).color = color;
            this.children.get(1).onDraw(g);
        }
        if(petal3){
            this.children.get(2).color = color;
            this.children.get(2).onDraw(g);
        }
        if(petal4){
            this.children.get(3).color = color;
            this.children.get(3).onDraw(g);
        }
        children.get(4).color = Color.color(.92, .88, .60);
        children.get(4).onDraw(g);
    }

    public void enactEvent(Integer which){
        if (which < 4) {
            if (which == 0) {
                petal1 = false;
            }
            if (which == 1) {
                petal2 = false;
            }
            if (which == 2) {
                petal3 = false;
            }
            if (which == 3) {
                petal4 = false;
            }
        }
        if (which == 4){
            returnPetals();
        }
    }

    public void returnPetals() {
        petal1 = true;
        petal2 = true;
        petal3 = true;
        petal4 = true;
    }

    public void upDateLocation(Vec2d difference) {
        for (UIElement child : children){
            child.location = new Vec2d(child.location.x - difference.x, child.location.y - difference.y);
        }
    }

}
