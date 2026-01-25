/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Lily Kassaei
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long ways;
    public static long countWays(int target, int[] coins) {
        ways = 0;
        int count = 0;
        countWaysHelper(target, count, coins);
        return ways;
    }

    public static void countWaysHelper(int target, int count, int[] coins) {
        if (count == target) {
            ways++;
        }
        if (count > target) {
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            countWaysHelper(target, count + coins[i], coins);
        }
    }
}
