package com.example.demo.mbextend;

/**
 * 顺序生成别名
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/7 9:58
 */
public class AliasWorker {
    private static final char[] charSource = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};
    private static final ThreadLocal<Integer> count;
    private static final int one = (int)(Math.pow(2,5) - 1);

    static {
        count = new InheritableThreadLocal<>();
        count.set(1);
    }

    public synchronized static String getAlias(){
        int count = AliasWorker.count.get();
        if(count==32767){
            AliasWorker.count.set(1);
        }else{
            AliasWorker.count.set(count+1);
        }
        return "t" + charSource[count >> 10 & one ] +
                charSource[count >> 5 & one ] +
                charSource[count & one];
    }
}
