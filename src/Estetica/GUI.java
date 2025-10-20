package Estetica;

import static Estetica.Medida.*;
import processing.core.PApplet;

public class GUI{

    //Botons
    public static Boto_Prova b1;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.LOGIN;

        b1 = new Boto_Prova(p5, "RED", 40,400,250,100);
        text1 = new Text_Field(p5, 50,20,50,20);
    }
    //Pantalles GUI

    public static void dibujoPantallaLogIn(PApplet p5){
        p5.background(255);
        p5.background(55);
        zonaLogo(p5);
        sideBar(p5);
        b1.display(p5);
        text1.display(p5);

    }

    public static void dibujoPantallaInicial(PApplet p5){
        p5.background(55);
        p5.rect(50,50,100,20);
    }

    //Zones de la GUI

    public static void logoLogIn(PApplet p5){

    }

    public static void zonaLogo(PApplet p5){
        p5.fill(200,50,100);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        p5.fill(0);
        p5.text("LOGO", marginH + logoWidth/2, marginV + logoHeight/2);
    }

    public static void sideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(50,200,100);
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0);
        p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
    }

   }
