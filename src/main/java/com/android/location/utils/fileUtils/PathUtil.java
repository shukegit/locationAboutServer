package com.android.location.utils.fileUtils;

import org.springframework.beans.factory.annotation.Value;

public class PathUtil {
	
	@Value("${win.base.path}")
	private static String winBassePath;
	@Value("${linux.base.path}")
	private static String linuxBassePath;
	@Value("${relative.path}")
	private static String relativePath;
	
	
	private static String seperator = System.getProperty("file.separator");
	
	/**
	 * 照片存贮的总的根路径
	 * E:/pictures/SchoolStorePhotos/
	 * @return
	 */
	public static String getBaseImgPath() {	
		
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = winBassePath;
		} else {
			basePath = linuxBassePath;
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	/**
	 * 不同用户图片的子路径upload/item/headImg
	 */
	
	public static String getRelativeImagePath(String sessionStr) {
		String imagePath = relativePath + sessionStr + "/";
		return imagePath.replace("/", seperator);
	}
	
	
}
