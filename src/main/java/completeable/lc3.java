package completeable;

/**
 * @Title: lc3
 * @Author XuTongzhi
 * @Description
 * @Date 2024/6/27 16:08
 */
public class lc3 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (sum >= len) {
                return true;
            }
            if (sum >= i) {
                sum = Math.max(sum, nums[i]);
            }
        }
        return false;
    }
}
