package Estetica;
import static Estetica.Medida.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import Estetica.Text_Field;

public class GUI{

    PFont pFont1;

    Paleta paleta;

    PImage logo, logoPantalles;
    //Botons
    public static Botons b1, b2, b3;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1;

    public GUI(PApplet p5){

        logo = p5.loadImage("LogoApp.png");
        logoPantalles = p5.loadImage("Logo.png");
        pFont1 = p5.createFont("Roboto-Black.ttf", 30);
        pantallaActual = PANTALLA.LOGIN;
        paleta = new Paleta(p5);

        b1 = new Botons(p5,"ENTRENADOR", p5.width/2-300,p5.height/2,250,100);
        b2 = new Botons(p5, "JUGADOR", p5.width/2+50, p5.height/2, 250, 100);
        b3 = new Botons(p5,"LOGIN", p5.width/2-250, p5.height/2+350, 500,75);
        text1 = new Text_Field(p5, p5.width/2-200, p5.height/2+200, 400,100);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.background (paleta.getColorAt(0));
        logoLogIn(p5, logo);
        b1.display(p5);
        b2.display(p5);
        b3.display(p5);
        text1.display(p5);
        p5.pushMatrix();
        p5.textFont(pFont1);
        p5.popMatrix();
        p5.text("USERNAME", p5.width/2-200, p5.height/2+180);

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
    }

    //Zones de la GUI

    public void logoLogIn(PApplet p5, PImage logo) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2 - 250, logoWidth/2+450, logoHeight/2+450);
    }

    public void logoPantallas(PApplet p5, PImage logoPantalles) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logoPantalles, p5.width/2-900, p5.height/2-475, logoPantallesWidth/2, logoPantallesHeight/2);
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
