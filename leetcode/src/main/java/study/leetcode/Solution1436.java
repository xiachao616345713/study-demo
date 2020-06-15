package study.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiachao
 * @date 2020/06/10
 */
public class Solution1436 {

    public String destCity(List<List<String>> paths) {
        Map<String, Integer> map = new HashMap<>();
        String start;
        String end;
        for (List<String> path : paths) {
            start = path.get(0);
            end = path.get(1);
            // start
            Integer num = map.getOrDefault(start, 0);
            map.put(start, num + 1);
            // end
            map.putIfAbsent(end, 0);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                return entry.getKey();
            }
        }
        return null;
    }
}
