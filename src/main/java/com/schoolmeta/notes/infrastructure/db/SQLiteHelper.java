package com.schoolmeta.notes.infrastructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteHelper {

    private static final String DB_URL = "jdbc:sqlite:notes.db"; // 替换为你的数据库路径

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // 创建表
    public static void createTable(String tableName, List<String> columns) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) sql.append(", ");
            sql.append(columns.get(i));
        }
        sql.append(");");

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加表字段
    public static void addColumn(String tableName, String column) {
        String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + column + ";";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 变更表字段属性
    public static void alterColumn(String tableName, String columnName, String newDefinition) {
        String sql = "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newDefinition + ";";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 插入数据
    public static void insertData(String tableName, List<String> columns, List<Object> values) {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        for (String column : columns) {
            sql.append(column).append(", ");
        }
        sql.delete(sql.length() - 2, sql.length()); // 删除最后多余的逗号和空格
        sql.append(") VALUES (");
        for (int i = 0; i < values.size(); i++) {
            sql.append("?, ");
        }
        sql.delete(sql.length() - 2, sql.length()).append(");");

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < values.size(); i++) {
                pstmt.setObject(i + 1, values.get(i));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新数据
    public static void updateData(String tableName, Map<String, Object> setValues, String whereClause) {
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        for (Map.Entry<String, Object> entry : setValues.entrySet()) {
            sql.append(entry.getKey()).append(" = ?, ");
        }
        sql.delete(sql.length() - 2, sql.length());
        if (whereClause != null && !whereClause.isEmpty()) {
            sql.append(" WHERE ").append(whereClause);
        }

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object value : setValues.values()) {
                pstmt.setObject(index++, value);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除数据
    public static void deleteData(String tableName, String whereClause) {
        String sql = "DELETE FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            sql += " WHERE " + whereClause;
        }
        sql += ";";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 条件查询
    public static List<Map<String, Object>> queryData(String tableName, String selectClause, String whereClause) {
        String sql = "SELECT " + (selectClause == null ? "*" : selectClause) + " FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            sql += " WHERE " + whereClause;
        }
        sql += ";";

        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // 分页查询
    public static List<Map<String, Object>> pageQuery(String tableName, String selectClause, int offset, int limit, String whereClause, String orderBy) {
        String sql = "SELECT " + (selectClause == null ? "*" : selectClause) + " FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            sql += " WHERE " + whereClause;
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " ORDER BY " + orderBy;
        }
        sql += " LIMIT ? OFFSET ?;";

        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // 批量查询
    public static List<List<Map<String, Object>>> batchQuery(List<String> tableNames, String selectClause, String whereClause) {
        List<List<Map<String, Object>>> resultSets = new ArrayList<>();
        for (String tableName : tableNames) {
            resultSets.add(queryData(tableName, selectClause, whereClause));
        }
        return resultSets;
    }

    public static void initCreateTable(String[] args) {
        // 创建 t_day_plan 表
        List<String> tDayPlanColumns = List.of(
                "plan_id TEXT PRIMARY KEY",
                "date_str TEXT",
                "progress REAL",
                "labels TEXT",
                "create_time TEXT",
                "update_time TEXT"
                );
        createTable("t_day_plan", tDayPlanColumns);

        // 创建 t_plan_item 表
        List<String> tPlanItemColumns = List.of(
                "plan_item_id TEXT PRIMARY KEY",
                "plan_id TEXT REFERENCES t_day_plan(plan_id)",
                "name TEXT",
                "status INTEGER",
                "is_pin INTEGER",
                "priority_order INTEGER",
                "create_time TEXT",
                "update_time TEXT"
        );
        createTable("t_plan_item", tPlanItemColumns);

        // 创建 t_item_detail 表
        List<String> tItemDetailColumns = List.of(
                "item_detail_id TEXT PRIMARY KEY",
                "plan_item_id TEXT REFERENCES t_plan_item(plan_item_id)",
                "value TEXT",
                "status INTEGER",
                "priority_order INTEGER",
                "details TEXT",
                "create_time TEXT",
                "update_time TEXT"
        );
        createTable("t_item_detail", tItemDetailColumns);

        // 更多测试...
    }
}