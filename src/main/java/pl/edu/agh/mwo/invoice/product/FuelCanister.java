package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.math.RoundingMode.HALF_UP;

public class FuelCanister extends Product {
    static String stepMotherDay = "05/03";
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
    static String date = sdf.format(new Date());

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"), calculateExcise());
    }
    public static void setStepMotherDay(String stepMotherDay) {
        FuelCanister.stepMotherDay = stepMotherDay;
    }
    public static void setDate(String date) {
        FuelCanister.date = date;
    }
    public static BigDecimal calculateExcise() {
        BigDecimal excise = new BigDecimal(5.56);
            if (date.equals(stepMotherDay)) {
                excise = BigDecimal.ZERO;
            }
            return excise.setScale(2, HALF_UP);
    }
}
