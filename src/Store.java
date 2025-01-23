import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private HashMap<Product, InventoryItem> inventory;
    private HashMap<Integer, Cart> carts;
    private HashMap<Category, List<InventoryItem>> aisleInventory; // physical

    public Store(HashMap<Product, InventoryItem> inventory, HashMap<Integer, Cart> carts) {
        this.inventory = inventory;
        this.carts = carts;
        this.aisleInventory = new HashMap<>();
    }

    public void addToCart(int cartId, Product product, int amount) {
        Cart cart = carts.get(cartId);

        if (cart == null) {
            carts.put(cartId, new Cart(cartId, LocalDate.now(), Type.VIRTUAL));
            cart = carts.get(cartId);
        }

        cart.addItem(product, amount);

        InventoryItem item = inventory.get(product);
        if (item != null) item.reserveItem(amount);
    }

    public void removeFromCart(int cartId, Product product, int amount) {
        Cart cart = carts.get(cartId);

        if (cart != null) {
            cart.removeItem(product, amount);

            InventoryItem item = inventory.get(product);
            if (item != null) item.releaseItem(amount);
        }
    }

    public void checkOutCart(int cartId) {
        Cart cart = carts.get(cartId);

        if (cart != null) {
            cart.getProducts().forEach((product,amount) -> inventory.get(product).sellItem(amount));
            cart.printSalesSlip();
        }
    }

    public void abandonCarts() {
        for (Integer id : carts.keySet()) {
            if (carts.get(id).getDate().getDayOfYear() < LocalDate.now().getDayOfYear()) {
                carts.get(id).getProducts().forEach((product,amount) ->
                        inventory.get(product).releaseItem(amount)
                );
            }
        }
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getDate().getDayOfYear() < LocalDate.now().getDayOfYear()) {


            }
        }
    }

    public void listProductsByCategory() {
        for (Category category : Category.values()) {
            aisleInventory.put(category, inventory.values()
                    .stream()
                    .filter(item -> item.getProduct().getCategory() == category)
                    .collect(Collectors.toList())
            );
        }

        aisleInventory.forEach((category,item) -> System.out.printf("%s | %s\n", category, item));
    }
}
