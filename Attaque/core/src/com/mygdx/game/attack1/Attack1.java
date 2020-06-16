package com.mygdx.game.attack1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.AttackPartGame;
import com.mygdx.game.attack4.Attack4;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class Attack1 extends BaseScreen {


	private SpriteBatch spriteBatch;
	private BitmapFont title ;
	private CharSequence str;
	private BitmapFont epreuve;
	private String txt;
	public ManipulationEntree saisie;

	private String message;
	private boolean txtSaisi;
	private boolean afficher;

	private Label Consigne;

	private SortilegesData sortilege;

	//Timer
	private float timeCount;
	private Integer worldSpaceGame;
	private Integer score;

	//Scene2D widgets
	private Label timeLabel;
	private Label countdownLabel;
	private Label scoreLabel;
	private Label levelLabel;
	private Label worldLabel;
	private Label marioLabel;
	
	boolean retour;




	public void initialize() {

		worldSpaceGame = 0;
		timeCount = 0;
		score = 0;



		//boutons 

		ButtonStyle buttonStyle = new ButtonStyle();

		Texture buttonTex = new Texture( Gdx.files.internal("undo.png") );
		TextureRegion buttonRegion =  new TextureRegion(buttonTex);
		buttonStyle.up = new TextureRegionDrawable( buttonRegion );

		Button restartButton = new Button( buttonStyle );
		restartButton.setColor( Color.WHITE);
		restartButton.setPosition(720,530);
		restartButton.sizeBy(5);
		uiStage.addActor(restartButton);

		restartButton.addListener(
				(Event e) -> 
				{ 
					InputEvent ie = (InputEvent)e;
					if ( ie.getType().equals(Type.touchDown) )
						AttackPartGame.setActiveScreen( new Attack1() );
					return false;
				}
				);

		ButtonStyle buttonStyleQuit = new ButtonStyle();

		Texture buttonQuit = new Texture( Gdx.files.internal("quit.png") );
		TextureRegion quitRegion =  new TextureRegion(buttonQuit);
		buttonStyleQuit.up = new TextureRegionDrawable( quitRegion );

		Button quitButton = new Button( buttonStyleQuit );
		quitButton.setColor( Color.WHITE);
		quitButton.setPosition(20,560);
		uiStage.addActor(quitButton);

		quitButton.addListener(
				(Event e) -> 
				{ 
					InputEvent ie = (InputEvent)e;
					if ( ie.getType().equals(Type.touchDown) )
						AttackPartGame.setActiveScreen( new PlayScreen() );
					return false;
				}
				);

		
		//Timer
		Table table = new Table();
		table.top();
		table.setFillParent(true);

		countdownLabel = new Label(String.format("%03d", worldSpaceGame), BaseGame.labelStyle); //car 3 chiffre 
		countdownLabel.setColor( Color.WHITE );
		countdownLabel.setPosition( AttackPartGame.V_WIDTH/2, 550 ); 
		uiStage.addActor(countdownLabel);
		
		

        //Attaque1
		BaseActor bg = new BaseActor(0,0, mainStage);    
		bg.loadTexture( "char1bg.jpg" );     
		bg.setSize(AttackPartGame.V_WIDTH,AttackPartGame.V_HEIGHT);     
		BaseActor.setWorldBounds(bg);


		sortilege = new SortilegesData();
		txt=SortilegesData.sortAleatoire;
		System.out.println(txt);


		txtSaisi=false;
		afficher=false;



		saisie = new ManipulationEntree(); //classe qui permet de récupérer l'entrée utilisateur


		Consigne = new Label("Recopie le plus vite possible sans erreur", BaseGame.labelStyle);
		Consigne.setColor( Color.BLUE );
		Consigne.setPosition( 5, 500 ); 
		uiStage.addActor(Consigne);



	}



	@Override
	public void update(float dt) {

		timeCount += dt;
		if(timeCount >= 1){
			worldSpaceGame ++; //ajoute une seconde
			countdownLabel.setText(String.format("%03d", worldSpaceGame));
			timeCount = 0;}
		
		/*if (retour) {
			BaseGame.setActiveScreen(new PlayScreen());
		}*/

		Consigne.setText("Recopie le plus vite possible sans erreur");

		if (txtSaisi == false) {
			txtSaisi=true;
			Gdx.input.getTextInput(saisie, "Recopie le texte le plus vite possible", "", txt); 

		}

		//vérifier la saisie et afficher le message de fin

		if (txtSaisi == true && saisie.texte != null && afficher == false) {
			afficher = true;
			System.out.println(saisie.texte);
			Consigne.setVisible(false);
			AttackPartGame.temps1 += worldSpaceGame;
			countdownLabel.setVisible(false);
			
			// si bon texte saisi

			if (saisie.texte.equals(txt)) {
				BaseActor messageWin = new BaseActor(0,0, uiStage);
				messageWin.loadTexture("message-win.png");
				messageWin.centerAtPosition(AttackPartGame.V_WIDTH/2,AttackPartGame.V_HEIGHT/2);
				messageWin.setOpacity(0);
				messageWin.addAction( Actions.fadeIn(1) );
				System.out.println(AttackPartGame.temps1);
				PlayScreen.health -= 0.02*(30-AttackPartGame.temps1) ;
				AttackPartGame.temps1 =0;
				//retour = true;

			}
			
			//si erreur
			
			else {
				BaseActor messageLose = new BaseActor(0,0, uiStage);
				messageLose.loadTexture("message-lose.png");
				messageLose.centerAtPosition(AttackPartGame.V_WIDTH/2,AttackPartGame.V_HEIGHT/2);
				messageLose.setOpacity(0);
				messageLose.addAction( Actions.fadeIn(1) );
				System.out.println(AttackPartGame.temps1);
			}
		}


		//Retour au menu
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))  
			AttackPartGame.setActiveScreen( new PlayScreen() );
		if (Gdx.input.isKeyPressed(Keys.SPACE))  
			AttackPartGame.setActiveScreen( new Attack1());

	}


}





