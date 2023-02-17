import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//用户时段组
//todo:数据存储可以使用json存储，可以为数据添加标签
public class InfoData {
    private static int dayEnter1 = 4;       //高一走读生进
    private static int dayOut1 = 1;        //高一走读出
    private static int dayOutNight1 = 9;    //高一走读晚自习出

    private static int dayEnter2 = 5;        //高二走读进
    private static int dayOut2 = 2;        //高二走读出
    private static int dayOutNight2 = 10;   //高二走读晚自习出

    private static int internationalEnter = 7;  //国际进
    private static int InternationalOut = 6;    //国际出
    private static int InternationalOutNight = 11;   //国际走读晚自习出

    private static int boarderEnter1 = 8;   //住宿进
    private static int boarderOut1 = 3;     //住宿出

    int enter =-1;      //默认错误组号
    int out = -2;       //默认错误组号

    //根据学生类型和年级绑定不同的时段组号
    //type：是否住宿，grade：年级
    public InfoData forType(String type,int grade) throws MyException {
        InfoData InfoData = new InfoData();
        //是否是晚自习走读学生
        Boolean night = false;
        if (type.equals("晚自习")){
            type = "走读";
            night = true;
        }
        //得到学生对应的权限组别
        List<Integer> integerList = setTypeList(type, grade);
        //进的门权限时段组
        InfoData.enter = integerList.get(0);
        //出的门权限时段组
        InfoData.out = integerList.get(1);
        //晚自习的权限时段组
        if(night){
            InfoData.out = integerList.get(2);
        }
        return InfoData;
    }

    //根据不同类型返回对应学生权限组别
    public static List<Integer> setTypeList(String type,int grade) throws MyException {
        //高一走读学生
        List<Integer> gradeFood1 = new LinkedList<Integer>(){{
                 add(dayEnter1);
                 add(dayOut1);
                 add(dayOutNight1);
             }};
        //高二走读学生
        List<Integer> gradeFood2 = new LinkedList<Integer>(){{
            add(dayEnter2);
            add(dayOut2);
            add(dayOutNight2);
        }};
        //国际走读学生
        List<Integer> gradeFood3 = new LinkedList<Integer>(){{
            add(internationalEnter);
            add(InternationalOut);
            add(InternationalOutNight);
        }};

        //住宿生
        List<Integer> gradeRoom = new LinkedList<Integer>(){{
            add(boarderEnter1);
            add(boarderOut1);
        }};

        switch (type) {
            case "住宿":
            case "是":
                return gradeRoom;
            case "国际":
                return gradeFood3;
            case "走读":
            case "否":
                if(grade == 1){
                    return gradeFood1;
                }
                else if (grade == 2){
                    return gradeFood2;
                }
                else if (grade ==3){
                    return gradeFood3;
                }
                else {
                    throw new MyException("走读生没有这年级! 当前学生年级为："+grade);
                }
            default:
                throw new MyException("查到不到当前学生类型");
        }
    }

    public static ArrayList<List<Integer>> allInfoDataList(){
        ArrayList<List<Integer>> dataList = new ArrayList<>();
        try {
            dataList.add(setTypeList("走读",1));
            dataList.add(setTypeList("走读",2));
            dataList.add(setTypeList("国际",3));
            dataList.add(setTypeList("住宿",1));
        } catch (MyException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public static int getDayEnter1() {
        return dayEnter1;
    }

    public static void setDayEnter1(int dayEnter1) {
        InfoData.dayEnter1 = dayEnter1;
    }

    public static int getDayOut1() {
        return dayOut1;
    }

    public static void setDayOut1(int dayOut1) {
        InfoData.dayOut1 = dayOut1;
    }

    public static int getDayOutNight1() {
        return dayOutNight1;
    }

    public static void setDayOutNight1(int dayOutNight1) {
        InfoData.dayOutNight1 = dayOutNight1;
    }

    public static int getBoarderEnter1() {
        return boarderEnter1;
    }

    public static void setBoarderEnter1(int boarderEnter1) {
        InfoData.boarderEnter1 = boarderEnter1;
    }

    public static int getBoarderOut1() {
        return boarderOut1;
    }

    public static void setBoarderOut1(int boarderOut1) {
        InfoData.boarderOut1 = boarderOut1;
    }

    public static int getDayEnter2() {
        return dayEnter2;
    }

    public static void setDayEnter2(int dayEnter2) {
        InfoData.dayEnter2 = dayEnter2;
    }

    public static int getDayOut2() {
        return dayOut2;
    }

    public static void setDayOut2(int dayOut2) {
        InfoData.dayOut2 = dayOut2;
    }

    public static int getDayOutNight2() {
        return dayOutNight2;
    }

    public static void setDayOutNight2(int dayOutNight2) {
        InfoData.dayOutNight2 = dayOutNight2;
    }

    public static int getInternationalEnter() {
        return internationalEnter;
    }

    public static void setInternationalEnter(int internationalEnter) {
        InfoData.internationalEnter = internationalEnter;
    }

    public static int getInternationalOut() {
        return InternationalOut;
    }

    public static void setInternationalOut(int internationalOut) {
        InternationalOut = internationalOut;
    }

    public static int getInternationalOutNight() {
        return InternationalOutNight;
    }

    public static void setInternationalOutNight(int internationalOutNight) {
        InternationalOutNight = internationalOutNight;
    }
}
