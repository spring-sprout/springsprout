package springsprout.common.util;

public class StringUtils {
	
	private StringUtils() {}
	
	public static boolean isEmpty(String val) {
		return (val == null || val.trim().length() == 0 || val.equals(""));
	}

	public static String toVariableNameStyle(String value) {
		StringBuilder result = new StringBuilder();
		String firstWord = value.substring(0, 1);
		result.append(firstWord.toLowerCase() + value.substring(1));
		return result.toString();
	}

	public static String toClassNameStyle(String value) {
		StringBuilder result = new StringBuilder();
		String firstWord = value.substring(0, 1);
		result.append(firstWord.toUpperCase() + value.substring(1));
		return result.toString();
	}

    public static String nl2br(String str) {
        str = str.replaceAll("(\r\n|\r|\n|\n\r)", "<br />");
        return str;
    }

    /**
     * 긴제목시 해당 cutByte 만큰 자르고 뒤에 ... 을 붙여준다.
     * @param strSource
     * @param cutByte
     * @return String
     */
    public static String cutBytes(String strSource, int cutByte) {
        //cutByte = 40;
        if (strSource == null) return "";
        String strPostfix = "...";
        int postfixSize = 3;
        strSource = strSource.trim();
        char[] charArray = strSource.toCharArray();
        int strIndex = 0;
        int byteLength = 0;
        for (; strIndex < strSource.length(); strIndex++) {
            int byteSize = 0;
            if (charArray[strIndex] < 256) {
                byteSize = 1;
            } else {
                byteSize = 3;
            }
            if ((byteLength + byteSize) > cutByte - postfixSize) {
                break;
            }
            byteLength = byteLength += byteSize;
        }

        if (strIndex == strSource.length())
            strPostfix = "";
        else {
            if ((byteLength + postfixSize) < cutByte)
                strPostfix = " " + strPostfix;
        }
        return strSource.substring(0, strIndex) + strPostfix;
    }

}
