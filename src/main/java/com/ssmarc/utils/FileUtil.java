/**
 * 公司名称  : 唐山启奥（Shinow）
 * 项目名称   : shinowit
 * JDK 版本号        : jdk1.6.2
 * 版本号                 : 1.0
 * 创建时间：2015年9月8日 下午4:09:37
 * 创建人：姚吟
 **/
package com.ssmarc.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类说明：文件操作类
 * 
 * @author 姚吟
 * @version Version 1.0
 * @since JDK 1.6
 */
public class FileUtil extends FileOper {

	/** */
	/**
	 * 文件重命名
	 * 
	 * @param path
	 *            文件目录
	 * @param oldname
	 *            原来的文件名
	 * @param newname
	 *            新文件名
	 */
	public static File renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + File.separator + oldname);
			File newfile = new File(path + File.separator + newname);
			if (!oldfile.exists()) {
				return null;// 重命名文件不存在
			}
			if (newfile.exists()) {// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				// System.out.println(newname+"已经存在！");
			} else {
				oldfile.renameTo(newfile);
			}
			return newfile;
		} else {
			return null;
			// System.out.println("新文件名和旧文件名相同...");
		}
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		if (!StringUtils.isNull(sPath)) {
			return true;
		}
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return true;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	private static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	private static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * 方法说明:获取文件的扩展名
	 * </p>
	 * 
	 * @param filename
	 * @return
	 * 
	 */
	public static String getFileExtName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(dot, filename.length());
			}
		}
		return filename;
	}

	public static String getFileNoExtName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public static Iterator<String> getRequestFilesName(
			HttpServletRequest request) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames(); // 取得request中的所有文件名
			return iter;
		}
		return null;
	}

	/**
	 * <p>
	 * 方法说明:返回request中上传的文件
	 * </p>
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public static List<MultipartFile> getRequestFiles(HttpServletRequest request) {
		List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames(); // 取得request中的所有文件名
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next()); // 取得上传文件
				if (file != null) {
					multipartFiles.add(file);
				}
			}
		}
		return multipartFiles;
	}

	public static MultipartFile getFirstRequestFile(HttpServletRequest request) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames(); // 取得request中的所有文件名
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next()); // 取得上传文件
				return file;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 方法说明:上传文件到指定路径
	 * </p>
	 * 
	 * @param file
	 * @param realPathDir
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void uploadFile(MultipartFile file, String realPathDir)
			throws IllegalStateException, IOException {
		File localFile = new File(realPathDir);
		if (!localFile.getParentFile().exists()) {
			localFile.getParentFile().mkdirs();
		} else if (!localFile.exists()) {
			localFile.mkdirs();
		}
		file.transferTo(localFile);
	}

	public static void moveFolder(String oldPath, String newPath)
			throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 删除文件夹
	 * 
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			// System.out.println("删除文件夹操作出错");
			e.printStackTrace();

		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 * @throws Exception
	 */
	public static void copyFolder(String oldPath, String newPath)
			throws Exception {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}

			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath
						+ File.separator + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件夹
				copyFolder(oldPath + File.separator + file[i], newPath
						+ File.separator + file[i]);
			}
		}
	}
}
