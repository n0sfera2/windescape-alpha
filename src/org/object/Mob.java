package org.object;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphics.Animation;
import org.graphics.Renderer;
import org.world.World;
import static org.world.World.currentWorld;

public class Mob extends Sprite {
    
    protected float runSpeed = 50.0f;
    private float velocityY = 0;
    private float gravity = 500.0f;
    private float jumpHeight = 30;
    
    private int direction = 1;
    public boolean doesTalk;
    
    public Mob(float posX, float posY, int width, int height, 
            boolean doesTalk, boolean doesCollision){
        super(posX, posY, width, height);
    }
    
    public Mob(float posX, float posY, int width, int height, 
            boolean doesTalk, boolean doesCollision, String image){
        super(posX, posY, width, height, doesCollision);
        
        Animation rest = new Animation();
        try {
            rest.images.add(Renderer.loadImage(image));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        animations = new Animation[]{
            rest
        };
    }
    
    public void update(float deltaTime){
        float moveX = 0;
        
        velocityY += gravity * deltaTime;

        // Do collisions
        if(doesCollide(posX+moveX*deltaTime, posY)){
            moveX -= moveX;
        }
        
        if(doesCollide(posX, posY+velocityY*deltaTime)){
            velocityY -= velocityY;
        }
        // End collisions
        
        posX += moveX * deltaTime;
        posY += velocityY * deltaTime;
    }
    
    private boolean doesCollide(float x, float y){
        float myLeft = x - super.width / 2;
        float myRight = x + super.width / 2;
        float myUp = y - super.height / 2;
        float myDown = y + super.height / 2;
        
        for(Sprite sprite : World.currentWorld.sprites){
            if(sprite == this){
                continue;
            }
            
            float otherLeft = sprite.posX - sprite.width / 2;
            float otherRight = sprite.posX + sprite.width / 2;
            float otherUp = sprite.posY - sprite.height / 2;
            float otherDown = sprite.posY + sprite.height / 2;
            
            if(myLeft < otherRight && myRight > otherLeft && myDown > otherUp 
                    && myUp < otherDown){
                return true;
            }
        }
        return false;
    }
    
    public void talk(){
        // make speech bubble sprite appear
    }
}
