import com.jcduhdt.sharp.codeposition.data.Data;
import com.jcduhdt.sharp.codeposition.dialog.NoticeDialog;
import com.jcduhdt.sharp.codeposition.util.RegexUtil;

public class TestRegexUtil {
    static String content = "[INFO][2021-12-28T19:14:32.821+0800][..ji.com/guarana/sharp/model/user_service.Register./login.go:57] _com_common_info||ctx_format=unset||func=getSendMQDelay||datetime=2021-09-25 18:00:00||area_id=55000425||regularRule={\"type\":2,\"cal_time\":\"-1\"}||target_tsp=1632600000||delay=-8090072||[:656841]\n";

    public static void main(String[] args) {
        try {
            Data data = RegexUtil.fileDataRegex("sharp", content);
            System.out.println(data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
