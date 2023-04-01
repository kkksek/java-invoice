package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Product {
    private final String name;
    private final BigDecimal price;
    private final BigDecimal taxPercent;
    private BigDecimal excise;
    private BigDecimal persentMult = new BigDecimal(100);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String date = sdf.format(new Date());

    protected Product(String name, BigDecimal price, BigDecimal tax, BigDecimal excise) {
        if (name == null || name.equals("")
                || price == null || tax == null || tax.compareTo(new BigDecimal(0)) < 0
                || price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
        this.excise = excise;
    }

    public BigDecimal getExcise() {
        return excise;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent.multiply(persentMult);
    }

    public BigDecimal getPriceWithTaxAndExcise() {
        return price.multiply(taxPercent).add(price).add(excise);
    }
}
