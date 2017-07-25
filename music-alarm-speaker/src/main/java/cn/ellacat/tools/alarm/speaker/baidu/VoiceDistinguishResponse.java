package cn.ellacat.tools.alarm.speaker.baidu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wjc133
 */
public class VoiceDistinguishResponse {
    @SerializedName("corpus_no")
    private String corpusNumber;
    @SerializedName("err_msg")
    private String msg;
    @SerializedName("err_no")
    private Integer code;
    private List<String> result;
    private String sn;

    public String getCorpusNumber() {
        return corpusNumber;
    }

    public void setCorpusNumber(String corpusNumber) {
        this.corpusNumber = corpusNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "VoiceDistinguishResponse{" +
                "corpusNumber='" + corpusNumber + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", result=" + result +
                ", sn='" + sn + '\'' +
                '}';
    }
}
