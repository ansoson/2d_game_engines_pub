package engine.components;

import engine.GameManager;
import engine.Systems.GameSystem;
import engine.support.Vec2d;

public class TransformComponent extends GameSystem {

    protected Vec2d location;
    protected Vec2d dimensions;
    protected Vec2d offset;

    public TransformComponent(Vec2d location, Vec2d dimensions, Vec2d offset, GameManager mama){
        super(mama);
        this.setTag("Transform");
        this.location = location;
        this.dimensions = dimensions;
        this.offset = offset;
    }
    public void setTransforms(Vec2d newLocation, Vec2d newDimensions, Vec2d newOffset){
        this.location = newLocation;
        this.dimensions = newDimensions;
        this.offset = newOffset;
    }
    public Vec2d getLocation(){return this.location;}
    public Vec2d getDimensions(){return this.dimensions;}
    public Vec2d getOffset(){return this.offset;}

}
