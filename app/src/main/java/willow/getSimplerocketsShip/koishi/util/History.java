package willow.getSimplerocketsShip.koishi.util;
import java.util.*;

public class History
{
	private List<HistoryBase> historyList;

    public List<HistoryBase> getData() {
        return historyList;
    }

    public void setData(List<HistoryBase> data) {
        this.historyList = data;
    }

    public static class HistoryBase {
        /**
         * type : true; 表示存档类型，true为火箭，false为沙盒
         * id : 1 表示存档id
         * time : 1 表示获取的时间
         */

        private boolean type;
        private String id;
        private String time;

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
		}
}
