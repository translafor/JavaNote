//package TestResp;
//
//import com.alibaba.fastjson.JSON;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @description: s
// * @author: frank.wu
// * @create: 2021-10-21
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class ReportPoliceConfig {
//    private List<String> reportReason;
//
//    private Integer reportInterval;
//
//    public static void main(String[] args) {
//        GuiYangGpsResp.builder().success(1)
//                .msg()
//
//
//        Integer a = 1919;
//        Integer b = 1919;
//        System.out.println(a.equals(b));
//        String s = String.valueOf(1 / 100);
//        System.out.println(s);
//
//        ReportPoliceConfig reportPoliceConfig = new ReportPoliceConfig();
//        reportPoliceConfig.setReportReason(Arrays.asList("遇到交通事故","遇到性骚扰或性侵","肢体冲突造成人身伤害","被限制人身自由","遭遇其他人身伤害"));
//        reportPoliceConfig.setReportInterval(60);
//
//        System.out.println(JSON.toJSONString(reportPoliceConfig));
//
//    }
//}
