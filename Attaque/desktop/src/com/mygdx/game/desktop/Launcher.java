package com.mygdx.game.desktop;

import com.badlogic.gdx.Game; 
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.game.AttackPartGame;

public class Launcher {    
	public static void main (String[] args)    {        
		Game myGame = new AttackPartGame();        
		LwjglApplication launcher = new LwjglApplication( myGame, "Attaque PRO3600", 800, 600 );   
	} 
}
