package Estetica;

import processing.core.PApplet;
import processing.core.PImage;

public class DraggableImage {

    PImage img;
    public float x, y, w, h;
    public boolean dragging = false;
    private float originalW, originalH;

    public DraggableImage(PImage img, float x, float y, float w, float h){
        this.img = img;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.originalW = w;
        this.originalH = h;
    }

    public void startDrag(){
        originalW = w;
        originalH = h;
        w *= 1.05f;
        h *= 1.05f;
        dragging = true;
    }

    public void stopDrag(){
        // Permanently grow to 1.1× the pre-drag base size
        w = originalW * 1.1f;
        h = originalH * 1.1f;
        // Update base so next drag scales correctly from the new size
        originalW = w;
        originalH = h;
        dragging = false;
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
