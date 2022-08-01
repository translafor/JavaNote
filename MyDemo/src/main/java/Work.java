import com.oraen.oxygen.excelutil.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.*;
import java.util.*;

public class Work extends WordUtil {


    private static String yuansu_name = "字段";
    private static String bixuan = "约束";
    private static String data_type = "数据类型";
    private static String describe = "描述";
    private static String intro = "说明";

    private static List VARCHAR_TYPE = asList("Varchar","varchar","字符型");
    private static List NUMBER_TYPE = asList("number","数字型","数型");
    private static List ZUO_BIAO = asList("经度","纬度","维度");


    public static List<String> asList(String ...sl){
        List<String> list = new ArrayList<>();
        Arrays.asList(sl).stream().forEach(s->list.add(s));
        return list;
    }


    public static String[] names = {
             "2", "ZFXX", "FWJG", "YJXX", "CLBX", "CLLC", "SJPX", "YDZD", "CKZC", "CKHMD", "JSYDW", "DDJS", "DDCX", "DDPD", "DDBC"
            , "JYSX", "JYXX", "CKSC", "CKXC", "DCYY", "PJXX", "CGTS", "JSYCF", "JSYXY", "JTWFXX", "JTWFXXResp","JTSGXX", "JTSGXXResp","HCPTXX", "HCFBXX", "HCDBJS","HCDBJS-passenger", "HCDCYY", "JSYZC","JSYZCResp", "JSYHT", "QYZC","QYZCResp", "QYJR", "JSYSH", "JSYSHResp","QYSH", "QYSHResp","JSYBG","JSYBGResp"
            , "CLBG", "CLBGResp","QYBG", "QYBG-res","QYJSY","QYJSYResp"
            , "QYCL","QYCLResp"

    };

    //过滤掉自己不要的接口表
    public static List<List<Map<String, Object>>> filter1(List<List<Map<String, Object>>> source) {
        System.out.println("过滤前数量 " + source.size());
        List<List<Map<String, Object>>> re = new ArrayList<>();
        for (List<Map<String, Object>> tab : source) {
            if (tab == null || tab.size() == 0) {
                continue;
            }
            Map<String, Object> map = tab.get(0);

            if(map.get("长度") == null){
                map.put("长度","0");
            }
            if (map.get(yuansu_name) == null || map.get(bixuan) == null || map.get(data_type) == null || map.get("长度") == null) {
                continue;
            }

            re.add(tab);
        }

        System.out.println("过滤后数量 " + re.size());
        return re;
    }

    //过滤掉应答表
    public static List<List<Map<String, Object>>> filter2(List<List<Map<String, Object>>> source) {
        System.out.println("过滤前数量 " + source.size());
        List<List<Map<String, Object>>> re = new ArrayList<>();
        boolean liu = true;
        for (List<Map<String, Object>> tab : source) {
            if (tab.size() > 0) {
                Map<String, Object> map1 = tab.get(0);
                Map<String, Object> map2 = tab.get(1);
                List<String> err = new ArrayList<>();
                err.add("successNum");
                err.add("success");
                err.add("errorMsg");
                err.add("failNum");
                if (err.contains(map1.get(yuansu_name).toString().trim()) || err.contains(map2.get(yuansu_name).toString().trim())) {
                    continue;
                }
            }
            re.add(tab);

        }

        System.out.println("过滤后数量 " + re.size());
        return re;
    }

    //表格转换逻辑
    public static Map<String, Object> zh(Map<String, Object> s) {
        String zdmc = (String) s.get(describe);
        String ysmc = (String) s.get(yuansu_name);
        String bx = (String) s.get(bixuan);
        String cd = (String) s.get("长度");
        String slx = (String) s.get(data_type);

        //深圳逻辑
        String data_type = slx;
        if(slx.indexOf('(')!=-1){
            data_type = slx.substring(0,slx.indexOf('('));
            cd = slx.substring(slx.indexOf('(')+1,slx.indexOf(')'));
            System.out.printf("");
        }

        if(null==cd||cd.equals("")){
            cd = "10";
        }
        String ms = (String) s.get(intro);
        if(ms.contains("Double") || ms.contains("double")){
            data_type = "number";
        }
        int len;
        try {
            len = Integer.parseInt(cd.substring(1));
        } catch (Exception e) {
            System.out.println("奇怪的长度cd" + cd);
            len = 0;
        }

        Map<String, Object> re = new HashMap<>();
        re.put("元素名称", ysmc);
        re.put("必选", bx);
        re.put("长度", "V"+cd);
        re.put("字段名称", zdmc);
        re.put("取值", ms);
        //    re.put("规则", ms);


        re.put("是否坐标", "0");
        if (VARCHAR_TYPE.contains(data_type)) {
            if (zdmc.contains("经度") || zdmc.contains("纬度") || zdmc.contains("维度")) {
                re.put("类型", "String");
                re.put("是否坐标", "1");
            } else {
                re.put("类型", "String");
            }
        } else if (NUMBER_TYPE.contains(data_type)) {
            if (zdmc.contains("经度") || zdmc.contains("纬度") || zdmc.contains("维度")) {
                re.put("类型", "Bigdecimal");
                re.put("是否坐标", "1");
            } else if (zdmc.contains("金额") || zdmc.contains("价")|| zdmc.contains("费")) {
                re.put("类型", "Bigdecimal");
            } else if (len > 8) {
                re.put("类型", "Long");
            } else {
                re.put("类型", "Integer");
            }
        } else {
            System.out.println("类型是 " + slx + "很是奇怪");
            re.put("类型", "String");
            re.put("是否坐标", "1");
        }

        return re;
    }


    //表格转换过程
    public static List<List<Map<String, Object>>> doing(List<List<Map<String, Object>>> source) {
        List<List<Map<String, Object>>> re = new ArrayList<>();
        for (List<Map<String, Object>> tab : source) {
            List<Map<String, Object>> newT = new ArrayList<>();
            for (Map<String, Object> jl : tab) {
                newT.add(zh(jl));
            }
            re.add(newT);
        }
        return re;
    }

    //表格的导出 excel工具有缺陷 后续改进
    public static void export(List<List<Map<String, Object>>> source) throws IOException {
        List<String> head = Arrays.asList("元素名称", "必选", "类型", "长度", "字段名称", "取值", "是否坐标");
        List<Workbook> l = new ArrayList<>();
        for (List<Map<String, Object>> table : source) {
            Workbook wb = ExcelUtil.getWorkbookFromMaps(head, table);
            l.add(wb);
        }

        System.out.println(names.length);

        for (int i = 1; i <= l.size(); i++) {
            String name;
            if (i < names.length) {
                name = names[i];
            } else {
                name = i + "qg";
            }
            l.get(i-1).setSheetName(0, name);
            l.get(i-1).write(new FileOutputStream(new File("/Users/ls/excel/" + name + ".xlsx")));

        }

    }


    public static void main(String[] args) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(new File("/Users/ls/sz.docx")));
        List<XWPFTable> list = document.getTables();
         shows(tabToMapss(list));
        List<List<Map<String, Object>>> source = tabToMapss(list);
        source = filter1(source);
        source = filter2(source);
        source = doing(source);
        export(source);
    }
}
