package sql.demo.table;

import java.sql.SQLException;

public class NotesTable extends BaseTable implements TableOperations {
    public NotesTable() throws SQLException {
        super("notes");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS notes(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255)," +
                "content VARCHAR(255)," +
                "created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
                "update_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)", "Создана таблица " + tableName);
    }
}