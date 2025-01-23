import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>(Arrays.asList(
                new Product("A100","apple","local",Category.PRODUCE),
                new Product("B100","banana","local",Category.PRODUCE),
                new Product("P100","pear","local",Category.PRODUCE),
                new Product("L103","lemon","local",Category.PRODUCE),
                new Product("M201","milk","farm",Category.DAIRY),
                new Product("Y001","yogurt","farm",Category.DAIRY),
                new Product("C333","cheese","farm",Category.DAIRY),
                new Product("R777","rice chex","Nabisco",Category.CEREAL),
                new Product("G111","granola","Nat Valley",Category.CEREAL),
                new Product("BB11","ground beef","butcher",Category.MEAT),
                new Product("CC11","chicken","butcher",Category.MEAT),
                new Product("BC11","bacon","butcher",Category.MEAT),
                new Product("BC77","coke","coca cola",Category.BEVERAGE),
                new Product("BC88","coffee","value",Category.BEVERAGE),
                new Product("BC99","tea","herbal",Category.BEVERAGE)
        ));

        HashMap<Product, InventoryItem> inventory = new HashMap<>();
        products.forEach(product -> inventory.put(product, new InventoryItem(
                product, 50, 0, 25, 10, 9.99)
        ));

        Store store = new Store(inventory, new HashMap<>());
        store.addToCart(0, products.get(0), 3); // apple
        store.removeFromCart(0, products.get(0), 3); // apple

        store.addToCart(1, products.get(5), 17); // yogurt
        store.removeFromCart(1, products.get(5), 5); // yogurt
        store.addToCart(1, products.get(11), 5); // bacon

        store.addToCart(2, products.get(7), 60); // rice
        store.removeFromCart(2, products.get(7), 30); // rice
        store.addToCart(2, products.get(7), 40); // rice
        store.removeFromCart(2, products.get(7), 5); // rice

        store.listProductsByCategory();
        store.checkOutCart(1);
        store.checkOutCart(0);
        store.checkOutCart(2);
        store.listProductsByCategory();
    }
}
