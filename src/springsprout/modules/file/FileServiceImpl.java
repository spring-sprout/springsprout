package springsprout.modules.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import springsprout.common.SpringSprout2System;
import springsprout.common.util.RandomStringUtils;
import springsprout.domain.UploadFile;
import springsprout.service.security.SecurityService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired FileRepository repository;
	@Autowired SecurityService securityService;

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

	public void delete(int id) {
		UploadFile uploadFile = repository.getById(id);
		File file = new File(uploadFile.getSavedFileName());
		file.delete();
		repository.delete(uploadFile);
	}

	public UploadFile upload(MultipartFile multipartFile) {
        String savePath = SpringSprout2System.DEFAULT_STORE_BASE;
    	preparePath(savePath);
    	File saveFile = new File(savePath + File.separator + multipartFile.getOriginalFilename() + "_" + RandomStringUtils.getRandomMD5());

        try {
            FileCopyUtils.copy(multipartFile.getBytes(), saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UploadFile uploadFile = new UploadFile();
		uploadFile.setFileName(multipartFile.getOriginalFilename());
		uploadFile.setSavedFileName(saveFile.getAbsolutePath());
		uploadFile.setFileSize(multipartFile.getSize());
		add(uploadFile);
		return uploadFile;
	}

    private void preparePath(String path) {
    	File savePath = new File(path);
    	if(!savePath.exists())
    		savePath.mkdirs();
    }


}
