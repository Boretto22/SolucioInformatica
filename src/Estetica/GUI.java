package Estetica;
import static Estetica.Medida.*;
import static processing.core.PConstants.HALF_PI;

import processing.core.*;
import Estetica.Text_Field;

public class GUI{

    PFont pFont1;

    static Paleta paleta;

    PImage logo, logoPantalles, footballfield;
    PShape house, fieldicon, football, calendar, alert;
    //Botons
    public BotonsEstat b1, b2, b4, b5, b6, b7, b8;
    public Botons b3, btable1, btable2;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, CALENDAR, JUGADAS, ENTRENAMIENTO};

    //Pantalla actual
    public PANTALLA pantallaActual;

    //Text Field
    public Text_Field text1;

    //Dimensions botons
    float buttonW = 120, buttonH = 60;

    //Taula paginada
    PagedTable t;

    // Dimensions de la taula
    float tableW = 1400, tableH = 600;

    // Número de files (capçalera inclosa) i columnes de la taula
    int files = 5, columnes = 5;

    // Títols de les columnes
    String[] headers = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};

    // Amplades de les columnes
    float[] colWidths = {20, 20, 20, 20, 20};

    // Dades de la taula
    String[][] info = {
            {"Campo:", "Descanso", "Descanso", "Campo:", "Campo:"},
            {"Rondos", "-", "-", "Juegos", "Rondos"},
            {"Juego", "-", "-", "Rondos", "Juego"},
            {"Posesion", "-", "-", "Posesion", "Automatismos"},
            {"Automatismo", "-", "-", "Fisico", "Jugadas"},
            {"Definición", "-", "-", "Definición", "Definición"},
    };

    //CalendariPlus
    CalendariPlus c;
    public String dataCalendari = "";

    public GUI(PApplet p5){

        logo = p5.loadImage("LogoApp.png");
        logoPantalles = p5.loadImage("Logo.png");
        house = p5.loadShape("house-solid-full.svg");
        fieldicon = p5.loadShape("football-field.svg");
        football = p5.loadShape("football.svg");
        calendar = p5.loadShape("calendar.svg");
        alert = p5.loadShape("alert.svg");
        pFont1 = p5.createFont("Roboto-Black.ttf", 30);
        pantallaActual = PANTALLA.LOGIN;
        paleta = new Paleta(p5);
        footballfield = p5.loadImage("Football_field.png");

        t = new PagedTable(files, columnes);
        t.setHeaders(headers);
        t.setData(info);
        t.setColumnWidths(colWidths);
        btable1 = new Botons(p5, "NEXT", p5.width/2+725, p5.height/2+340, buttonW, buttonH);
        btable2 = new Botons(p5, "PREV", p5.width/2+555, p5.height/2+340, buttonW, buttonH);

        // Crea el Calendari
        c = new CalendariPlus(p5,p5.width/2-550,p5.height/2-275,1400,800);



        b1 = new BotonsEstat(p5,"ENTRENADOR", p5.width/2-300,p5.height/2,250,100);
        b2 = new BotonsEstat(p5, "JUGADOR", p5.width/2+50, p5.height/2, 250, 100);
        b3 = new Botons(p5,"LOGIN", p5.width/2-250, p5.height/2+350, 500,75);
        b4 = new BotonsEstat(p5,house, marginH+25,2*marginV+150, 65,65);
        b5 = new BotonsEstat(p5,fieldicon, marginH+25,2*marginV+250, 65,65);
        b6 = new BotonsEstat(p5,football, marginH+25,2*marginV+350, 65,65);
        b7 = new BotonsEstat(p5,calendar, marginH+25,2*marginV+450, 65,65);
        b8 = new BotonsEstat(p5,alert, marginH+25,2*marginV+550, 65,65);

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
        p5.fill(0);
        p5.textFont(pFont1);
        p5.text("USERNAME", p5.width/2-200, p5.height/2+180);
        p5.popMatrix();

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
    }

    public void dibujoPantallaCalendar(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        c.display(p5);
        p5.text(dataCalendari, 190, 45);
    }

    public void dibujoPantallaJugadas(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        footballField(p5, footballfield);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
    }

    public void dibujoPantallEntrenamientos(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        t.display(p5, p5.width/2-550, p5.height/2-275, tableW, tableH);
        btable1.display(p5);
        btable2.display(p5);
        p5.textFont(pFont1);
        p5.pushMatrix();
        p5.fill(0);
        p5.textSize(75);
        p5.text("ENTRENAMIENTOS DEL MES", p5.width/2-300, p5.height/2-365);
        p5.popMatrix();
    }

    //Zones de la GUI

    public void logoLogIn(PApplet p5, PImage logo) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2 - 265, logoWidth/2+450, logoHeight/2+450);
    }

    public void logoPantallas(PApplet p5, PImage logoPantalles) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logoPantalles, p5.width/2-900, p5.height/2-465, logoPantallesWidth/2+50, logoPantallesHeight/2+50);
    }

    public void footballField(PApplet p5, PImage footballfield){
        p5.imageMode(PConstants.CENTER);
        p5.pushMatrix();
        //p5.rotate(HALF_PI);
        p5.image(footballfield, p5.width/2, p5.height/2, footballFieldWidth, footballFielsHeight+300);
        p5.popMatrix();
    }


    public static void sideBar(PApplet p5){
        p5.noStroke();
        p5.fill (paleta.getColorAt(4));
        p5.rect(marginH, 2*marginV + 100, sidebarWidth, sidebarHeight, 20);
    }

   }
