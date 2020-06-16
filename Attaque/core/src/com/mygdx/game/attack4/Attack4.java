package com.mygdx.game.attack4;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.BaseActor;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.AttackPartGame;
import com.badlogic.gdx.graphics.Color;

public class Attack4 extends BaseScreen
{
    private Label messageLabel;
    private Random random;
    private String image;
    
    
    public void initialize() 
    {        
        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture("space.png");
        
        int numberRows = 3;
        int numberCols = 3;
        random = new Random();
        int nbimage = random.nextInt(4);
        System.out.println("sun" + Integer.toString(nbimage)+ ".png");
        image = "Attack4/sun" + Integer.toString(nbimage)+ ".png";
        Texture texture = new Texture(Gdx.files.internal(image), true);
        
    
        
        
        int imageWidth  = texture.getWidth();
        int imageHeight = texture.getHeight();
        
  
        
        int pieceWidth  = imageWidth  / numberCols;
        int pieceHeight = imageHeight / numberRows;

        TextureRegion[][] temp = TextureRegion.split(texture, pieceWidth, pieceHeight);

        for (int r = 0; r < numberRows; r++)
        {
            for (int c = 0; c < numberCols; c++)
            {
                // create puzzle piece at random location on left half of screen
                int pieceX = MathUtils.random(0, 400 - pieceWidth);
                int pieceY = MathUtils.random(0, 600 - pieceHeight); 
                PuzzlePiece pp = new PuzzlePiece(pieceX, pieceY, mainStage);
                
                Animation<TextureRegion> anim = new Animation<TextureRegion>(1, temp[r][c]);   
                pp.setAnimation( anim );
                pp.setRow(r);
                pp.setCol(c);

                int borderX = (400 - imageWidth)/2;
                int borderY = (600 - imageHeight)/2;

                int areaX = (400 + borderX) + pieceWidth * c;
                int areaY = (600 - borderY - pieceHeight) - pieceHeight * r;

                PuzzleArea pa = new PuzzleArea(areaX, areaY, mainStage);
                pa.setSize(pieceWidth, pieceHeight);
                pa.setBoundaryRectangle();
                pa.setRow(r);
                pa.setCol(c);
            }
        }
        
        messageLabel = new Label("...", BaseGame.labelStyle);
        messageLabel.setColor( Color.WHITE );
        uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
        messageLabel.setVisible(false);
    }

    public void update(float dt)
    {
        // check to see if pieces are in correct positions
        boolean solved = true;
        for (BaseActor actor : BaseActor.getList(mainStage, "com.mygdx.game.attack4.PuzzlePiece"))
        {
            PuzzlePiece pp = (PuzzlePiece)actor;
            
            if ( !pp.isCorrectlyPlaced() )
                solved = false;
        }

        if (solved)
        {
            messageLabel.setText("You win!");
            messageLabel.setVisible(true);
        }
        else
        {
            messageLabel.setText("...");
            messageLabel.setVisible(false);
        }
        
        
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))  
			AttackPartGame.setActiveScreen( new PlayScreen() );
        if (Gdx.input.isKeyPressed(Keys.SPACE))  
			AttackPartGame.setActiveScreen( new Attack4());
        
    }
}