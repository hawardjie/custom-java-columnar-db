import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ColumnStoreController {

    private final ColumnStore columnStore = new ColumnStore();

    @PostMapping("/databases")
    public void createDatabase(@RequestParam String dbName) {
        columnStore.createDatabase(dbName);
    }

    @PostMapping("/databases/{dbName}/tables")
    public void createTable(
            @PathVariable String dbName,
            @RequestParam String tableName,
            @RequestBody List<ColumnDefinition> columns) {
        columnStore.createTable(dbName, tableName, columns);
    }

    @PostMapping("/databases/{dbName}/tables/{tableName}/records")
    public void insertRecord(
            @PathVariable String dbName,
            @PathVariable String tableName,
            @RequestBody Map<String, Object> data) {
        columnStore.insert(dbName, tableName, data);
    }

    @GetMapping("/databases/{dbName}/tables/{tableName}/records")
    public List<Map<String, Object>> selectRecords(
            @PathVariable String dbName,
            @PathVariable String tableName,
            @RequestParam List<String> columns) {
        return columnStore.select(dbName, tableName, columns);
    }
}
