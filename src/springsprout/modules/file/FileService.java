package springsprout.modules.file;

import org.springframework.web.multipart.MultipartFile;
import springsprout.domain.UploadFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 7. 24
 * Time: 오후 2:49:44
 */
public interface FileService {
    UploadFile getUploadFileById(int id);

    void add(UploadFile uploadFile);

    List<UploadFile> list();

    void delete(int id);

    UploadFile upload(MultipartFile multipartFile);
}
