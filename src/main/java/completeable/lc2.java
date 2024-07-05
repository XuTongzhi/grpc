package completeable;

/**
 * @Title: lc2
 * @Author XuTongzhi
 * @Description
 * @Date 2024/6/27 15:49
 */
public class lc2 {

    public static void main(String[] args){
        System.out.println();
    }
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int len = prices.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (prices[i] < min){
                min = prices[i];
            }
            res = Math.max(res,prices[i] - min);
        }
        return res;
    }
}
