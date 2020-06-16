package com.mygdx.game;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.dialogbox.StoryScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;



public class MenuScreen extends BaseScreen {
	
	
	
	public void initialize()    { 
		BaseActor bg = new BaseActor(0,0, mainStage);
		bg.loadTexture( "attackbg.jpg" );       
		bg.setSize(800,600);
		
		
		TextButton startButton = new TextButton( "Attaquer!", BaseGame.textButtonStyle );
        startButton.setPosition(150,AttackPartGame.V_HEIGHT/2 - 50);
        uiStage.addActor(startButton);

        startButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) )
                    return false;

                if ( !((InputEvent)e).getType().equals(Type.touchDown) )
                    return false;
                    
                BaseGame.setActiveScreen( new StoryScreen() );
                return true;
            }
        );
        
        TextButton quitButton = new TextButton( "Fuir", BaseGame.textButtonStyle );
        quitButton.setPosition(500,AttackPartGame.V_HEIGHT/2 - 50);
        uiStage.addActor(quitButton);
        
        quitButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) )
                    return false;

                if ( !((InputEvent)e).getType().equals(Type.touchDown) )
                    return false;
                    
                Gdx.app.exit();
                return true;
            }
        );
		
		
	}

	public void update(float dt)    {       
		if (Gdx.input.isKeyPressed(Keys.S))  
			AttackPartGame.setActiveScreen( new PlayScreen() );  
	}
}