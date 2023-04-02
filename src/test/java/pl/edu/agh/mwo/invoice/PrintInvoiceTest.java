package pl.edu.agh.mwo.invoice;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pl.edu.agh.mwo.invoice.product.FuelCanister;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static pl.edu.agh.mwo.invoice.PrintInvoice.printInvoice;

public class PrintInvoiceTest extends TestCase {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testPrintOneLineOfProduct(){
        Invoice invoice = new Invoice();
        Product product = new FuelCanister("Diesel",new BigDecimal(4));
        invoice.addProduct(product,1);
        printInvoice(invoice);
        Assert.assertEquals(
                "Invoice number: 1\r\n"+
                "Product: Diesel, Unit price: 4, " +
                "Quantity: 1 || Netto value: 4 || VAT 23.00% || " +
                "Excise: 5.56 || Gross value: 10.48\r\n"+
                        "Number of positions: 1\r\n"+
                "Net total: 4 || Tax total: 6.48 || Gross total: 10.48".trim(),
                outputStreamCaptor.toString()
                        .trim());

    }
    @Test
    public void testPrintTwoLinesOfProduct(){
        Invoice invoice = new Invoice();
        Product product = new FuelCanister("Diesel",new BigDecimal(4));
        invoice.addProduct(product,1);
        Product product1 = new OtherProduct("Buty",new BigDecimal(3));
        invoice.addProduct(product1,5);
        printInvoice(invoice);
        Assert.assertEquals(
                "Invoice number: 2\r\n"+
                        "Product: Diesel, Unit price: 4, Quantity: 1 || Netto value: 4 || VAT 23.00% || Excise: 5.56 || Gross value: 10.48\r\n" +
                        "Product: Buty, Unit price: 3, Quantity: 5 || Netto value: 15 || VAT 23.00% || Gross value: 18.45\r\n" +
                        "Number of positions: 2\r\n"+
                        "Net total: 19 || Tax total: 9.93 || Gross total: 28.93".trim(),
                outputStreamCaptor.toString()
                        .trim());

    }

}