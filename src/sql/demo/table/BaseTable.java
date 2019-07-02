package sql.demo.table;

import sql.demo.service.NotesDB;

import java.io.Closeable;
import java.sql.*;

public class BaseTable implements Closeable {
    Connection connection;
    String tableName;

    BaseTable(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = NotesDB.getConnection();
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    // Выполнить SQL команду без параметров в СУБД, по завершению выдать сообщение в консоль
    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
        if (description != null)
            System.out.println(description);
    };

    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    };

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = NotesDB.getConnection();
        }
    }
}
