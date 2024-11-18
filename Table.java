import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
  private Map<String, List<Object>> columns;
  private List<ColumnDefinition> columnDefinitions;
  private int rowCount;

  public Table(List<ColumnDefinition> columnDefinitions) {
    this.columns = new HashMap<>();
    this.columnDefinitions = columnDefinitions;
    this.rowCount = 0;

    // Initialize columns
    for (ColumnDefinition col : columnDefinitions) {
      columns.put(col.getName(), new ArrayList<>());
    }
  }
  
  public void insert(Map<String, Object> data) {
    for (ColumnDefinition colDef : columnDefinitions) {
      String colName = colDef.getName();
      Object value = data.getOrDefault(colName, null);
      columns.get(colName).add(value);
    }
    rowCount++;
  }

  public List<Map<String, Object>> select(List<String> columnNames) {
    List<Map<String, Object>> result = new ArrayList<>();
    List<String> cols = columnNames.isEmpty() ? columns.keySet().stream().toList() : columnNames;

    for (int i = 0; i < rowCount; i++) {
      Map<String, Object> row = new HashMap<>();
      for (String colName : cols) {
        row.put(colName, columns.get(colName).get(i));
      }
      result.add(row);
    }
    return result;
  }
}
