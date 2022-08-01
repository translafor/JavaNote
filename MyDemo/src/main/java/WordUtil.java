import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtil {

    public static String get(XWPFTable xt, int row, int cell){
        XWPFTableRow r = xt.getRow(row);
        if(r == null){
            return null;
        }

        XWPFTableCell c = r.getCell(cell);
        if(c == null){
            return null;
        }

        return c.getText();
    }

    public static List<List<Map<String, Object>>> tabToMapss(XWPFDocument document){
        List<XWPFTable> list = document.getTables();
        return tabToMapss(list);
    }

    public static List<List<Map<String, Object>>> tabToMapss(List<XWPFTable> list){
        List<List<Map<String, Object>>> re = new ArrayList<>();
        for(XWPFTable xw : list){
            re.add(tabToMaps(xw));
        }
        return re;
    }

    public static List<Map<String, Object>> tabToMaps(XWPFTable xt){
        List<Map<String, Object>> re = new ArrayList<>();
        XWPFTableRow first = xt.getRow(0);
        int cellNum = first.getTableCells().size();
        int rowNum = xt.getRows().size();
        for(int i=1; i<rowNum; i++){
            Map<String, Object> map = new HashMap<>();
            for(int j=0; j<cellNum; j++){
                String key = get(xt, 0, j);
                String value = get(xt, i, j);
                if(key != null){
                    key = key.trim();
                }
                if(value != null){
                    value = value.trim();
                }
                map.put(key, value);
            }
            re.add(map);
        }

        return re;
    }

    public static void show(List<Map<String, Object>> t){
        System.out.println("\n =============================================");
        for(Map<String, Object> m : t){
            System.out.println(m);
        }
        System.out.println("=============================================  \n");

    }

    public static void shows(List<List<Map<String, Object>>> t){
        for(List<Map<String, Object>> x : t){
            System.out.println("\n\n\n   一张表");
            show(x);
        }
    }

    public static void main(String[] args) throws Exception {
        XWPFDocument document= new XWPFDocument(new FileInputStream(new File("/Users/corki/Desktop/ls/gz.docx")));
        List<XWPFTable> list = document.getTables();
        shows(tabToMapss(list));

    }
}
