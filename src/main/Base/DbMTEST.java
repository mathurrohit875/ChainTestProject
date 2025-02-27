package main.Base;

import java.sql.*;

public class DbMTEST {

  private static final String DB_URL = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
  private static final String USER = "sa";
  private static final String PASS = "UAT_R0INET_SQL";
  private Connection connection;

  public DbMTEST() {
    try {
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet executeQuery(String query) {
    ResultSet resultSet = null;
    try {
      Statement stmt = connection.createStatement();
      resultSet = stmt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultSet;
  }

  public int executeUpdate(String query, Object... params) {
    int result = 0;
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      for (int i = 0; i < params.length; i++) {
        pstmt.setObject(i + 1, params[i]);
      }
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public ResultSet executePreparedQuery(String query, Object... params) {
    ResultSet resultSet = null;
    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      for (int i = 0; i < params.length; i++) {
        pstmt.setObject(i + 1, params[i]);
      }
      resultSet = pstmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultSet;
  }

}
