package engine;

import javafx.scene.image.Image;

public class SpriteLoader {

    protected Image spriteSheet;

    public SpriteLoader(Image sprites){
        this.spriteSheet = sprites;
    }
    public SpriteLoader(){
    }

    public Image getSpriteSheet(){
        return this.spriteSheet;
    }

    public void setSpriteSheet(Image sheet){
        this.spriteSheet = sheet;
    }

}
