package nums;

public class Read {
    public static void main(String[] args) {
        readIntegers(args);
        readFloats(args);
        readInt(args);
    }

    private static void readIntegers(String[] args) {
        if (args.length >= 2) {
            int result = 0;
            for (String arg : args) {
                int curr;
                try {
                    curr = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    continue;
                }
                result += curr;
            }
            System.out.println(result);
        }
        else {
            System.err.println("less that two arg");
        }
    }

    private static void readFloats(String[] args) {
        if (args.length >= 2) {
            float result = 0;
            for (String arg : args) {
                float curr;
                try {
                    curr = Float.parseFloat(arg);
                } catch (NumberFormatException e) {
                    continue;
                }
                result += curr;
            }
            System.out.println(result);
        }
        else {
            System.err.println("less that two arg");
        }
    }

    private static void readInt(String ... args) {
        int sum = 0;
        for (String arg : args) {
            sum += Integer.valueOf(arg).intValue();
        }
        System.out.println(sum);
    }

}
