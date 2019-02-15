package com.dc.spring.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class FileUtility {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtility.class);
	
	@Autowired
	private Environment environment;
	
	public void download(String downloadUrl, String destination, boolean overrdie) {
		LOGGER.info("entered download :: " + downloadUrl);
		URL website = null;
		InputStream inputStream = null;
		try {
			website = new URL(downloadUrl);
			String fileName = getFileName(downloadUrl);
			inputStream = website.openStream();
			Files.copy(inputStream, Paths.get(destination+"/"+fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		finally{
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error(e.getLocalizedMessage(), e);
				}
			}
		}
		
		LOGGER.info("exit download");
	}
	
	public void download(String downloadUrl) {
		final String fileLocation = environment.getProperty("default.file.download.location");
		download(downloadUrl,fileLocation,true);
	}

	public static String getFileName(String downloadURL) throws UnsupportedEncodingException {
		LOGGER.info("entered getFileName :" + downloadURL);
		String baseName = FilenameUtils.getBaseName(downloadURL);
		String extension = FilenameUtils.getExtension(downloadURL);
		String fileName = baseName + "." + extension;

		int questionMarkIndex = fileName.indexOf("?");
		if (questionMarkIndex != -1) {
			fileName = fileName.substring(0, questionMarkIndex);
		}
		fileName = fileName.replaceAll("-", "");
		
		String response = URLDecoder.decode(fileName, "UTF-8"); 
		LOGGER.info("fileName :" + response);
		return response;
	}
}
