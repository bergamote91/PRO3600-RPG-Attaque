package com.mygdx.game.attack1;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ManipulationEntree implements TextInputListener{
	
	public String texte;
	
	
	@Override
	public void input(String text) {
		// TODO Auto-generated method stub
		texte = text;
		//System.out.println("texte saisi " + text);
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
	
}

