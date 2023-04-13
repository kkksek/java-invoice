package pl.edu.agh.mwo.invoice;

import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import pl.edu.agh.mwo.invoice.product.FuelCanister;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static pl.edu.agh.mwo.invoice.PrintInvoice.printInvoice;

public class PrintInvoiceWithOneLineTest extends TestCase {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    Invoice invoice = new Invoice();
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }



    @Test
    public void testPrintOneLineOfProductInvoiceNumber() {

        Product product = new FuelCanister("Diesel", new BigDecimal(4));
        invoice.addProduct(product, 1);
        printInvoice(invoice);
        String printedInvoice = outputStreamCaptor.toString().trim();
        Assert.assertThat(printedInvoice, Matchers.containsString("Invoice number: 1"));

    }

    @Test
    public void testPrintOneLineOfProductPositionWithValues() {

        Product product = new FuelCanister("Diesel", new BigDecimal(4));
        invoice.addProduct(product, 1);
        printInvoice(invoice);
        String printedInvoice = outputStreamCaptor.toString().trim();
        Assert.assertThat(printedInvoice, Matchers.containsString("Product: Diesel, Unit price: 4, Quantity: 1 || Netto value: 4 || VAT 23.00% || Excise: 5.56 || Gross value: 10.48"));
    }

    @Test
    public void testPrintOneLineOfProductNumberOfPositions() {

        Product product = new FuelCanister("Diesel", new BigDecimal(4));
        invoice.addProduct(product, 1);
        printInvoice(invoice);
        String printedInvoice = outputStreamCaptor.toString().trim();
        Assert.assertThat(printedInvoice, Matchers.containsString("Number of positions: 1"));
    }

    @Test
    public void testPrintOneLineOfProductTotalNumbers() {

        Product product = new FuelCanister("Diesel", new BigDecimal(4));
        invoice.addProduct(product, 1);
        printInvoice(invoice);
        String printedInvoice = outputStreamCaptor.toString().trim();
        Assert.assertThat(printedInvoice, Matchers.containsString("Net total: 4 || Tax total: 6.48 || Gross total: 10.48"));
    }


}