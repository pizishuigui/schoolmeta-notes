package com.schoolmeta.notes.infrastructure.db;

public class DBInitialize {
    public static void initDB(){
        // 创建plan表
        createPlanTable();
        // 创建plan_item表
        createPlanItemTable();
        // 创建item_detail表
        createItemDetailTable();
    }

    private static void createItemDetailTable() {
        String tableName = "t_item_detail";
        String sqlCreateTable = "(item_detail_id TEXT PRIMARY KEY, plan_item_id TEXT, value TEXT, status TEXT, is_pin INTEGER)";
        SQLiteUtil.createTable(tableName, sqlCreateTable);
    }

    private static void createPlanItemTable() {
        String tableName = "t_plan_item";
        String sqlCreateTable = "(plan_item_id TEXT PRIMARY KEY, name TEXT, plan_id TEXT)";
        SQLiteUtil.createTable(tableName, sqlCreateTable);
    }
    private static void createPlanTable() {
        String tableName = "t_plan";
        String sqlCreateTable = "(plan_id TEXT PRIMARY KEY, dateStr TEXT, progress REAL, labels TEXT)";
        SQLiteUtil.createTable(tableName, sqlCreateTable);
    }
}
