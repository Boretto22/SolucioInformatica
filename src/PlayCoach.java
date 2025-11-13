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
        switch(appGUI.pantallaActual) {
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
            appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key=='1'){
            appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        appGUI.text1.keyPressed(key, keyCode);

    }
    public void updateHandCursor(){
        if(appGUI.b1.updateHandCursor(this) || appGUI.b2.updateHandCursor(this)|| appGUI.b3.updateHandCursor(this) ||
                appGUI.b4.updateHandCursor(this) || appGUI.b5.updateHandCursor(this) || appGUI.b6.updateHandCursor(this) || appGUI.b7.updateHandCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }
    public void mousePressed() {
        if (appGUI.b1.mouseOverButton(this)) {
            println("B1 has been pressed!!");
            appGUI.b1.toggleSelected();
        }
        if (appGUI.b2.mouseOverButton(this)) {
            println("B2 has been pressed!!");
            appGUI.b2.toggleSelected();
        }
        if (appGUI.b3.mouseOverButton(this)) {
            println("B3 has been pressed!!");
            appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        if (appGUI.b4.mouseOverButton(this)) {
            println("B4 has been pressed!!");
            appGUI.b4.toggleSelected();
        }
        if (appGUI.b5.mouseOverButton(this)) {
            println("B4 has been pressed!!");
            appGUI.b5.toggleSelected();
        }
        if (appGUI.b6.mouseOverButton(this)) {
            println("B4 has been pressed!!");
            appGUI.b6.toggleSelected();
        }
        if (appGUI.b7.mouseOverButton(this)) {
            println("B4 has been pressed!!");
            appGUI.b7.toggleSelected();
        }

        appGUI.text1.isPressed(this);
    }


}
