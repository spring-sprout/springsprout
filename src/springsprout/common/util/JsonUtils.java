package springsprout.common.util;

import org.springframework.ui.Model;

import static springsprout.common.SpringSprout2System.CLEAN_KEY;

public class JsonUtils {
	
	public static void cleanModel(Model model){
		model.asMap().clear();
        model.addAttribute(CLEAN_KEY,"");
	}
}
