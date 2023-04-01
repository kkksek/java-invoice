package pl.edu.agh.mwo.invoice;


import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;

public class PrintInvoice {

    public void printInvoice(Invoice invoice){
        int posCounter = 0;

        System.out.println("Invoice number: "+invoice.getNumber());
        for (Product product : invoice.getProducts().keySet()) {
            System.out.print("Product: " + product.getName() + ", Unit price: "+ product.getPrice() + ", Quantity: " + invoice.getProducts().get(product)
                            + " || Netto value: " + invoice.getPositionValue(product)
                            + " || VAT "+ product.getTaxPercent() + "%");

                    if (!(product.getExcise()==BigDecimal.ZERO)){
                        System.out.print(" || Excise: "+product.getExcise());
                        }
                    System.out.println(
                            " || Gross value: " + invoice.getPositionValueWithTaxAndExcise(product));

            posCounter++;
        }
        System.out.println("Number of positions: "+posCounter);
        System.out.println("Net total: " +invoice.getNetTotal()+" || Tax total: "+invoice.getTaxTotal()+" || Gross total: "+invoice.getGrossTotal());
    }
}
