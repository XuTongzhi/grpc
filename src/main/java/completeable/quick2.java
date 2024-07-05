package completeable;

/**
 * @Title: quick2
 * @Author XuTongzhi
 * @Description
 * @Date 2024/6/27 11:29
 */
public class quick2 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        quick2 quick2 = new quick2();
        quick2.findKthLargest(nums,k);
    }
    int quickselect(int[] nums, int left, int right, int k) {
        if (left == right){
            return nums[k];
        }
        int key = nums[left];
        int l = left - 1;
        int r = right + 1;
        while (l<r){
            do l++; while (nums[l] < key);
            do r--; while (nums[r] > key);
            if (l<r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
        }
        if (k <=l){
            return quickselect(nums,left,l,k);
        }else {
            return quickselect(nums,l+1,right,k);
        }
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }
}
