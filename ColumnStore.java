import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ColumnStore {
  private Map<String, Map<String, Table>> databases;

  public ColumnStore() {
    this.databases = new HashMap<>();
  }

  public void createDatabase(String dbName) {
    databases.putIfAbsent(dbName, new HashMap<>());
  }

  public void createTable(String dbName, String tableName, List<ColumnDefinition> columns) {
    Map<String, Table> db = databases.get(dbName);
    if (db == null) {
      throw new IllegalArgumentException("Database " + dbName + " not found");
    }
    db.put(tableName, new Table(columns));
  }

  public void insert(String dbName, String tableName, Map<String, Object> data) {
    Map<String, Table> db = databases.get(dbName);
    if (db == null) {
      throw new IllegalArgumentException("Database " + dbName + " not found");
    }

    Table table = db.get(tableName);
    if (table == null) {
      throw new IllegalArgumentException("Table " + tableName + " not found");
    }
    table.insert(data);
  }

  public List<Map<String, Object>> select(String dbName, String tableName, List<String> columns) {
    Map<String, Table> db = databases.get(dbName);
        if (db == null) {
      throw new IllegalArgumentException("Database " + dbName + " not found");
    }

    Table table = db.get(tableName);
    if (table == null) {
      throw new IllegalArgumentException("Table " + tableName + " not found");
    }
    return table.select(columns);
  }
}
