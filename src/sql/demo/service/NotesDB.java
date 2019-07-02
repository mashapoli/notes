package sql.demo.service;

import sql.demo.table.*;
import java.sql.*;

public class NotesDB {
    public static final String DB_URL = "jdbc:h2:/D:/JavaPrj/SQLDemo/db/notesDB";
    public static final String DB_Driver = "org.h2.Driver";

    NotesTable notes;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public NotesDB() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        notes = new NotesTable();
    }

    public void createTables() throws SQLException {
        notes.createTable();
    }

    public static void main(String[] args) {
        try{
            NotesDB stockExchangeDB = new NotesDB();
            stockExchangeDB.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
