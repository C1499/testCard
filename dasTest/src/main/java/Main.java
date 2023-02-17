
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//todo:用户输入错误的文件名会退出程序
public class Main {


    //上传学生名单并解析(学生信息表)
    //根据学生名单一个一个创建对应的门权限信息行(解析后的学生信息对象，门的名称，权限信息，生成的信息)
    //生成最终的表格（导出表格)
    public static void main(String[] args) throws Exception {
        head();
        // 1.获取文件流
        System.out.println("请输入导入的Excel表地址，以回车结束。格式：G:\\xxxx.xlsx");
        Scanner sc=new Scanner(System.in);
        String address = sc.next();//获取用户输入文件名
        InputStream inputStream = new FileInputStream(address);

        // 2.创建工作区workbook
        XSSFSheet sheet;
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
            sheet = workbook.getSheetAt(0);
            ArrayList<Student> students = new ArrayList<>();

            // 4.获取数据，需要跳过表头
            // getLastRowNum() 获取一张sheet表中行的数量
            for(int i = 1 ; i <= sheet.getLastRowNum();i++){
                // 获取第i行的数据
                XSSFRow row = sheet.getRow(i);
                //默认POI读取单元格内容为数字的话，自动变成Dubbo类型的，所以需要格式化
                DataFormatter formatter = new DataFormatter();
                String id = formatter.formatCellValue(row.getCell(0));
                String name = formatter.formatCellValue(row.getCell(1));
                if (id.equals(" ") && name.equals(" ")){
                    continue;
                }
                String grade = formatter.formatCellValue(row.getCell(2));
                String type = formatter.formatCellValue(row.getCell(3));
                String timeType = formatter.formatCellValue(row.getCell(4));
                String dueTime = formatter.formatCellValue(row.getCell(5));
                Student student = new Student(id,name,type,grade,timeType,dueTime);
                students.add(student);
            }
            newExcel(null,students,"D:/qxExcel.xlsx");
            // 6.关闭资源
            inputStream.close();
            System.out.println("生成完毕，生成文件地址为：D:/qxExcel.xlsx");
        }catch (ExceptionInInitializerError e){
            e.getCause().printStackTrace();
        }
        head();
    }

    //默认提示语句
    public static void head() throws InterruptedException, FileNotFoundException {
        while (true){

            System.out.println("\n============欢迎使用生成大门闸机权限工具，请输入对应代码获取相应服务============");
            System.out.println("============1.生成进出入校门权限表格==================");
            System.out.println("============2.生成参加晚自习走读生权限表格==================");
            System.out.println("============3.获取导入表格模板===============");
            System.out.println("============4.获取进出入所有门名称============");
            System.out.println("============5.获取用户时段组组号=============");
            System.out.println("============6.退出程序=====================\n");
            Scanner sc=new Scanner(System.in);
             switch (sc.next()){
                 case "1":return;
                 case "2":
                     nightStudent();break;
                 case "3":
                     newExcel(new String[]{"人员编号", "姓名", "年级", "是否住宿", "权限类别","失效日期"},new ArrayList<>(),"\\doorTemplate.xlsx");
                     newExcel(new String[]{"人员编号", "姓名", "年级", "权限类别","失效日期"},new ArrayList<>(),"\\nightTemplate.xlsx");
                     System.out.println("生成完毕，学生校门权限导入表格模板为：doorTemplate.xlsx,申请晚自习走读学生校门权限导入模板为：nightTemplate.xlsx");
                     break;
//                     System.out.println("人员编号\t || 姓名\t ||年级\t ||是否住宿\t ||权限类别");break;
                 case "4":
                     System.out.println(new InfoDoor().getEnterList());
                     System.out.println(new InfoDoor().getOutList());
                     break;
                 case "5":
                     ArrayList<List<Integer>> dataList = InfoData.allInfoDataList();
                     System.out.println("List里依次为[进去的时段组、出去的时段组、申请晚自习学生出去的时段组]");
                     System.out.println("高一走读生用户时段组号:"+dataList.get(0));
                     System.out.println("高二走读生用户时段组号:"+dataList.get(1));
                     System.out.println("国际走读生用户时段组号:"+dataList.get(2));
                     System.out.println("住宿生用户时段组号:"+dataList.get(3));
                     break;
                 case "6":
                    System.exit(0);
             }
            Thread.sleep(1000);
        }
    }

    //生成最终表格的方法
    public static void newExcel(String[] title,ArrayList<Student> studentList,String fileName){
        System.out.println("正在生成中-------请稍等");
        System.out.println("提示：高三学生默认按照国际生配置权限！！如有不同，请手动修改生成后的表格！\\n");
        if(title ==null){
            title = new String[]{"人员编号", "姓名", "门的名称", "权限类别", "用户时段组","失效日期"};
        }
        InfoDoor infoDoor = new InfoDoor();
        ArrayList allEnter = infoDoor.getEnterList();
        ArrayList allOut = infoDoor.getOutList();
        Workbook workbook = new SXSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("sheet0");
        // 创建第一行
        Row row = sheet.createRow(0);
        // 创建一个单元格
        Cell cell = null;
        // 创建表头
        int index = 0;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        index++;
        // 从第二行开始追加数据
        for (int i = 0; i < studentList.size(); i++) {
            index = createStudentData(studentList, allEnter, sheet, index, i,true);
            index = createStudentData(studentList, allOut, sheet, index, i,false);
        }
        // 创建一个文件
        File file = new File(fileName);
        try {
            file.createNewFile();
            // 打开文件流
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //学生跟门一一对应
    private static int createStudentData(ArrayList<Student> studentList, ArrayList<String> allEnter, Sheet sheet, int index, int i,Boolean time) {
        for (int j = 0; j < allEnter.size(); j++) {
            // 创建第index行
            Row nextRow = sheet.createRow(index++);
            // 参数代表第几列
            Cell cell2 = nextRow.createCell(0);
            cell2.setCellValue(studentList.get(i).id);      //人员编号
            cell2 = nextRow.createCell(1);
            cell2.setCellValue(studentList.get(i).name);        //人员姓名
            cell2 = nextRow.createCell(2);
            cell2.setCellValue(allEnter.get(j));            //门的名称
            cell2 = nextRow.createCell(3);
            cell2.setCellValue(studentList.get(i).timeType);       //用户时段组
            cell2 = nextRow.createCell(4);
            if (time){
                cell2.setCellValue(studentList.get(i).infoData.enter);      //用户时段组
            }else cell2.setCellValue(studentList.get(i).infoData.out);
            cell2 = nextRow.createCell(5);
            cell2.setCellValue(studentList.get(i).dueTime);
        }
        return index;
    }

    //晚自习学生绑定出门
    private static void nightStudent() throws FileNotFoundException {
        // 1.获取文件流
        System.out.println("请输入导入的Excel表地址，以回车结束。格式：G:\\xxxx.xlsx");
        Scanner sc=new Scanner(System.in);
        String address = sc.next();//获取用户输入文件名
        InputStream inputStream = new FileInputStream(address);

        //2.解析文件
        // 2.创建工作区workbook
        XSSFSheet sheet;
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
            sheet = workbook.getSheetAt(0);
            ArrayList<Student> students = new ArrayList<>();

            // 4.获取数据，需要跳过表头
            // getLastRowNum() 获取一张sheet表中行的数量
            for(int i = 1 ; i <= sheet.getLastRowNum();i++){
                // 获取第i行的数据
                XSSFRow row = sheet.getRow(i);
                //默认POI读取单元格内容为数字的话，自动变成Dubbo类型的，所以需要格式化
                DataFormatter formatter = new DataFormatter();
                String id = formatter.formatCellValue(row.getCell(0));  //人员标号
                String name = formatter.formatCellValue(row.getCell(1));    //名字
                if (id.equals(" ") && name.equals(" ")){
                    continue;
                }
                String grade = formatter.formatCellValue(row.getCell(2));   //年级
                String timeType = formatter.formatCellValue(row.getCell(3));    //权限类别
                String dueTime = formatter.formatCellValue(row.getCell(4));     //失效日期
                Student student = new Student(id,name,"晚自习",grade,timeType,dueTime);
                students.add(student);
            }
            newExcel(null,students,"D:/nightExcel.xlsx");
            // 6.关闭资源
            inputStream.close();
            System.out.println("生成完毕，生成文件地址为：D:/nightExcel.xlsx");
        }catch (ExceptionInInitializerError e){
            e.getCause().printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
