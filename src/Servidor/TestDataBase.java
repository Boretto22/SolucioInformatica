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

        int m = db.getNumFilesTaula("usuari");
        System.out.printf("Hay %d usuari.\n", m);

        //Nom del client amb id
        String usuarioCliente = db.getUsuarioClienteConId("54987354T");
        System.out.println(usuarioCliente);

        String[] nombres = db.getUsuarioTodosClientes();
        db.printArray1D(nombres);

        String[][] infoClientes = db.getInfoTodosClientes();
        db.printArray2D(infoClientes);




    }
}
