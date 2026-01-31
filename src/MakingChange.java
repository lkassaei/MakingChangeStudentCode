import java.util.Arrays;

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
        // Sort coins to make faster
        Arrays.sort(coins);

        // Set the size of dp to target + 1 in the cols to include 0-target
        dp = new long[coins.length][target + 1];
        //return countWaysMemo(target, coins);
        return countWaysTab(target, coins);
    }

    // Method to fill dp array with memoization
    public static long countWaysMemo(int target, int[] coins) {
        // Go through coins and target, filling in the array with memoization
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                // Fill in array passing in the coinIndex, coins array, and target
                dp[i][j] = countWaysHelperMemo(i, coins, j);
            }
        }
        // Return the bottom right square
        return dp[coins.length - 1][target];
    }

    // Memoization recursive
    public static long countWaysHelperMemo(int coinIndex, int[] coins, int target) {
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
        return countWaysHelperMemo(coinIndex - 1, coins, target) + countWaysHelperMemo(coinIndex, coins, target - coins[coinIndex]);
    }

    // Tabulation (iterative)
    public static long countWaysTab(int target, int[] coins) {
        // Row-major traversal
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                // Current coin
                int coinValue = coins[i];
                // Base case: if we are on our last coin, then the answer is if we can use it
                if (i == 0) {
                    if (j % coins[i] == 0) {
                        dp[i][j] = 1;
                    }
                }
                // If we made it to target == 0, then we found a way
                else if (j == 0) {
                    dp[i][j] = 1;
                }
                // The new way is the square above plus the square currentCoin to the left
                else {
                    // Make sure we are in bounds
                    if (j - coinValue >= 0)  {
                        // Above square + left square
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coinValue];
                    }
                    else {
                        // If we can't go left, just go above (case where i == 0 is already handle so no bounds check)
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        // Return bottom right square
        return dp[coins.length - 1][target];
    }
}
