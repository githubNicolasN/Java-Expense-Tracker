import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private double amount;
    private LocalDateTime dateTime;
    private String category;
    private String description;
    private String additionalInfo;

    
    public Expense(double amount, String category, String description, String additionalInfo, LocalDateTime dateTime) {
        this.amount = amount;
        this.dateTime = dateTime; 
        this.category = category;
        this.description = description;
        this.additionalInfo = additionalInfo;
    }

    // Overloaded constructor that automatically uses the current date and time
    public Expense(double amount, String category, String description, String additionalInfo) {
        this(amount, category, description, additionalInfo, LocalDateTime.now());
    }

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Expense{" +
                "amount=" + amount + ", " +
                "dateTime=" + dateTime.format(formatter) + ", " +
                "category='" + category + "', " +
                "description='" + description + "', " +
                "additionalInfo='" + additionalInfo + "'" +
                '}';
    }
}