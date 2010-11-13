package springsprout.common.web.support;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import springsprout.common.util.ImageUtils;

@Service
public class ThumbnailGenerator {

	private int width = 64;
	private int height = 64;
	private String imageType = "jpg";
	private boolean preserveAlpha = true;
	
	public void generate( File srcImage, File descImage) {
		try {
			preparePath( descImage.getAbsolutePath());
			ImageUtils.resizeImage(srcImage, descImage, width, height, imageType, preserveAlpha);
		} catch (IOException e) {
			new RuntimeException("Cannot Create Thumbnail!!!");
		}
	}
	
	private void preparePath(String path) {
    	File savePath = new File(path);
    	if(!savePath.exists())
    		savePath.mkdirs();
    }
}
