package cn.ellacat.tools.alarm.encrypt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjc133 on 2015/11/27.
 * Time: 0:17
 */
public class MixedText implements Serializable {
    private List<Long> ids = new ArrayList<>();
    private String csrf_token;
    private Long br;

    public MixedText() {

    }

    public MixedText(Long id) {
        this.ids.add(id);
        this.csrf_token = "";
//        this.lv = "-1";
//        this.tv = "-1";
        this.br = 320000L;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getCsrf_token() {
        return csrf_token;
    }

    public void setCsrf_token(String csrf_token) {
        this.csrf_token = csrf_token;
    }


    public Long getBr() {
        return br;
    }

    public void setBr(Long br) {
        this.br = br;
    }

    @Override
    public String toString() {
        return "MixedText{" +
                "ids=" + ids +
                ", csrf_token='" + csrf_token + '\'' +
                ", br=" + br +
                '}';
    }
}
