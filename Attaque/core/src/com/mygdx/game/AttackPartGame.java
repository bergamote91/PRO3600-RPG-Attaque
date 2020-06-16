package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AttackPartGame extends BaseGame {  
	
	  
    //ajout pour le timer
    public static SpriteBatch batch;
    public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	public static int temps1 = 0;
	public static int temps2 = 0;
	
    //fin ajout
    
 
	
	public void create()    {  
		
		
		
		super.create();   
		
		// pour le timer
		batch = new SpriteBatch();
		//fin ajout
		
		setActiveScreen( new MenuScreen() );
		
	} 
	
	public void render () {
		super.render();
	}
}


