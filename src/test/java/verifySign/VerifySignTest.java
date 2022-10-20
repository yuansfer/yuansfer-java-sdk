package verifySign;

import com.yuansfer.payment.utils.VerifyUtils;

import java.util.HashMap;
import java.util.Map;

public class VerifySignTest {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        String verifySign = "435d01c5d5fa88cfe8168fbfbf732519";
        String token = "5cbfb079f15b150122261c8537086d77a";

        //Get the parameters (verifySign excluded) and then put them into a map
        map.put("transactionNo", "305680500880311923");
        map.put("status", "success");
        map.put("amount", "0.01");
        map.put("currency","USD");
        map.put("settleCurrency", "USD");
        map.put("reference", "test1666247277861");
        map.put("time", "20221020142817");

        boolean flag = VerifyUtils.validateVerifySign(map, token, verifySign);

        System.out.println("flag:" + flag);
    }
}
