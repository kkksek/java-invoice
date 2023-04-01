package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static java.math.RoundingMode.HALF_UP;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("23.00"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("8.00"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTaxAndExcise()));
    }

    @Test
    public void testPriceWithTaxAndExcise(){
        Product product = new BottleOfWine("Chardonnay",new BigDecimal(3));
        Assert.assertThat(new BigDecimal("9.25"), Matchers.comparesEqualTo(product.getPriceWithTaxAndExcise()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }
    @Test
    public void testExciseCalculationForFuelWhenTheSameDate(){
        Product fuel = new FuelCanister("Diesel",new BigDecimal(4));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        String date = sdf.format(new Date());
        ((FuelCanister) fuel).setStepMotherDay(date);

        Assert.assertEquals(BigDecimal.ZERO.setScale(2,HALF_UP), ((FuelCanister) fuel).calculateExcise());

    }

    @Test
    public void testExciseCalculationForFuelWhenDifferentDate(){
        Product fuel = new FuelCanister("Diesel",new BigDecimal(4));
        String date = "01/06";
        ((FuelCanister) fuel).setStepMotherDay(date);
        ((FuelCanister) fuel).setDate("05/06");

        Assert.assertEquals(new BigDecimal(5.56).setScale(2,HALF_UP), ((FuelCanister) fuel).calculateExcise());

    }
}
