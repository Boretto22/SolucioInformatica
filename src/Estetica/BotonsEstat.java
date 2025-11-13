package Estetica;

import processing.core.PApplet;
import processing.core.PShape;

public class BotonsEstat extends Botons{

    boolean selected = false;

    public BotonsEstat(PApplet p5, String text, float x, float y, float w, float h) {
        super(p5, text, x, y, w, h);
    }

    public BotonsEstat(PApplet p5, PShape shape, float x, float y, float w, float h) {
        super(p5, shape, x, y, w, h);
    }

    public void setSelected(boolean b){ this.selected = b; }

    public void toggleSelected(){ this.selected = !this.selected ; }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(this.selected){
            if(img = false) {
                p5.fill(fillColorOver);
            } else { p5.fill(paleta.getColorAt(6));}
        }
        else{
            if(img = false) {
                p5.fill(fillColorOver);
            } else { p5.fill(paleta.getColorAt(7));}
        }
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
        this.fillColor = paleta.getColorAt(2);
        this.fillColorOver = paleta.getColorAt(1);
        this.strokeColor = p5.color(0);
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

        // Text (color, alineació i mida)
        if(this.shape==null) {
            p5.fill(0);
            p5.textAlign(p5.CENTER);
            p5.textSize(50);
            p5.text(textBoto, this.x + this.w / 2, this.y + this.h / 2 + 15);
        }
        else {
            this.fillColor = paleta.getColorAt(2);
            this.fillColorOver = paleta.getColorAt(1);
            p5.shapeMode(p5.CENTER);
            p5.shape(shape, this.x + this.w / 2, this.y + this.h / 2, this.w, this.h);
        }
        p5.popStyle();
    }

}
