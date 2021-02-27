package org.windescape;

import com.sun.glass.events.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphics.Renderer;
import org.input.Input;
import org.object.Mob;
import org.object.Platform;
import org.object.Player;
import org.world.World;



public class WindEscape {
    
    public static void main(String[] args) {
        
        Renderer.init();

        // TODO ADD MOBS TO WORLD MOBLIST
        World.currentWorld = new World();
        try {
            World.currentWorld.addBackdrop(Renderer
                    .loadImage("/resources/images/backgrounds/bg1color.png"));
        } catch (IOException ex) {
            Logger.getLogger(WindEscape.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        World.currentWorld.addSprite(new Platform(200, 234, 1000, 10));
        World.currentWorld.addSprite(new Mob(300, 100, 32, 32, true, false, 
                "/resources/images/mobs/bela/belaRight.png"));
        World.currentWorld.addSprite(new Mob(332, 100, 32, 32, true, false, 
                "/resources/images/mobs/sakura/sakLeft.png"));
        World.currentWorld.addSprite(new Mob(364, 100, 32, 32, true, false, 
                "/resources/images/mobs/thomas/thomasLeft.png"));
        World.currentWorld.addSprite(new Mob(268, 100, 32, 32,  true, false, 
                "/resources/images/mobs/joe/joeRight.png"));
        World.currentWorld.addSprite(new Player(100, 100, 32, 32));
    }

    public static void quit() {
        System.exit(0);
    }

}
