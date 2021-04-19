package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/3/9
 */
public class Temp {
    public String LCS (String str1, String str2) {
        // write code here
        // dp[i][j][l] 表示str1从i起，str2从j起，长度为l的字符串是否相等
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = Math.min(str1.length(), str2.length());
        boolean[][][] dp = new boolean[len1][len2][minLen];
        // init
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                dp[i][j][0] = str1.charAt(i) == str2.charAt(j);
            }
        }
        for (int l = 2; l <= minLen; ++l) {
            int tmpLen1 = len1 - l + 1;
            int tmpLen2 = len2 - l + 1;
            for (int i = 0; i < tmpLen1; ++i) {
                for (int j = 0; j < tmpLen2; ++j) {
                    dp[i][j][l - 1] = dp[i][j][l - 2] && str1.charAt(i + l - 1) == str2.charAt(j + l - 1);
                }
            }
        }
        for (int l = minLen; l >= 1; --l) {
            for (int i = 0; i + l - 1 < len1; ++i) {
                for (int j = 0; j + l - 1 < len2; ++j) {
                    if (dp[i][j][l - 1]) {
                        return str1.substring(i, i + l);
                    }
                }
            }
        }
        return "";
    }
    
    private Map<State, Map<Action, State>> stateMap;

    {
        stateMap = new HashMap<>();
        Map<Action, State> initAction = new HashMap<Action, State>() {{
            put(Action.POSI_OR_NEGA, State.BACK_SIGNAL);
            put(Action.NUM, State.BACK_INTEGER);
            put(Action.POINT, State.BACK_POINT_LEFT_EMPTY);
            put(Action.BLANK, State.INIT);
        }};
        stateMap.put(State.INIT, initAction);

        Map<Action, State> backSignalAction = new HashMap<>();
        backSignalAction.put(Action.NUM,State.BACK_INTEGER);
        backSignalAction.put(Action.POINT, State.BACK_POINT_LEFT_EMPTY);
        stateMap.put(State.BACK_SIGNAL, backSignalAction);

        Map<Action, State> backIntegerAction = new HashMap<>();
        backIntegerAction.put(Action.NUM, State.BACK_INTEGER);
        backIntegerAction.put(Action.POINT, State.BACK_POINT);
        backIntegerAction.put(Action.E, State.EXP);
        backIntegerAction.put(Action.BLANK, State.END_BLANK);
        backIntegerAction.put(Action.END, State.END);
        stateMap.put(State.BACK_INTEGER, backIntegerAction);

        Map<Action, State> backPointAction = new HashMap<>();
        backPointAction.put(Action.NUM, State.BACK_DECIMAL);
        backPointAction.put(Action.E, State.EXP);
        backPointAction.put(Action.BLANK, State.END_BLANK);
        backPointAction.put(Action.END, State.END);
        stateMap.put(State.BACK_POINT, backPointAction);

        Map<Action, State> backPointLeftEmptyAction = new HashMap<>();
        backPointLeftEmptyAction.put(Action.NUM, State.BACK_DECIMAL);
        stateMap.put(State.BACK_POINT_LEFT_EMPTY, backPointLeftEmptyAction);

        Map<Action, State> backDecimalAction = new HashMap<>();
        backDecimalAction.put(Action.NUM, State.BACK_DECIMAL);
        backDecimalAction.put(Action.E, State.EXP);
        backDecimalAction.put(Action.BLANK, State.END_BLANK);
        backDecimalAction.put(Action.END, State.END);
        stateMap.put(State.BACK_DECIMAL, backDecimalAction);

        Map<Action, State> expAction = new HashMap<>();
        expAction.put(Action.POSI_OR_NEGA, State.EXP_SIGNAL);
        expAction.put(Action.NUM, State.EXP_INTEGER);
        stateMap.put(State.EXP, expAction);

        Map<Action, State> expSignalAction = new HashMap<>();
        expSignalAction.put(Action.NUM, State.EXP_INTEGER);
        stateMap.put(State.EXP_SIGNAL, expSignalAction);

        Map<Action, State> expIntegerAction = new HashMap<>();
        expIntegerAction.put(Action.NUM, State.EXP_INTEGER);
        expIntegerAction.put(Action.BLANK, State.END_BLANK);
        expIntegerAction.put(Action.END, State.END);
        stateMap.put(State.EXP_INTEGER, expIntegerAction);

        Map<Action, State> endBlankAction = new HashMap<>();
        endBlankAction.put(Action.BLANK, State.END_BLANK);
        endBlankAction.put(Action.END, State.END);
        stateMap.put(State.END_BLANK, endBlankAction);
    }

    enum State {
        INIT,
        BACK_SIGNAL,
        BACK_INTEGER,
        BACK_POINT,
        BACK_POINT_LEFT_EMPTY,
        BACK_DECIMAL,
        EXP,
        EXP_SIGNAL,
        EXP_INTEGER,
        END_BLANK,
        END
    }

    enum Action {
        NUM,
        POSI_OR_NEGA,
        POINT,
        E,
        END,
        BLANK
    }

    public boolean isNumber(String s) {
        State cur = State.INIT;
        Map<Action, State> actionMap;
        for (int i = 0; i < s.length() ; ++i) {
            char ch = s.charAt(i);
            actionMap = stateMap.get(cur);
            if (cur == null) {
                return false;
            }
            cur = actionMap.get(judge(ch));
        }
        actionMap = stateMap.get(cur);
        return actionMap != null && actionMap.get(Action.END) == State.END;
    }

    private Action judge(char ch) {
        if (ch >= '0' && ch <= '9') {
            return Action.NUM;
        } else if (ch == '+' || ch == '-') {
            return Action.POSI_OR_NEGA;
        } else if (ch == '.') {
            return Action.POINT;
        } else if (ch == 'e' || ch == 'E') {
            return Action.E;
        } else if (ch == ' ') {
            return Action.BLANK;
        } else {
            return null;
        }
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int s, int e) {
        if (s >= e) {
            return 0;
        }
        int m = (s + e) / 2;
        int cost = mergeSort(nums, s, m);
        cost += mergeSort(nums, m + 1, e);
        cost += merge(nums, s, e, m);
        return cost;
    }

    private int merge(int[] nums, int s, int e, int m) {
        int[] tmp = new int[e - s + 1];
        int k = 0;
        int l = s;
        int r = m + 1;
        int cost = 0;
        while (l <= m && r <= e) {
            if (nums[l] <= nums[r]) {
                nums[k++] = nums[l++];
            } else {
                nums[k++] = nums[r++];
                cost += (m - l + 1);
            }
        }
        while (l <= m) {
            nums[k++] = nums[l++];
        }
        while (r <= e) {
            nums[k++] = nums[r++];
        }
        l = s;
        for (int i = 0; i < tmp.length; ++i) {
            nums[l++] = tmp[i];
        }
        return cost;
    }
    
    private int[][] a ={{1, 2},{2}};
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        
        return match(A, B);
    }

    private boolean match(TreeNode A, TreeNode B) {
        if ((A == null && B == null) || B == null) {
            return true;
        } else if (A == null) {
            return false;
        } else {
            return A.val == B.val && match(A.left, B.left) && match(A.right, B.right);
        }
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
