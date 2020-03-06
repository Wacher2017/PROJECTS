package chapter002;

import java.util.HashSet;

/**
 * 数组中重复的数字
 * @author Chunming_Wang
 */
public class ch0001 {

    /**
     * Parameters:
     *    numbers:     an array of integers
     *    length:      the length of array numbers
     *    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
     *                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
     *    这里要特别注意~返回任意重复的一个，赋值duplication[0]
     * Return value:       true if the input is valid, and there are some duplications in the array number
     *                     otherwise false
     */
    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if(nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if(nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * JDK 实现（HashSet）
     */
    public boolean duplicate1(int[] nums, int length, int[] duplication) {
        if(nums == null || length <= 0) {
            duplication[0] = -1;
            return false;
        } else  {
            HashSet<Integer> hs = new HashSet<>();
            for(int i = 0; i < length; i++) {
                if(!hs.add(nums[i])) {
                    duplication[0] = nums[i];
                    return true;
                }
            }
        }
        return false;
    }

}
