import Estetica.Paleta;
import processing.core.PApplet;

public class FutbolManager extends PApplet {

    //Palera de la app
    Paleta appPaleta;

    public static void main(String[] args) {
        PApplet.main("FutbolManager");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){
        appPaleta = new Paleta(this);
    }

    public void draw(){
        // Dibuixa el fons (blanc)
        background(255);



        // Mostra la paleta de colors
        appPaleta.displayPaleta(this, 100,100,width-200);
    }
}
