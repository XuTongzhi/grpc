package completeable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: lc4
 * @Author XuTongzhi
 * @Description
 * @Date 2024/6/28 9:30
 */
public class lc4 {
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
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (sum >= len-1) {
                return true;
            }
            if (sum >= i) {
                sum = Math.max(sum, nums[i]+i);
            }
        }
        return false;
    }

    public int jump(int[] nums) {
        int len = nums.length;
        int maxlen = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < len - 1; i++) {
            maxlen = Math.max(maxlen,nums[i] + i);
            if (i == end){
                step++;
                end = maxlen;
            }
        }
        return step;
    }
    public int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    public int rob(int[] nums) {
        int len = nums.length;
        int dp[] = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int dp[] = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < len; j++) {
                if (i >= coins[j]){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1:dp[amount];
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <=len ; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            max= Math.max(max,dp[i]);
        }
        return max;
    }
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0){
                int tmp = max;
                max = min;
                min = tmp;
            }
            max = Math.max(nums[i],nums[i] * max);
            min = Math.min(nums[i],nums[i] * min);

            res = Math.max(res,max);
        }
        return res;
    }
}
