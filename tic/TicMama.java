package tic;

import engine.GameManager;
import engine.UITools.UIElement;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TicMama extends GameManager {
    public TicMama(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        this.squareStartX = x;
        this.squareStartY = y;
    }
    protected boolean XOrOMaster = true;
    protected int turnShifts = 0;
    protected  ArrayList<Integer> OBoxes = new ArrayList<Integer>();
    protected  ArrayList<Integer> XBoxes = new ArrayList<Integer>();


    @Override
    public void onTick(long nanosSincePreviousTick) {
        super.onTick(nanosSincePreviousTick);
//        System.out.println(this.location.x + "    " + squarestartX);
    }

    @Override
    public void onClick(Vec2d coords) {
        System.out.println("ticmama onclick activated");
        int i = 0;
        for (UIElement child : uiChildren) {
            i++;
            if(child.onClick(coords) == 1) {
                if (XOrOMaster){
                    XBoxes.add(i);
                    System.out.println(i);
                } else {
                    OBoxes.add(i);
                    System.out.println(i);
                }
                turnShift();
            }
            if(child.onClick(coords) == 2) {
                myScreen.myApp.onRestart();
            }
        }
    }

    public void turnShift() {
        myScreen.gameTime = 10;
        uiChildren.get(10).enactEvent(4);
        uiChildren.get(11).enactEvent(4);
        turnShifts++;
        isTheGameOver();
        for (UIElement child : uiChildren) {
            if (child.getClass() == TicBox.class) {
                    ((TicBox) child).XOrO = !XOrOMaster;
                }
            }
        if (!itsJoever){
            XOrOMaster = !XOrOMaster;
        }
        if (XOrOMaster) {
            uiChildren.get(10).color = Color.color(.90, .75, .80, 1.0);
            uiChildren.get(11).color = Color.color(.90, .75, .55, 0.0);
        } else {
            uiChildren.get(11).color = Color.color(.90, .75, .55, 1.0);
            uiChildren.get(10).color = Color.color(.90, .75, .80, 0.0);
        }
    }
@Override
    public void turnShiftNoTick() {
        for (UIElement child : uiChildren) {
            if (child.getClass() == TicBox.class) {
                ((TicBox) child).XOrO = !XOrOMaster;
            }
        }

        XOrOMaster = !XOrOMaster;
    if (XOrOMaster) {
        uiChildren.get(10).color = Color.color(.90, .75, .80, 1.0);
        uiChildren.get(11).color = Color.color(.90, .75, .55, 0.0);
    } else {
        uiChildren.get(11).color = Color.color(.90, .75, .55, 1.0);
        uiChildren.get(10).color = Color.color(.90, .75, .80, 0.0);
    }
    }

    public void isTheGameOver(){
        if (turnShifts == 9) {
            itsJoever = true;
            myScreen.itsJoever(3);
        }
        if (XOrOMaster){
            scoreCheck(XBoxes, 1);
        } else {
            scoreCheck(OBoxes, 2);
        }
    }

    public void scoreCheck(ArrayList<Integer> board, Integer i){
        if (board.contains(2)){
            if (board.contains(3) && board.contains(4)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
            if (board.contains(5) && board.contains(8)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
            if (board.contains(6) && board.contains(10)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
        } else if (board.contains(6)){
            if (board.contains(3) && board.contains(9)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
            if (board.contains(5) && board.contains(7)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
            if (board.contains(4) && board.contains(8)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
        } else if (board.contains(10)){
            if (board.contains(8) && board.contains(9)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
            if (board.contains(4) && board.contains(7)){
                itsJoever = true;
                myScreen.itsJoever(i);
            }
        }
    }

}

