package tic;

import engine.Screen;
import engine.UITools.BasicBox;
import engine.UITools.UIElement;
import engine.UITools.BasicFlower;
import engine.UITools.BasicRoundedBox;
import engine.support.Vec2d;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameScreen extends Screen {

    protected boolean quickfix = false;

    public GameScreen(String title) {
        super(title);
    }

    public GameScreen(String title, Vec2d windowSize, Double gameDimensions) {
        super(title, windowSize, gameDimensions);
        this.windowSize = windowSize;
        gameManager = new TicMama(0.0, 0.0, 800.0, 800.0, Color.color(.80,.86,.84, 0.0));
        gameManager.myScreen = this;
        UIElement background = new BasicBox(0.0, 0.0, 800.0, 800.0, Color.color(.80,.86,.84));
        background.children.add(new BasicRoundedBox(120.0, 55.0, 560.0, 560.0, Color.color(.59, .71, .65)));
        gameManager.uiChildren.add(background);
        gameManager.uiChildren.add(new TicBox(127.5, 62.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(127.5, 245.0, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(127.5, 427.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(310.0, 62.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(310.0, 245.0, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(310.0, 427.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(492.5, 62.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(492.5, 245.0, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new TicBox(492.5, 427.5, 180.0, 180.0, Color.color(.70, .80, .66)));
        gameManager.uiChildren.add(new BasicFlower(650.0, 650.0, 120.0, 120.0, Color.color(.90, .75, .80, 1.0), new Vec2d(0.0, 0.0)));
        gameManager.uiChildren.add(new BasicFlower(30.0, 650.0, 120.0, 120.0, Color.color(.90, .75, .55, 0.0), new Vec2d(0.0, 0.0)));
    }

    @Override
    public void onTick(long nanosSincePreviousTick){
        super.onTick(nanosSincePreviousTick);
        if (!gameManager.itsJoever) {
            if (quickfix) {
                gameTime = gameTime - (double) nanosSincePreviousTick / 1000000000;

                if (gameTime <= 0) {
                    gameManager.uiChildren.get(10).enactEvent(4);
                    gameManager.uiChildren.get(11).enactEvent(4);
                    gameManager.turnShiftNoTick();
                    gameTime = 10;
                } else if (gameTime <= 2.0) {
                    gameManager.uiChildren.get(10).enactEvent(3);
                    gameManager.uiChildren.get(11).enactEvent(3);
                } else if (gameTime <= 4.0) {
                    gameManager.uiChildren.get(10).enactEvent(2);
                    gameManager.uiChildren.get(11).enactEvent(2);
                } else if (gameTime <= 6.0) {
                    gameManager.uiChildren.get(10).enactEvent(1);
                    gameManager.uiChildren.get(11).enactEvent(1);
                } else if (gameTime <= 8.0) {
                    gameManager.uiChildren.get(10).enactEvent(0);
                    gameManager.uiChildren.get(11).enactEvent(0);
                } else {
                    gameManager.uiChildren.get(10).enactEvent(4);
                    gameManager.uiChildren.get(11).enactEvent(4);
                }

            } else {
                quickfix = true;
            }
        }
    }

    public void onMouseClicked(MouseEvent e, Vec2d mouseCoords){
        gameManager.onClick(new Vec2d(e.getX(), e.getY()));
    }

    public void itsJoever(int result){
        super.itsJoever(result);
        for (UIElement child : gameManager.uiChildren) {
            if (child.getClass() == TicBox.class) {
                ((TicBox) child).selfDestructed = true;
            }
        }
    }


}
