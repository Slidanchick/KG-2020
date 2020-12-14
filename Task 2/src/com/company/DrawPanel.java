package com.company;

import com.company.line_drawers.*;
import com.company.line_drawers.BresenhamLineDrawer;
import com.company.line_drawers.DDALineDrawer;
import com.company.line_drawers.WuLineDrawer;
import com.company.pixel_drawers.GraphicsPixelDrawer;
import com.company.utils.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

//ctrl+o
public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics bi_g = bi.createGraphics();
        bi_g.setColor(Color.white);
        bi_g.fillRect(0, 0, getWidth(), getHeight());
        bi_g.setColor(Color.black);
        PixelDrawer pd = new GraphicsPixelDrawer(bi_g);
        LineDrawer ld = new DDALineDrawer(pd);
        //LineDrawer gl = new GraphicsLineDrawer(bi_g);
        LineDrawer bl = new BresenhamLineDrawer(pd);
        LineDrawer wu = new WuLineDrawer(pd);
        /**/
        drawAll(ld, bl,wu);
        /**/
        g.drawImage(bi, 0, 0, null);
        bi_g.dispose(); //убираемся
    }

    private void drawAll(LineDrawer ld, LineDrawer bl, LineDrawer wu) {
        int mouseX = position.x - 10;
        int mouseY = position.y;
        ld.drawLine(250, 200, mouseX, mouseY);
        bl.drawLine(900, 300, mouseX, mouseY);
        wu.drawLine(500, 300, mouseX, mouseY);

        DrawUtils.drawSnowflake(ld, 250,500, 100, 32);
        DrawUtils.drawSnowflake(wu, 500,500, 100, 32);
        DrawUtils.drawSnowflake(bl, 900,500, 100, 32);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();

    }
}
