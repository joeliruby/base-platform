package com.matariky.automation.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	private static final int BUFFER_SIZE = 2 * 1024;

	public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException {

		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
			long end = System.currentTimeMillis();
			System.out.println("COMPRESSION COMPLETED IN:" + (end - start) + " ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 递归压缩方法
	 * 
	 * @param sourceFile       源文件
	 * @param zos              zip输出流
	 * @param name             压缩后的 Name
	 * @param KeepDirStructure  Wether 保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩Failed)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) {
		byte[] buf = new byte[BUFFER_SIZE];
		FileInputStream in = null;
		try {
			if (sourceFile.isFile()) {
				// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
				zos.putNextEntry(new ZipEntry(name));
				// copy文件到zip输出流中
				int len;
				in = new FileInputStream(sourceFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				// Complete the entry

			} else {
				File[] listFiles = sourceFile.listFiles();
				if (listFiles == null || listFiles.length == 0) {
					// 需要保留原来的文件结构时,需要对空文件夹进行处理
					if (KeepDirStructure) {
						// 空文件夹的处理
						zos.putNextEntry(new ZipEntry(name + "/"));
						// 没有文件，不需要文件的copy
						zos.closeEntry();
					}

				} else {
					for (File file : listFiles) {
						// 判断 Wether 需要保留原来的文件结构
						if (KeepDirStructure) {
							// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
							// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
							compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
						} else {
							compress(file, zos, file.getName(), KeepDirStructure);
						}

					}
				}
			}
		} catch (Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
//        finally {
//        	if (in!=null) {
//        		try {
//					in.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//        	}
//        	if(zos!=null) {
//        		try {
//					zos.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//        	}
//        }
	}

	public static void downLoadZip(String fileName, String path, HttpServletResponse response) throws Exception {
		File file = new File(path);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
		response.setContentLength((int) file.length());
		response.setContentType("application/zip");// 定义输出Type 
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];// 相当于我们的缓存
		long k = 0;// 该值用于计算当前实际Download了多少字节
		OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备Download
		// 开始循环Download
		while (k < file.length()) {
			int j = buff.read(b, 0, 1024);
			k += j;
			myout.write(b, 0, j);
		}
		myout.flush();
		buff.close();

	}

}
