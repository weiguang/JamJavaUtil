package other.alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/25 18:31.
 */
public class Main {
    static int[] use;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<UnilateralLine> lineList = new ArrayList<UnilateralLine>();
        while (scanner.hasNextLine()) {
            String[] options = scanner.nextLine().split(";");
            if (options.length < 5) {
                break;
            }
            lineList.add(new UnilateralLine(options[0], options[1], options[2], options[3], options[4], options[5]));
        }
        scanner.close();

        use = new int[lineList.size()];
        // wirte your code here
        List<String> result = calculateUnilateral(lineList);

        for (String str : result) {
            System.out.println(str);
        }
    }
    public static List<String> calculateUnilateral(List<UnilateralLine> lineList) {
        List<String> result = new ArrayList<String>();
        cal1(lineList, result);
        cal2(lineList, result);
        cal3(lineList, result);
        return result;
    }

    public static void cal1(List<UnilateralLine> lineList, List result){
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 1; j < lineList.size(); j++) {
                if( use[i] == 1 || use[j] == 1) continue;
                if (lineList.get(i).sCen.equals(lineList.get(j).eCen) && lineList.get(i).eCen.equals(lineList.get(j).sCen)  && lineList.get(i).getTType().equals(lineList.get(j).getTType()) ) {
                    result.add("rule1:" + lineList.get(i).getId() + "+" + lineList.get(j).getId());
                    use[i] = 1; use[j] = 1;
                }
            }
        }
    }

    static boolean check(List<String> list,String id1,String id2, String id3){
        List t = new ArrayList();
        for (String o : list) {
            String[] ss = o.split("\\+");
            t.add(ss[0]);
            t.add(ss[1]);
            t.add(ss[2]);
            if(t.contains(id1) && t.contains(id2) && t.contains(id3)) return false;
        }
        return true;
    }

    public static void cal2(List<UnilateralLine> lineList, List result) {
        List<String> list = new ArrayList();
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = 0; j < lineList.size(); j++) {
                for (int k = 0; k < lineList.size(); k++) {
                    if( use[i] == 1 || use[j] == 1 || use[k] == 1) continue;
                    if(i!=j && j!=k && i!=k) {
                        if( lineList.get(i).getSCen().equals(lineList.get(k).getECen())
                                && lineList.get(i).getECen().equals(lineList.get(j).getSCen())
                                && lineList.get(j).getECen().equals(lineList.get(k).getSCen())){
                            String  s = lineList.get(i).getId() + "+" + lineList.get(j).getId() + "+" + lineList.get(k).getId();
                            if( check(list, lineList.get(i).getId(),lineList.get(j).getId(),lineList.get(k).getId())){
                                list.add(s);
                                result.add("rule2:" + s);
                                use[i] = 1; use[j] = 1; use[k] = 1;
                            }

                        }
                    }
                }
            }
        }
    }


    public static void cal3(List<UnilateralLine> lineList, List result){
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 1; j < lineList.size(); j++) {
                if( use[i] == 1 || use[j] == 1) continue;
                if (lineList.get(i).sCen.equals(lineList.get(j).eCen) && !lineList.get(i).eCen.equals(lineList.get(j).sCen)  && lineList.get(i).getEPro().equals(lineList.get(j).getSPro()) ) {
                    result.add("rule3:" + lineList.get(i).getId() + "+" + lineList.get(j).getId());
                    use[i] = 1; use[j] = 1;
                }
            }
        }
    }
    public static class UnilateralLine {
        private String id;
        private String sCen;//出发分拨
        private String sPro;//出发省
        private String eCen;//到达分拨
        private String ePro;//到达省
        //9.6m/17.5m
        private String tType;//车型
        public UnilateralLine(String id, String sCen, String sPro, String eCen, String ePro,String tType) {
            this.id = id;this.sCen = sCen;this.sPro = sPro;this.eCen = eCen;this.ePro = ePro;this.tType = tType;}
        public String getId() {return id;}
        public void setId(String id) {this.id = id;}
        public String getSCen() {return sCen;}
        public void setSCen(String ePro) {this.ePro = ePro;}
        public String getSPro() {return sPro;}
        public void setSPro(String sPro) {this.sPro = sPro;}
        public String getECen() {return eCen;}
        public void setECen(String eCen) {this.eCen = eCen;}
        public String getEPro() {return ePro;}
        public void setEPro(String ePro) {this.ePro = ePro;}
        public String getTType() {return tType;}
        public void setTType(String tType) {this.tType = tType;}
    }
}
