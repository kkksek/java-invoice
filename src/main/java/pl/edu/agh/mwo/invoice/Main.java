package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.BottleOfWine;
import pl.edu.agh.mwo.invoice.product.FuelCanister;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

    Invoice invoice = new Invoice();
    PrintInvoice print = new PrintInvoice();
    Product kapcie = new OtherProduct("Kapcie",new BigDecimal(12));
    Product buty = new OtherProduct("Buty",new BigDecimal(3));
    invoice.addProduct(kapcie,2);
    invoice.addProduct(buty,5);
    invoice.addProduct(kapcie,7);

    Product wino = new BottleOfWine("Chardonnay",new BigDecimal(3));
    Product fuel = new FuelCanister("Diesel",new BigDecimal(4));
    invoice.addProduct(wino);
    invoice.addProduct(fuel);
    print.printInvoice(invoice);

    Invoice invoice1 = new Invoice();
    PrintInvoice print1 = new PrintInvoice();
    Product kapciase = new OtherProduct("as",new BigDecimal(12));
    invoice1.addProduct(kapciase,2);
    print1.printInvoice(invoice1);
    }

}
