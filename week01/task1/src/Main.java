
public class Main {
    public static void main(String[] args) {

        System.out.println(IPValidator.validateIPv4Address("192.168.1.1") == true);
        System.out.println(IPValidator.validateIPv4Address("192.168.1.0") == true);
        System.out.println(IPValidator.validateIPv4Address("192.168.1.00") == false);
        System.out.println(IPValidator.validateIPv4Address("192.168@1.1") == false);

    }
}