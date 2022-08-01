import com.jfinal.kit.StrKit;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * @description: doc工具类
 * @author: frank.wu
 * @create: 2021-08-21
 */
public class MyDocUtil {

    /**
     * 读取word文档工具类
     */


    /**
     * 根据文件路径读取读取word文件 将表格数据转List数据结构
     *
     * @param url
     * @return
     */
    public static List<List<String[]>> getWordTableUrl(String url) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getWordTableInputStream(in);
    }

    /**
     * 根据输入流读取word文件 将表格数据转List数据结构
     *
     * @param inputStream
     * @return
     */
    public static List<List<String[]>> getWordTableInputStream(FileInputStream inputStream) {
        // 表格集合
        List<List<String[]>> tabList = new ArrayList();
        try {
            POIFSFileSystem pfs = new POIFSFileSystem(inputStream);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();
            TableIterator it = new TableIterator(range);
            int tableCount = 1;
            while (it.hasNext()) {
                List<String[]> arrList = new ArrayList();
                Table tb = (Table) it.next();
                for (int i = 0; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    // 排除第一个表格
                    if (tableCount != 1 && tableCount != 2) {
                        // 如果第二列为空 则不加入
                        int cellNum = tr.numCells();
                        if (cellNum < 2 && tableCount != 3) {
                            continue;
                        }
                        if (tableCount != 3) {
                            TableCell tdItem = tr.getCell(1);
                            Paragraph para = tdItem.getParagraph(0);
                            if (StrKit.isBlank(para.text().trim())) {
                                continue;
                            }
                        }

                    }

                    String[] arr = new String[tr.numCells()];
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);
                        for (int k = 0; k < td.numParagraphs(); k++) {
                            Paragraph para = td.getParagraph(k);
                            arr[j] = arr[j] == null ? "" + para.text().trim() : arr[j] + para.text().trim();
                        }
                    }
                    arrList.add(arr);
                }
                tabList.add(arrList);
                tableCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tabList;
    }

    /**
     * 通过文件路径读取文件
     *
     * @param url
     * @return
     */
//    public static XWPFDocument getWordStringUrl(String url, Map params) {
//        FileInputStream in = null;
//        try {
//            in = new FileInputStream(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return getWordStringInputStream(in, params);
//    }
//
//    public static XWPFDocument getWordStringInputStream(FileInputStream inputStream, Map params) {
//        XWPFDocument doc = null;
//        try {
//            doc = new XWPFDocument(inputStream);
//            WordReplaceUtil.me.replaceInPara(doc, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return doc;
//    }

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
    /**
     * <p>将base64字符解码保存文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
    /**
     * <p>将base64字符保存文本文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code,String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
    public static void main(String[] args) {
        try {
            String base64Code =encodeBase64File("/Users/admin/Downloads/合同.pdf");
            System.out.println(base64Code);
//            decoderBase64File(base64Code, "/Users/Crazy/Desktop/zyb.png");
//            toFile(base64Code, "/Users/Crazy/Desktop/zyb.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
