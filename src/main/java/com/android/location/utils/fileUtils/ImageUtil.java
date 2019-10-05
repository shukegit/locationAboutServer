package com.android.location.utils.fileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.android.location.utils.fileUtils.FileHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	// 通过线程的形式获取当前文件的绝对地址，即src/main.resources(classpath)的路径
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	// 定义时间格式
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	// 定义随机数.用于生成文件名
	private static final Random RANDOM = new Random();
	// 添加日志信息
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * 
	 * @param args
	 * @throws IOException
	 */
	//
	/**
	 * 这个方法生成图片的存储路径 返回的String类型的targetAddr是图片的存储路径
	 * 
	 * @param thumbnail这是组合后的img
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(File thumbnail, String targetAddr, boolean flag) {

		// 系统生成不重名的照片文件随机名
		String realFileName = getRandomFileName();
		// 获取扩展名.jpg等
		String extension = getFileExtension(thumbnail);
		// 图片的存储路径可能不存在，所以这里要生成这个路径
		makeDirPath(targetAddr);
		// 相对路径
		String relativeAddr = targetAddr + realFileName + extension;
		System.out.println("相对路径：" + relativeAddr);
		logger.debug("current realtiveAddr is:" + relativeAddr);

		// 文件路径由相对路径加根路径组成
		File dest = new File(PathUtil.getBaseImgPath() + relativeAddr);

		logger.debug("current completeAddr is:" + PathUtil.getBaseImgPath() + relativeAddr);
		System.out.println("绝对路径：" + PathUtil.getBaseImgPath() + relativeAddr);
		// 创建缩略图
		if(flag) {
			try {
				Thumbnails.of(thumbnail).size(200, 200)
						.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/sign.jpeg")), 0.25f)
						.outputQuality(0.8f).toFile(dest);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new RuntimeException("创建缩略图失败" + e.getMessage());
			}
		}
		

		// 这里为什么要返回图片的路径呢，因为shop表里有个shop_img字段，它存储的是图片的地址，因此在处理完图片之后就需要返回图片的地址
		// 为什么要返回相对地址而不是绝对地址呢，原因是我们希望程序迁移到其他电脑时依然能够读出图片，而不需要修改数据表shop_img字段
		return relativeAddr;
	}

	public static String generateNormalImg(FileHolder productImgholder, String targetAddr) {
		File file = new File(productImgholder.getFileName());
		try {
			FileUtils.copyInputStreamToFile(productImgholder.getImageInputStream(), file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 系统生成不重名的照片文件随机名
		String realFileName = getRandomFileName();
		// 获取扩展名.jpg等
		String extension = getFileExtension(file);
		// 图片的存储路径可能不存在，所以这里要生成这个路径
		makeDirPath(targetAddr);
		// 相对路径(相对路径加文件名)
		String relativeAddr = targetAddr + realFileName + extension;
		// 文件路径由相对路径加根路径组成
		File dest = new File(PathUtil.getBaseImgPath() + relativeAddr);
		// 创建缩略图
		try {
			Thumbnails.of(file).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/sign.jpeg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (Exception e) {
			throw new RuntimeException("创建缩略图失败" + e.getMessage());
		}
		//返回图片相对路径地址
		return relativeAddr;
	}

	/**
	 * 将CommonsMultipartFile转换为File类型
	 * 
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFiletoFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {

		String realFileParentPath = PathUtil.getBaseImgPath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		// 如果文件不存在，则生成该文件的路径
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件名的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(File cFile) {

		String originalFileName = cFile.getName();
		// 返回最后个点号以后的字符
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名 当前年月日时分秒 + 五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {

		// 获取随机的五位数
		int rannum = RANDOM.nextInt(89999) + 10000;
		// 按照固定格式生成当前时间
		String nowTimeStr = SIMPLE_DATE_FORMAT.format(new Date());
		return nowTimeStr + rannum;
	}

	/**
	 * 当更新图片地址的时候，我们需要删除原来的图片，因此： 判断storePath是文件路径还是目录路径 如果是文件路径则删除该文件
	 * 如果是目录路径则删除该目录下的所有文件
	 * 
	 * @param storePath
	 */
	public static void deleteFIleOrPath(String storePath) {

		File fileOrPath = new File(PathUtil.getBaseImgPath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}

	}

	// public static void main(String[] args) throws IOException {
	// Thumbnails.of(new File("E:\\pictures\\tiger.jpg")).size(600, 600)
	// .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath +
	// "/katu.jpg")), 0.25f)
	// .outputQuality(0.8f).toFile("E:\\pictures\\tigerNew.jpg");
	// }
}
