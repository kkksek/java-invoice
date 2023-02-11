package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public void addProduct(Product product) {
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products) {
                subtotal = subtotal.add(product.getPrice());
            }
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products) {
                tax = tax.add(product.getTaxPercent());
            }
        }
        return tax;
    }

    public BigDecimal getTotal() {
        return null;
    }
}
