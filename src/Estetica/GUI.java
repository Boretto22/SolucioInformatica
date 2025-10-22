package Estetica;

import static Estetica.Medida.*;
import processing.core.PApplet;

public class GUI{

    Paleta paleta;

    //Botons
    public static Botons b1, b2;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.LOGIN;

        paleta = new Paleta(p5);

        b1 = new Botons(p5, "ENTRENADOR", p5.width/2-200,p5.height/2,250,100);
        b2 = new Botons(p5,"JUGADOR", p5.width/2+200, p5.height/2, 250, 100);
        text1 = new Text_Field(p5, p5.width/2-125, p5.height/2+200, 250,100);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.background (paleta.getColorAt(0));
        logoLogIn(p5);
        b1.display(p5);
        b2.display(p5);
        text1.display(p5);
        p5.textSize(30);
        p5.text("USERNAME", p5.width/2-125, p5.height/2+175);

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background(55);
        p5.rect(50,50,100,20);
    }

    //Zones de la GUI

    public static void logoLogIn(PApplet p5){
        p5.fill(100);
        p5.circle(p5.width/2, p5.height/2-200, 300);
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
