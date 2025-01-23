import java.time.LocalDate;
import java.util.HashMap;

public class Cart {
    private int id;
    private HashMap<Product, Integer> products;
    private LocalDate date;
    private Type type;

    public Cart(int id, LocalDate date, Type type) {
        this.id = id;
        this.products = new HashMap<>();
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public void addItem(Product product, int amount) {
        products.put(product, amount);
    }

    public void removeItem(Product product, int amount) {
        if (products.containsKey(product)) {
            int totalAmount = products.get(product);

            if (amount < totalAmount) {
                products.put(product, totalAmount - amount);
            } else {
                products.remove(product);
            }
        }
    }

    public void printSalesSlip() {
        if (!products.isEmpty()) {
            System.out.printf("Order ID: %d\nDate: %s\n", id, date.toString());
            products.forEach((product,amount) -> System.out.printf("%dx %s\n", amount, product.getName()));
        } else System.out.println("Cart is empty.");
    }
}
