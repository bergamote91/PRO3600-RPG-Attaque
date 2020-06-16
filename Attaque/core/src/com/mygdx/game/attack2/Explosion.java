package com.mygdx.game.attack2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.BaseActor;

public class Explosion extends BaseActor {  
	public Explosion(float x, float y, Stage s)    {   
		super(x,y,s);

		loadAnimationFromSheet("explosion.png", 6, 6, 0.03f, false); 
	}

	public void act(float dt)    {      
		super.act(dt);

		if ( isAnimationFinished() )        
			remove();  
	}
}
