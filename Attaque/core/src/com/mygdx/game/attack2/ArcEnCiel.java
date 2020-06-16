package com.mygdx.game.attack2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;

public class ArcEnCiel extends BaseActor {   
	public ArcEnCiel(float x, float y, Stage s)    { 
		super(x,y,s);
		loadTexture("laser.png");
		addAction( Actions.delay(1) );    
		addAction( Actions.after( Actions.fadeOut(0.5f) ) ); 
		addAction( Actions.after( Actions.removeActor() ) );

		setSpeed(400);    
		setMaxSpeed(400);  
		setDeceleration(0);   
	}
	public void act(float dt)  {        
		super.act(dt);      
		applyPhysics(dt);     
		wrapAroundWorld();  
	} 
}