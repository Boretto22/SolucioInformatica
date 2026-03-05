package Servidor;

import java.util.Scanner;

public class TestDataBase {

    public static DataBase db;

    public static void main(String[] args) {
        db = new DataBase("admin", "12345", "playcoach");
        db.connect();

        String s = db.getInfo("usuari", "nom", "dni", "54987354T");
        System.out.println(s);

        int n = db.getNumFilesTaula("usuari");
        System.out.println(n);

        String[] noms = db.getInfoArray("usuari", "nom");
        for (int i=0; i< noms.length; i++){
            System.out.println(noms[i]);
        }
    }
}
