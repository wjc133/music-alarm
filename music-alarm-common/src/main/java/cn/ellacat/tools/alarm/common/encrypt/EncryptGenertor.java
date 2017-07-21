package cn.ellacat.tools.alarm.common.encrypt;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjc133 on 2015/11/27.
 * Time: 0:10
 */
public class EncryptGenertor {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptGenertor.class);

    private static final String bhv = "010001";
    private static final String[] bHds = {"00e0b509f6259df8642", "dbc35662901477df22677",
            "ec152b5ff68ace615bb7b725", "152b3ab17a876aea8a5aa7",
            "6d2e417629ec4ee341f56135", "fccf695280104e0312ecbd",
            "a92557c93870114af6c9d05", "c4f7f0c3685b7a46bee2",
            "55932575cce10b424d813", "cfe4875d3e82047b97ddef5",
            "2741d546b8e289dc693", "5b3ece0462db0a22b8e7"};
    private static final String bHd = StringUtils.join(bHds);
    private static final String bHu = "0CoJUm6Qyw8W8jud";

    public static EncryptResult getResult(long id) {
        MixedText mixedText = new MixedText(id);
        Gson gson = GsonCreator.getInstance();
        String text = gson.toJson(mixedText);
        LOGGER.info("id={}, MixedText >>> {}", id, text);
        return Encrypt.aesRsaEncrypt(text, bhv, bHd, bHu);
    }
}
