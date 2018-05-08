package todoitem;

import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class MemoManager {
    private static volatile MemoManager memoManager;    //volatile关键字保证不能同时对itemManager的读写
    private ArrayList<Memo> memoList = new ArrayList<>();

    private MemoManager() {

    }

    public static MemoManager getInstance() {
        if (memoManager == null) {  //降低同步锁发生概率
            synchronized (MemoManager.class) {  //添加同步锁，保证线程安全
                if (memoManager == null) {
                    memoManager = new MemoManager();
                }
            }
        }
        return memoManager;
    }

    public static void destroy() {
        memoManager = null;
    }

    public ArrayList<Memo> getItemsByStamp(TimeStamp from, TimeStamp to) {
        ArrayList<Memo> resultList = new ArrayList<>();
        for (Memo tmp : memoList) {
            if (tmp.isDuringTime(from, to)) {
                resultList.add(tmp);
            }
        }
        return resultList;
    }

    /**
     * what if add the same thing twice;
     * how about sorting them in an order;
     */
    public void addItem(Memo memo) {
        memoList.add(memo);
    }

    /**
     * what if delete the same thing twice;
     */
    public void deleteItem(Memo memo) {
        memoList.remove(memo);
    }

}
