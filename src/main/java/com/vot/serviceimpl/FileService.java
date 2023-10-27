package com.vot.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements com.vot.service.FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//file name
		String name = file.getOriginalFilename();
		
		//file path
		String filePath=path+File.separator+name;
		
		File f=new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//file coppy
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
	
		return name;
	}

	@Override
	public InputStream getResource(String pathName, String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullname=pathName+File.separator+filename;
		
		FileInputStream is= new FileInputStream(fullname);
		
		return is;
	}
}
