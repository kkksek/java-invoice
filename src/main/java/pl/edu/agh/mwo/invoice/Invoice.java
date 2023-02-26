package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private HashMap<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product==null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.products.put(product,1);
    }

    public void addProduct(Product product, int quantity) {
        if (quantity<=0){
            throw new IllegalArgumentException("Quantity cannot be less or equal to 0");
        }
        this.products.put(product,quantity);
        //komentarz to skomitowania
    }

    public BigDecimal getSubtotal() {//zmienic nazwe jesli niejasna
        BigDecimal subtotal = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products.keySet()) {
                subtotal = subtotal.add(product.getPrice().multiply(new BigDecimal(products.get(product))));
            }
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = new BigDecimal("0");
        if (!(products==null||products.isEmpty())) {
            for (Product product : this.products.keySet()) {
                tax = tax.add(product.getTaxPercent().multiply(product.getPrice().multiply(new BigDecimal(products.get(product)))));
            }
        }
        return tax;
    }


    public BigDecimal getTotal() {
        return getTax().add(getSubtotal());
    }
}
