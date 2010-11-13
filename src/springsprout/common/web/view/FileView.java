package springsprout.common.web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class FileView extends AbstractView {

	public FileView(){
		this.setContentType("application/octet-stream");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		File file = (File)model.get("downloadFile");
		
		response.setContentType(super.getContentType());
		response.setContentLength((int) file.length());

		response.setHeader("Content-Disposition", "attachment;filename=" + (String)model.get("fileName") + ";");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if( out != null ) out.close();
			if( fis != null ) fis.close();
		}
		
		out.flush();
		
	}

}
