package springsprout.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	
	public static String stripHTML(String txt){
		if(txt == null){
			return "";
		}
		return txt.replaceAll("<([^>]+)>"," ");

	}

	public static String convertDate2String(Date date) {
		SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();
		int nowYear = calendar.get(calendar.YEAR);
		calendar.setTime(date);

		if(nowYear != calendar.get(calendar.YEAR)){
			return fullDateFormat.format(date);
		}
		int second = Math.round((now - calendar.getTimeInMillis())/1000);
		
		if(second <= 5){
			return "now";
		}else if(second <= 60){
			return String.format("%d초전", second);
		}else if(second < (60*60)){
			return String.format("%s분전", (int)Math.floor(second / 60));
		}else if(second < (24*60*60)){
			return String.format("%s시간전", (int)Math.floor(second / (60 * 60)));
		}else{
			return dateFormat.format(date);
		}
	}
}
