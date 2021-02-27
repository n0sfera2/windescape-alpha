package org.object;

import java.awt.Color;
import java.awt.Graphics;
import org.graphics.Renderer;

public class Platform extends Sprite{
    
    public Platform(float posX, float posY, int width, int height) {
        super(posX, posY, width, height, true);
    }
    
    public void render(Graphics g){
        g.setColor(Color.GREEN);
        g.drawRect((int)(posX - width / 2) - (int) Renderer.camX + 
                Renderer.gameWidth / 2, 
                (int)(posY - height / 2) - (int) Renderer.camY +
                Renderer.gameHeight / 2,  
                (int)super.width, (int)height);  
    }
    
    
    
    
}
