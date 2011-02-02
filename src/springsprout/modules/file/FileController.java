package springsprout.modules.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import springsprout.common.web.view.FileView;
import springsprout.domain.UploadFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired @Qualifier("fileService") FileService fileService;
    @Autowired FileView fileView;

	@RequestMapping("/list")
	public String list(Model model) {
        model.addAttribute("fileList", fileService.list());
		return "file/list";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "file/upload";
	}
	
	@RequestMapping(value = "/uploadPop", method = RequestMethod.GET)
	public String uploadPop() {
		return "file/uploadPop";
	}
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("uploadfile") MultipartFile multipartFile, Model model, HttpSession session) throws IOException {
    	if(multipartFile.getSize() == 0)
    		return "file/upload";

        model.addAttribute(fileService.upload(multipartFile));
		session.setAttribute("SESSION_FLASH_MSG", "파일 업로드가 완료되었습니다.");
    	return "file/uploadresult";
    }
    
	@RequestMapping(value = "/fileuploadPop", method = RequestMethod.POST)
    public String fileUploadPop(@RequestParam("uploadfile") MultipartFile multipartFile, Model model) throws IOException {
    	if(multipartFile.getSize() == 0)
    		return "file/uploadPop";

        model.addAttribute(fileService.upload(multipartFile));
    	return "file/uploadresultPop";
    }
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public View fileDownload(@RequestParam("id") int id, @RequestHeader("User-Agent") String userAgent, Model model) throws IOException {
    	UploadFile uploadFile = fileService.getUploadFileById(id);
        File file = new File(uploadFile.getSavedFileName());
		String fileName = uploadFile.getFileName();

		if (userAgent.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "UTF-8"); // 파일명이 깨지기 때문
		}
		fileName = encoding(fileName,"utf-8");

		model.addAttribute("downloadFile",file);
		model.addAttribute("fileName", fileName);
		return fileView;
    }
	
	//한글명 첨부파일 다운로드 시 사용하는  함수
	private String encoding(String value, String encoding) {
		byte b[];
	    try {
	        b = value.getBytes(encoding);
	    } catch (Exception ex) {
	        b = value.getBytes();
	    }
	    
	    char c[] = new char[b.length];
	    for (int i=0;i<b.length;i++){
	        c[i]=(char)(((char)b[i])&0xff);
	    }
	    
	    return new String(c);
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		fileService.delete(id);
		return "redirect:/file/list";
	}
	
}

