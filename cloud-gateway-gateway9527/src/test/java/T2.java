import java.time.ZonedDateTime;

/**
 * @author yun
 * @version 1.0
 * @date 2020-11-13 14:14
 */
public class T2 {
    public static void main(String[] args) {
        // 默认时区
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime); // 2020-11-13T14:15:13.028+08:00[Asia/Shanghai]
    }
}
