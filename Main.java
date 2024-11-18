import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
  public static void main(String[] args) {
    ColumnStore db = new ColumnStore();
    String dbName = "investmentDB";
    String investorsTableName = "investors";
    String stocksTableName = "stocks";
    String transactionsTableName = "transactions";
    String portfoliosTableName = "portfolios";
    String marketDataTableName = "marketData";
    String watchListTableName = "watchList";

    // Create database
    db.createDatabase(dbName);

    // Create column definitions
    List<ColumnDefinition> investorsColDefList = Arrays.asList(
      new ColumnDefinition("investorId", String.class),
      new ColumnDefinition("firstName", String.class),
      new ColumnDefinition("lastName", String.class),
      new ColumnDefinition("email", String.class),
      new ColumnDefinition("status", String.class),
      new ColumnDefinition("createdAt", LocalDateTime.class)
    );

    List<ColumnDefinition> stocksColDefList = Arrays.asList(
      new ColumnDefinition("stockId", String.class),
      new ColumnDefinition("tickerSymbol", String.class),  // AAPL, ORCL
      new ColumnDefinition("companyName", String.class), 
      new ColumnDefinition("market", String.class)    // NASDAQ, NYSE
    );

    List<ColumnDefinition> transactionsColDefList = Arrays.asList(
      new ColumnDefinition("transactionId", String.class),
      new ColumnDefinition("investorId", String.class),
      new ColumnDefinition("stockId", String.class),
      new ColumnDefinition("transactionType", String.class),
      new ColumnDefinition("quantity", Integer.class),
      new ColumnDefinition("pricePerShare", Float.class),
      new ColumnDefinition("TransactionDate", LocalDateTime.class)
    );

    List<ColumnDefinition> portfoliosColDefList = Arrays.asList(
      new ColumnDefinition("investorId", String.class),
      new ColumnDefinition("stockId", String.class),
      new ColumnDefinition("quantityOwned", Integer.class)
    );

    List<ColumnDefinition> marketDataColDefList = Arrays.asList(
      new ColumnDefinition("stockId", String.class),
      new ColumnDefinition("date", LocalDateTime.class),
      new ColumnDefinition("openPrice", Float.class),
      new ColumnDefinition("closePrice", Float.class),
      new ColumnDefinition("highPrice", Float.class),
      new ColumnDefinition("lowPrice", Float.class),
      new ColumnDefinition("volume", Integer.class)
    );

    List<ColumnDefinition> watchListColDefList = Arrays.asList(
      new ColumnDefinition("watchListId", String.class),
      new ColumnDefinition("investorId", String.class),
      new ColumnDefinition("stockId", String.class),
      new ColumnDefinition("addedDate", LocalDateTime.class)
    );

    // Create tables
    db.createTable(dbName, investorsTableName, investorsColDefList);
    db.createTable(dbName, stocksTableName, stocksColDefList);
    db.createTable(dbName, transactionsTableName, transactionsColDefList);
    db.createTable(dbName, portfoliosTableName, portfoliosColDefList);
    db.createTable(dbName, marketDataTableName, marketDataColDefList);
    db.createTable(dbName, watchListTableName, watchListColDefList);

    // Create records 
    Map<String, Object> hawardInvestorData = new HashMap<>();
    hawardInvestorData.put("investorId", UUID.randomUUID().toString());
    hawardInvestorData.put("firstName", "Haward");
    hawardInvestorData.put("lastName", "Jie");
    hawardInvestorData.put("email", "haward.jie@columnardb.com");
    hawardInvestorData.put("status", "Active ");
    hawardInvestorData.put("createdAt", LocalDateTime.now());

    Map<String, Object> maryInvestorData = new HashMap<>();
    maryInvestorData.put("investorId", UUID.randomUUID().toString());
    maryInvestorData.put("firstName", "Mary");
    maryInvestorData.put("lastName", "Smith");
    maryInvestorData.put("email", "mary.smith@columnardb.com");
    maryInvestorData.put("status", "Active ");
    maryInvestorData.put("createdAt", LocalDateTime.now());

    // Insert records into table(s)
    db.insert(dbName, investorsTableName, hawardInvestorData); 
    db.insert(dbName, investorsTableName, maryInvestorData);

    // Query
    List<String> columnList = Arrays.asList("firstName", "email");
    List<Map<String, Object>> result = db.select(dbName, "investors", columnList);
    System.out.println("Number of investors: " + result.size());
    System.out.println("Investors: " + result);

  }
}