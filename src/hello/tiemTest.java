package hello;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class tiemTest {

    public static void main(String[] args) {
        int a=2;
        int b=33;
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String num = df.format((float)a/b*100);//返回的是String类型
        System.out.println(num+"%");

    }
}
