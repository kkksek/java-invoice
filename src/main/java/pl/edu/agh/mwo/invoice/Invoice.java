package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            quantity = quantity + products.get(product);
        }
        products.put(product, quantity);
    }

    public BigDecimal getPositionValue(Product product) {
        BigDecimal quantity = new BigDecimal(products.get(product));
        BigDecimal value = product.getPrice().multiply(quantity);
    return value;
    }

    public BigDecimal getPositionValueWithTaxAndExcise(Product product) {
        BigDecimal quantity = new BigDecimal(products.get(product));
        BigDecimal value = product.getPriceWithTaxAndExcise().multiply(quantity);
        return value;
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            totalNet = totalNet.add(getPositionValue(product));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            totalGross = totalGross.add(getPositionValueWithTaxAndExcise(product));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
