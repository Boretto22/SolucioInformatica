import Estetica.Paleta;
import Estetica.Tipografia;
import processing.core.PApplet;

public class FutbolManager extends PApplet {

    //Palera de la app
    Paleta appPaleta;
    Tipografia appTipografia;


    public static void main(String[] args) {
        PApplet.main("FutbolManager");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){
        appPaleta = new Paleta(this);
        appTipografia = new Tipografia(this);

    }

    public void draw(){
        // Dibuixa el fons (blanc)
        background(255);

        textFont(appTipografia.getFirstTipografia());
        text("Titulo de la App", 50, 200);

        fill(50);
        textFont(appTipografia.getSecondTipografia());
        text("Subtitulo de la App", 50, 250);

        fill(55,0,0);
        textFont(appTipografia.getThirdTipografia());
        text("Paragrafo de la App", 50,300);




        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appTipografia.displayTipografia(this, 100,400,500);
    }
}
