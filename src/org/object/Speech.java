package org.object;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphics.Animation;
import org.graphics.Renderer;

public class Speech extends Sprite {
    public Speech(float posX, float posY){
        super(posX, posY, 16, 16);
        Animation restRight = new Animation();
        try {
            restRight.images.add(Renderer.loadImage(
                    "/resources/images/speech.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
