package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.aides.StoryScreen1;
import com.mygdx.game.attack1.Attack1;
import com.mygdx.game.attack1.Attack1;
import com.mygdx.game.attack2.Attack2;
import com.mygdx.game.attack4.Attack4;
import com.mygdx.game.dialogbox.Scene;

public class PlayScreen extends BaseScreen {

	private BitmapFont title ;
	private CharSequence str;
	private BaseActor monster;

	Background background;
	Scene scene;

	private BaseActor char1;
	private BaseActor char2;
	private BaseActor char3;
	private BaseActor char4;

	//barre de vie
	BaseActor healthBar;
	public static float health = 1; //0 = dead, 1= full health

	private static final int OBSTACLE_SPACING=125;
	private static final int CASE_COUNT=4;
	private static final int CHAR_GAP1=50;

	ShapeRenderer rect1;
	int x;
	int y;

	public void initialize() {

		background = new Background(0,0, mainStage);    
		BaseActor.setWorldBounds(background);
		background.setAnimation( background.darkforest );

		TextButton char1Button = new TextButton( "?", BaseGame.textButtonStyle );
		char1Button.setPosition((OBSTACLE_SPACING),10);
		char1Button.setTransform(true);
		char1Button.setScale(0.5f);
		uiStage.addActor(char1Button);

		TextButton char2Button = new TextButton( "?", BaseGame.textButtonStyle );
		char2Button.setPosition((2*OBSTACLE_SPACING+50),10);
		char2Button.setTransform(true);
		char2Button.setScale(0.5f);
		uiStage.addActor(char2Button);

		TextButton char3Button = new TextButton( "?", BaseGame.textButtonStyle );
		char3Button.setPosition((3*OBSTACLE_SPACING+100),10);
		char3Button.setTransform(true);
		char3Button.setScale(0.5f);
		uiStage.addActor(char3Button);

		TextButton char4Button = new TextButton( "?", BaseGame.textButtonStyle );
		char4Button.setPosition((4*OBSTACLE_SPACING+150),10);
		char4Button.setTransform(true);
		char4Button.setScale(0.5f);
		uiStage.addActor(char4Button);


		monster = new BaseActor(350,400,mainStage);
		monster.loadTexture("monster.jpg.jpg");
		monster.setSize(160,140);


		char1=new BaseActor((OBSTACLE_SPACING), 50, mainStage);
		char1.loadTexture("char1.png");
		char1.setSize(100,100);
		char1.setColor(Color.WHITE);

		char2=new BaseActor((2*OBSTACLE_SPACING+50), 50, mainStage);
		char2.loadTexture("char2.png");
		char2.setSize(100,100);

		char3=new BaseActor((3*OBSTACLE_SPACING+100), 50, mainStage);
		char3.loadTexture("char3.png");
		char3.setSize(100,100);

		char4=new BaseActor((4*OBSTACLE_SPACING+150), 50, mainStage);
		char4.loadTexture("char4.png");
		char4.setSize(100,100);



		char1Button.addListener(
				(Event e) -> 
				{ 
					if ( !(e instanceof InputEvent) )
						return false;
					if ( !((InputEvent)e).getType().equals(Type.touchDown) )
						return false;
					BaseGame.setActiveScreen( new StoryScreen1("char1") );
					return true;
				}
				);

		char2Button.addListener(
				(Event e) -> 
				{ 
					if ( !(e instanceof InputEvent) )
						return false;

					if ( !((InputEvent)e).getType().equals(Type.touchDown) )
						return false;
					BaseGame.setActiveScreen( new StoryScreen1("char2") );
					return true;
				}
				);

		char3Button.addListener(
				(Event e) -> 
				{ 
					if ( !(e instanceof InputEvent) )
						return false;

					if ( !((InputEvent)e).getType().equals(Type.touchDown) )
						return false;
					BaseGame.setActiveScreen( new StoryScreen1("char3") );
					return true;
				}
				);

		char4Button.addListener(
				(Event e) -> 
				{ 
					if ( !(e instanceof InputEvent) )
						return false;

					if ( !((InputEvent)e).getType().equals(Type.touchDown) )
						return false;
					BaseGame.setActiveScreen( new StoryScreen1("char4") );
					return true;
				}
				);

		//barre de vie
		healthBar = new BaseActor(0,0, mainStage); 
		healthBar.loadTexture("health.png");
		healthBar.setSize(AttackPartGame.V_WIDTH*health,20);

	}


	@Override
	public void update(float dt) {

		if (Gdx.input.isKeyPressed(Keys.A)) 
			AttackPartGame.setActiveScreen( new Attack1() );  
		if (Gdx.input.isKeyPressed(Keys.Z))
			AttackPartGame.setActiveScreen( new Attack2() );  
		if (Gdx.input.isKeyPressed(Keys.E))
			AttackPartGame.setActiveScreen( new Attack3() );  
		if (Gdx.input.isKeyPressed(Keys.R))
			AttackPartGame.setActiveScreen( new Attack4() );  

		/*if (healthBar.getWidth() <0) {
			SpaceGame.setActiveScreen(new EndGame()); //classe de fin, à faire
		}*/
	}

}

