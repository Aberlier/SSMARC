package com.ssmarc.utils;

import java.io.*;

/**
 * 
 * 类说明：文件（文件夹）的移动，删除，复制
 * 
 * @author lzy
 * @version Version 1.0
 * @since JDK 1.6
 */
public abstract class FileOper {
	/**
	 * 删除文件（夹）
	 * 
	 * @param file文件
	 *            （夹）
	 */
	public static void delete(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
		} else {
			for (File f : file.listFiles()) {
				delete(f);
			}
			file.delete();
		}
	}

	/**
	 * 复制文件（夹）到一个目标文件夹
	 * 
	 * @param resFile
	 *            源文件（夹）
	 * @param objFolderFile
	 *            目标文件夹
	 * @throws IOException
	 */
	public static void copy(File resFile, File objFolderFile)
			throws IOException {
		if (!resFile.exists())
			return;
		if (!objFolderFile.exists())
			objFolderFile.mkdirs();
		if (resFile.isFile()) {
			File objFile = new File(objFolderFile.getPath() + File.separator
					+ resFile.getName());
			// 复制文件到目标地
			InputStream ins = new FileInputStream(resFile);
			FileOutputStream outs = new FileOutputStream(objFile);
			byte[] buffer = new byte[1024 * 512];
			int length;
			while ((length = ins.read(buffer)) != -1) {
				outs.write(buffer, 0, length);
			}
			ins.close();
			outs.flush();
			outs.close();
		} else {
			String objFolder = objFolderFile.getPath() + File.separator
					+ resFile.getName();
			File _objFolderFile = new File(objFolder);
			_objFolderFile.mkdirs();
			for (File sf : resFile.listFiles()) {
				copy(sf, new File(objFolder));
			}
		}
	}

	/**
	 * 将文件（夹）移动到令一个文件夹
	 * 
	 * @param resFile
	 *            源文件（夹）
	 * @param objFolderFile
	 *            目标文件夹
	 * @throws IOException
	 */
	public static void move(File resFile, File objFolderFile)
			throws IOException {
		copy(resFile, objFolderFile);
		delete(resFile);
	}

	/**
	 * 将文件对象转换成字节数组
	 * 
	 * @param file
	 *            文件对象
	 * @return 将文件对象转换成字节数组值
	 * @throws IOException
	 *             文件操作异常
	 */
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		// Get the size of the file
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	/**
	 * 根据文件路径获取文件夹下的所有文件名称
	 * 
	 * @param filePath
	 *            指定的文件路径 例如：E:/remark/0ff2c8de-e4a5-41b9-a60b-9dac1c1c9ba7/
	 * @return 文件夹下所有的文件名称的集合
	 */
	public static String[] getFileList(String filePath) {
		// 实例化File对象
		File file = new File(filePath);
		// 获取指定文件路径下的所有文件名称
		String[] files = file.list();
		return files;
	}

	/**
	 * 根据获取的字节流信息和输出文件路径生成文件信息
	 * 
	 * @param bytes
	 *            字节流信息
	 * @param outPath
	 *            输出文件路径
	 * @return 生成文件的执行结果
	 * @throws IOException
	 *             文件操作异常
	 */
	public static void generatorFile(byte[] bytes, String outPath)
			throws Exception {
		// 创建输出流对象
		FileOutputStream out = null;

		// 将字节流对象写入到文件中
		try {
			// 实例化输出流对象
			out = new FileOutputStream(outPath);
			out.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
					// 关闭输出流对象
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
