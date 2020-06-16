package com.mygdx.game.dialogbox;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.BaseActor;
import com.badlogic.gdx.graphics.g2d.Animation;

public class SetAnimationAction extends Action
{
    protected Animation animationToDisplay;
    
    public SetAnimationAction(Animation a)
    {  
        animationToDisplay = a;
    }
    
    public boolean act(float dt)
    {
        BaseActor ba = (BaseActor)target;
        ba.setAnimation( animationToDisplay );
        return true; // action completed
    }
}