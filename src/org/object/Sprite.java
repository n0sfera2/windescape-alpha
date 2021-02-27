package org.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.graphics.Animation;
import org.graphics.Renderer;

public class Sprite {
    
    public float posX = 0;
    public float posY = 0;

    public Animation[] animations;
    public int currentAnimation = 0;
    
    public int width;
    public int height;
    
    public boolean isSolid = false;
    public boolean doesCollision;
    
    public Sprite(float posX, float posY, int width, 
            int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    
    public Sprite(float posX, float posY, int width, 
            int height, boolean doesCollision){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.doesCollision = doesCollision;
    }
    
    public void update(float deltaTime){}
    
    public void render(Graphics g){
        if(animations == null || currentAnimation >= animations.length){
            return;
        }
        
        animations[currentAnimation].playAnimation();

        BufferedImage image = animations[currentAnimation].getImage();
        
        if(image == null){
            return;
        }
        
        int realX = (int) posX - image.getWidth() / 2;
        int realY = (int) posY - image.getHeight() / 2;
        
        realX = realX - (int) Renderer.camX + Renderer.gameWidth / 2;
        realY = realY - (int) Renderer.camY + Renderer.gameHeight / 2;
        
        g.drawImage(image, realX, realY, image.getWidth(),
                image.getHeight(), null);
    }
}
