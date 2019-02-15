package com.dc.spring.utility.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dc.spring.utility.FileUtility;

@RestController
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileUtility fileUtility;
	
	@GetMapping(value="download")
	public void download(
				@RequestParam(value="downloadUrl", required=true) String downloadUrl,
				@RequestParam(value="destination", required=false) String destination,
				@RequestParam(value="override"   , required=false) boolean override) {
		LOGGER.info(" Filedownload :: " + downloadUrl);
		fileUtility.download(downloadUrl, destination, override);
	}
}
