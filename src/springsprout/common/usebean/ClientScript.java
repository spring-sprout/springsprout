package springsprout.common.usebean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/21/12
 * Time: 5:10 PM
 */
public class ClientScript {
	/**
	 * HEAD TAG 사이.
	 */
	public static final String POS_HEAD  = "HEAD";

	/**
	 * BODY TAG 바로 다음.
	 */
	public static final String POS_BEGIN = "BEGIN";

	/**
	 * BODY TAG 끝나기 바로 직전
	 */
	public static final String POS_END   = "END";

	/**
	 * jQuery ready fn 안.
	 */
	public static final String POS_READY = "READY";
	
	private String scriptTagFormat = "<script type=\"text/javascript\">\n" +
								     "/*<![CDATA[*/\n"+
								     "%s\n"+
			                         "/*]]>*/\n" +
									 "</script>";
	private String jQueryReadyFnFormat = "$(document).ready( function(){\n"+
									     "%s\n"+
			                             "});";

	private Map<String,ArrayList<String>> scriptStore = new HashMap<String,ArrayList<String>>();


	public void setHeadScript(String script){
		setScript(POS_HEAD,script);
	}

	public void setBeginScript(String script){
		setScript(POS_BEGIN,script);
	}

	public void setEndScript(String script){
		setScript(POS_END,script);
	}

	public void setReadyScript(String script){
		setScript(POS_READY,script);
	}
	
	public String getHeadScript(){
		return makeScriptTag(POS_HEAD);
	}

	public String getBeginScript(){
		return makeScriptTag(POS_BEGIN);
	}
	
	public String getEndScript(){
		return makeScriptTag(POS_END);
	}
	
	public String getReadyScript(){
		return makeScriptTag(POS_READY);
	}

	private void setScript(String pos, String script){
		ArrayList<String> scripts = scriptStore.get(pos);
		if(scripts == null){
			scripts  = new ArrayList<String>();
		}
		scripts.add(script);
		scriptStore.put(pos,scripts);
	}

	private String makeScriptTag(String pos){
		ArrayList<String> scripts = scriptStore.get(pos);
		String scriptTag = "";
		if(scripts != null){
			StringBuilder scriptBuilder = new StringBuilder("");
			for(String _script : scripts){
				scriptBuilder.append(_script+"\n");
			}
			scripts.clear();
			scriptStore.remove(pos);
			scriptTag = String.format(scriptTagFormat,
					(pos == POS_READY?
							String.format(jQueryReadyFnFormat,scriptBuilder.toString()) :
							scriptBuilder.toString()));
		}
		return scriptTag;
	}
}
