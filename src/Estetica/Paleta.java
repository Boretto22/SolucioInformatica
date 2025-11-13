package Estetica;

import processing.core.PApplet;

public class Paleta {

    public int[]colors;

    public Paleta(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    public void setColors(PApplet p5){
        this.colors = new int[10];
        this.colors[0] = p5.color(0xFFD9E1F1); //blau pantalla fondo
        this.colors[1] = p5.color(0xFF8FB3E2); //blau botons damunt
        this.colors[2] = p5.color(0xFF6582AA); //blau botons
        this.colors[3] = p5.color(0xFF999999); //gris
        this.colors[4] = p5.color(0xFF6582AA); //sidebar
        this.colors[5] = p5.color(0xFFE0E0E0); //blanc
        this.colors[6] = p5.color(0xFF47E336); // botons sidebar
        this.colors[7] = p5.color(0xFF79F06E); // boto pressed sidebar

    }

    // Getter del número de colors
    public int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    public int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    public int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    public int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    public int getColorAt(int i){
        return this.colors[i];
    }

    // Dibuixa paleta de colors
    public void displayPaleta(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }
}
