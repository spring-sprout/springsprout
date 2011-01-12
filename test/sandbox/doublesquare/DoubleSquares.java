package sandbox.doublesquare;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: Whiteship
 * Date: 11. 1. 10
 * Time: 오후 1:27
 */
public class DoubleSquares {
    public Integer getCountOfDoubleSquares(int num) {
        Map<Integer, Answer> answerMap = new HashMap<Integer, Answer>();

        int limit = 46300; //(int) Math.sqrt(2147483647)
        for(int i = 0 ; i < limit  ; i++) {
            int firstNum = i;
            int remain = num - (firstNum*firstNum);
            double sqrt = Math.sqrt(remain);
            int secondNum = (int)sqrt;

            Answer existingAnswer = answerMap.get(firstNum+secondNum);
            if(existingAnswer == null && sqrt - secondNum == 0) {
                System.out.println(num + " is " + firstNum + " , " + secondNum);
                Answer answer = new Answer(firstNum, secondNum);
                answerMap.put(firstNum+secondNum, answer);
            }
        }
        return answerMap.size();
    }
}
