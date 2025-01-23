public class InventoryItem {
    private Product product;
    private int qtyTotal; // in stock
    private int qtyReserved;
    private int qtyReorder; // order once triggered
    private int qtyLow; // trigger for more
    private double salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReserved, int qtyReorder, int qtyLow, double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReserved = qtyReserved;
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void reserveItem(int amount) {
        if (qtyTotal - amount >= 0) {
            qtyReserved += amount;
            qtyTotal -= amount;

            if (qtyTotal <= qtyLow) {
                placeInventoryOrder();
            }
        }
    }

    public void releaseItem(int amount) {
        if (qtyReserved - amount >= 0) {
            qtyReserved -= amount;
            qtyTotal += amount;
        }
    }

    public void sellItem(int amount) {
        if (qtyReserved - amount >= 0) {
            qtyReserved -= amount;
        }
    }

    public void placeInventoryOrder() {
        qtyTotal += qtyReorder;
    }

    @Override
    public String toString() {
        return qtyTotal + "x " + product.getName();
    }
}
