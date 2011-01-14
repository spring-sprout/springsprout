package sandbox.doublesquare;

/**
 * User: Whiteship
 * Date: 11. 1. 10
 * Time: 오후 1:45
 */
public class Answer {

    int firstNum;
    int secondNum;

    public Answer(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (firstNum != answer.firstNum) return false;
        if (secondNum != answer.secondNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstNum;
        result = result + secondNum;
        return result;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }
}
