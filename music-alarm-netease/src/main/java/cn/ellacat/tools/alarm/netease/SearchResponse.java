package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class SearchResponse {
    private int code;
    private SearchResult result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SearchResult getResult() {
        return result;
    }

    public void setResult(SearchResult result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
