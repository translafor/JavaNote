package testresp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 返回体
 * @author: frank.wu
 * @create: 2021-09-09
 */
@Data
@Builder
@AllArgsConstructor
public class GuiYangGpsResp {
    private String msg;
    private Integer success;
}
