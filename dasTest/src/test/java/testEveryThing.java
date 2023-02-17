import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testEveryThing {

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);  //年份
        int currentMonth = now.get(Calendar.MONTH) + 1; // 月份从0开始计算，因此需要加1
        System.out.println(currentMonth);
        if(currentMonth<7){
            year = year+3-2;     //用新生举例，9月份新生入学，如果到了1-7月，那么说明学生已经在校读了半个学期，那么就应该是当前年份往后推2年的7月份。
        }else{
            year = year+4-3;     //用新生举例，如果在7-12月，说明学生处于第一个学期，那么应该是当前年份往后推3年的7月份。
        }
        now.set(year,6,15,0,0,0); //每年的7月15日，高三学生毕业。
        Date date = now.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);
        System.out.println(formatter.format(date));
    }

}
