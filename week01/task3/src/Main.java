public class Main {
    public static void main(String[] args) {

        System.out.println(BrokenKeyboard.calculateFullyTypedWords("i love mjt", "qsf3o") == 2);
        System.out.println(BrokenKeyboard.calculateFullyTypedWords("secret      message info      ", "sms") == 1);
        System.out.println(BrokenKeyboard.calculateFullyTypedWords("dve po 2 4isto novi beli kecove", "o2sf") == 2);
        System.out.println(BrokenKeyboard.calculateFullyTypedWords("     ", "asd") == 0);
        System.out.println(BrokenKeyboard.calculateFullyTypedWords(" - 1 @ - 4", "s") == 5);

    }
}