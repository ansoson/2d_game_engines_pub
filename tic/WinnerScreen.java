package tic;

import engine.Screen;
import engine.UITools.BasicFlower;
import engine.UITools.UIElement;
import engine.support.Vec2d;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class WinnerScreen extends Screen {
    public WinnerScreen(String title, Vec2d windowSize, Double gameDimensions, Integer result) {
        super(title, windowSize, gameDimensions);
        double tempX = 0;
        double tempY = 0;
        double tempSizeX;
        double tempSizeY;
        this.windowSize = windowSize;

        if (this.windowSize.x >= this.windowSize.y){
            tempX = (this.windowSize.x - this.windowSize.y)/2;
            tempSizeX = this.windowSize.y;
            tempSizeY = this.windowSize.y;
        } else {
            tempY = (this.windowSize.y - this.windowSize.x)/2;
            tempSizeX = this.windowSize.x;
            tempSizeY = this.windowSize.x;
        }
        Vec2d offset = new Vec2d(tempX, tempY);
        gameManager = new TicMama(0.0, 0.0, tempSizeX, tempSizeY, Color.color(.80,.86,.84,  0.5));
        gameManager.myScreen = this;

        System.out.println(this.windowSize.x + "   " + this.windowSize.y + "   " + tempX + "   " + tempY + "   " + tempSizeX + "   " + tempSizeY);
        if (result == 1){
            gameManager.uiChildren.add(new BasicFlower(1.0/8.0 * tempSizeX, 1.0/8.0 * tempSizeY, 1.0/8.0 * tempSizeX, 1.0/8.0 * tempSizeY, Color.color(.90, .75, .80, 1.0), offset));
            gameManager.uiChildren.add(new BasicFlower(1.0/16.0 * tempSizeX, 5.0/8.0 * tempSizeY, 2.0/8.0 * tempSizeX, 2.0/8.0 * tempSizeY, Color.color(.90, .75, .80, 1.0), offset));
            gameManager.uiChildren.add(new BasicFlower(5.0/8.0 * tempSizeX, 1.0/16.0 * tempSizeY, 60.0/800.0 * tempSizeX, 60.0/800.0 * tempSizeY, Color.color(.90, .75, .80, 1.0), offset));
            gameManager.uiChildren.add(new BasicFlower(640.0/800.0 * tempSizeX, 370.0/800.0 * tempSizeY, 1.0/8.0 * tempSizeX, 1.0/8.0 * tempSizeY, Color.color(.90, .75, .80, 1.0), offset));
            gameManager.uiChildren.add(new PlayAgainButton(325.0/800.0 * tempSizeX, 660.0/800.0 * tempSizeY, 150.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, Color.color(.59, .71, .65)));
        } else if (result == 2) {
            gameManager.uiChildren.add(new BasicFlower(600.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, 100.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, Color.color(.90, .75, .55, 1.0)));
            gameManager.uiChildren.add(new BasicFlower(550.0/800.0 * tempSizeX, 500.0/800.0 * tempSizeY , 200.0/800.0 * tempSizeX, 200.0/800.0 * tempSizeY, Color.color(.90, .75, .55, 1.0)));
            gameManager.uiChildren.add(new BasicFlower(140.0/800.0 * tempSizeX, 50.0/800.0 * tempSizeY, 60.0/800.0 * tempSizeX, 60.0/800.0 * tempSizeY, Color.color(.90, .75, .55, 1.0)));
            gameManager.uiChildren.add(new BasicFlower(60.0/800.0 * tempSizeX, 370.0/800.0 * tempSizeY, 100.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, Color.color(.90, .75, .55, 1.0)));
            gameManager.uiChildren.add(new PlayAgainButton(325.0/800.0 * tempSizeX, 660.0/800.0 * tempSizeY, 150.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, Color.color(.59, .71, .65)));
        } else if (result == 3) {
            gameManager.uiChildren.add(new PlayAgainButton(325.0/800.0 * tempSizeX, 660.0/800.0 * tempSizeY, 150.0/800.0 * tempSizeX, 100.0/800.0 * tempSizeY, Color.color(.59, .71, .65)));
        }
    }

    public void onMouseClicked(MouseEvent e) {
        gameManager.onClick(new Vec2d(e.getX(), e.getY()));
    }

    @Override
    public void onResize(Vec2d currentSize, Vec2d newSize, Double gameDims) {
        super.onResize(currentSize, newSize, gameDims);
        System.out.println("RESIZING WINNER");
    }


}
