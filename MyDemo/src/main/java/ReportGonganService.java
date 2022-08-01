
import testresp.GuiYangGpsResp;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description: gps上报公安
 * @author: frank.wu
 * @create: 2021-09-08
 */
public class ReportGonganService {
  public static void post() throws IOException {
    DataOutputStream out = null;
    BufferedReader in = null;
    String addr = "Http://222.85.224.5:9080/gjza/imp/net/postGpsData";
    URL realUrl1 = new URL(addr);
    HttpURLConnection conn = (HttpURLConnection) realUrl1.openConnection();
    conn.setRequestMethod("POST");
    // 设置通用的请求属性
    conn.setAllowUserInteraction(true);
    conn.setRequestProperty("accept", "*/*");
    conn.setRequestProperty("connection", "Keep-Alive");
    conn.setRequestProperty(
        "user-agent", "Mozilla/4. 0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    conn.setRequestProperty("Accept-Charset", "utf-8");
    conn.setRequestProperty("contentType", "utf-8");
    conn.setRequestProperty("X-Authorization", "c2a56f30212f409ab87d8dd7864c86fd");
    conn.setDoOutput(true);
    conn.setDoInput(true);
    OutputStream outputStream = conn.getOutputStream();
//    out = new DataOutputStream(conn.getOutputStream());
    String jsonStr = "sss";
    outputStream.write(jsonStr.getBytes("utf-8"));
    out.flush();
    in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    String line;
    String result = "";
    while ((line = in.readLine()) != null) {
      result += line;
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws IOException {
    String s = "{\"msg\":\"1000\",\"success\":1}";
    GuiYangGpsResp guiYangGpsResp = JSON.parseObject(s,GuiYangGpsResp.class);
    System.out.println();
  }
}
