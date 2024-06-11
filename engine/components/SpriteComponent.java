package engine.components;

import engine.support.Vec2d;

public class SpriteComponent {

    int tickCount;
    int ticksPerFrame;
    int spritesPerCycle;
    Vec2d spriteLoc;
    Vec2d altSpriteLoc1;
    Vec2d altSpriteLoc2;
    Vec2d selectedSprite;
    Vec2d spriteDims;


    public SpriteComponent(Vec2d spriteLoc, Vec2d spriteDims, int ticksPerFrame, int spritesPerCycle) {
        tickCount = 0;
        this.spriteLoc = spriteLoc;
        this.spriteDims = spriteDims;
        this.ticksPerFrame = ticksPerFrame;
        this.spritesPerCycle = spritesPerCycle;
        this.altSpriteLoc1 = spriteLoc;
        this.altSpriteLoc2 = spriteLoc;
        this.selectedSprite = spriteLoc;
    }

    public SpriteComponent(Vec2d spriteLoc, Vec2d altSprite1, Vec2d altSprite2, Vec2d spriteDims, int ticksPerFrame, int spritesPerCycle) {
        tickCount = 0;
        this.spriteLoc = spriteLoc;
        this.spriteDims = spriteDims;
        this.ticksPerFrame = ticksPerFrame;
        this.spritesPerCycle = spritesPerCycle;
        this.altSpriteLoc1 = altSprite1;
        this.altSpriteLoc2 = altSprite2;
        this.selectedSprite = this.spriteLoc;
    }

    public void tick() {
        tickCount++;
        if (tickCount >= ticksPerFrame * spritesPerCycle) {
            tickCount = 0;
        }
    }

    public Vec2d getLoc(){
        return new Vec2d(selectedSprite.x + (tickCount/ticksPerFrame) * spriteDims.x, selectedSprite.y);
    }
    public Vec2d getSize() {
        return spriteDims;
    }

    public void setCurrentSprite(int i) {
        switch (i) {
            case 0 -> selectedSprite = spriteLoc;
            case 1 -> selectedSprite = altSpriteLoc1;
            case 2 -> selectedSprite = altSpriteLoc2;
        }
        tickCount = 1;
    }

}
