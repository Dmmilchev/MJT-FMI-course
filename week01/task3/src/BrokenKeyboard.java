public class BrokenKeyboard {
    public static int calculateFullyTypedWords(String message, String brokenKeys) {
        String[] words = message.split("\\s+");
        int count = words.length;
        for (String word : words) {
            if (word.isBlank()) {
                count--;
                continue;
            }

            for (char c : word.toCharArray()) {
                if (brokenKeys.contains(Character.toString(c))) {
                    count--;
                    break;
                }
            }
        }

        return count;
    }
}
