package sql.demo;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.sql.*;
import java.util.*;


public class Main {
    public static List<Map<String, Object>> executeQuery(Connection con, String query)
            throws SQLException {
        Statement stmt = null;
        query = "SUP_ID, TITLE, CONTENT, CREATED_ON, UPDATE_ON ";
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try {
            stmt = con.createStatement();
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
            while (rs.next()) {
                int supplierID = rs.getInt("SUP_ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Timestamp created_on = rs.getTimestamp("CREATED_ON");
                Timestamp update_on = rs.getTimestamp("UPDATE_ON");
                System.out.println(supplierID + "\t" + title +
                        "\t" + content + "\t" + created_on +
                        "\t" + update_on);
            }
        } catch (SQLException e ) {
            e.printStackTrace(); //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return mapList;
    }
}

