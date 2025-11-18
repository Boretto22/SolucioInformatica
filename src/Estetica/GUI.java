package Estetica;
import static Estetica.Medida.*;

import processing.core.*;
import Estetica.Text_Field;

public class GUI{

    PFont pFont1;

    static Paleta paleta;

    PImage logo, logoPantalles;
    PShape house, fieldicon;
    //Botons
    public BotonsEstat b1, b2, b4, b5, b6, b7;
    public Botons b3;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public PANTALLA pantallaActual;

    //Text Field
    public Text_Field text1;

    public GUI(PApplet p5){

        logo = p5.loadImage("LogoApp.png");
        logoPantalles = p5.loadImage("Logo.png");
        house = p5.loadShape("house-solid-full.svg");
        fieldicon = p5.loadShape("football-field.svg");
        pFont1 = p5.createFont("Roboto-Black.ttf", 30);
        pantallaActual = PANTALLA.LOGIN;
        paleta = new Paleta(p5);

        b1 = new BotonsEstat(p5,"ENTRENADOR", p5.width/2-300,p5.height/2,250,100);
        b2 = new BotonsEstat(p5, "JUGADOR", p5.width/2+50, p5.height/2, 250, 100);
        b3 = new Botons(p5,"LOGIN", p5.width/2-250, p5.height/2+350, 500,75);
        b4 = new BotonsEstat(p5,house, marginH+25,2*marginV+150, 50,50);
        b5 = new BotonsEstat(p5,fieldicon, marginH+25,2*marginV+250, 50,50);
        b6 = new BotonsEstat(p5,house, marginH+25,2*marginV+250, 50,50);
        b7 = new BotonsEstat(p5,house, marginH+25,2*marginV+250, 50,50);

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
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
    }

    //Zones de la GUI

    public void logoLogIn(PApplet p5, PImage logo) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2 - 265, logoWidth/2+450, logoHeight/2+450);
    }

    public void logoPantallas(PApplet p5, PImage logoPantalles) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logoPantalles, p5.width/2-900, p5.height/2-465, logoPantallesWidth/2, logoPantallesHeight/2);
    }


    public static void sideBar(PApplet p5){
        p5.noStroke();
        p5.fill (paleta.getColorAt(4));
        p5.rect(marginH, 2*marginV + 100, sidebarWidth, sidebarHeight, 20);
    }

   }
