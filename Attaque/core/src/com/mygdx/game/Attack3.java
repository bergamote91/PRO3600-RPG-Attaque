package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.attack2.Explosion;


public class Attack3 extends BaseScreen {


	BaseActor heroes; 
	BaseActor monster;
	BaseActor souris;


	int x;
	int y;
	private Random random;
	int xmonster;
	int ymonster;
	int monsterwidth;
	int monsterheight;
	int nbtours;
	int i;
	
	private Label Consigne;



	public void initialize() {
		BaseActor bg = new BaseActor(0,0, mainStage);    
		bg.loadTexture( "space.png" );     
		bg.setSize(AttackPartGame.V_WIDTH,AttackPartGame.V_HEIGHT);     
		BaseActor.setWorldBounds(bg);


		heroes = new BaseActor(500, 50, mainStage);
		heroes.loadTexture("char3.png");

		random = new Random();
		xmonster = random.nextInt(AttackPartGame.V_WIDTH - monsterwidth);
		ymonster = random.nextInt(AttackPartGame.V_HEIGHT - monsterheight);
		monsterwidth = 150;
		monsterheight = 100;


		monster = new BaseActor(xmonster, ymonster, mainStage);
		monster.loadTexture("monster.jpg.jpg");
		monster.setSize(monsterwidth, monsterheight);


		souris = new BaseActor( 0,0, mainStage);
		souris.loadTexture("pointeur.png");
		souris.setSize(50, 50);
		
		Consigne = new Label("Attrape le monstre !", BaseGame.labelStyle);
		Consigne.setColor( Color.WHITE);
		Consigne.setPosition( 20, 520 ); 
		uiStage.addActor(Consigne);
		
		ButtonStyle buttonStyle = new ButtonStyle();

	    Texture buttonTex = new Texture( Gdx.files.internal("undo.png") );
	    TextureRegion buttonRegion =  new TextureRegion(buttonTex);
	    buttonStyle.up = new TextureRegionDrawable( buttonRegion );
	    
	    Button restartButton = new Button( buttonStyle );
	    restartButton.setColor( Color.CYAN );
	    restartButton.setPosition(720,520);
	    uiStage.addActor(restartButton);
		
	    restartButton.addListener(
	            (Event e) -> 
	            { 
	                InputEvent ie = (InputEvent)e;
	                if ( ie.getType().equals(Type.touchDown) )
	                    AttackPartGame.setActiveScreen( new Attack3() );
	                return false;
	            }
	        );
		




		nbtours = 10;
		i = 0;

	}

	@Override
	public void update(float dt) {
		
		Consigne.setText("Attrape le monstre!!");



		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			x = (int) touchPos.x;
			y = (int) touchPos.y;
			souris.centerAtPosition(x, AttackPartGame.V_HEIGHT-y );

			if (souris.overlaps(monster)) {
				souris.remove();
				i += 1;
				xmonster = random.nextInt(AttackPartGame.V_WIDTH - monsterwidth );
				ymonster = random.nextInt(AttackPartGame.V_HEIGHT - monsterheight );
				monster.centerAtPosition(xmonster,  ymonster);
			}
		}




		if (Gdx.input.isKeyPressed(Keys.ESCAPE))  
			AttackPartGame.setActiveScreen( new PlayScreen() ); 
		if (Gdx.input.isKeyPressed(Keys.SPACE))  
			AttackPartGame.setActiveScreen( new Attack3());

		if (i == nbtours) {
			Explosion boom = new Explosion(0,0, mainStage);
			boom.centerAtActor(monster);
			monster.remove();
			BaseActor messageWin = new BaseActor(0,0, uiStage);
			messageWin.loadTexture("message-win.png");
			messageWin.centerAtPosition(AttackPartGame.V_WIDTH/2,AttackPartGame.V_HEIGHT/2);
			messageWin.setOpacity(0);
			messageWin.addAction( Actions.fadeIn(1) );

		}



	}


}
