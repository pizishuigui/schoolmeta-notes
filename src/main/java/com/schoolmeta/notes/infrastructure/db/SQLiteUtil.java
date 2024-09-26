package com.schoolmeta.notes.infrastructure.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLiteUtil {  
    private static final String DB_URL = "jdbc:sqlite:notes.db";
  
    // 私有构造函数，防止实例化  
    private SQLiteUtil() {}  
  
    // 获取数据库连接  
    public static Connection getConnection() throws SQLException {  
        return DriverManager.getConnection(DB_URL);  
    }  
  
    // 关闭连接  
    public static void closeConnection(Connection conn) {  
        if (conn != null) {  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 查询数据  
    public static List<String[]> query(String sql, Object... params) {  
        List<String[]> results = new ArrayList<>();  
        try (Connection conn = getConnection();  
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    pstmt.setObject(i + 1, params[i]);  
                }  
            }  
            try (ResultSet rs = pstmt.executeQuery()) {  
                while (rs.next()) {  
                    int columnCount = rs.getMetaData().getColumnCount();  
                    String[] row = new String[columnCount];  
                    for (int i = 0; i < columnCount; i++) {  
                        row[i] = rs.getString(i + 1);  
                    }  
                    results.add(row);  
                }  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return results;  
    }  
  
    // 更新、插入、删除数据  
    public static int update(String sql, Object... params) {  
        int affectedRows = 0;  
        try (Connection conn = getConnection();  
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    pstmt.setObject(i + 1, params[i]);  
                }  
            }  
            affectedRows = pstmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return affectedRows;  
    }

    // 插入数据
    public static void insert(String tableName, String sqlInsert) {
        executeSQL(sqlInsert);
    }

    // 创建表
    public static void createTable(String tableName, String sqlCreateTable) {
        executeSQL("CREATE TABLE IF NOT EXISTS " + tableName + " " + sqlCreateTable);
    }

    // 删除表
    public static void dropTable(String tableName) {
        executeSQL("DROP TABLE IF EXISTS " + tableName);
    }

    // 执行SQL语句（用于创建表、删除表等）
    public static void executeSQL(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 示例用法  
    public static void main(String[] args) {
        // 创建表
        String tableName = "plan";
        String sqlCreateTable = "(plan_id TEXT PRIMARY KEY, dateStr TEXT, progress REAL, labels TEXT)";
        createTable(tableName, sqlCreateTable);

        // 插入数据
        String sqlInsert1 = "INSERT INTO " + tableName + " (plan_id, dateStr, progress, labels) VALUES ('"+ UUID.randomUUID()+"', '2023-04-01', 0.25, 'Started')";
        String sqlInsert2 = "INSERT INTO " + tableName + " (plan_id, dateStr, progress, labels) VALUES ('"+ UUID.randomUUID()+"', '2023-04-02', 0.5, 'In Progress')";
        SQLiteUtil.insert(tableName, sqlInsert1);
        SQLiteUtil.insert(tableName, sqlInsert2);

        // 查询示例  
        List<String[]> results = query("SELECT * FROM plan");
        for (String[] row : results) {  
            for (String col : row) {  
                System.out.print(col + " ");}
            System.out.println();  
        }
    }  
}