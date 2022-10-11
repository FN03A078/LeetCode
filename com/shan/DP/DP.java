package com.LeetCode.com.shan.DP;

import java.util.Arrays;
import java.util.List;

public class DP {
    public static void main(String[] args) {
        //System.out.println(isMatch("abc c c", "a*b*c"));
        int[] num1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] num2 = {1, 0, 1, 0, 1};
        System.out.println(isMatchII("abccc", "a*b*c"));
    }

    /*
     �����������ַ�����s1="people"��s2="eplm"������Ҫ��������Ĺ����Ӵ�������һ�۾��ܿ�
     �����ǵ�������Ӵ���"pl"��������2��������ַ����ر𳤵Ļ��Ͳ�������ô�۲��ˡ�
     */
    public static int maxLong(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;
        int max = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    //��������������dp�����ֵֻ������һ�е�ֵ�����Կ���ʹ��һά����
    public static int maxLongSingleArray(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;
        int max = 0;
        int[] dp = new int[str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = str2.length(); j >= 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

    /*
    �����������
    */
    public static int maxLongSon(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;
        int max = 0;
        //ʹ��һλ����
        int[] dp = new int[str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            int last = 0;
            for (int j = 1; j <= str2.length(); j++) {
                //�ַ���ͬʱ�����������Ͻǵ�ֵ��
                //����ֵ������һ������ʱ���滻������ʹ��temp��¼
                int temp = dp[j];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = last + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                last = temp;
            }
        }
        return dp[str2.length()];
    }


    /*
    ������������word1��word2,���������� word1 ת���� word2 ��ʹ�õ����ٲ�������
    ����Զ�һ�����ʽ����������ֲ����� ����һ���ַ� ɾ��һ���ַ� �滻һ���ַ�
    ���룺word1 ="horse", word2 = "ros"
    �����3
    ���ͣ� horse -> rorse (��'h'�滻Ϊ'r')
          rorse -> rose (ɾ��'r')
          rose -> ros (ɾ��'e')
    */
    public static int minDistance(String word1, String word2) {
        if (word1.length() * word2.length() == 0) return word1.length() + word2.length();
        //int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int[] dp = new int[word2.length() + 1];
        //for (int i = 0; i < word1.length(); i++) dp[i][0] = i;
        //for (int i = 0; i < word2.length(); i++) dp[0][i] = i;
        for (int i = 1; i <= word2.length(); i++) dp[i] = i;
//        for (int i = 1; i <= word1.length(); i++) {
//            for (int j = 1; j <= word2.length(); j++) {
//                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else {
//                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;//�ֱ�Ϊɾ���ģ���
//                }
//            }
//        }
        //last��¼[i-1][j-1],�����Ͻ�λ��
        int last;
        for (int i = 1; i <= word1.length(); i++) {
            last = dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = last;
                } else {
                    dp[j] = Math.min(Math.min(dp[j], last), dp[j - 1]) + 1;//�ֱ�Ϊɾ���ģ���
                }
                last = temp;
            }
        }
        return dp[word2.length()];
    }


    /*
    ����һ���ַ���(s)��һ���ַ�ģʽ(p),ʵ��һ��֧��'?'��'*'��ͨ���ƥ�䡣
    */
    public static boolean isMatchI(String s, String p) {
        if (s.length() != 0 && p.length() == 0) return false;
        int sLen = s.length(), pLen = p.length();
        //boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        boolean[] dp = new boolean[pLen + 1];
        dp[0] = true;
        //�߽�������ʼ��
//        for (int j = 1; j <= pLen && dp[0][j - 1]; j++) {//�˴����Ͻ�ȡpȥƥ����ַ�����ֻ��p��һ�ν�ȡΪ*�Ż�ɹ�
//        ����һ��ƥ��ʧ�ܣ����������ʧ�ܣ�ֱ������
//            dp[0][j] = p.charAt(j - 1) == '*';
//        }
        for (int j = 1; j <= pLen && dp[j - 1]; j++) {
            dp[j] = p.charAt(j - 1) == '*';
        }
//        for (int i = 1; i <= sLen; i++) {
//            for (int j = 1; j <= pLen; j++) {
//                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else if (p.charAt(j - 1) == '*') {
//                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                }
//            }
//        }
        for (int i = 1; i <= sLen; i++) {
            boolean last = i == 1;
            for (int j = 1; j <= pLen; j++) {
                boolean temp = dp[j];
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[j] = last;
                } else if (p.charAt(j - 1) == '*') {
                    dp[j] = dp[j - 1] || dp[j];
                } else {//��ά�����в����˲����ԭ����Ĭ��ֵΪfalse������û�и��ǲ���
                    // �˴����˲�������Ϊ����һ����dp[j]���ܱ���Ϊtrue��������ƥ��ʧ�ܺ󣬾ͱ��뽫������Ϊfalse
                    dp[j] = false;
                }
                last = temp;
            }
        }
        return dp[pLen];
    }

    /*
     * �������������� A �� B ���������������й����ġ��������������ĳ��ȡ�
     * ע�⣡�� ���� 123��32 �������������鳤��Ϊ0����
     * */
    public static int findLength(int[] A, int[] B) {
        int[] dp = new int[B.length + 1];
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            int last = 0;
            for (int j = 1; j <= B.length; j++) {
                int temp = dp[j];
                if (A[i - 1] == B[j - 1]) {
                    dp[j] = last + 1;
                } else {
                    dp[j] = 0;
                }
                last = temp;
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }


    /*
     * һ��������λ��һ�� m x n ��������Ͻǣ���ʼ������ͼ�б��Ϊ��Start������ ������ÿ��ֻ�����»��������ƶ�һ����
     * ��������ͼ�ﵽ��������½ǣ�����ͼ�б��Ϊ�� Finish������
     * */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //��һ�ж���1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        //��һ�ж���1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //�����ǵ��ƹ�ʽ
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }


    /*
     * һ��������λ��һ��mxn��������Ͻǣ���ʼ������ͼ�б��Ϊ��Start������
     * ������ÿ��ֻ�����»��������ƶ�һ������������ͼ�ﵽ��������½ǣ�����ͼ�б��Ϊ�� Finish������
     * ���ڿ������������ϰ����ô�����Ͻǵ����½ǽ����ж�������ͬ��·����
     * */
    public static int uniquePathsII(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //��һ�г�ʼ��
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) dp[i][0] = 1;
            else break;
        }
        //��һ�г�ʼ��
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) dp[0][i] = 1;
            else break;
        }
        //���ƹ�ʽ
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }


    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        //��ʼ��
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < n; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int length = prices.length;
        //int[][] dp = new int[length][2];
        //�߽�����
        int noHold = 0;
        int hold = -prices[0];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            //���ƹ�ʽ
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, -prices[i]);
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        //��ӹ���ɣ����϶�������û���й�Ʊ����Ż����Ҳ��������ȥ��
        //return dp[length - 1][0];
        return noHold;
    }


    public static int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int noHold = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        return noHold;
    }


    public int maxProfitIII(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;
        int noHold = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            noHold = Math.max(noHold, hold + prices[i] - fee);
            hold = Math.max(hold, noHold - prices[i]);
        }
        return noHold;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        //int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        int[] dp = new int[triangle.size() + 1];
//        for (int i = triangle.size() - 1; i >= 0; i--) {
//            for (int j = 0; j < triangle.get(i).size(); j++) {
//                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
//            }
//        }
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.max(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        //return dp[0][0];
        return dp[0];
    }


    public static int massage(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //int[][] dp = new int[nums.length][2];
        int yes = nums[0];//��
        int no = 0;//����
//        dp[0][0] = 0;
//        dp[0][1] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
//            dp[i][1] = dp[i - 1][0] + nums[i];
//        }
        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(yes, no);
            yes = no + nums[i];
            no = temp;
        }
        //return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
        return Math.max(yes, no);
    }


    public static int maxSubArray(int[] num) {
        //int[] dp = new int[num.length];
        int cur = num[0];
        int max = cur;
        //dp[0] = num[0];
        for (int i = 1; i < num.length; i++) {
            //dp[i] = Math.max(dp[i - 1], 0) + num[i];
            //max = Math.max(max, dp[i]);
            cur = Math.max(cur, 0) + num[i];
            max = Math.max(max, cur);
        }
        return max;
    }


    public static boolean isMatchII(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1];
                    }
                }
            }
        }
        return dp[m][n];
    }


    public static String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0, maxLen = 0;
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


    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        //�Զ���Ƚ��� �Զ�ά�����Կ��Ϊ�������������򣬵������ͬ��ʱ�򣬶Ը߶Ƚ�������
        Arrays.sort(envelopes, (int[] arr1, int[] arr2) -> {
            if (arr1[0] == arr2[0])
                return arr2[1] - arr1[1];
            else
                return arr1[0] - arr2[0];
        });
        return lengthOfLIS(envelopes);
    }

    private static int lengthOfLIS(int[][] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i][1] > nums[j][1]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int longestPalindromeSubSeq(String s) {
        if (s.length() < 2) return 1;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][len - 1];
    }

}
