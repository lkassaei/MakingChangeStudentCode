/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Lily Kassaei
 */

public class MakingChange {
    // Array to store passed calculations using memoization.
    public static long[][] dp;

    // Given a target and array of coin values, return how many ways can you make target with coins
    public static long countWays(int target, int[] coins) {
        // Set the size of dp to target + 1 in the cols to include 0-target
        dp = new long[coins.length][target + 1];

        // Go through coins and target, filling in the array with memoization
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                // Fill in array passing in the coinIndex, coins array, and target
                dp[i][j] = countWaysHelper(i, coins, j);
            }
        }
        // Return the bottom right square
        return dp[coins.length - 1][target];
    }

    // Memoization
    public static long countWaysHelper(int coinIndex, int[] coins, int target) {
        // Base cases:
        // If there are no more coins to use then return 0
        if (coinIndex < 0 || coins.length == 0) {
            return 0;
        }
        // If we made it to 0 then we have found 1 way
        if (target == 0) {
            return 1;
        }
        // If there is one coin left and if can't make target than return 0
        if (coinIndex == 0 && target % coins[coinIndex] != 0) {
            return 0;
        }
        // If target is negative then return 0
        if (target < 0) {
            return 0;
        }
        // If we already have calculated then return that number
        if (dp[coinIndex][target] != 0) {
            return dp[coinIndex][target];
        }
        // Return the sum of the two ways: including the current coin or excluding
        return countWaysHelper(coinIndex - 1, coins, target) + countWaysHelper(coinIndex, coins, target - coins[coinIndex]);
    }
}
