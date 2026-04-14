package Estetica;

import processing.core.PApplet;
import processing.core.PVector;

public class TacticalArrow {
    PVector start;
    PVector end;
    int color;

    public TacticalArrow(PVector start, PVector end, int color){
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public void setEnd(PVector end){
        this.end = end;
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.stroke(color);
        p5.strokeWeight(3);
        p5.fill(color);

        // Dibujar flecha
        p5.line(start.x, start.y, end.x, end.y);

        // Draw arrowhead
        float angle = PApplet.atan2(end.y - start.y, end.x - start.x);
        float arrowSize = 10;

        p5.pushMatrix();
        p5.translate(end.x, end.y);
        p5.rotate(angle);
        p5.triangle(0, 0, -arrowSize, -arrowSize/2, -arrowSize, arrowSize/2);
        p5.popMatrix();

        p5.popStyle();
    }
}
