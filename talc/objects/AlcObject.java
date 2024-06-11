package talc.objects;

import engine.GameObject;
import engine.Systems.GameSystem;
import engine.Systems.SpriteSystem;
import engine.UITools.BasicFlower;
import engine.collisionShapes.AxisAlignedBoxShape;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlcObject extends GameObject {
    public AlcObject(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        draggingCoords = location;
        placeHolderDraw = new BasicFlower(this.getTransform().getLocation().x,
                                            this.getTransform().getLocation().y,
                                            this.getTransform().getDimensions().x,
                                            this.getTransform().getDimensions().y,
                                            color);
        hasBeenDraggedYet = false;
        collisionHitbox = new AxisAlignedBoxShape(location, dimensions);
        spriteCoords = new Vec2d(0.0, 0.0);
        spriteDims = new Vec2d(70.0, 70.0);
    }

    protected Vec2d draggingCoords;
    public boolean beingDragged;
    protected boolean hasBeenDraggedYet;
    protected BasicFlower placeHolderDraw;

    public boolean isPink = false;
    public boolean isBlue = false;
    public boolean isPurple = false;
    public boolean isOrange = false;
    public boolean isPP = false;
    public boolean isOB = false;

    public int whatTier = 0;

    public Vec2d spriteCoords;
    public Vec2d spriteDims;

    public void mouseManage(boolean mousePressed, boolean mouseReleased, Vec2d mouseCoords) {
        if (mousePressed) {
            Double XLoc = this.getTransform().getLocation().x;
            Double YLoc = this.getTransform().getLocation().y;
            Double XOff = this.getTransform().getOffset().x;
            Double YOff = this.getTransform().getOffset().y;
            Double XDim = this.getTransform().getDimensions().x;
            Double YDim = this.getTransform().getDimensions().y;
            if ((XLoc + XOff + myMama.viewport.offsetX) <= mouseCoords.x/myMama.viewport.scaleAdjust && mouseCoords.x/myMama.viewport.scaleAdjust <= (XLoc + XOff + XDim + + myMama.viewport.offsetX)) {
                if ((YLoc + YOff + myMama.viewport.offsetY) <= mouseCoords.y/myMama.viewport.scaleAdjust && mouseCoords.y/myMama.viewport.scaleAdjust <= (YLoc + YOff + YDim + myMama.viewport.offsetY)) {
                    draggingCoords = mouseCoords;
                    beingDragged = true;
                    if (!hasBeenDraggedYet){
                        AlcObject menuReplacement = new Tier1Flower(this.getTransform().getLocation(),
                                this.getTransform().getDimensions(),
                                this.getTransform().getOffset(),
                                placeHolderDraw.color,
                                this.spriteCoords,
                                this.spriteDims);
                        menuReplacement.myMama = this.myMama;
                        myMama.gameObjects.add(menuReplacement);
                        for (GameSystem system : myMama.gameSystems) {
                            if (system.getTag().equals("draws")) {
                                system.addObject(menuReplacement, 3.0);
                            }
                            if (system.getTag().equals("mouse")) {
                                system.addObject(menuReplacement, 3.0);
                            }
                            if (system.getTag().equals("collision")) {
                                system.addObject(menuReplacement, 3.0);
                            }
                        }
                        menuReplacement.isBlue = this.isBlue;
                        menuReplacement.isOrange = this.isOrange;
                        menuReplacement.isPink = this.isPink;
                        menuReplacement.isPurple = this.isPurple;
                        menuReplacement.isPP = this.isPP;
                        menuReplacement.isOB = this.isOB;
                    }
                    hasBeenDraggedYet = true;
                }
            }
        }
        if (mouseReleased) {
            beingDragged = false;
        }
        if (beingDragged) {
            Double locAdjustX = (draggingCoords.x - mouseCoords.x)/myMama.viewport.scaleAdjust;
            Double locAdjustY = (draggingCoords.y - mouseCoords.y)/myMama.viewport.scaleAdjust;
            this.getTransform().setTransforms(  new Vec2d(this.getTransform().getLocation().x - locAdjustX, this.getTransform().getLocation().y - locAdjustY),
                                                this.getTransform().getDimensions(),
                                                this.getTransform().getOffset());
            draggingCoords = new Vec2d(mouseCoords.x, mouseCoords.y);
            this.collisionHitbox.setLocation(this.getTransform().getLocation());
            this.placeHolderDraw.location = this.getTransform().getLocation();
            this.placeHolderDraw.upDateLocation(new Vec2d(locAdjustX, locAdjustY));
        }

    }

    public void draw(GraphicsContext g){
//        super.draw(g);
//        placeHolderDraw.onDraw(g);
        for (GameSystem system : myMama.gameSystems) {
            if (system.getTag().equals("sprite")) {
                ((SpriteSystem) system).drawSprite(g,spriteCoords.x, spriteCoords.y, spriteDims.x, spriteDims.y,
                        this.getTransform().getLocation().x,
                        this.getTransform().getLocation().y,
                        this.getTransform().getDimensions().x,
                        this.getTransform().getDimensions().y);
            }
        }
//        placeHolderDraw.onDraw(g);
    }

    @Override
    public boolean beingDragged() {
        return this.beingDragged;
    }

    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        if (!this.beingDragged && !collidedWith.beingDragged()) {
            System.out.println("AND IM OUTTA HERE");
            AlcObject menuReplacement = new Tier2Flower(this.getTransform().getLocation(),
                    this.getTransform().getDimensions(),
                    this.getTransform().getOffset(),
                    Color.BLACK);
            menuReplacement.myMama = this.myMama;
            menuReplacement.hasBeenDraggedYet = true;
            myMama.gameObjects.add(menuReplacement);
            for (GameSystem system : myMama.gameSystems) {
                if (system.getTag().equals("draws")) {
                    system.addObject(menuReplacement, 3.0);
                }
                if (system.getTag().equals("mouse")) {
                    system.addObject(menuReplacement, 3.0);
                }
                if (system.getTag().equals("collision")) {
                    system.addObject(menuReplacement, 3.0);
                }
            }
            return true;
        }
        return false;
    }
}
