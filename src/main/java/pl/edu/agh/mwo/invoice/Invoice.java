package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private HashMap<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        this.products.put(product,1);
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
    }

    public BigDecimal getSubtotal() {//zmienic nazwe jesli niejasna
        BigDecimal subtotal = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products.keySet()) {
                subtotal = subtotal.add(product.getPrice());
            }
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products.keySet()) {
                tax = tax.add(product.getTaxPercent().multiply(product.getPrice()));
            }
        }
        return tax;
    }

    public BigDecimal getTotal() {
        return getTax().add(getSubtotal());
    }
}
