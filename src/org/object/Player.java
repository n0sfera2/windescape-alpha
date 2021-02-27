package org.object;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphics.Animation;
import org.graphics.Renderer;
import org.input.Input;
import org.windescape.WindEscape;
import org.world.World;

public class Player extends Mob{
    
    private float velocityY = 0;
    private float gravity = 500.0f;
    private float jumpHeight = 60;
    private int direction = 1;
    
    public Player(float posX, float posY, int width, int height) {
        super(posX, posY, width, height, false, false);
        runSpeed = 100;
        
        Animation restRight = new Animation();
        try {
            restRight.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericRight.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        Animation restLeft = new Animation();
        try {
            restLeft.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericLeft.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        Animation walkRight = new Animation();
        try {
            walkRight.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericRight.png"));
            walkRight.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericRightWalk.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        Animation walkLeft = new Animation();
        try {
            walkLeft.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericLeft.png"));
            walkLeft.images.add(Renderer.loadImage(
                    "/resources/images/mobs/eric/ericLeftWalk.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        animations = new Animation[]{
            restRight, restLeft, walkRight, walkLeft
        };
        
    }
   
    public void update(float deltaTime){
        float moveX = 0;
        
        if(Input.getKey(KeyEvent.VK_RIGHT)){
            moveX += runSpeed;
            direction = 1;
            currentAnimation = 2;
        }
        if(Input.getKey(KeyEvent.VK_LEFT)){
            moveX -= runSpeed;
            direction = -1;
            currentAnimation = 3;
        }
        if(Input.getKeyUp(KeyEvent.VK_RIGHT)){
            currentAnimation = 0;
            direction = 1;
        }
        if(Input.getKeyUp(KeyEvent.VK_LEFT)){
            currentAnimation = 1;
            direction = -1;
        }
        if (Input.getKey(KeyEvent.VK_ESCAPE)) {
            WindEscape.quit();
        }

        if(moveX > 0){
            direction = 1;
        }
        if(moveX < 0){
            direction = -1;
        }
        
        velocityY += gravity * deltaTime;
        if(doesCollide(posX, posY+1)){
            if(Input.getKeyDown(KeyEvent.VK_SPACE)){
                velocityY = (float) -Math.sqrt(2*jumpHeight*gravity);
            }
        }

        // Do collisions
        if(doesCollide(posX+moveX*deltaTime, posY)){
            moveX -= moveX;
        }
        
        if(doesCollide(posX, posY+velocityY*deltaTime)){
            velocityY -= velocityY;
        }
        // End collisions
        
        // Do talk
        if(doesTalk(posX+moveX*deltaTime, posY)){
            // trigger mob speech bubble
            System.out.println("BLLLLLLLLAHHHHHHHHHHHHHHHH");
        }
        
        if(doesTalk(posX, posY+velocityY*deltaTime)){
            // trigger mob speech bubble
            System.out.println("BLLLLLLLLAHHHHHHHHHHHHHHHH");
        }
        
        
        posX += moveX * deltaTime;
        posY += velocityY * deltaTime;
        
        Renderer.camX = posX;
        Renderer.camY = 100;
    }
    
    private boolean doesTalk(float x, float y){
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;
        
        for(Mob mob : World.currentWorld.mobs){
            if(mob == this){
                continue;
            }
            
            float otherLeft = mob.posX - mob.width / 2;
            float otherRight = mob.posX + mob.width / 2;
            float otherUp = mob.posY - mob.height / 2;
            float otherDown = mob.posY + mob.height / 2;
            System.out.println("doesTalk: FIORLOPP");
            
            if(myLeft < otherRight && myRight > otherLeft && myDown > otherUp 
                    && myUp < otherDown){
                
                if(mob.doesTalk){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean doesCollide(float x, float y){
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;
        
        for(Sprite sprite : World.currentWorld.sprites){
            if(sprite == this){
                continue;
            }
            if(!sprite.doesCollision){
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
}
