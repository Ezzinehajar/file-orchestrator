package com.pictet.technologies.ezzine.fileorchestrator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

	@PostMapping(path = "/file-upload")
	public String text(@RequestBody(required = false) ParametreDTO parametre) {
		return null;
	}

}
