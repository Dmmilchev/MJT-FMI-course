public class JumpGame {
    public static boolean canWin(int[] array) {
        int canGoTo = 1;
        for (int i = 0; i < canGoTo && i < array.length; i++) {
            canGoTo = Math.max(canGoTo, i + 1 + array[i]);
        }

        return canGoTo >= array.length;
    }
}
