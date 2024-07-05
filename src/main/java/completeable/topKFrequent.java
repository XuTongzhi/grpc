package completeable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Title: topKFrequent
 * @Author XuTongzhi
 * @Description
 * @Date 2024/6/27 15:13
 */
public class topKFrequent {
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] -o2[1]);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (queue.size() == k){
                if (queue.peek()[1] < value){
                    queue.poll();
                    queue.offer(new int[]{key,value});
                }
            }else if (queue.size()<k){
                queue.offer(new int[]{key,value});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }
}
