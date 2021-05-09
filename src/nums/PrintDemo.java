package nums;

import java.text.DecimalFormat;

public class PrintDemo {

    public static void main(String[] args) {
//        System.out.format("this is a decimal %d", 1000);

        //use of DecimalFormat class
//        System.out.println(new DecimalFormat("this is dollar $###,###.###").format(1000.1079999));
//        System.out.println(System.out.getClass().getSimpleName());

        System.out.println(Integer.toHexString(65));
        System.out.println(Integer.parseInt("230", 5));
        System.out.println(Double.isNaN(Double.NaN));
        System.out.println(Integer.valueOf(1).equals(Long.valueOf(1)));
    }
}
