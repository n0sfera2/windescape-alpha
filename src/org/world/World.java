package org.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphics.Renderer;
import org.object.Mob;
import org.object.Sprite;

public class World {
    
    public static World currentWorld = null;
    
    private static long lastTime = System.nanoTime();
    
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public ArrayList<Sprite> addSprites = new ArrayList<Sprite>();
    public ArrayList<Sprite> removeSprites = new ArrayList<Sprite>();
    public ArrayList<Mob> mobs = new ArrayList<Mob>();
    public ArrayList<Mob> addMobs = new ArrayList<Mob>();
    public ArrayList<Mob> removeMobs = new ArrayList<Mob>();
    public static ArrayList<BufferedImage> backdrops = new ArrayList<BufferedImage>();
    public ArrayList<BufferedImage> addBackdrops = new ArrayList<BufferedImage>();
    public ArrayList<BufferedImage> removeBackdrops = new ArrayList<BufferedImage>();
    
    public static void update(){
        float deltaTime = (System.nanoTime() - lastTime) / 1000000000.0f;
        lastTime = System.nanoTime();
        
        for(Sprite sprite : currentWorld.sprites){
            sprite.update(deltaTime);
        }
        
        /* MAY NOT NEED THIS
        for(Mob mob : currentWorld.mobs){
            mob.update(deltaTime);
        }
        */
        
        for(Sprite sprite : currentWorld.addSprites){
            if(!currentWorld.sprites.contains(sprite)){
                currentWorld.sprites.add(sprite);
            }
        }
        currentWorld.addSprites.clear();
        
        for(Mob mob : currentWorld.addMobs){
            if(!currentWorld.mobs.contains(mob)){
                currentWorld.mobs.add(mob);
            }
        }
        currentWorld.addMobs.clear();
        
        for(BufferedImage backdrop : currentWorld.addBackdrops){
            if(!currentWorld.backdrops.contains(backdrop)){
                currentWorld.backdrops.add(backdrop);
            }
        }
        currentWorld.addBackdrops.clear();
        
        for(Sprite sprite : currentWorld.removeSprites){
            if(currentWorld.sprites.contains(sprite)){
                currentWorld.sprites.remove(sprite);
            }
        }
        currentWorld.removeSprites.clear();
        
        for(Mob mob : currentWorld.removeMobs){
            if(currentWorld.mobs.contains(mob)){
                currentWorld.mobs.remove(mob);
            }
        }
        currentWorld.removeMobs.clear();
        
        for(BufferedImage backdrop : currentWorld.removeBackdrops){
            if(currentWorld.backdrops.contains(backdrop)){
                currentWorld.backdrops.remove(backdrop);
            }
        }
        currentWorld.removeBackdrops.clear();
    }
    
    public static void loopBackdrop(Graphics g, BufferedImage backdrop, 
            int backdropX, int bufferX){
        if(backdropX < Renderer.camX - Renderer.gameWidth){
                backdropX += Renderer.gameWidth;
            }
        if(backdropX > Renderer.camX + Renderer.gameWidth){
            backdropX -= Renderer.gameWidth;
        }
        g.drawImage(backdrop, bufferX, 0, Renderer.gameWidth, 
                Renderer.gameHeight, null);
    }
    
    public static void render(Graphics g){
        if(!backdrops.isEmpty()){
            for(BufferedImage backdrop : currentWorld.backdrops){
                int backdropX = 0;
                int x = backdropX - (int)Renderer.camX;
                int bufferX = 0;
            
                if(backdropX > Renderer.camX){
                    bufferX = backdropX - Renderer.gameWidth - (int) Renderer.camX;
                }else{
                    bufferX = backdropX + Renderer.gameWidth - (int) Renderer.camX;
                }
                // +233, +100
                g.drawImage(backdrop, x, -20, backdrop.getWidth(),    
                    backdrop.getHeight(), null);
                // loopBackdrop(g, backdrop, backdropX, bufferX);
                for(Sprite sprite : currentWorld.sprites){
                    sprite.render(g);
                }
            }
        }  
    }
    
    public void addSprite(Sprite sprite){
        if(!addSprites.contains(sprite)){
            addSprites.add(sprite);
        }
    }
    
    public void addMob(Mob mob){
        if(!addMobs.contains(mob)){
            addMobs.add(mob);
        }
    }
    
    public void addBackdrop(BufferedImage backdrop){
        if(!addBackdrops.contains(backdrop)){
            addBackdrops.add(backdrop);
        }
    }
    
    public void removeSprite(Sprite sprite){
        if(!removeSprites.contains(sprite)){
            removeSprites.add(sprite);
        }
    }
    
    public void removeMob(Mob mob){
        if(!removeMobs.contains(mob)){
            removeMobs.add(mob);
        }
    }
    
    public void removeBackdrop(BufferedImage backdrop){
        if(!removeBackdrops.contains(backdrop)){
            removeBackdrops.add(backdrop);
        }
    }
    
    
}
