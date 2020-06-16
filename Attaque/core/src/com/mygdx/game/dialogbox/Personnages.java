package com.mygdx.game.dialogbox;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.BaseActor;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Personnages extends BaseActor
{
    public Animation normal;
    public Animation sad;
    public Animation lookLeft;
    public Animation lookRight;
    public Animation char1;
    public Animation char2;
    public Animation char3;
    public Animation char4;
    
    public Personnages(float x, float y, Stage s)
    {
       super(x,y,s);
       normal = loadTexture("monster_normal.png");
       sad = loadTexture("monster_talk.png");
       lookLeft = loadTexture("gentil_normal.png");
       lookRight = loadTexture("monster_normal.png");
       char1 = loadTexture("char1.png");
 
       char2 = loadTexture("char2.png");
       char3 = loadTexture("char3.png");
       char4 = loadTexture("char4.png");
    
    }
}