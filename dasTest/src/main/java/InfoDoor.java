import java.util.ArrayList;

//门的名称列表
public class InfoDoor {

    private String northEnter1 = "北门闸机_01_进";
    private String northOut1 = "北门闸机_01_出";
    private String northEnter2 = "北门闸机_02_进";
    private String northOut2 = "北门闸机_02_出";
    private String northEnter3 = "北门闸机_03_进";
    private String northOut3 = "北门闸机_03_出";

    private String southEnter1 = "南门闸机_1号进";
    private String southOut1 = "南门闸机_1号出";
    private String southEnter2 = "南门闸机_2号进";
    private String southOut2 = "南门闸机_2号出";

    private String eastEnter1 = "东门闸机_01进（新）";
    private String eastOut1 = "东门闸机_01出（新）";

    //返回所有进的门
    public ArrayList getEnterList(){
        ArrayList <String> list = new ArrayList<String>();
        list.add(this.northEnter1);
        list.add(this.northEnter2);
        list.add(this.northEnter3);
        list.add(this.southEnter1);
        list.add(this.southEnter2);
        list.add(this.eastEnter1);
        return list;
    }

    //返回所有出的门
    public ArrayList getOutList(){
        ArrayList <String> list = new ArrayList<String>();
        list.add(this.northOut1);
        list.add(this.northOut2);
        list.add(this.northOut3);
        list.add(this.southOut1);
        list.add(this.southOut2);
        list.add(this.eastOut1);
        return list;
    }

    public String getNorthEnter1() {
        return northEnter1;
    }

    public void setNorthEnter1(String northEnter1) {
        this.northEnter1 = northEnter1;
    }

    public String getNorthOut1() {
        return northOut1;
    }

    public void setNorthOut1(String northOut1) {
        this.northOut1 = northOut1;
    }

    public String getNorthEnter2() {
        return northEnter2;
    }

    public void setNorthEnter2(String northEnter2) {
        this.northEnter2 = northEnter2;
    }

    public String getNorthOut2() {
        return northOut2;
    }

    public void setNorthOut2(String northOut2) {
        this.northOut2 = northOut2;
    }

    public String getNorthEnter3() {
        return northEnter3;
    }

    public void setNorthEnter3(String northEnter3) {
        this.northEnter3 = northEnter3;
    }

    public String getNorthOut3() {
        return northOut3;
    }

    public void setNorthOut3(String northOut3) {
        this.northOut3 = northOut3;
    }

    public String getSouthEnter1() {
        return southEnter1;
    }

    public void setSouthEnter1(String southEnter1) {
        this.southEnter1 = southEnter1;
    }

    public String getSouthOut1() {
        return southOut1;
    }

    public void setSouthOut1(String southOut1) {
        this.southOut1 = southOut1;
    }

    public String getSouthEnter2() {
        return southEnter2;
    }

    public void setSouthEnter2(String southEnter2) {
        this.southEnter2 = southEnter2;
    }

    public String getSouthOut2() {
        return southOut2;
    }

    public void setSouthOut2(String southOut2) {
        this.southOut2 = southOut2;
    }

    public String getEastEnter1() {
        return eastEnter1;
    }

    public void setEastEnter1(String eastEnter1) {
        this.eastEnter1 = eastEnter1;
    }

    public String getEastOut1() {
        return eastOut1;
    }

    public void setEastOut1(String eastOut1) {
        this.eastOut1 = eastOut1;
    }
}
