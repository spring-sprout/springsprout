package springsprout.modules.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import springsprout.common.web.support.ThumbnailGenerator;
import springsprout.domain.UploadFile;
import springsprout.domain.study.board.ImageFile;
import springsprout.service.security.SecurityService;

@Service("imageFileService")
@Transactional
public class ImageFileServiceImpl implements FileService {

	@Autowired FileRepository repository;
	@Autowired SecurityService securityService;
	@Autowired ThumbnailGenerator thumbnailGenerator;
	@Autowired WebApplicationContext context;
	
	private String thumbnailName_64 = "_thumbnail64";
	
	public UploadFile getUploadFileById(int id) {
		UploadFile uploadFile = repository.getById(id);
		uploadFile.setLastDownload(new Date());
		return uploadFile;
	}

	public void add(UploadFile uploadFile) {
		uploadFile.setUploader(securityService.getCurrentMember());
		uploadFile.setUploadDate(new Date());
		repository.add(uploadFile);
	}

	public List<UploadFile> list() {
		return repository.list();
	}

	/**
	 * @todo path도 없어서 안된다.
	 */
	public void delete(int id) {
		String webPath = null;
		try {
			webPath = WebUtils.getRealPath(context.getServletContext(), "/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageFile uploadFile = (ImageFile) repository.getById(id);
		deleteSaveFileFromStorage(webPath, uploadFile);
		removeThumbnailFromStorage(webPath, uploadFile);
		repository.delete(uploadFile);
	}

	private void removeThumbnailFromStorage( String webPath, ImageFile uploadFile) {
		File file = new File(webPath + "/images/userimage/" + uploadFile.getUploader().getEmail() + File.pathSeparator + uploadFile.getThumbNailName());
		file.delete();
	}

	private void deleteSaveFileFromStorage( String webPath, ImageFile uploadFile) {
		File file = new File(webPath + "/images/userimage/" + uploadFile.getUploader().getEmail() + File.pathSeparator + uploadFile.getSavedFileName());
		file.delete();
	}

	public ImageFile upload(MultipartFile multipartFile) {
		File srcImage;
		File descImage;
        try {
        	String savePath = preparePath();
        	long fileName = UUID.randomUUID().getLeastSignificantBits();
        	srcImage = makeSrcFile(savePath, fileName);
        	descImage = new File(savePath + File.separator + fileName + thumbnailName_64 + ".jpg");
            FileCopyUtils.copy(multipartFile.getBytes(), srcImage);
            generateThumbnail(srcImage, descImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageFile uploadFile = setUploadFile(multipartFile, srcImage, descImage);
		add(uploadFile);
		return uploadFile;
	}
	
	private File makeSrcFile(String savePath, long fileName) {
		File srcImage = new File(savePath + File.separator + fileName + ".jpg");
		return srcImage;
	}

	private ImageFile setUploadFile( MultipartFile multipartFile, File saveFile, File descImage) {
		ImageFile uploadFile = new ImageFile();
		uploadFile.setFileName(multipartFile.getOriginalFilename());
		uploadFile.setSavedFileName(saveFile.getName());
		uploadFile.setFileSize(multipartFile.getSize());
		uploadFile.setThumbNailName(descImage.getName());
		return uploadFile;
	}

    private String preparePath() throws FileNotFoundException {
    	String webPath = WebUtils.getRealPath(context.getServletContext(), "/");
    	File savePath = new File(webPath + "/images/userimage/" + securityService.getCurrentMember().getEmail());
    	if(!savePath.exists()) savePath.mkdirs();
    	return savePath.getAbsolutePath();
    }
    
    private void generateThumbnail( File srcImage, File descImage) {
    	thumbnailGenerator.generate(srcImage, descImage);
    }


}
