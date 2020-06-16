package com.mygdx.game.dialogbox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Background;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.MenuScreen;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.AttackPartGame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class StoryScreen extends BaseScreen
{
    Scene scene;
    Background background;
    Personnages perso;
    DialogBox dialogBox;
    BaseActor continueKey;
    Table buttonTable;
    BaseActor theEnd;

    public void initialize()
    {
        background = new Background(0,0, mainStage);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        perso = new Personnages(0,0, mainStage);

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

        // this is being added to the mainStage
        //  so that it does not block access to the buttons
        theEnd = new BaseActor(0,0,mainStage);
        theEnd.loadTexture("assets/the-end.png");
        theEnd.centerAtActor(background);
        theEnd.setScale(2);
        theEnd.setOpacity(0);
        
        scene = new Scene();
        mainStage.addActor(scene);

        darkforestmechant();
    }

    public void addTextSequence(String s)
    {
        scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
        scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
    }

    public void darkforestmechant()
    {
        scene.clearSegments();

        background.setAnimation( background.darkforest );
        dialogBox.setText("");
        perso.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( perso, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Aaargh ! Qui voilà ! Un étudiant perdu de TSP venu m'attaquer ?" );
        addTextSequence( "Défies moi si tu l'oses. Mouahahahahah" );

        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( perso, SceneActions.moveToOutsideRight(1) )); 
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        scene.addSegment( new SceneSegment( background, Actions.run(() -> { darkforestgentil(); }) ));

        scene.start();
    }

    public void darkforestgentil()
    {
        scene.clearSegments();

        background.setAnimation( background.darkforest );
        scene.addSegment( new SceneSegment( perso, SceneActions.setAnimation( perso.lookLeft ) ));
        dialogBox.setText("");
        perso.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( perso, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Tu as quatre acolytes pour t'aider dans ta mission" );
        addTextSequence( "Rapidité, mémoire, dextérité, que privilégieras-tu?" );

        
        
        scene.addSegment( new SceneSegment( buttonTable, Actions.show() ));

        // set up options
        TextButton debuterButton = new TextButton("Commencer", BaseGame.textButtonStyle);
        debuterButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) || 
                     !((InputEvent)e).getType().equals(Type.touchDown) )
                     return false;

                scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                BaseGame.setActiveScreen( new PlayScreen() );
                return false;
            }
        );
        

        buttonTable.clearChildren();
        buttonTable.add(debuterButton);
        
        buttonTable.row();
        

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