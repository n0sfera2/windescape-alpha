package org.windescape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.graphics.Renderer;

public class Menu {
    public void render(Graphics g){
        Font fnt0 = new Font("Bolackmoor LET", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        g.drawString("WINDESCAPE", Renderer.gameWidth / 2, 100);
    }
}
