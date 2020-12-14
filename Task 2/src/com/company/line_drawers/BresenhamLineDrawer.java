package com.company.line_drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int d = 0;
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        int ix = x2 < x1 ? 1 : -1;
        int iy = y2 < y1 ? 1 : -1;
        int x = x2;
        int y = y2;
        //k<0
        if(dx >= dy){
            while(true){
                pd.setPixel(x, y, Color.green);
                if(x == x1)
                    break;
                x += ix;
                d += dy*2;
                if(d > dx){
                    y += iy;
                    d -= dx*2;
                }
            }
        }
        else{
            while(true){
                pd.setPixel(x, y, Color.green);
                if(y == y1)
                    break;
                y += iy;
                d += dx*2;
                if(d > dy){
                    x += ix;
                    d -= dy*2;
                }
            }
        }
        }
    }
