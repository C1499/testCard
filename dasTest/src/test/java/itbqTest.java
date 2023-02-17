import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

public class itbqTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject(new LinkedHashMap());
        int num = 2;   //一次发布多少个考试

        jsonObject.put("customType","1"); //课程类型（0：展示考场模式 1：自定义链接模式）
        jsonObject.put("name","批量测试");
        jsonObject.put("beforeShowTm","0"); //提前展示时间（分钟）
        for (int i = 0;i<num;i++){
            jsonObject.put("examinationRoomList["+i+"].gradeId","e3b0da523d664b9895eab92795343829");        //年级id
            jsonObject.put("examinationRoomList["+i+"].classesId","e7468f1096544e39b59882666954abb6");  //教室id
            jsonObject.put("examinationRoomList["+i+"].examCode","批量测试");
            jsonObject.put("examinationRoomList["+i+"].urlName","1");       //链接名
            jsonObject.put("examinationRoomList["+i+"].url","http://172.19.26.213:5000/#/electronic-class-card?ClassRoomId=B504");        //链接
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].date","2023-02-06");       //日期
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].startTime","16:40");       //开始时间
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].endTime","18:00");         //结束时间
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].subjectId","0056e91ad0b74569a7bf0936adad0226");
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].subjectName","高二31班/精品阅读1(邓妍方)");  //课程名
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].teacherId","");
            jsonObject.put("examinationRoomList["+i+"].examinationSubjectList["+i+"].teacherName","1");
        }
        System.out.println(jsonObject);
    }
}
