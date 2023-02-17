import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//学生信息
public class Student {

    String id;  //学号
    String name;    //姓名
    String type;    //学生类型(走读还是住宿)
    String grade;       //年级
    String timeType;    //时段权限(0/1)
    InfoData infoData = new InfoData();     //用户时段组

    String dueTime;     //失效日期


    Student (String id,String name,String type,String grade,String timeType,String dueTime){
        this.id = id;
        this.name = name;
        this.type = type;
        this.grade = grade;
        this.timeType = timeType;
        int gradeNum = -1;  //将中文年级更换为数字
        switch (grade){
            case "高一": gradeNum = 1;break;
            case "高二": gradeNum = 2;break;
            case "高三":
            case "国际":
                gradeNum = 3;break;
        }
        try {
            infoData = infoData.forType(type,gradeNum);
        } catch (MyException e) {
            System.out.println("【出错啦！】 学生："+name+" 报错信息是："+e.getMessage());
            e.printStackTrace();
        }
        if(dueTime.equals("")){
            setDueTime(gradeNum);
        }

    }

    public void setDueTime(int gradeNum) {
        //处理人员的失效日期（思路：创建日期，根据学生的年级来判断它应该多久之后失效。不能根据学生编号，因为存在休学复学学生）
        // 使用 Calendar 类获取当前年份
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);  //年份
        int currentMonth = now.get(Calendar.MONTH) + 1; // 月份从0开始计算，因此需要加1
        if(currentMonth<7){
            year = year+3-gradeNum;     //用新生举例，9月份新生入学，如果到了1-7月，那么说明学生已经在校读了半个学期，那么就应该是当前年份往后推2年的7月份。
        }else{
            year = year+4-gradeNum;     //用新生举例，如果在7-12月，说明学生处于第一个学期，那么应该是当前年份往后推3年的7月份。
        }
        now.set(year,6,15,0,0,0); //每年的7月15日，高三学生毕业。
        Date date = now.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dueTime = formatter.format(date);
    }
}
