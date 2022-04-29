package com.example.ToDo;
import com.example.ToDo.models.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBController {

    String connectionUrl;
    String username;
    String passwort;
    
    public DBController(){
        // ACHTUNG! Hier den Port entsprechend XAMPP austauschen, z.B. jdbc:mysql://localhost:3306/javadb
        // javadb ist der Name der Datenbank, kann auch bei euch anders sein!
        setConnectionUrl("jdbc:mysql://localhost:3306/javadb");
        setPasswort("root");
        setUsername("root");
    }
    

    // Holt alle ToDos aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Todo> getAllToDos(){

        // Lokale Todos-Arraylist erstellen
        ArrayList<Todo> todos = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllToDos = "SELECT * FROM `todos` JOIN person ON person.id=todos.id_person";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllToDos); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String person = rs.getString("name");
                int personId = (int) rs.getLong("id_person");
                String description = rs.getString("description");
                todos.add(new Todo(id, description, person, personId));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todos;
    }

    // Füge neue Todo hinzu
    public void addNewToDo(int person, String description) {
        try{
            String sqlSelectAllPersons = "INSERT INTO todos(id_person,description) VALUES('"+person+"','"+description+"');";
            Connection con = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = con.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche eine Todo
    public void delToDo(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM todos WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifische Todo
    public Todo getToDo(int id){
        Todo todo = null;
        try{
            String sqlSelectToDo = "SELECT * FROM `todos` JOIN person ON person.id=todos.id_person WHERE todos.id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectToDo); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int todoId = (int) rs.getLong("id");
                int personId = (int) rs.getLong("id_person");
                String person = rs.getString("name");
                String description = rs.getString("description");
                todo = new Todo(todoId, description, person, personId);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todo;
    }

    // Hole spezifische Todo und aktualisiere diese ab
    public Todo updateToDo(int id, String description, int personId){
        Todo todo = null;
        try{
            String sqlSelectAllPersons = "UPDATE todos SET id_person='"+personId+"', description='"+description+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todo;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getUsername() {
        return username;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    // Holt alle ToDos aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Person> getAllPersonen(){

        // Lokale Todos-Arraylist erstellen
        ArrayList<Person> personen = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllToDos = "SELECT * FROM person";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllToDos); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String name = rs.getString("name");
                personen.add(new Person(name, id));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personen;
    }

    
}
