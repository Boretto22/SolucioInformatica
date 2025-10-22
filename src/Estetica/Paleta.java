package Estetica;

import processing.core.PApplet;

public class Paleta {

    public int[]colors;

    public Paleta(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    public void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(0xFF0099FF); //blau pantalla fondo
        this.colors[1] = p5.color(0xFF1CA332); //verd botons damunt
        this.colors[2] = p5.color(0xFF2BE045); // verd botons no funciona
        this.colors[3] = p5.color(0xFFE80000);
        this.colors[4] = p5.color(0xFFE800AA);
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
