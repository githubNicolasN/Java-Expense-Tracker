import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    // Add an expense to the tracker
    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully.");
    }

    // View all expenses
    public List<Expense> viewExpenses() {
        return new ArrayList<>(expenses);
    }

    // View expenses by category
    public List<Expense> viewExpensesByCategory(String category) {
        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    // Delete an expense by index
    public void deleteExpense(Expense expense) {
        if (expenses.contains(expense)) {
            expenses.remove(expense);
            System.out.println("Expense removed successfully.");
        } else {
            System.out.println("Expense not found.");
        }
    }
}