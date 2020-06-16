package com.mygdx.game.attack2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.AttackPartGame;
import com.mygdx.game.attack4.Attack4;

public class Attack2 extends BaseScreen {   
	private LicorneAttack licorne;
	private Mechant mechant;
	private Boolean tir;
	
	 //Timer
		
		private float timeCount;
		private Integer worldSpaceGame;
		private Integer score;

		//Scene2D widgets
		private Label countdownLabel;
		

	public void initialize()    {  	
		
		//Timer
		worldSpaceGame = 0;
		timeCount = 0;
		score = 0;
	
		Table table = new Table();
		table.top();
		table.setFillParent(true);

		countdownLabel = new Label(String.format("%03d", worldSpaceGame), BaseGame.labelStyle); //car 3 chiffre 
		countdownLabel.setColor( Color.WHITE );
		countdownLabel.setPosition( AttackPartGame.V_WIDTH/2 - 20, AttackPartGame.V_HEIGHT - 50 ); 
		uiStage.addActor(countdownLabel);

	    
		//fond
		BaseActor space = new BaseActor(0,0, mainStage);    
		space.loadTexture( "space.png" );     
		space.setSize(AttackPartGame.V_WIDTH,AttackPartGame.V_HEIGHT);     
		BaseActor.setWorldBounds(space);

		licorne = new LicorneAttack(300,50, mainStage);   


		mechant = new Mechant(AttackPartGame.V_WIDTH/2,400, mainStage); 
		System.out.println(mechant.shieldPower);
		
		tir = false;

	}
	
	

	public void update(float dt)  {
		
		//Timer
		timeCount += dt;
		if(timeCount >= 1){
			worldSpaceGame ++;
			countdownLabel.setText(String.format("%03d", worldSpaceGame));
			timeCount = 0;
		}

		for ( BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.game.attack2.ArcEnCiel") )
		{
			
			if (laserActor.overlaps(mechant) )
				
			{
				tir=true;
				if (mechant.shieldPower > 0 && tir == true)
				{
					tir=false;
					laserActor.remove();
					mechant.shieldPower -= 34;
					

					
				}
				else if (mechant.shieldPower <= 0)
					{
						Explosion boom = new Explosion(0,0, mainStage);
	                    boom.centerAtActor(mechant);
	                    laserActor.remove();
	                    mechant.remove();
	                    
	                    //timer
	                    AttackPartGame.temps2 += worldSpaceGame;
	    				countdownLabel.setVisible(false);
	                    System.out.println(AttackPartGame.temps1);
						PlayScreen.health -= Math.max(0,0.005*(10-AttackPartGame.temps1)) ;
						AttackPartGame.temps2 =0;
						
	                    BaseActor messageLose = new BaseActor(0,0, uiStage);
	                    messageLose.loadTexture("message-win.png");
	                    messageLose.centerAtPosition(AttackPartGame.V_WIDTH/2,AttackPartGame.V_HEIGHT/2);
	                    messageLose.setOpacity(0);
	                    messageLose.addAction( Actions.fadeIn(1) );
	                    
					}



			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))  
			AttackPartGame.setActiveScreen( new PlayScreen() ); 
		if (Gdx.input.isKeyPressed(Keys.N))  
			AttackPartGame.setActiveScreen( new Attack2());
		
	}


	//pour tirer laser
	public boolean keyDown(int keycode)
	{
		if ( keycode == Keys.SPACE )
			licorne.shoot();
		return false;

	}
}