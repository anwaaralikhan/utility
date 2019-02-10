package com.dc.spring.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.spring.utility.FileUtility;

@RestController
public class FileController {
	@Autowired
	private FileUtility fileUtility;
	
	@GetMapping(value="download")
	public void download(String downloadUrl) {
		fileUtility.download(downloadUrl);
	}
}
