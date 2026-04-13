package Estetica;

import processing.core.PApplet;

import java.util.Calendar;

public class CalendariPlus extends Calendari {
    // Botons del calendari
    public Botons bNext, bPrev, bOK;

    // Visibilitat del calendari
    boolean visible = true;

    // Constructor
    public CalendariPlus(PApplet p5, int x, int y, int w, int h) {

        super(x, y, w, h);

        bNext = new Botons(p5, "Seguent", x+ w/3+255, y -70, 100, 50);
        bPrev = new Botons(p5, "Anterior", x+w/3+115, y - 70, 100, 50);
        //bOK   = new Botons(p5, "OK", x+w/3+200, y - 70, 50, 50);
    }

    // Dibuixa el Calendari
    public void display(PApplet p5) {
        if (visible) {
            p5.pushStyle();

            p5.fill(255); p5.noStroke();
            p5.rect(x, y-80, w, h);

            super.display(p5);



            // Dibuixa els botons
            bNext.display(p5);
            bPrev.display(p5);
            //bOK.display(p5);
            p5.popStyle();
        }

    }
}