package mqtt;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @description: base64使用
 * @author: frank.wu
 * @create: 2021-09-23
 */
public class TestBase64 {
    public static void main1(String[] args) throws Exception {
        String json = "{\"dataKey\":\"5105SZXWBG54_PTZTYYBB\",\"identifier\":\"91440101MA9Y1J5M30\",\"symbol\":\"5105SZXWBG54_PTZTYYBB\",\"companyId\":\"5105SZXWBG54\",\"pauseTime\":\"20210701\",\"flag\":3,\"pauseReason\":\"未知原因\",\"companyName\":\"四川神州行网约车服务有限公司广州分公司\",\"updateTime\":20210804170000,\"ipcType\":\"PTZTYYBB\"}";

        String base64Code =encodeBase64File("/Users/admin/Downloads/合同.pdf");
        Map map = JSON.parseObject(json, Map.class);
        map.put("pauseReasonPhoto",base64Code);

        String s = JSON.toJSONString(map);
        System.out.println(JSON.toJSONString(map));

    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("510002013"));
        String json = "{\"companyId\":\"5105SZXWBG54\",\"key\":\"NrqOJ7Jh4aSPBRUj\",\"platform\":\"SZXWBG54\",\"ipcType\":\"ACCESS\"}";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        System.out.println(length);
        String s = DigestUtils.md5Hex(bytes);
        System.out.println(s);
    }


    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
}
