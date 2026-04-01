import Estetica.*;
import Servidor.DataBase;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class    PlayCoach extends PApplet {


    public static DataBase db;
    Tipografia appTipografia;
    GUI appGUI;
    //PagedTable appPagedTable;
    //CalendariPlus appCalendariPlus;
    //Calendari appCalendari;
    Counter appCounter;
    PImage iconaMes, iconaMenys;

    boolean loginOK = true;


    public static void main(String[] args) {
        PApplet.main("PlayCoach");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {

        db = new DataBase("admin", "12345", "playcoach");
        db.connect();

        iconaMes = loadImage("iconaMes.png");
        iconaMenys = loadImage("iconaMenys.png");
        appTipografia = new Tipografia(this);
        appGUI = new GUI(this);
        //appPagedTable = new PagedTable(this,7,5);
        //appCalendariPlus = new CalendariPlus(this,50,200,700,550);
        appCounter = new Counter(this, iconaMes, iconaMenys, 400, 400, 100, 50);
    }

    public void draw() {
        // Dibuixa el fons (blanc)
        updateHandCursor();
        background(255);

        textFont(appTipografia.getFirstTipografia());
        text("Titulo de la App", 50, 200);

        fill(50);
        textFont(appTipografia.getSecondTipografia());
        text("Subtitulo de la App", 50, 250);

        fill(55, 0, 0);
        textFont(appTipografia.getThirdTipografia());
        text("Paragrafo de la App", 50, 300);

        // Dibuixa la pantalla corresponent
        switch (appGUI.pantallaActual) {
            case LOGIN:
                appGUI.dibujoPantallaLogIn(this);
                break;

            case INICIAL:
                appGUI.dibujoPantallaInicial(this);
                break;

            case CALENDAR:
                appGUI.dibujoPantallaCalendar(this);
                break;

            case JUGADAS:
                appGUI.dibujoPantallaJugadas(this);
                break;

            case ENTRENAMIENTO:
                appGUI.dibujoPantallEntrenamientos(this);
                break;

            case ALERTAS:
                appGUI.dibujoPantallaAlerta(this);
                break;
        }

        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appTipografia.displayTipografia(this, 100, 400, 500);

    }

    //KEYBOARD interaction

    public void keyTyped(){
        appGUI.text1.keyTyped(key);
        appGUI.text2.keyTyped(key);
        appGUI.textAlert.keyTyped(key);
    }


    public void keyPressed() {
       // appGUI.text1.keyPressed(key, keyCode);
       // appGUI.text2.keyPressed(key,keyCode);
       // appGUI.textAlert.keyPressed(key, keyCode);
        appGUI.text1.keyPressed(keyCode);
        appGUI.text2.keyPressed(keyCode);
        appGUI.textAlert.keyPressed(keyCode);
        if (appGUI.nombreEquipoTextField != null) appGUI.nombreEquipoTextField.keyPressed(key, keyCode);
        if (keyCode == LEFT) {
            //appPagedTable.prevPage();
            appGUI.prevPage();
        } else if (keyCode == RIGHT) {
            //appPagedTable.nextPage();
            appGUI.nextPage();
        }

    }

    public void updateHandCursor() {
        if (appGUI.b1.updateHandCursor(this) || appGUI.b2.updateHandCursor(this) || appGUI.blogin.updateHandCursor(this) ||
                appGUI.b4.updateHandCursor(this) || appGUI.b5.updateHandCursor(this) || appGUI.b6.updateHandCursor(this) || appGUI.b7.updateHandCursor(this)) {
            cursor(HAND);
        } else {
            cursor(ARROW);
        }
    }

    public void mousePressed() {
        if (appGUI.b1.mouseOverButton(this)) {
            println("B1 has been pressed!!");
            appGUI.b1.setSelected(true);
            appGUI.b2.setSelected(false);
        }
        if (appGUI.b2.mouseOverButton(this)) {
            println("B2 has been pressed!!");
            appGUI.b2.setSelected(true);
            appGUI.b1.setSelected(false);
        }

        if (appGUI.b4.mouseOverButton(this)) {
            println("B4 has been pressed!!");
            appGUI.b4.setSelected(true);
            appGUI.b5.setSelected(false);
            appGUI.b6.setSelected(false);
            appGUI.b7.setSelected(false);
            appGUI.b8.setSelected(false);
            appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        if (appGUI.b5.mouseOverButton(this)) {
            println("B5 has been pressed!!");
            appGUI.b5.setSelected(true);
            appGUI.b4.setSelected(false);
            appGUI.b6.setSelected(false);
            appGUI.b7.setSelected(false);
            appGUI.b8.setSelected(false);
            appGUI.pantallaActual = GUI.PANTALLA.JUGADAS;

        }
        if (appGUI.b6.mouseOverButton(this)) {
            println("B6 has been pressed!!");
            appGUI.b6.setSelected(true);
            appGUI.b4.setSelected(false);
            appGUI.b5.setSelected(false);
            appGUI.b7.setSelected(false);
            appGUI.b8.setSelected(false);
            appGUI.pantallaActual = GUI.PANTALLA.ENTRENAMIENTO;

        }

        if (appGUI.b7.mouseOverButton(this)) {
            println("B7 has been pressed!!");
            appGUI.b7.setSelected(true);
            appGUI.b4.setSelected(false);
            appGUI.b5.setSelected(false);
            appGUI.b6.setSelected(false);
            appGUI.b8.setSelected(false);
            appGUI.pantallaActual = GUI.PANTALLA.CALENDAR;
        }
        if (appGUI.b8.mouseOverButton(this)) {
            println("B8 has been pressed!!");
            appGUI.b8.setSelected(true);
            appGUI.b4.setSelected(false);
            appGUI.b5.setSelected(false);
            appGUI.b6.setSelected(false);
            appGUI.b7.setSelected(false);
            appGUI.pantallaActual = GUI.PANTALLA.ALERTAS;

        }
        if (appGUI.btable1.mouseOverButton(this) && appGUI.btable1.isEnabled()) {
            //appPagedTable.nextPage();
            appGUI.nextPage();
        } else if (appGUI.btable2.mouseOverButton(this) && appGUI.btable2.isEnabled()) {
            //appPagedTable.prevPage();
            appGUI.prevPage();
        }

        appGUI.text1.isPressed(this);
        appGUI.text2.isPressed(this);
        appGUI.textAlert.isPressed(this);

        // Comprovar si clicam sobre botons del Calendari
        //appCalendariPlus.checkButtons(this);
        appGUI.c.checkButtons(this);

        // Si pitjam el botó de Next, canviarà al seguent mes
        //if(appCalendariPlus.bNext.mouseOverButton(this)){
        //appCalendariPlus.nextMonth();
        if (appGUI.c.bNext.mouseOverButton(this)) {
            appGUI.c.nextMonth();
        }
        // Si pitjam el botó de Prev, canviarà al mes anterior
        //if(appCalendariPlus.bPrev.mouseOverButton(this)){
        //appCalendariPlus.prevMonth();
        if (appGUI.c.bPrev.mouseOverButton(this)) {
            appGUI.c.prevMonth();
        }

        if (appGUI.c.isDateSelected()) {
            appGUI.dataCalendari = appGUI.c.getSelectedDate();
        }

        if (appGUI.bLoadImg.mouseOverButton(this)) {
            // Obrim el dialeg
            selectInput("Selecciona una imatge ...", "fileSelected");
        }

        if (appGUI.pantallaActual == GUI.PANTALLA.INICIAL) {
            appGUI.checkMousePantallaInicial(this);
        }

        if (appGUI.pantallaActual == GUI.PANTALLA.JUGADAS) {
            appGUI.checkMousePressedJugadas(this);
        }

        if (appGUI.blogin.mouseOverButton(this)){
            String id= appGUI.text1.getText();
            String contraseña = appGUI.text2.getText();
            if (db.loginCorrecte(id, contraseña)){
                loginOK = true;
                if (appGUI.blogin.mouseOverButton(this)) {
                    println("BLogIn has been pressed!!");
                    appGUI.currentUserNombre = appGUI.text1.getText();
                    appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
                }
                println("LOGIN OK");
            } else {
                println("LOGIN WRONG");
                loginOK = false;
            }
        }

        else if (appGUI.bsignup.mouseOverButton(this)) {

            try {
                String nombre = appGUI.text1.getText();
                String contraseña = appGUI.text2.getText();

                String tipus = "";
                if (appGUI.b1.isSelected()) tipus = "Entrenador";
                else if (appGUI.b2.isSelected()) tipus = "Jugador";

                int id = (int)(Math.random() * Integer.MAX_VALUE);

                String sql = "INSERT INTO usuario (ID, nombre, Contraseña, Tipus) VALUES (?, ?, ?, ?)";

                java.sql.PreparedStatement ps = db.getConnection().prepareStatement(sql);

                ps.setInt(1, id);
                ps.setString(2, nombre);
                ps.setString(3, contraseña);
                ps.setString(4, tipus);

                ps.executeUpdate();

                println(nombre);

                println("USUARI GUARDAT A MYSQL - ID: ");

            } catch (Exception e) {
                e.printStackTrace();
                println("ERROR EN EL SIGNUP");
            }

            if (appGUI.bsignup.mouseOverButton(this)) {
                println("BLogIn has been pressed!!");
                appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
        }


    }




    public void mouseDragged() {
        if (appGUI.pantallaActual == GUI.PANTALLA.JUGADAS) {
            appGUI.checkMouseDraggedJugadas(this);
        }
    }

    public void mouseReleased() {
        if (appGUI.pantallaActual == GUI.PANTALLA.JUGADAS) {
            appGUI.checkMouseReleasedJugadas(this);
        }
    }

    // Carrega Imatge
    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            appGUI.loadImage = loadImage(rutaImatge);  // Actualitzam imatge
            appGUI.titol = selection.getName();  // Actualitzam títol
        }
    }
}
