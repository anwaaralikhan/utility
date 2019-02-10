package com.dc.spring.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;

public class FileUtility {
	
	public void download(String downloadUrl) {
		URL website = null;
		InputStream inputStream = null;
		try {
			website = new URL(downloadUrl);
			String fileName = getFileName(downloadUrl);
			inputStream = website.openStream();
			Files.copy(inputStream, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getFileName(String downloadURL) {
		String baseName = FilenameUtils.getBaseName(downloadURL);
		String extension = FilenameUtils.getExtension(downloadURL);
		String fileName = baseName + "." + extension;

		int questionMarkIndex = fileName.indexOf("?");
		if (questionMarkIndex != -1) {
			fileName = fileName.substring(0, questionMarkIndex);
		}

		fileName = fileName.replaceAll("-", "");
		return URLDecoder.decode(fileName, "UTF-8");
	}
}
