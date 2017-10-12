package stringcounter;

public class StringClass {


    public static int isWord(String sentence) {

        if (sentence == null || sentence.isEmpty()) {
            return 0;
        } else {
            String[] words = sentence.split("\\s+");
            return words.length;
        }
    }

}
