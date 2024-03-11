public class IPValidator {
    public static boolean validateIPv4Address(String str){
        String[] xi = str.split("\\.");
        if (xi.length > 4) {
            return false;
        }

        for (String x : xi) {
            if (!validateIPv4Token(x)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateIPv4Token(String token) {
        if (token.startsWith("0") && token.length() > 1) {
            return false;
        }

        if (!token.matches("\\d+")) {
            return false;
        }

        if (Integer.parseInt(token) < 0 || Integer.parseInt(token) > 255) {
            return false;
        }

        return true;
    }
}
