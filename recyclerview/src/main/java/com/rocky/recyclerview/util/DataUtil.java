package com.rocky.recyclerview.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description : com.rocky.recyclerview
 *
 * @author : rocky
 * @Create Time : 2018/8/30 下午6:07
 * @Modified Time : 2018/8/30 下午6:07
 */
public class DataUtil {
    public static List<String> getData(int count) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getData());
        }
        return list;
    }

    public static String getData() {
        String[] arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer("#");
        for (int j = 0; j < 6; j++) {
            stringBuffer.append(arr[random.nextInt(arr.length)]);
        }
        String string = stringBuffer.toString();
        return string;
    }
}
