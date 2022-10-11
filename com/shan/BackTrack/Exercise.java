package com.LeetCode.com.shan.BackTrack;

import java.util.*;

/* 回溯模板
    private void backtrack("原始参数") {
        //终止条件(递归必须要有终止条件)
        if ("终止条件") {
            //一些逻辑操作（可有可无，视情况而定）
            return;
        }

        for (int i = "for循环开始的参数"; i < "for循环结束的参数"; i++) {
            //一些逻辑操作（可有可无，视情况而定）

            //做出选择

            //递归
            backtrack("新的参数");
            //一些逻辑操作（可有可无，视情况而定）

            //撤销选择
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

    /*回溯算法求组合问题
    给定一个无重复元素的数组candidates和一个目标数target,找出candidates中所有可以使数字和为target的组合。
    输入: candidates = [2, 3, 6, 7], target = 7
    输出: [[7],[2, 2, 3]]
    */
    //每选择一个数，，如果target小于选择的数则跳过，否则target减去该数，最终target为零即是终止条件。
    //**！！**变形：所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。 candidates 中的每个数字在每个组合中只能使用一次。
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //变形：Arrays.sort(candidates);
        combinationSumBackTrack(candidates, target, res, new ArrayList<Integer>(),0);
        return res;
    }

    private static void combinationSumBackTrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int start) {
        //终止条件
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //遍历这个N叉树的子节点，
        for (int i = start; i < candidates.length; i++) {
            //逻辑判断
            if (target < candidates[i]) continue;
            //变形：（去重）
            // if (i > start && candidates[i] == candidates[i - 1])
            //      continue;
            //做出选择
            list.add(candidates[i]);
            //递归
            //变形：combinationSumBackTrack(candidates, target - candidates[i], res, list, i + 1);
            combinationSumBackTrack(candidates, target - candidates[i], res, list, i);
            //撤销选择
            list.remove(list.size() - 1);
        }
    }


    /*
    组合问题2
    给定两个整数 n 和 k，返回 1 . . . n 中所有可能的 k 个数的组合。
    输入: n = 4, k = 2
    输出: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
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
            //数字不能重复，跳过
            if (list.contains(i)) continue;
            list.add(i);
            combineBackTrack(n, k, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /*
    全排列
    给定一个不含重复数字的数组nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
    输入：nums = [ 1 , 2 , 3 ]
    输出：[ [ 1 , 2 , 3 ] , [ 1 , 3 , 2 ] , [ 2 , 1 , 3 ] , [ 2 , 3 , 1 ] , [ 3 , 1 , 2 ] , [ 3 , 2 , 1 ] ]
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
    给定一个可包含重复数字的序列nums，按任意顺序返回所有不重复的全排列。
    输入: nums = [1, 1, 2]
    输出: [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteUniqueBackTrack(nums, res, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    /**
     * 由于包含重复数字，会造成重复结果，因此要进行剪枝操作
     *
     * @param nums
     * @param res
     * @param list
     * @param used boolean数组，用于标记数字是否被选择
     */
    private static void permuteUniqueBackTrack(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //数字被使用过，跳过
            if (used[i]) continue;
            //剪枝，如果当前数与前一个数一样，并且前一个数还未访问过，则跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            //标记为已访问
            used[i] = true;
            list.add(nums[i]);
            permuteUniqueBackTrack(nums, res, list, used);
            //撤销操作
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }


    /*
    输入一个字符串，打印出该字符串中字符的所有排列。
    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
    输入: s = "abc"
    输出：["abc", "acb", "bac", "bca", "cab", "cba"]
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
    给定正整数N，我们按任何顺序（包括原始顺序）将数字重新排序，
    注意其前导数字不能为零。如果我们可以通过上述方式得到2的幂，返回true；否则，返回false。
    思路参考有重复数字的全排列
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
            //过滤前导零，注意条件不能为[i] == 0
            if (num == 0 && charNum[i] == '0') continue;
            //剪枝过滤重复数字
            if (used[i]) continue;
            //剪枝
            if (i > 0 && charNum[i] == charNum[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            if (reorderedPowerOfTwoBackTrack(index + 1, charNum, num * 10 + charNum[i] - '0', used))
                return true;
            used[i] = false;
        }
        return false;
    }

    //判断是否为2的幂
    private static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }


    /*
    输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
    输入: [1, 1, 2, 2, 2]
    输出: true
    */
    public static boolean makeSquare(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        //如果数组和不为4的倍数，或和等于0，或数组长度小于四，直接返回false
        if (nums.length < 4 || sum % 4 != 0 || sum == 0) return false;
        //sum >> 2表示边长
        Arrays.sort(nums);//优化
        return makeSquareBackTrackFix(nums, sum >> 2, new int[4], nums.length - 1);
    }

    /**
     * @param nums
     * @param len   边长
     * @param size  长度为4的数组，存放边的长度
     * @param index 访问到第几个数字
     * @return
     */
    private static boolean makeSquareBackTrack(int[] nums, int len, int[] size, int index) {
        //终止条件：所有数字已访问
        if (index >= nums.length ) {
            //如果四边相等返回true
            if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                return true;
            //否则false
            return false;
        }
        //遍历size数组
        for (int i = 0; i < size.length; i++) {
            //如果将当前火柴放在size[i]这条边上，其长度大于边长，则跳过；
            if (size[i] + nums[index] > len) continue;
            size[i] += nums[index];
            //再放下一个火柴，如果最终可以成为正方形，直接返回true
            if (makeSquareBackTrack(nums, len, size, index + 1)) return true;
            //撤销操作
            size[i] -= nums[index];
        }
        //如果不能构成正方形，返回false
        return false;
    }

    //进行优化，从大的开始递归，需要先对数组进行排序
    private static boolean makeSquareBackTrackFix(int[] nums, int len, int[] size, int index) {
        //终止条件：所有数字已访问
        if (index == -1) {
            //如果四边相等返回true
            if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                return true;
            //否则false
            return false;
        }
        //遍历size数组
        for (int i = 0; i < size.length; i++) {
            //如果将当前火柴放在size[i]这条边上，其长度大于边长，则跳过；
            //(i > 0 && size[i] == size[i - 1])表示当前分支值与上一个分支一样，上一个分支没有成功，
            //说明这个分支也不会成功，直接跳过即可
            if (size[i] + nums[index] > len || (i > 0 && size[i] == size[i - 1])) continue;
            size[i] += nums[index];
            //再放下一个火柴，如果最终可以成为正方形，直接返回true
            if (makeSquareBackTrackFix(nums, len, size, index - 1)) return true;
            //撤销操作
            size[i] -= nums[index];
        }
        //如果不能构成正方形，返回false
        return false;
    }


    /*
    给定一个整数数组nums和一个正整数k,找出是否有可能把这个数组分成k个非空子集,其总和都相等。
    输入:nums = [ 4 , 3 , 2 , 3 , 5 , 2 , 1 ], k = 4
    输出:True
    说明:有可能将其分成 4 个子集( 5 )，( 1 , 4 )，( 2 , 3 )，( 2 , 3 )等于总和。
    此题与上题思路相同~~
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
    给定一个仅包含数字0-9的字符串num和一个目标值整数target
    在num的数字之间添加二元运算符（不是一元）+、-或*，返回所有能够得到目标值的表达式。
    输入: num = "123", target = 6
    输出: ["1+2+3", "1*2*3"]
    */
    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        addOperatorsBackTrack(res, num, target, 0, 0, 0, "");
        return res;
    }


    /**
     * @param res
     * @param num    字符串
     * @param target 目标值
     * @param index  访问到第几个字符
     * @param preNum 前面的连续乘积
     * @param sum    表达式前面计算得到的和
     * @param path   表达式
     */
    private static void addOperatorsBackTrack(List<String> res, String num, int target, int index, long preNum, long sum, String path) {
        if (index >= num.length()) {
            if (sum == target) {
                res.add(path);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            //过滤前导零，例如当num为012时，第二次截取会取到01，应当过滤
            if (i != index && num.charAt(index) == '0') break;
            //截取数字
            long number = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {//当取表达式第一个数字时，要特殊处理
                addOperatorsBackTrack(res, num, target, i + 1, number, number, number + "");
            } else {
                //+
                addOperatorsBackTrack(res, num, target, i + 1, number, sum + number, path + "+" + number);
                //-
                addOperatorsBackTrack(res, num, target, i + 1, -number, sum - number, path + "-" + number);
                //* preNum * number + sum - preNum 例如 1 + 2 (* 3)
                // preNum为2，sum为3，number为3 则该表达式值为 2(pre)*3(number)+3(sum)-2(pre)
                addOperatorsBackTrack(res, num, target, i + 1, preNum * number,
                        preNum * number + sum - preNum, path + "*" + number);
            }
        }
    }


    /*
    给定一个数字字符串S，比如S= "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
    */
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        splitIntoFibonacciBackTrack(res, 0, S);
        return res;
    }

    private static boolean splitIntoFibonacciBackTrack(List<Integer> res, int index, String S) {
        if (index >= S.length() && res.size() >= 3) return true;
        for (int i = index; i < S.length(); i++) {
            //当截取的数是两位数以上时，过滤前导零
            if (i > index && S.charAt(index) == '0') break;
            //截取数字
            long num = Long.parseLong(S.substring(index, i + 1));
            //截取的数字越界，跳出
            if (num > Integer.MAX_VALUE) break;
            //
            int size = res.size();
            //当前截取数字大于前两数之和时，跳出，因为后面截取的会越来越大
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) break;
            //当选取数字小于2，或者截取数字等于前两数之和，加入res
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);
                //递归
                if (splitIntoFibonacciBackTrack(res, i + 1, S)) return true;
                //撤销操作
                res.remove(res.size() - 1);
            }
        }
        return false;
    }


    /*
    给定一个非负整数数组A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。
    返回A的正方形排列的数目。两个排列A1和A2不同的充要条件是存在某个索引i，使得A1[i] != A2[i]。
    */
    static int count = 0;//记录结果数

    public static int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);//排序 如果不排 反例{1, 1, 8, 1, 8}
        numSquarefulPermsBackTrack(nums, new boolean[nums.length], -1, 0);
        return count;
    }

    /**
     * @param nums
     * @param used
     * @param preNum 记录前一个选择的数 初始值为-1
     * @param index  访问到第几个
     */
    private static void numSquarefulPermsBackTrack(int[] nums, boolean[] used, int preNum, int index) {
        if (index == nums.length) {
            count++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            //当选择的数为排列的第一个数时不判断完全平方数
            if (preNum > 0 && !isPerfecrSquare(preNum + nums[i])) continue;
            used[i] = true;
            numSquarefulPermsBackTrack(nums, used, nums[i], index + 1);
            used[i] = false;
        }
    }

    //判断完全平方数
    private static boolean isPerfecrSquare(int n) {
        int sqr = (int) Math.sqrt(n);
        return sqr * sqr == n;
    }

    /*
    给你一个字符串s，请你将s分割成一些子串，使每个子串都是回文串。返回s所有可能的分割方案。
    */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        //进行优化，参考540动态规划解回文字符串，将该字符串的所有回文字符串标记在dp中
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
    你有一套活字字模tiles，其中每个字模上都刻有一个字母tiles[i]。返回你可以印出的非空字母序列的数目。
    注意：本题中，每个活字字模只能使用一次。
    输入："AAB"
    输出：8
    解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
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
    给定一个非空字符串s和一个包含非空单词列表的字典wordDict，在字符串中增加空格来构建一个句子，
    使得句子中所有的单词都在词典中。返回所有这些可能的句子。
    输入: s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    输出: ["cats and dog", "cat sand dog"]
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
    给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
    同一个单元格内的字母不允许被重复使用。
    board = [
    ['A','B','C','E'] ,
    ['S','F','C','S'] ,
    ['A','D','E','E']
    ]
    给定word = "ABCCED", 返回 true
    给定word = "SEE", 返回 true
    给定word = "ABCB", 返回 false
    */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        //从第一个元素开始遍历
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existBackTrack(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean existBackTrack(char[][] board, char[] words, int i, int j, int index) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[index]) return false;
        //当遍历到单词最后一位时，说明已经匹配成功
        if (index == words.length - 1) return true;
        ////把当前坐标的值保存下来，为了在最后复原
        char temp = board[i][j];
        //然后修改当前坐标值
        board[i][j] = '.';
        //像四个方向查找
        boolean res = existBackTrack(board, words, i + 1, j, index + 1)
                || existBackTrack(board, words, i - 1, j, index + 1)
                || existBackTrack(board, words, i, j + 1, index + 1)
                || existBackTrack(board, words, i, j - 1, index + 1);
        //撤销操作，复原
        board[i][j] = temp;
        return res;
    }


    /*451
    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 说明：解集不能包含重复的子集。
    输入: nums = [1,2,3]
    输出: [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
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
    你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示
    这一单元格中的黄金数量；如果该单元格是空的，那么就是0。
    为了使收益最大化，矿工需要按以下规则来开采黄金：
    ·每当矿工进入一个单元，就会收集该单元格中的所有黄金。
    ·矿工每次可以从当前位置向上下左右四个方向走。
    ·每个单元格只能被开采（进入）一次。
    ·不得开采（进入）黄金数目为0的单元格。
    ·矿工可以从网格中任意一个有黄金的单元格出发或者是停止。
    输入： grid = [[0,6,0],[5,8,7],[0,9,0]]
    输出： 24
    */
    static int res = 0;//挖到的金矿数
    static int max = 0;//最大值

    public static int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {//从第一个有金子的矿开始挖
                    getMaximumGoldBackTrack(grid, i, j);
                }
            }
        }
        return max;
    }

    private static void getMaximumGoldBackTrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;
        res += grid[i][j];
        //保存当前金矿黄金数量
        int temp = grid[i][j];
        //修改当前金矿
        grid[i][j] = 0;
        //四个方向挖
        getMaximumGoldBackTrack(grid, i + 1, j);
        getMaximumGoldBackTrack(grid, i - 1, j);
        getMaximumGoldBackTrack(grid, i, j + 1);
        getMaximumGoldBackTrack(grid, i, j - 1);
        //记录最大值
        max = Math.max(max, res);
        //撤销操作
        grid[i][j] = temp;
        res -= grid[i][j];
    }


    /*
    员工参加考试，判断题X10（每个2分），单选题X10（每个4分），多选题X5（每个八分）。
    只能顺序作答，答对得分，答错不得分。连续答错三道，中止考试。输入考试结果分数，输出答题可能情况个数
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
    输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
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
        //如果到达叶子节点，并且sum等于tempSum，说明我们找到了一组，
        //要把它放到result中
        if (root.left == null && root.right == null) {
            if (sum == tempSum) res.add(new ArrayList<>(list));
            //此时移除list最后一个值，因为下一步是return，不会到最后一行的remove
            list.remove(list.size() - 1);
            return;
        }
        //左右两个子节点查找
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


