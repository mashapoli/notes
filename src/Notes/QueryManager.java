package Notes;

import java.sql.*;

import java.util.*;

public class QueryManager {

    private Connection connection;

    public QueryManager(Connection connection) {
        this.connection = connection;
    }

    public void execute(String query) {
        try (Statement stmt = connection.createStatement()){
            stmt.execute(query);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int colums = md.getColumnCount();
            while (rs.next()){
                Map<String, Object> row = new HashMap<String, Object>(colums);
                for(int i = 1; i<= colums; i++){
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                mapList.add(row);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return mapList;
    }
}
