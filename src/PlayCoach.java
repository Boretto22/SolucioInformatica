import Estetica.GUI;
import Estetica.Tipografia;
import processing.core.PApplet;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.Key;

public class    PlayCoach extends PApplet {


    Tipografia appTipografia;
    GUI appGUI;


    public static void main(String[] args) {
        PApplet.main("PlayCoach");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){
        appTipografia = new Tipografia(this);
        appGUI = new GUI(this);
    }

    public void draw(){
        // Dibuixa el fons (blanc)
        updateHandCursor();
        background(255);

        textFont(appTipografia.getFirstTipografia());
        text("Titulo de la App", 50, 200);

        fill(50);
        textFont(appTipografia.getSecondTipografia());
        text("Subtitulo de la App", 50, 250);

        fill(55,0,0);
        textFont(appTipografia.getThirdTipografia());
        text("Paragrafo de la App", 50,300);

        // Dibuixa la pantalla corresponent
        switch(GUI.pantallaActual) {
            case LOGIN:
                appGUI.dibujoPantallaLogIn(this);
                break;

            case INICIAL:
                appGUI.dibujoPantallaInicial(this);
                break;
        }

        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appTipografia.displayTipografia(this, 100,400,500);

    }

    //KEYBOARD interaction

    public void keyPressed(){
        if(key=='0'){
            GUI.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key=='1'){
            GUI.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        GUI.text1.keyPressed(key, keyCode);

    }
    public void updateHandCursor(){
        if(GUI.b1.updateHandCursor(this) || GUI.b2.updateHandCursor(this)|| GUI.b3.updateHandCursor(this) || GUI.b4.updateHandCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }
    public void mousePressed() {
        if (GUI.b1.mouseOverButton(this)) {
            println("B1 has been pressed!!");
        }
        if (GUI.b2.mouseOverButton(this)) {
            println("B2 has been pressed!!");
        }
        if (GUI.b3.mouseOverButton(this)) {
            println("B3 has been pressed!!");
            GUI.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        if (GUI.b4.mouseOverButton(this)) {
            println("B4 has been pressed!!");
        }

        GUI.text1.isPressed(this);
    }


}
