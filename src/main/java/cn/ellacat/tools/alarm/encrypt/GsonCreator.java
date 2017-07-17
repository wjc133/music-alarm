package cn.ellacat.tools.alarm.encrypt;

import com.google.gson.Gson;

/**
 * Created by wjc133
 * Date: 2015/11/27
 * Time: 18:37
 */
public class GsonCreator {
    private static Gson gson = new Gson();

    private GsonCreator() {

    }

    public static Gson getInstance() {
        return gson;
    }
}
