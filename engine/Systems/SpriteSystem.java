package engine.Systems;

import engine.GameManager;
import engine.SpriteLoader;
import engine.components.SpriteComponent;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpriteSystem extends GameSystem{
    public SpriteSystem(GameManager mama, Image spriteSheet) {
        super(mama);
        this.setTag("sprite");
        this.spriteLoader = new SpriteLoader(spriteSheet);
    }
    public SpriteSystem(GameManager mama) {
        super(mama);
        this.setTag("sprite");
        this.spriteLoader = new SpriteLoader();
    }

    protected SpriteLoader spriteLoader;

    public void drawSprite(GraphicsContext g,
                           Double SourceX,
                           Double SourceY,
                           Double SourceW,
                           Double SourceH,
                           Double SheetX,
                           Double SheetY,
                           Double SheetW,
                           Double SheetH) {
        g.drawImage(this.spriteLoader.getSpriteSheet(), SourceX, SourceY, SourceW, SourceH, SheetX, SheetY, SheetW, SheetH);
    }
    public void drawSprite(SpriteComponent spriteInfo, GraphicsContext g, Vec2d sourceLoc, Vec2d sourceDims) {
        Vec2d loc = spriteInfo.getLoc();
        Vec2d size = spriteInfo.getSize();
        g.drawImage(this.spriteLoader.getSpriteSheet(), loc.x, loc.y, size.x, size.y, sourceLoc.x, sourceLoc.y, sourceDims.x, sourceDims.y);

    }


    public void setSpriteLoaderSheet(Image spriteSheet){
        spriteLoader.setSpriteSheet(spriteSheet);
    }

}
