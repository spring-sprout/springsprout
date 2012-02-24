package sandbox.stediousstudent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Whiteship
 * Date: 11. 1. 10
 * Time: 오후 2:14
 */
public class Student {
    public String study(String words) {
        String[] wordList = words.split(" ");
//        System.out.println(wordList.length);

        List<String> sortedWordList = Arrays.asList(wordList);
        Collections.sort(sortedWordList, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if(s1.startsWith(s2))
                    return -1;
                else if(s2.startsWith(s1))
                    return 1;
                else
                    return s1.compareTo(s2);
            }
        });

//        System.out.println(sortedWordList);

        String result = "";
        for(String word : sortedWordList){
            result += word;
        }

        System.out.println(result);
        return result;
    }
}
