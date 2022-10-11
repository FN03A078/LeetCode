package com.LeetCode.com.shan.DoubleIdentifier;


import java.util.*;

/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 10:42 2022/6/18
 */
public class DoubleIdentifier {


    public static void main(String[] args) {
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        System.out.println(minWindow2(s, t));


        //System.out.println(lengthOfLongestSubstring(s));
        //int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //System.out.println(trap(arr));
        //System.out.println(isPalindrome(s));

//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(2);
//        ListNode listNode2 = new ListNode(4);
//        ListNode listNode3 = new ListNode(5);
//        listNode.next = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        ListNode res = rotateRight(listNode, 2);
//        while (res != null) {
//            System.out.println(res.val);
//            res = res.next;
//        }

    }

    /**
     * 398.给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;
        Queue<Character> queue = new LinkedList<>();
        int max = 0;
        for (char c : s.toCharArray()) {
            while (queue.contains(c)) {
                queue.poll();
            }
            queue.add(c);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    /**
     * 447.给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        int len = 1;
        while (fast.next != null) {
            len++;
            fast = fast.next;
        }
        fast.next = head;
        int step = len - k % len;
        while (step-- > 1) {
            slow = slow.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        return temp;
    }

    /**
     * 396.给你 n 个非负整数 a1，a2，. . .，an，每个数代表坐标中的一个点 (i , a i) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i , a i) 和 (i , 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     */
    public int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1, area = 0;
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
     * 397.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int water = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                water += leftMax - height[left++];
            } else {
                water += rightMax - height[right--];
            }
        }
        return water;
    }

    /**
     * 497.给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小 写。
     */
    public static boolean isPalindrome(String s) {
        String res = s.replaceAll("\\pP|\\pS", "").replaceAll(" ", "").toLowerCase();
        int left = 0, right = res.length() - 1;
        while (left < right) {
            char l = res.charAt(left);
            char r = res.charAt(right);
            if (l == r) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 514.
     */
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int left = 0, right = 0;
        int[] map = new int[26];
        int maxSameCount = 0, maxWindow = 0;
        for (; right < len; right++) {
            maxSameCount = Math.max(maxSameCount, ++map[s.charAt(right) - 'A']);
            if (k + maxSameCount < right - left + 1) {
                map[s.charAt(left) - 'A']--;
                left++;
            } else {
                maxWindow = Math.max(maxWindow, right - left + 1);
            }
        }
        return maxWindow;
    }

    /**
     * 527.给定两个数组，编写一个函数来计算它们的交集。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                if (!list.contains(nums1[i])) list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }

    /**
     * 538.输入一个正整数target，输出所有和为target的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     */
    //数学方法
    //假如target是两个连续数字的和，那么这个序列的首项就是( target -1) /2。
    // 假如target是三个连续数字的和，那么这个序列的首项就是( target -1-2) /3。
    // 假如target是四个连续数字的和，那么这个序列的首项就是( target -1-2-3) /4。
    public int[][] findContinuousSequence(int target) {
        ArrayList<Integer> res = new ArrayList<>();
        target--;
        for (int n = 2; target > 0; n++) {
            if (target % n == 0) {
                int[] arr = new int[n];
                int start = target / n;
                for (int k = 0; k < n; k++) {
                    arr[k] = target + k;
                }
                target -= n;
            }
        }
        Collections.reverse(res);
        return res.toArray(new int[res.size()][]);
    }

    //滑动窗口
    public int[][] findContinuousSequence2(int target) {
        int left = 1, right = 2;
        int sum = left + right;
        List<int[]> res = new ArrayList<>();
        while (left <= target / 2) {
            if (sum < target) {
                sum += ++right;
            } else if (sum > target) {
                sum -= left++;
            } else {
                int[] arr = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    arr[i - left] = i;
                }
                res.add(arr);
                sum -= left++;
                sum += ++right;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 539.给你一个有序数组nums ，请你原地删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用O(1)额外空间的条件下完成。
     */
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++) {
            if (A[left] != A[right]) A[++left] = A[right];
        }
        return ++left;
    }

    /**
     * 542.给你一个字符串s、一个字符串t。
     * 返回s中涵盖t所有字符的最小子串。如果s中不存在涵盖t所有字符的子串，则返回空字符串" "。
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int strStart = 0;
        int windowLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (map.containsKey(rightChar))
                map.put(rightChar, map.getOrDefault(rightChar, 0) - 1);
            right++;
            while (checkMap(map)) {
                windowLen = Math.min(windowLen, right - left);
                strStart = left;

                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar))
                    map.put(leftChar, map.getOrDefault(leftChar, 0) + 1);
                left++;
            }
        }
        if (windowLen != Integer.MAX_VALUE)
            return s.substring(strStart, strStart + windowLen);
        return "";

    }

    private static boolean checkMap(Map<Character, Integer> map) {
        for (Integer value : map.values()) {
            if (value > 0) return false;
        }
        return true;
    }


    public static String minWindow2(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int n = chars.length, m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) hash[ch]--;

        String res = "";
        for (int right = 0, left = 0, cnt = 0; right < n; right++) {
            hash[chars[right]]++;
            if (hash[chars[right]] <= 0) cnt++;
            while (cnt == m && hash[chars[left]] > 0) hash[chars[left++]]--;
            if (cnt == m)
                if (res.equals("") || res.length() > right - left + 1)
                    res = s.substring(left, right + 1);
        }
        return res;
    }


    /**
     * 549.几张卡牌排成一行，每张卡牌都有一个对应的点数。点数由整数数组cardPoint s给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿k张卡牌。
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     * 给你一个整数数组cardPoint s和整数k，请你返回可以获得的最大点数。
     */

    public int maxScore(int[] cardPoints, int k) {
        int maxWindow = 0, length = cardPoints.length;
        for (int i = 0; i < k; i++)
            maxWindow += cardPoints[i];
        int curWindow = maxWindow;
        for (int i = length - 1; i >= length - k; i--) {
            curWindow -= cardPoints[k - (length - i)];
            curWindow += cardPoints[i];
            maxWindow = Math.max(maxWindow, curWindow);
        }
        return maxWindow;
    }


    /**
     * 597.给定一个非空字符串s，最多删除一个字符。判断是否能成为回文字符串。
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right)
                        || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }
        return true;
    }

    /**
     * 608.给定一个整数数组和一个整数 k ，判断数组中是否存在两个不同的索引 i 和 j ，
     * 使得nums[i]=nums[j]，并且 i 和 j 的差的绝对值至多为 k。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                if (i - index <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * 613.给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c，使得 a+b+c=0？请你找出所有和为0且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) continue;
            if (num[i] > 0) break;
            int left = i + 1;
            int right = num.length - 1;
            int target = -num[i];
            while (left < right) {
                int sum = num[left] + num[right];
                if (sum == target) {
                    res.add(Arrays.asList(num[i], num[left], num[right]));
                    while (left < right && num[left] == num[left + 1]) left++;
                    while (left < right && num[right] == num[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right++;
                }
            }
        }
        return res;
    }

    /**
     * 615.给你两个非空的链表，表示两个非负的整数。
     * 它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字0之外，这两个数都不会以0开头。
     */
    public ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2) {
        ListNode dummy = new ListNode(0);
        ListNode preNode = dummy;
        int carry = 0;
        while (listNode1 != null || listNode2 != null || carry != 0) {
            int sum = carry;
            if (listNode1 != null) {
                sum += listNode1.val;
                listNode1 = listNode1.next;
            }
            if (listNode2 != null) {
                sum += listNode2.val;
                listNode2 = listNode2.next;
            }
            preNode.next = new ListNode(sum % 10);
            carry = sum / 10;
            preNode = preNode.next;
        }
        return dummy.next;
    }

    /**
     * 629.给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数0、1和2分别表示红色、白色和蓝色。
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int index = 0;
        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, left++, index++);
            } else if (nums[index] == 1) {
                index++;
            } else if (nums[index] == 2) {
                swap(nums, right--, index);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
