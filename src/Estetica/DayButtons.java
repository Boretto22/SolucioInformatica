package Estetica;

import processing.core.PApplet;

public class DayButtons {

    public class DayButton {
        PApplet p5;
        float x, y, w, h;
        int dia, mes, any;
        boolean enabled;
        boolean selected = false;

        public DayButton(PApplet p5, float x, float y, float w, float h, int dia, int mes, int any, boolean enabled) {
            this.p5 = p5;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.dia = dia;
            this.mes = mes;
            this.any = any;
            this.enabled = enabled;
        }

        public void display() {
            p5.pushStyle();
            if (selected) p5.fill(100, 150, 255);
            else if (enabled) p5.fill(200);
            else p5.fill(240);

            p5.stroke(0);
            p5.rect(x, y, w, h);

            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(dia, x + w / 2, y + h / 2);
            p5.popStyle();
        }

        public boolean isMouseOver() {
            return p5.mouseX >= x && p5.mouseX <= x + w && p5.mouseY >= y && p5.mouseY <= y + h;
        }

        public boolean isEnabled() { return enabled; }
        public void setSelected(boolean s) { this.selected = s; }
    }
}