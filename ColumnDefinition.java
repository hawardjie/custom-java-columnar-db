import java.io.Serializable;

public class ColumnDefinition implements Serializable {
  private String name;
  private Class<?> type;
  
  public ColumnDefinition(String name, Class<?> type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public Class<?> getType() {
    return type;
  }
}
