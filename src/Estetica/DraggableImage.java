package Estetica;

import processing.core.PApplet;
import processing.core.PImage;

public class DraggableImage {

    PImage img;
    public float x, y, w, h;
    public boolean dragging = false;

    public DraggableImage(PImage img, float x, float y, float w, float h){
        this.img = img;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean contains(float mx, float my){
        return mx >= x - w/2 && mx <= x + w/2 && my >= y - h/2 && my <= y + h/2;
    }

    public void display(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(img, x, y, w, h);
    }
}
