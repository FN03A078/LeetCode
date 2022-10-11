package com.LeetCode.com.shan.inter;

import java.util.*;

/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 10:19 2022/6/30
 */
public class Top200 {
    public static void main(String[] args) {
        int[] nums1 = {0, 1, 1, 2, 3, 3, 3, 4};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(findMedianSortedArraysBinary(nums1, nums2));
//        System.out.println(longestPalindromeCenter("ababa"));
//        System.out.println(isMatch("abccc", "abc*"));
//        System.out.println(intToRoman(13084));
//        System.out.println(letterCombinationsBFS("23"));
//        System.out.println(fourSumDfs(nums1, 0));
//        System.out.println(removeDuplicates(nums1));
//        System.out.println(divide(20, 3));
//        System.out.println("中文");
        System.out.println(testA(0));
    }

    /**
     * 1.两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {//暴力
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }


    /**
     * 2.两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int sum = n1 + n2 + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }


    /**
     * 3.无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {//队列求解
        if (s.length() == 0) return 0;
        int max = 0;
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            while (queue.contains(c)) {
                queue.poll();
            }
            queue.add(c);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public static int lengthOfLongestSubstringMap(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            //j的值只能增大不能减小，也就是说j只能往右移动，不能往左移动.
            // 所以下面代码中j取的是重复元素位置的下一个值和j这两个值的最大值
            if (map.containsKey(s.charAt(i))) j = Math.max(j, map.get(s.charAt(i)) + 1);
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * 4.寻找两个有序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index = 0, n1 = 0, n2 = 0;
        int[] temp = new int[nums1.length + nums2.length];
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] < nums2[n2]) {
                temp[index++] = nums1[n1++];
            } else {
                temp[index++] = nums2[n2++];
            }
        }
        while (n1 < nums1.length) {
            temp[index++] = nums1[n1++];
        }
        while (n2 < nums2.length) {
            temp[index++] = nums2[n2++];
        }
        int len = temp.length;
        return len % 2 != 0 ? temp[(len / 2)] : (temp[len / 2] + temp[len / 2 - 1]) / 2.0;
    }

    public static double findMedianSortedArraysBinary(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int totalLen = len1 + len2;
        if (totalLen % 2 == 1) {
            int mid = totalLen / 2;
            return getElement(nums1, nums2, mid + 1, len1, len2);
        } else {
            int mid1 = totalLen / 2 - 1, mid2 = totalLen / 2;
            return (getElement(nums1, nums2, mid1 + 1, len1, len2)
                    + getElement(nums1, nums2, mid2 + 1, len1, len2)) / 2.0;
        }
    }

    private static int getElement(int[] nums1, int[] nums2, int k, int len1, int len2) {
        int index1 = 0, index2 = 0;
        while (true) {
            if (index1 == len1) return nums2[index2 + k - 1];
            if (index2 == len2) return nums1[index1 + k - 1];
            if (k == 1) return Math.min(nums1[index1], nums2[index2]);
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    /**
     * 5.最长回文子串
     */
    public static String longestPalindrome(String s) {//DP
        if (s.length() < 2) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0, maxLen = 1;
        for (int right = 1; right < len; right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) != s.charAt(right)) continue;
                if (right == left) {
                    dp[left][right] = true;
                } else if (right - left <= 2) {
                    dp[left][right] = true;
                } else {
                    dp[left][right] = dp[left + 1][right - 1];
                }
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    start = left;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static String longestPalindromeCenter(String s) {
        if (s.length() < 2) return s;
        int start = 0, maxLen = 0;
        int len = s.length();
        for (int i = 0; i < len; ) {
            if (len - i <= maxLen / 2) break;
            int left = i, right = i;
            while (right < len - 1 && s.charAt(right + 1) == s.charAt(right))
                ++right;
            i = right + 1;
            while (right < len - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right;
                --left;
            }
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
        }
        return s.substring(start, start + maxLen);
    }


    public int reverse(int x) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Math.abs(x));
        try {
            int res = Integer.parseInt(stringBuilder.reverse().toString());
            return x > 0 ? res : -res;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int reverseVersion2(int x) {
        int res = 0;
        while (x != 0) {
            //个位
            int t = x % 10;
            //新数
            int newRes = res * 10 + t;
            //除十后不等说明溢出
            if ((newRes - t) / 10 != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }
        return res;
    }

    public int reverseVersion3(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    /**
     * 10.正则表达式匹配
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) dp[0][i + 1] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i][j + 1];
                    }
                    dp[i + 1][j + 1] = dp[i + 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 11.盛最多水的容器
     */
    public int maxArea(int[] height) {
        int maxArea = 0, area = 0, left = 0, right = height.length - 1;
        while (left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(area, maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /**
     * 12.整数转罗马数字
     */
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) break;
        }
        return roman.toString();
    }

    /**
     * 13.罗马数字转整数
     */
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = helper(s.charAt(i));
            if (i + 1 < s.length() && val < helper(s.charAt(i + 1))) {
                sum -= val;
            } else {
                sum += val;
            }
        }
        return sum;
    }

    private int helper(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    /**
     * 14.最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int len = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 15.三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) break;
            int left = i + 1, right = nums.length - 1, target = -nums[i];
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    /**
     * 16.最接近的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans)) ans = sum;
                if (sum > target)
                    right--;
                else if (sum < target)
                    left++;
                else
                    return ans;
            }
        }
        return ans;
    }


    /**
     * 17.电话号码的字母组合0
     */
    public List<String> letterCombinationsDFS(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        char[][] tab = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        dfs(res, 0, digits, tab, "");
        return res;
    }

    private void dfs(List<String> res, int index, String digits, char[][] tab, String path) {
        if (path.length() == digits.length()) {
            res.add(path);
            return;
        }
        char[] chars = tab[digits.charAt(index) - '2'];
        for (int i = 0; i < chars.length; i++) {
            dfs(res, index + 1, digits, tab, path + chars[i]);
        }
    }

    public static List<String> letterCombinationsBFS(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;
        char[][] tab = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        res.add("");
        while (res.peek().length() != digits.length()) {
            String remove = res.poll();
            char[] chars = tab[digits.charAt(remove.length()) - '2'];
            for (int i = 0; i < chars.length; i++) {
                res.add(remove + chars[i]);
            }
        }
        return res;
    }


    /**
     * 19.删除倒数第N的节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        int last = length(head) - n;
        if (last == 0) return head.next;
        for (int i = 0; i < last - 1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    private static int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }


    public static ListNode removeNthFromEndDouble(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    /**
     * 18.四数之和
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if ((long) nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) continue;

            for (int j = i + 1; j < len - 2; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[len - 2] + nums[len - 1] < target) continue;

                int left = j + 1, right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }


    static int cur = 0;

    public static List<List<Integer>> fourSumDfs(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, target, 0, res, new ArrayList<Integer>());
        return res;
    }

    private static void dfs(int[] nums, int target, int begin, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == 4) {
            if (target == cur) res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums.length - i < 4 - list.size()) return;
            if (begin != i && nums[i - 1] == nums[i]) continue;
            if (i < nums.length - 1 && cur + nums[i] + (3 - list.size()) * nums[i + 1] > target) return;
            if (i < nums.length - 1 && cur + nums[i] + (3 - list.size()) * nums[nums.length - 1] < target) continue;
            cur += nums[i];
            list.add(nums[i]);
            dfs(nums, target, i + 1, res, list);
            cur -= nums[i];
            list.remove(list.size() - 1);
        }
    }


    /**
     * 有效括号
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 合并k个升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) queue.add(cur.next);
        }
        return dummy.next;
    }

    /**
     * 删除重复项
     */
    public static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++)
            if (A[left] != A[right]) A[++left] = A[right];
        return ++left;
    }

    /**
     * 两数相除
     */
    public static int divide(int dividend, int divisor) {
        int sign = (dividend ^ divisor) >= 0 ? 1 : -1;
        long dividendTemp = Math.abs((long) dividend);
        long divisorTemp = Math.abs((long) divisor);
        long res = 0;
        while (dividendTemp >= divisorTemp) {
            long temp = divisorTemp;
            long times = 1;
            while (dividendTemp >= (temp << 1)) {
                temp <<= 1;
                times <<= 1;
            }
            dividendTemp -= temp;
            res += times;
        }
        res = sign > 0 ? res : -res;
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }

    public static int divideNew(int dividend, int divisor) {
        int sign = (dividend ^ divisor) >= 0 ? 1 : -1;
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        int res = 0;
        int threshold = Integer.MIN_VALUE >> 1;
        while (dividend <= divisor) {
            int temp = divisor;
            int times = 1;
            while (temp >= threshold && dividend <= (temp << 1)) {
                temp <<= 1;
                times <<= 1;
            }
            dividend -= temp;
            res += times;
        }
        if (res == Integer.MIN_VALUE && sign == 1) {
            return Integer.MAX_VALUE;
        }
        return sign < 0 ? res : -res;
    }

    public static int testA(int x) {
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0)
                x += 1;
            else
                x -= 1;
        }
        return x;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
