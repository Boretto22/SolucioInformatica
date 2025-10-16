package Estetica;

import processing.core.PApplet;

public class GUI {

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    public GUI(){
        pantallaActual = PANTALLA.LOGIN;
    }

    //Pantalles GUI

    public static void dibujoPantallaLogIn(PApplet p5){
        p5.background(55);
        p5.circle(p5.width/2, p5.height/2, 55);
    }

    public static void dibujoPantallaInicial(PApplet p5){
        p5.background(55);
        p5.rect(50,50,100,20);
    }

    //Zones de la GUI

    public void zonaLogo(PApplet p5){}

    public void zonaMenu(PApplet p5){}

   }
