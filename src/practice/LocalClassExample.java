package practice;

public class LocalClassExample {
    static String regularExp = "[^0-9]";

    public static void validatePhoneNumber(
            String phoneNumber1, String phoneNumber2
    ) {
        final int numberLength = 10;

        class PhoneNumber {
            String formattedPhoneNum = null;

            PhoneNumber (String phoneNum) {
                String currentNum = phoneNum.replaceAll(regularExp, "");

                if (currentNum.length() == numberLength)
                    formattedPhoneNum = currentNum;
            }

            public String getNum() {
                return formattedPhoneNum;
            }

            public void  printOriginalNumebers() {
                System.out.println("Original Nums " + phoneNumber1 + "and " + phoneNumber2);
            }
        }

        PhoneNumber num1 = new PhoneNumber(phoneNumber1);
        PhoneNumber num2 = new PhoneNumber(phoneNumber2);

        num1.printOriginalNumebers();
        if (num1.getNum() == null)
            System.out.println("Invalid num1");
        else
            System.out.println("formatted num1 " + phoneNumber1);

        num2.printOriginalNumebers();
        if (num2.getNum() == null)
            System.out.println("Invalid num2");
        else
            System.out.println("formatted num1 " + phoneNumber1);

    }

    public static void main(String[] args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
