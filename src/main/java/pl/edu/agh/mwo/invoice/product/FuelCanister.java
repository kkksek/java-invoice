package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.math.RoundingMode.HALF_UP;

public class FuelCanister extends Product{



    public FuelCanister(String name, BigDecimal price) {
        super(name, price,new BigDecimal("0.23"),calculateExcise());
    }

    public static BigDecimal calculateExcise(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        BigDecimal excise;
         if (date.equals("05/04/2023")){
             excise = BigDecimal.ZERO;
         }
        else {excise = new BigDecimal(5.56);}

         return excise.setScale(2,HALF_UP);
    }

}
