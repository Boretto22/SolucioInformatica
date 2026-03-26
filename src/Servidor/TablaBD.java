package Servidor;

import processing.core.PApplet;
import Estetica.Table;

public class TablaBD extends PApplet {

    DataBase db;
    Table t;

    float tableW = 800, tableH = 300;
    String[] headers = {"ID", "Contraseña", "Foto", "Tipus"};
    float[] colWidths = {20, 80};
    String[][] info;

    public static void main(String[] args) {
        PApplet.main("Servidor.TableDB", args);
    }

    public void setup(){
        // Configura els paràmetres de connexió a la BBDD
        db = new DataBase("admin", "12345", "playcoach");
        // Connecta amb la BBDD
        db.connect();

        // Número de files d'una taula
        int files = db.getNumRowsTaula("unitat");
        int columnes = 4;

        // Dades d'una taula (unitat)
        info = db.getInfoTaulaUnitat();

        // Creació de la taula
        t = new Table(files, columnes);
        t.setHeaders(headers);
        t.setData(info);
        t.setColumnWidths(colWidths);
    }

        public void draw(PApplet p5){
            background(255);
            fill(0); textSize(28);

            // Dibuixa la Table
            t.display(this, 50, 50, tableW, tableH);
        }

}
