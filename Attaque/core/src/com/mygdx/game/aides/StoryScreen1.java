package com.mygdx.game.aides;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Background;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.AttackPartGame;
import com.mygdx.game.dialogbox.DialogBox;
import com.mygdx.game.dialogbox.Personnages;
import com.mygdx.game.dialogbox.Scene;
import com.mygdx.game.dialogbox.SceneActions;
import com.mygdx.game.dialogbox.SceneSegment;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class StoryScreen1 extends BaseScreen
{
	Scene scene;
	Background background;
	Personnages kelsoe;
	DialogBox dialogBox;
	BaseActor continueKey;
	Table buttonTable;
	private String txt1;
	private String txt2;
	private String txt3;
	private String txt4;
	private Animation image;


	public StoryScreen1(String character)  {


		background = new Background(0,0, mainStage);
		background.setOpacity(0);
		BaseActor.setWorldBounds(background);

		kelsoe = new Personnages(0,0, mainStage);

		dialogBox = new DialogBox(0,0, uiStage);
		dialogBox.setDialogSize(600, 150);
		dialogBox.setBackgroundColor( new Color(0.2f, 0.2f, 0.2f, 1) );
		dialogBox.setVisible(false);

		continueKey = new BaseActor(0,0,uiStage);
		continueKey.loadTexture("assets/key-C.png");
		continueKey.setSize(32,32);
		continueKey.setVisible(false);

		dialogBox.addActor(continueKey);
		continueKey.setPosition( dialogBox.getWidth() - continueKey.getWidth(), 0 );

		buttonTable = new Table();
		buttonTable.setVisible(false);

		uiTable.add().expandY();
		uiTable.row();
		uiTable.add(buttonTable);
		uiTable.row();
		uiTable.add(dialogBox);


		scene = new Scene();
		mainStage.addActor(scene);
		
		
		if ("char1".contentEquals(character)) {
			txt1 = "Je suis la fée!";
			txt2 = "Plus tu recopies vite le texte, plus mon attaque sera puissante !";
			txt3 = "Pour utiliser mon attaque, appuie sur A. Appuie sur échap si tu as terminé";
			image = kelsoe.char1;

		}
		else if (character.contentEquals("char2")) {
			txt1 = "Je suis la licorne !" ;
			txt2 = " J'attaque le monstre avec des faisceaux d'arc en ciel qui le font exploser. Fais moi pivoter avec les flèches droites et gauche, puis tir avec espace !";
			txt3 = "Pour utiliser mon attaque, appuie sur Z. Appuie sur échap si tu as terminé" ;
			image = kelsoe.char2;
		}

		else if (character.contentEquals("char3")) {
			txt1 = "Je suis le dragon! Le monstre ne peut pas m'échapper." ;
			txt2 = "Attrappe-le en cliquant dessus dès qu'il bouge sur l'écran !" ;
			txt3 = "Pour utiliser mon attaque, appuie sur E. Appuie sur échap si tu as terminé" ;
			image = kelsoe.char3;
		}
		else {
			txt1 = "Je suis la Sorcière !" ;
			txt2 = "Résous mon puzzle le plus vite possible !";
			txt3= "Pour utiliser mon attaque, appuie sur R. Appuie sur échap si tu as terminé" ;
			image = kelsoe.char4;
		}





		hallway();


	}

	public void initialize() {
	}


	public void addTextSequence(String s)
	{
		scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
		scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
		scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
		scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
	}

	public void hallway()
	{
		scene.clearSegments();

		background.setAnimation( background.darkforest );
		dialogBox.setText("");
		kelsoe.addAction( SceneActions.moveToOutsideLeft(0) );

		scene.addSegment( new SceneSegment( kelsoe, SceneActions.setAnimation( image) ));
		scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
		scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(1) )); 
		scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

		addTextSequence( txt1 );
		addTextSequence( txt2 );
		addTextSequence( txt3 );

		scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
		scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideRight(1) )); 
		scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

		scene.addSegment( new SceneSegment( background, Actions.run(() -> { AttackPartGame.setActiveScreen( new PlayScreen() ); }) ));

		scene.start();
	}



	public void update(float dt)
	{


	}

	public boolean keyDown(int keyCode)
	{
		if ( keyCode == Keys.C ) // && continueKey.isVisible() )
			scene.loadNextSegment();

		return false;
	}
}