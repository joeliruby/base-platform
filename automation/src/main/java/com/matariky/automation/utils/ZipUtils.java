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
	 * Recursive compression method
	 * 
	 * @param sourceFile       Source file
	 * @param zos              zip output stream
	 * @param name             Compressed name
	 * @param KeepDirStructure  Wether retains the original directory structure, true: retain the directory structure;
	 *                         FALSE: All files run to the root directory of the compressed package (note: without retaining the directory structure, there may be files of the same name, which will compressFailed)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) {
		byte[] buf = new byte[BUFFER_SIZE];
		FileInputStream in = null;
		try {
			if (sourceFile.isFile()) {
				// Add a ZIP entity to the output stream to the ZIP output stream, and the name of the file of the ZIP entity in the constructor
				zos.putNextEntry(new ZipEntry(name));
				// copyFile to ZIP output stream
				int len;
				in = new FileInputStream(sourceFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				// Complete the entry

			} else {
				File[] listFiles = sourceFile.listFiles();
				if (listFiles == null || listFiles.length == 0) {
					// When you need to keep the original file structure, you need to deal with the folder
					if (KeepDirStructure) {
						// The processing of an empty folder
						zos.putNextEntry(new ZipEntry(name + "/"));
						// No file, no file Copy
						zos.closeEntry();
					}

				} else {
					for (File file : listFiles) {
						// Judging that WETHER needs to retain the original file structure
						if (KeepDirStructure) {
							//Note: File.getName () You need to bring the name of the Father Fold
							// Otherwise, the original file structure cannot be retained in the compressed package, that is, all files run to the roots of the compressed package.
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
	}

	public static void downLoadZip(String fileName, String path, HttpServletResponse response) throws Exception {
		File file = new File(path);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
		response.setContentLength((int) file.length());
		response.setContentType("application/zip");// Define output Type
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];// Equivalent to our cache
		long k = 0;// This value is used to calculate how many bytes of the current actual download
		OutputStream myout = response.getOutputStream();// Obtain output stream from the Response object, prepare DOWNLOAD
		// Start loopDownload
		while (k < file.length()) {
			int j = buff.read(b, 0, 1024);
			k += j;
			myout.write(b, 0, j);
		}
		myout.flush();
		buff.close();

	}

}
