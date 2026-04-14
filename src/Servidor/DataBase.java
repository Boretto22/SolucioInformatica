package Servidor;

import jdk.jshell.spi.ExecutionControlProvider;
import java.sql.*;


public class DataBase {

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection(){
        return c;
    }


    // Retorna la informació d'una casella

    public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador){
        try{
            String q =  "SELECT " + nomColumna +
                    " FROM " + nomTaula +
                    " WHERE "+ nomClau  + " = '" + identificador + "' ";
            System.out.println(q);
            ResultSet rs= query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "";
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna totes les caselles d'una columna

    public String[] getInfoArray(String nomTaula, String nomColumna){
        int n = getNumFilesTaula(nomTaula);
        String[] info = new String[n];
        String q = "SELECT "+ nomColumna +
                " FROM " + nomTaula +
                " ORDER BY " + nomColumna + " ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString(nomColumna);
                f++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }

    // Retorna totes les caselles (files i columnes) d'una taula

    public String[][] getInfoArray2DUnitat(){
        int nf = getNumFilesTaula("unitat");
        String[][] info = new String[nf][3]; // posar num dades que necesiti
        String q = "SELECT numero, nom, curs FROM unitat ORDER BY numero ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f][0] = String.valueOf( rs.getInt("numero"));
                info[f][1] = rs.getString("nom");
                info[f][2] = String.valueOf( rs.getInt("curs"));
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return info;
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }


    // Retorna true si el nom d'usuari i password estan a la taula (usuario)
    public boolean isUserOk(String nom, String password){
        String q = "SELECT COUNT(*) AS n" +
                " FROM usuario "+
                " WHERE nombre='" + nom + "' AND password='" + password + "' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // Inserta un usuari a la taula usuario amb nombre i password
    // INSERT INTO `usuario` (`nombre`, `password`) VALUES ('bel', 'qwerty');
    public void insertaUsuario(String n, String p){
        String q = "INSERT INTO usuario (nombre, password) " +
                "VALUES ('"+n+"', '"+p+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    // Esborra un usuari de la taula usuario
    // DELETE FROM `usuario` WHERE `usuario`.`nombre` = \'pep\'"
    public void deleteUsuario(String nom){
        String q = "DELETE FROM usuario WHERE nombre ='" + nom + "'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // UPDATE `usuario` SET `nombre` = 'paquito', `password` = 'abcdefghi' WHERE `usuario`.`nombre` = 'paco';
    // Modifica les dades d'un usuari
    public void updateUsuario(String nomActual, String nouNom, String nouPassword){
        String q = "UPDATE usuario SET " +
                " nombre = '"+nouNom+"' , "+
                " password = '"+nouPassword+ "' " +
                " WHERE nombre='"+nomActual+"'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Cercador de Preguntes
    // SELECT * FROM pregunta WHERE enunciat LIKE '%Quin%'
    public String[][] preguntesCercador(String clauCerca){

        String qNF = "SELECT COUNT(*) AS num FROM pregunta WHERE enunciat LIKE '%"+ clauCerca+"%'";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = "SELECT numero, enunciat FROM pregunta WHERE enunciat LIKE '%"+ clauCerca+"%'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n=0;
            while(rs.next()){
                info[n][0] = String.valueOf(rs.getInt("numero"));
                info[n][1] = rs.getString("enunciat");
                n++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }





    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    // Retorna totes les dades d'una taula en concret
    public String[][] getInfoTaulaUnitat(){
        int numFiles = getNumRowsTaula("usuario");
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM usuario");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("ID"));
                info[nr][1] = rs.getString("Contraseña");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void printArray1D(String[] info){
        System.out.println();
        for(int i=0; i< info.length; i++){
            System.out.printf("%d:",i);
            System.out.printf("%s.\t", info[i]);
            System.out.println();
        }
    }

    public void printArray2D(String[][] info){
        System.out.println();
        for(int i=0; i<info.length; i++){
            for (int j=0; j<info.length; j++) {
                System.out.printf("%d:", i);
                System.out.printf("%s.\t", info[i][j]);
                System.out.println();
            }
        }
    }

    //Funcion que devuelve el nombre de un cliente con un cierto id
    public String getUsuarioClienteConId(String id){
        String q = "SELECT ID FROM usuario WHERE Contraseña='"+id+"'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString("ID");
            //String usuari= rs.getString("nom");
            //return usuari;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[] getUsuarioTodosClientes(){
        String q = "SELECT ID FROM usuario ORDERED BY ID ASC";
        System.out.println(q);
        try{
            int numFiles = getNumFilesTaula("usuario");
            String[] info = new String[numFiles];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()){
                info[f] = rs.getString("ID");
                f++;
            }
            return info;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTodosClientes() {
        String q = "SELECT ID, Contraseña FROM usuario ORDERED BY ID ASC";
        System.out.println(q);
        try {
            int numFiles = getNumFilesTaula("usuario");
            String[][] info = new String[numFiles][2];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f][0] = rs.getString("ID");
                info[f][1] = rs.getString("Contraseña");
                f++;
            }
            return info;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public int getNumFilesQuery(String q){

        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt('q');
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public boolean loginCorrecte(String nombre, String contraseña){
        String q = "SELECT COUNT(*) AS N "+
                "FROM usuario "+
                "WHERE nombre = '" + nombre + "'AND Contraseña ='"+ contraseña + "'";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // Guarda la alerta del usuario en la BBDD.
// Si el usuario ya tiene una alerta → actualiza (UPDATE).
// Si no tiene ninguna → la crea (INSERT).
// Así cada usuario tiene siempre como máximo una fila en la tabla alerta.
    public boolean insertarAlerta(String texto, String usuarioID) {

        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("ERROR: el texto de la alerta está vacío.");
            return false;
        }
        if (usuarioID == null || usuarioID.isEmpty()) {
            System.out.println("ERROR: no hay usuario en sesión.");
            return false;
        }

        try {
            // 1) Comprobamos si el usuario ya tiene una alerta
            String sqlCheck = "SELECT ID FROM advertencias WHERE Usuario_ID = ? LIMIT 1";
            java.sql.PreparedStatement psCheck = this.getConnection().prepareStatement(sqlCheck);
            psCheck.setString(1, usuarioID);
            java.sql.ResultSet rs = psCheck.executeQuery();

            String alertaIDExistente = null;
            if (rs.next()) {
                alertaIDExistente = rs.getString("ID");
            }
            rs.close();
            psCheck.close();

            if (alertaIDExistente != null) {
                // 2a) Ya existe → UPDATE (sobrescribimos el texto)
                String sqlUpdate = "UPDATE advertencias SET Texto = ? WHERE ID = ?";
                java.sql.PreparedStatement psUpdate = this.getConnection().prepareStatement(sqlUpdate);
                psUpdate.setString(1, texto);
                psUpdate.setString(2, alertaIDExistente);
                psUpdate.executeUpdate();
                psUpdate.close();

                System.out.println("ALERTA ACTUALIZADA OK - ID: " + alertaIDExistente +
                        " | Usuario: " + usuarioID +
                        " | Texto: " + texto);
            } else {
                // 2b) No existe → INSERT (la creamos por primera vez)
                String alertaID = "A" + System.currentTimeMillis();
                if (alertaID.length() > 15) {
                    alertaID = alertaID.substring(0, 15);
                }

                String sqlInsert = "INSERT INTO advertencias (ID, Texto, Usuario_ID) VALUES (?, ?, ?)";
                java.sql.PreparedStatement psInsert = this.getConnection().prepareStatement(sqlInsert);
                psInsert.setString(1, alertaID);
                psInsert.setString(2, texto);
                psInsert.setString(3, usuarioID);
                psInsert.executeUpdate();
                psInsert.close();

                System.out.println("ALERTA CREADA OK - ID: " + alertaID +
                        " | Usuario: " + usuarioID +
                        " | Texto: " + texto);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR AL GUARDAR LA ALERTA EN MYSQL");
            return false;
        }
    }

    // Devuelve el texto de la última alerta guardada por el usuario.
// Si el usuario no tiene ninguna alerta, devuelve "".
    public String getUltimaAlerta(String usuarioID) {
        System.out.println("DEBUG getUltimaAlerta >>> buscando para usuarioID = [" + usuarioID + "]");
        if (usuarioID == null || usuarioID.isEmpty()) {
            System.out.println("DEBUG >>> usuarioID vacío, devuelvo ''");
            return "";
        }
        try {
            String sql = "SELECT Texto FROM advertencias WHERE Usuario_ID = ? ORDER BY ID DESC LIMIT 1";
            java.sql.PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, usuarioID);

            java.sql.ResultSet rs = ps.executeQuery();
            String texto = "";
            if (rs.next()) {
                texto = rs.getString("Texto");
                System.out.println("DEBUG >>> query encontró: [" + texto + "]");
            } else {System.out.println("DEBUG >>> query NO encontró ninguna fila");}
            rs.close();
            ps.close();
            return texto;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR AL RECUPERAR LA ALERTA DE MYSQL");
            return "";
        }
    }

    // Guarda las estadísticas del usuario. Si ya existen → UPDATE, si no → INSERT.
    public boolean guardarEstadisticas(String usuarioID, String nombreEquipo,
                                       int año, int puntos, int goles, int asistencias,
                                       int pj, int pg, int pe, int pp,
                                       int tRojas, int tAmarillas) {

        if (usuarioID == null || usuarioID.isEmpty()) {
            System.out.println("ERROR: no hay usuario en sesión.");
            return false;
        }

        try {
            // 1) Comprobar si el usuario ya tiene estadísticas
            String sqlCheck = "SELECT ID FROM estadisticas WHERE Usuario_ID = ? LIMIT 1";
            java.sql.PreparedStatement psCheck = this.getConnection().prepareStatement(sqlCheck);
            psCheck.setString(1, usuarioID);
            java.sql.ResultSet rs = psCheck.executeQuery();

            Integer idExistente = null;
            if (rs.next()) {
                idExistente = rs.getInt("ID");
            }
            rs.close();
            psCheck.close();

            if (idExistente != null) {
                // UPDATE
                String sqlUpdate = "UPDATE estadisticas SET NombreEquipo=?, Año=?, Puntos=?, Goles=?, " +
                        "Assistencias=?, PJ=?, PG=?, PE=?, PP=?, T_Rojas=?, T_Amarillas=? " +
                        "WHERE ID=?";
                java.sql.PreparedStatement ps = this.getConnection().prepareStatement(sqlUpdate);
                ps.setString(1, nombreEquipo);
                ps.setInt(2, año);
                ps.setInt(3, puntos);
                ps.setInt(4, goles);
                ps.setInt(5, asistencias);
                ps.setInt(6, pj);
                ps.setInt(7, pg);
                ps.setInt(8, pe);
                ps.setInt(9, pp);
                ps.setInt(10, tRojas);
                ps.setInt(11, tAmarillas);
                ps.setInt(12, idExistente);
                ps.executeUpdate();
                ps.close();
                System.out.println("ESTADÍSTICAS ACTUALIZADAS OK - Usuario: " + usuarioID);
            } else {
                // INSERT
                String sqlInsert = "INSERT INTO estadisticas (NombreEquipo, Año, Puntos, Goles, Assistencias, " +
                        "PJ, PG, PE, PP, T_Rojas, T_Amarillas, Usuario_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                java.sql.PreparedStatement ps = this.getConnection().prepareStatement(sqlInsert);
                ps.setString(1, nombreEquipo);
                ps.setInt(2, año);
                ps.setInt(3, puntos);
                ps.setInt(4, goles);
                ps.setInt(5, asistencias);
                ps.setInt(6, pj);
                ps.setInt(7, pg);
                ps.setInt(8, pe);
                ps.setInt(9, pp);
                ps.setInt(10, tRojas);
                ps.setInt(11, tAmarillas);
                ps.setString(12, usuarioID);
                ps.executeUpdate();
                ps.close();
                System.out.println("ESTADÍSTICAS CREADAS OK - Usuario: " + usuarioID);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR AL GUARDAR ESTADÍSTICAS EN MYSQL");
            return false;
        }
    }


    // Actualiza el campo Foto de la tabla usuario
    public boolean actualizarFotoUsuario(String usuarioID, String nombreFoto) {
        if (usuarioID == null || usuarioID.isEmpty()) return false;
        try {
            String sql = "UPDATE usuario SET Foto = ? WHERE ID = ?";
            java.sql.PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, nombreFoto);
            ps.setString(2, usuarioID);
            ps.executeUpdate();
            ps.close();
            System.out.println("FOTO ACTUALIZADA OK - Usuario: " + usuarioID + " | Foto: " + nombreFoto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Devuelve el nombre de la foto del usuario, o "" si no tiene.
    public String getFotoUsuario(String usuarioID) {
        if (usuarioID == null || usuarioID.isEmpty()) return "";
        try {
            String sql = "SELECT Foto FROM usuario WHERE ID = ? LIMIT 1";
            java.sql.PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, usuarioID);
            java.sql.ResultSet rs = ps.executeQuery();
            String foto = "";
            if (rs.next()) {
                foto = rs.getString("Foto");
                if (foto == null) foto = "";
            }
            rs.close();
            ps.close();
            return foto;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
