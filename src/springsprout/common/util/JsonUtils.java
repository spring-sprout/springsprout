package springsprout.common.util;

import static springsprout.common.SpringSprout2System.CLEAN_KEY;

import org.springframework.ui.Model;

public class JsonUtils {
	
	public static void cleanModel(Model model){
		model.asMap().clear();
        model.addAttribute(CLEAN_KEY,"");
	}
}
