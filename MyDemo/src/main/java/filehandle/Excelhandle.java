package filehandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-05
 */
public class Excelhandle {

    private static String readUrlFile(String url) {
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            URL httpUrl = new URL(url);
            String line = null;
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);
            connection.setReadTimeout(10 * 1000);
            connection.connect();

            connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));


            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    public static void main(String[] args) throws Exception {
        String url = "https://blog.csdn.net/qiaobing1226/article/details/122867476";
        String content = readUrlFile(url);
        System.out.println(content);
    }
}
