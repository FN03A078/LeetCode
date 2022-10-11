package com.LeetCode.com.shan.BackTrack;

import java.util.*;

/* ����ģ��
    private void backtrack("ԭʼ����") {
        //��ֹ����(�ݹ����Ҫ����ֹ����)
        if ("��ֹ����") {
            //һЩ�߼����������п��ޣ������������
            return;
        }

        for (int i = "forѭ����ʼ�Ĳ���"; i < "forѭ�������Ĳ���"; i++) {
            //һЩ�߼����������п��ޣ������������

            //����ѡ��

            //�ݹ�
            backtrack("�µĲ���");
            //һЩ�߼����������п��ޣ������������

            //����ѡ��
        }
    }
*/
public class Exercise {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        int target = 4;
        int n = 2;

        System.out.println(subsets(nums));

        String s = "catsanddog";
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
//        wordDict.add("and");
//        wordDict.add("sand");
//        wordDict.add("dog");
//        //List<String> res = new ArrayList<>();
//        List<List<Integer>> res = new ArrayList<>();
//        TreeNode root = new TreeNode(5);
//        TreeNode node1 = new TreeNode(4);
//        TreeNode node2 = new TreeNode(8);
//        TreeNode node3 = new TreeNode(11);
//        TreeNode node4 = new TreeNode(7);
//        TreeNode node5 = new TreeNode(2);
//        TreeNode node6 = new TreeNode(13);
//        TreeNode node7 = new TreeNode(4);
//        TreeNode node8 = new TreeNode(5);
//        TreeNode node9 = new TreeNode(1);
//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node3.left = node4;
//        node3.right = node5;
//        node2.left = node6;
//        node2.right = node7;
//        node7.left = node8;
//        node7.right = node9;
//        res = pathSum(root, 22);
//        System.out.println(res);
        System.out.println(res);
    }

    /*�����㷨���������
    ����һ�����ظ�Ԫ�ص�����candidates��һ��Ŀ����target,�ҳ�candidates�����п���ʹ���ֺ�Ϊtarget����ϡ�
    ����: candidates = [2, 3, 6, 7], target = 7
    ���: [[7],[2, 2, 3]]
    */
    //ÿѡ��һ�����������targetС��ѡ�����������������target��ȥ����������targetΪ�㼴����ֹ������
    //**����**���Σ��������֣�����Ŀ������������������ �⼯���ܰ����ظ�����ϡ� candidates �е�ÿ��������ÿ�������ֻ��ʹ��һ�Ρ�
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //���Σ�Arrays.sort(candidates);
        combinationSumBackTrack(candidates, target, res, new ArrayList<Integer>(),0);
        return res;
    }

    private static void combinationSumBackTrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int start) {
        //��ֹ����
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //�������N�������ӽڵ㣬
        for (int i = start; i < candidates.length; i++) {
            //�߼��ж�
            if (target < candidates[i]) continue;
            //���Σ���ȥ�أ�
            // if (i > start && candidates[i] == candidates[i - 1])
            //      continue;
            //����ѡ��
            list.add(candidates[i]);
            //�ݹ�
            //���Σ�combinationSumBackTrack(candidates, target - candidates[i], res, list, i + 1);
            combinationSumBackTrack(candidates, target - candidates[i], res, list, i);
            //����ѡ��
            list.remove(list.size() - 1);
        }
    }


    /*
    �������2
    ������������ n �� k������ 1 . . . n �����п��ܵ� k ��������ϡ�
    ����: n = 4, k = 2
    ���: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
    */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineBackTrack(n, k, res, new ArrayList<Integer>(), 1);
        return res;
    }

    private static void combineBackTrack(int n, int k, List<List<Integer>> res, List<Integer> list, int index) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            //���ֲ����ظ�������
            if (list.contains(i)) continue;
            list.add(i);
            combineBackTrack(n, k, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /*
    ȫ����
    ����һ�������ظ����ֵ�����nums�����������п��ܵ�ȫ���С�����԰�����˳�򷵻ش𰸡�
    ���룺nums = [ 1 , 2 , 3 ]
    �����[ [ 1 , 2 , 3 ] , [ 1 , 3 , 2 ] , [ 2 , 1 , 3 ] , [ 2 , 3 , 1 ] , [ 3 , 1 , 2 ] , [ 3 , 2 , 1 ] ]
    */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteBackTrack(nums, res, new ArrayList<Integer>());
        return res;
    }

    private static void permuteBackTrack(int[] nums, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            permuteBackTrack(nums, res, list);
            list.remove(list.size() - 1);
        }
    }


    /*
    ����һ���ɰ����ظ����ֵ�����nums��������˳�򷵻����в��ظ���ȫ���С�
    ����: nums = [1, 1, 2]
    ���: [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteUniqueBackTrack(nums, res, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    /**
     * ���ڰ����ظ����֣�������ظ���������Ҫ���м�֦����
     *
     * @param nums
     * @param res
     * @param list
     * @param used boolean���飬���ڱ�������Ƿ�ѡ��
     */
    private static void permuteUniqueBackTrack(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //���ֱ�ʹ�ù�������
            if (used[i]) continue;
            //��֦�������ǰ����ǰһ����һ��������ǰһ������δ���ʹ���������
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            //���Ϊ�ѷ���
            used[i] = true;
            list.add(nums[i]);
            permuteUniqueBackTrack(nums, res, list, used);
            //��������
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }


    /*
    ����һ���ַ�������ӡ�����ַ������ַ����������С�
    �����������˳�򷵻�����ַ������飬�����治�����ظ�Ԫ�ء�
    ����: s = "abc"
    �����["abc", "acb", "bac", "bca", "cab", "cba"]
    */
    public static String[] permutation(String s) {
        Set<String> strings = new HashSet<>();
        permutationBackTrack(s, strings, "", new boolean[s.length()]);
        return strings.toArray(new String[strings.size()]);
    }

    private static void permutationBackTrack(String s, Set<String> strings, String path, boolean[] used) {
        if (path.length() == s.length()) {
            strings.add(path);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (used[i]) continue;
            used[i] = true;
            permutationBackTrack(s, strings, path + s.charAt(i), used);
            used[i] = false;
        }
    }


    /*
    ����������N�����ǰ��κ�˳�򣨰���ԭʼ˳�򣩽�������������
    ע����ǰ�����ֲ���Ϊ�㡣������ǿ���ͨ��������ʽ�õ�2���ݣ�����true�����򣬷���false��
    ˼·�ο����ظ����ֵ�ȫ����
    */
    public static boolean reorderedPowerOfTwo(int n) {
        char[] charNum = String.valueOf(n).toCharArray();
        Arrays.sort(charNum);
        return reorderedPowerOfTwoBackTrack(0, charNum, 0, new boolean[charNum.length]);
    }

    private static boolean reorderedPowerOfTwoBackTrack(int index, char[] charNum, int num, boolean[] used) {
        if (index == charNum.length) {
            return isPowerOfTwo(num);
        }
        for (int i = 0; i < charNum.length; i++) {
            //����ǰ���㣬ע����������Ϊ[i] == 0
            if (num == 0 && charNum[i] == '0') continue;
            //��֦�����ظ�����
            if (used[i]) continue;
            //��֦
            if (i > 0 && charNum[i] == charNum[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            if (reorderedPowerOfTwoBackTrack(index + 1, charNum, num * 10 + charNum[i] - '0', used))
                return true;
            used[i] = false;
        }
        return false;
    }

    //�ж��Ƿ�Ϊ2����
    private static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }


    /*
    ����ΪСŮ��ӵ�л�����Ŀ��ÿ��������䳤�ȱ�ʾ�������Ϊ�Ƿ��������еĻ��ƴ�������Ρ�
    ����: [1, 1, 2, 2, 2]
    ���: true
    */
    public static boolean makeSquare(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        //�������Ͳ�Ϊ4�ı�������͵���0�������鳤��С���ģ�ֱ�ӷ���false
        if (nums.length < 4 || sum % 4 != 0 || sum == 0) return false;
        //sum >> 2��ʾ�߳�
        Arrays.sort(nums);//�Ż�
        return makeSquareBackTrackFix(nums, sum >> 2, new int[4], nums.length - 1);
    }

    /**
     * @param nums
     * @param len   �߳�
     * @param size  ����Ϊ4�����飬��űߵĳ���
     * @param index ���ʵ��ڼ�������
     * @return
     */
    private static boolean makeSquareBackTrack(int[] nums, int len, int[] size, int index) {
        //��ֹ���������������ѷ���
        if (index >= nums.length ) {
            //����ı���ȷ���true
            if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                return true;
            //����false
            return false;
        }
        //����size����
        for (int i = 0; i < size.length; i++) {
            //�������ǰ������size[i]�������ϣ��䳤�ȴ��ڱ߳�����������
            if (size[i] + nums[index] > len) continue;
            size[i] += nums[index];
            //�ٷ���һ�����������տ��Գ�Ϊ�����Σ�ֱ�ӷ���true
            if (makeSquareBackTrack(nums, len, size, index + 1)) return true;
            //��������
            size[i] -= nums[index];
        }
        //������ܹ��������Σ�����false
        return false;
    }

    //�����Ż����Ӵ�Ŀ�ʼ�ݹ飬��Ҫ�ȶ������������
    private static boolean makeSquareBackTrackFix(int[] nums, int len, int[] size, int index) {
        //��ֹ���������������ѷ���
        if (index == -1) {
            //����ı���ȷ���true
            if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                return true;
            //����false
            return false;
        }
        //����size����
        for (int i = 0; i < size.length; i++) {
            //�������ǰ������size[i]�������ϣ��䳤�ȴ��ڱ߳�����������
            //(i > 0 && size[i] == size[i - 1])��ʾ��ǰ��ֵ֧����һ����֧һ������һ����֧û�гɹ���
            //˵�������֧Ҳ����ɹ���ֱ����������
            if (size[i] + nums[index] > len || (i > 0 && size[i] == size[i - 1])) continue;
            size[i] += nums[index];
            //�ٷ���һ�����������տ��Գ�Ϊ�����Σ�ֱ�ӷ���true
            if (makeSquareBackTrackFix(nums, len, size, index - 1)) return true;
            //��������
            size[i] -= nums[index];
        }
        //������ܹ��������Σ�����false
        return false;
    }


    /*
    ����һ����������nums��һ��������k,�ҳ��Ƿ��п��ܰ��������ֳ�k���ǿ��Ӽ�,���ܺͶ���ȡ�
    ����:nums = [ 4 , 3 , 2 , 3 , 5 , 2 , 1 ], k = 4
    ���:True
    ˵��:�п��ܽ���ֳ� 4 ���Ӽ�( 5 )��( 1 , 4 )��( 2 , 3 )��( 2 , 3 )�����ܺ͡�
    ����������˼·��ͬ~~
    */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        return canBackTrack(nums, new int[k], sum / k, nums.length - 1);
    }

    private static boolean canBackTrack(int[] nums, int[] size, int sum, int index) {
        if (index == -1) {
            for (int i = 1; i < size.length; i++) {
                if (size[i] != size[i - 1]) return false;
            }
            return true;
        }
        for (int i = 0; i < size.length; i++) {
            if (size[i] + nums[index] > sum || (i > 0 && size[i] == size[i - 1])) continue;
            size[i] += nums[index];
            if (canBackTrack(nums, size, sum, index - 1)) return true;
            size[i] -= nums[index];
        }
        return false;
    }

    /*
    ����һ������������0-9���ַ���num��һ��Ŀ��ֵ����target
    ��num������֮����Ӷ�Ԫ�����������һԪ��+��-��*�����������ܹ��õ�Ŀ��ֵ�ı��ʽ��
    ����: num = "123", target = 6
    ���: ["1+2+3", "1*2*3"]
    */
    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        addOperatorsBackTrack(res, num, target, 0, 0, 0, "");
        return res;
    }


    /**
     * @param res
     * @param num    �ַ���
     * @param target Ŀ��ֵ
     * @param index  ���ʵ��ڼ����ַ�
     * @param preNum ǰ��������˻�
     * @param sum    ���ʽǰ�����õ��ĺ�
     * @param path   ���ʽ
     */
    private static void addOperatorsBackTrack(List<String> res, String num, int target, int index, long preNum, long sum, String path) {
        if (index >= num.length()) {
            if (sum == target) {
                res.add(path);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            //����ǰ���㣬���統numΪ012ʱ���ڶ��ν�ȡ��ȡ��01��Ӧ������
            if (i != index && num.charAt(index) == '0') break;
            //��ȡ����
            long number = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {//��ȡ���ʽ��һ������ʱ��Ҫ���⴦��
                addOperatorsBackTrack(res, num, target, i + 1, number, number, number + "");
            } else {
                //+
                addOperatorsBackTrack(res, num, target, i + 1, number, sum + number, path + "+" + number);
                //-
                addOperatorsBackTrack(res, num, target, i + 1, -number, sum - number, path + "-" + number);
                //* preNum * number + sum - preNum ���� 1 + 2 (* 3)
                // preNumΪ2��sumΪ3��numberΪ3 ��ñ��ʽֵΪ 2(pre)*3(number)+3(sum)-2(pre)
                addOperatorsBackTrack(res, num, target, i + 1, preNum * number,
                        preNum * number + sum - preNum, path + "*" + number);
            }
        }
    }


    /*
    ����һ�������ַ���S������S= "123456579"�����ǿ��Խ����ֳ�쳲�����ʽ������ [123, 456, 579]��
    */
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        splitIntoFibonacciBackTrack(res, 0, S);
        return res;
    }

    private static boolean splitIntoFibonacciBackTrack(List<Integer> res, int index, String S) {
        if (index >= S.length() && res.size() >= 3) return true;
        for (int i = index; i < S.length(); i++) {
            //����ȡ��������λ������ʱ������ǰ����
            if (i > index && S.charAt(index) == '0') break;
            //��ȡ����
            long num = Long.parseLong(S.substring(index, i + 1));
            //��ȡ������Խ�磬����
            if (num > Integer.MAX_VALUE) break;
            //
            int size = res.size();
            //��ǰ��ȡ���ִ���ǰ����֮��ʱ����������Ϊ�����ȡ�Ļ�Խ��Խ��
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) break;
            //��ѡȡ����С��2�����߽�ȡ���ֵ���ǰ����֮�ͣ�����res
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);
                //�ݹ�
                if (splitIntoFibonacciBackTrack(res, i + 1, S)) return true;
                //��������
                res.remove(res.size() - 1);
            }
        }
        return false;
    }


    /*
    ����һ���Ǹ���������A�����������ÿ������Ԫ��֮����һ����ȫƽ�����������һ����Ϊ���������顣
    ����A�����������е���Ŀ����������A1��A2��ͬ�ĳ�Ҫ�����Ǵ���ĳ������i��ʹ��A1[i] != A2[i]��
    */
    static int count = 0;//��¼�����

    public static int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);//���� ������� ����{1, 1, 8, 1, 8}
        numSquarefulPermsBackTrack(nums, new boolean[nums.length], -1, 0);
        return count;
    }

    /**
     * @param nums
     * @param used
     * @param preNum ��¼ǰһ��ѡ����� ��ʼֵΪ-1
     * @param index  ���ʵ��ڼ���
     */
    private static void numSquarefulPermsBackTrack(int[] nums, boolean[] used, int preNum, int index) {
        if (index == nums.length) {
            count++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            //��ѡ�����Ϊ���еĵ�һ����ʱ���ж���ȫƽ����
            if (preNum > 0 && !isPerfecrSquare(preNum + nums[i])) continue;
            used[i] = true;
            numSquarefulPermsBackTrack(nums, used, nums[i], index + 1);
            used[i] = false;
        }
    }

    //�ж���ȫƽ����
    private static boolean isPerfecrSquare(int n) {
        int sqr = (int) Math.sqrt(n);
        return sqr * sqr == n;
    }

    /*
    ����һ���ַ���s�����㽫s�ָ��һЩ�Ӵ���ʹÿ���Ӵ����ǻ��Ĵ�������s���п��ܵķָ����
    */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        //�����Ż����ο�540��̬�滮������ַ����������ַ��������л����ַ��������dp��
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) dp[j][i] = true;
            }
        }
        partitionBackTrack(s, 0, res, new ArrayList<>(), dp);
        return res;
    }

    private static void partitionBackTrack(String s, int index, List<List<String>> res, List<String> list, boolean[][] dp) {
        if (index >= s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (!dp[index][i]) continue;
            list.add(s.substring(index, i + 1));
            partitionBackTrack(s, i + 1, res, list, dp);
            list.remove(list.size() - 1);
        }
    }

    /*private static boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }*/

    /*498
    ����һ�׻�����ģtiles������ÿ����ģ�϶�����һ����ĸtiles[i]�����������ӡ���ķǿ���ĸ���е���Ŀ��
    ע�⣺�����У�ÿ��������ģֻ��ʹ��һ�Ρ�
    ���룺"AAB"
    �����8
    ���ͣ����ܵ�����Ϊ "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
    */
    public static int numTilePossibilities(String tiles) {
        if (tiles.length() == 1) return 1;
        char[] str = tiles.toCharArray();
        Arrays.sort(str);
        numTilePossibilitiesBackTrack(str, 0, new boolean[tiles.length()]);
        return count;
    }

    private static void numTilePossibilitiesBackTrack(char[] str, int index, boolean[] used) {
        if (index == str.length) {
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (used[i]) continue;
            if (i > 0 && str[i] == str[i - 1] && !used[i - 1]) continue;
            count++;
            used[i] = true;
            numTilePossibilitiesBackTrack(str, index + 1, used);
            used[i] = false;
        }
    }

    /*575
    ����һ���ǿ��ַ���s��һ�������ǿյ����б���ֵ�wordDict�����ַ��������ӿո�������һ�����ӣ�
    ʹ�þ��������еĵ��ʶ��ڴʵ��С�����������Щ���ܵľ��ӡ�
    ����: s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    ���: ["cats and dog", "cat sand dog"]
    */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        wordBreakBackTrack(wordDict, 0, s, res, "");
        return res;
    }

    private static void wordBreakBackTrack(List<String> wordDict, int index, String s, List<String> res, String path) {
        if (index >= s.length()) {
            res.add(path);
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (!wordDict.contains(s.substring(index, i + 1))) continue;
            String temp = s.substring(index, i + 1);
            if (path == "") {
                wordBreakBackTrack(wordDict, i + 1, s, res, path + temp);
            } else {
                wordBreakBackTrack(wordDict, i + 1, s, res, path + " " + temp);
            }
        }
    }


    /*478
    ����һ����ά�����һ�����ʣ��ҳ��õ����Ƿ�����������С�
    ���ʱ��밴����ĸ˳��ͨ�����ڵĵ�Ԫ���ڵ���ĸ���ɣ����С����ڡ���Ԫ������Щˮƽ���ڻ�ֱ���ڵĵ�Ԫ��
    ͬһ����Ԫ���ڵ���ĸ�������ظ�ʹ�á�
    board = [
    ['A','B','C','E'] ,
    ['S','F','C','S'] ,
    ['A','D','E','E']
    ]
    ����word = "ABCCED", ���� true
    ����word = "SEE", ���� true
    ����word = "ABCB", ���� false
    */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        //�ӵ�һ��Ԫ�ؿ�ʼ����
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existBackTrack(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean existBackTrack(char[][] board, char[] words, int i, int j, int index) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[index]) return false;
        //���������������һλʱ��˵���Ѿ�ƥ��ɹ�
        if (index == words.length - 1) return true;
        ////�ѵ�ǰ�����ֵ����������Ϊ�������ԭ
        char temp = board[i][j];
        //Ȼ���޸ĵ�ǰ����ֵ
        board[i][j] = '.';
        //���ĸ��������
        boolean res = existBackTrack(board, words, i + 1, j, index + 1)
                || existBackTrack(board, words, i - 1, j, index + 1)
                || existBackTrack(board, words, i, j + 1, index + 1)
                || existBackTrack(board, words, i, j - 1, index + 1);
        //������������ԭ
        board[i][j] = temp;
        return res;
    }


    /*451
    ����һ�鲻���ظ�Ԫ�ص��������� nums�����ظ��������п��ܵ��Ӽ����ݼ����� ˵�����⼯���ܰ����ظ����Ӽ���
    ����: nums = [1,2,3]
    ���: [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
    */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsBackTrack(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private static void subsetsBackTrack(int[] nums, List<List<Integer>> res, List<Integer> list, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsBackTrack(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /*446
    ��Ҫ����һ����󣬵��ʿ���ѧ���Ѿ�̽������������е���Դ�ֲ������ô�СΪ m * n ������ grid �����˱�ע��ÿ����Ԫ���е������ͱ�ʾ
    ��һ��Ԫ���еĻƽ�����������õ�Ԫ���ǿյģ���ô����0��
    Ϊ��ʹ������󻯣�����Ҫ�����¹��������ɻƽ�
    ��ÿ���󹤽���һ����Ԫ���ͻ��ռ��õ�Ԫ���е����лƽ�
    ����ÿ�ο��Դӵ�ǰλ�������������ĸ������ߡ�
    ��ÿ����Ԫ��ֻ�ܱ����ɣ����룩һ�Ρ�
    �����ÿ��ɣ����룩�ƽ���ĿΪ0�ĵ�Ԫ��
    ���󹤿��Դ�����������һ���лƽ�ĵ�Ԫ�����������ֹͣ��
    ���룺 grid = [[0,6,0],[5,8,7],[0,9,0]]
    ����� 24
    */
    static int res = 0;//�ڵ��Ľ����
    static int max = 0;//���ֵ

    public static int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {//�ӵ�һ���н��ӵĿ�ʼ��
                    getMaximumGoldBackTrack(grid, i, j);
                }
            }
        }
        return max;
    }

    private static void getMaximumGoldBackTrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;
        res += grid[i][j];
        //���浱ǰ���ƽ�����
        int temp = grid[i][j];
        //�޸ĵ�ǰ���
        grid[i][j] = 0;
        //�ĸ�������
        getMaximumGoldBackTrack(grid, i + 1, j);
        getMaximumGoldBackTrack(grid, i - 1, j);
        getMaximumGoldBackTrack(grid, i, j + 1);
        getMaximumGoldBackTrack(grid, i, j - 1);
        //��¼���ֵ
        max = Math.max(max, res);
        //��������
        grid[i][j] = temp;
        res -= grid[i][j];
    }


    /*
    Ա���μӿ��ԣ��ж���X10��ÿ��2�֣�����ѡ��X10��ÿ��4�֣�����ѡ��X5��ÿ���˷֣���
    ֻ��˳�����𣬴�Ե÷֣�����÷֡����������������ֹ���ԡ����뿼�Խ�������������������������
    */
    public static int answer(int target) {
        if (target == 0 || target == 100) return 1;
        if (target % 2 != 0) return 0;
        int[] questions = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8};
        answerBackTrack(questions, target, 0, 0, 0);
        return count;
    }

    private static void answerBackTrack(int[] questions, int target, int index, int score, int err) {
        if (score >= target) {
            if (score == target) count++;
            return;
        }

        for (int i = index; i < questions.length; i++) {
            if (err >= 3) break;
            score += questions[i];
            answerBackTrack(questions, target, i + 1, score, err);
            score -= questions[i];
            err++;
        }
    }

    /*
    ����һ�ö�������һ����������ӡ���������нڵ�ֵ�ĺ�Ϊ��������������·���������ĸ��ڵ㿪ʼ����һֱ��Ҷ�ڵ��������Ľڵ��γ�һ��·����
    */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        pathSumBackTrack(root, sum, res, new ArrayList<>(), 0);
        return res;
    }

    private static void pathSumBackTrack(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list, int tempSum) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        tempSum += root.val;
        //�������Ҷ�ӽڵ㣬����sum����tempSum��˵�������ҵ���һ�飬
        //Ҫ�����ŵ�result��
        if (root.left == null && root.right == null) {
            if (sum == tempSum) res.add(new ArrayList<>(list));
            //��ʱ�Ƴ�list���һ��ֵ����Ϊ��һ����return�����ᵽ���һ�е�remove
            list.remove(list.size() - 1);
            return;
        }
        //���������ӽڵ����
        pathSumBackTrack(root.left, sum, res, list, tempSum);
        pathSumBackTrack(root.right, sum, res, list, tempSum);
        list.remove(list.size() - 1);
    }

    public static void pathfinder() {
        System.out.println("nothing");
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


