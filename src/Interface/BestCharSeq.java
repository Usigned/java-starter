package Interface;


public class BestCharSeq implements java.lang.CharSequence {

    char[] chars;

    public BestCharSeq (String words) {
        char[] temp = words.toCharArray();
        chars = new char[temp.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = temp[chars.length - i -1];
        }
    }

    @Override
    public int length() {
        return chars.length;
    }

    public int fromEnd(int i) {
        return chars.length -1 -i;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index > chars.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return chars[fromEnd(index)];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result += chars[i];
        }
        return new BestCharSeq(result);
    }

    @Override
    public String toString() {
        String result = "";
        for (char aChar : chars) {
            result += aChar;
        }
        return result;
    }

    public static void main(String[] args) {
        String a = "abcdefghijklmn";
        BestCharSeq seq = new BestCharSeq(a);
        System.out.println(seq.toString());

    }
}
