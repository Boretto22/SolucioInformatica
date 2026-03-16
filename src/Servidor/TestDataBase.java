package Servidor;

import java.util.Scanner;

public class TestDataBase {

    public static DataBase db;

    public static void main(String[] args) {
        db = new DataBase("admin", "12345", "playcoach");
        db.connect();

        String s = db.getInfo("usuario", "ID", "Contraseña", "54321");
        System.out.println(s);

        int n = db.getNumFilesTaula("usuario");
        System.out.println(n);

        String[] noms = db.getInfoArray("usuario", "ID");
        for (int i=0; i< noms.length; i++){
            System.out.println(noms[i]);
        }

        int m = db.getNumFilesTaula("usuario");
        System.out.printf("Hay %d usuario.\n", m);

        //Nom del client amb id
        String usuarioCliente = db.getUsuarioClienteConId("54321");
        System.out.println(usuarioCliente);

        String[] nombres = db.getUsuarioTodosClientes();
        db.printArray1D(nombres);

        String[][] infoClientes = db.getInfoTodosClientes();
        db.printArray2D(infoClientes);




    }
}
