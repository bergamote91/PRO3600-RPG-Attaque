package com.mygdx.game;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.BaseActor;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Background extends BaseActor
{
    
    public Animation darkforest;
    
    public Background(float x, float y, Stage s)
    {
        super(x,y,s);

       
        darkforest = loadTexture("darkforest.png");
        

        setSize(800,600);
    }

}