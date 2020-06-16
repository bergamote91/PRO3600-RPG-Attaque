package com.mygdx.game.attack2;

import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.Action; 
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;
import com.badlogic.gdx.math.MathUtils;

public class Mechant extends BaseActor {   
	
	
	private Shield shield;
	public int shieldPower;

	public Mechant(float x, float y, Stage s)    {     
		super(x,y,s);

		loadTexture("rock.png");
		
		shield = new Shield(0,0, s);
        addActor(shield);
        shield.centerAtPosition( getWidth()/2, getHeight()/2 );
        shieldPower = 200;


		//essayer de faire déplacer sur une ligne horizontale 

		float random = MathUtils.random(30);

		//addAction( Actions.forever( Actions.rotateBy(30 + random, 1) ) );
		setSpeed(50 + random);   
		setMaxSpeed(50 + random);   
		setDeceleration(0);

		setMotionAngle(180); 
	} 
	
	
	
	public void act(float dt)    {   
		super.act(dt); 

		applyPhysics(dt);  
		wrapAroundWorld();  
		
		 shield.setOpacity(shieldPower / 100f);
		 if (shieldPower <= 0)   
			 shield.setVisible(false);

	} 
}
