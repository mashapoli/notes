package sql.demo;
import java.sql.*;
import java.util.*;


public class Main {
    public static List<Map<String, Object>> executeQuery(Connection con, String query)
            throws SQLException {
        query = "SUP_ID, TITLE, CONTENT, CREATED_ON, UPDATE_ON ";
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData  md = rs.getMetaData();
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

