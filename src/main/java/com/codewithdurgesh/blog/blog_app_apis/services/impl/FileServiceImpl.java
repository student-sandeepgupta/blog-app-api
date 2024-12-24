package com.codewithdurgesh.blog.blog_app_apis.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.blog_app_apis.services.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
   // file name
   String name = file.getOriginalFilename();
   //abc.png

   //random name genration file
   String randomId = UUID.randomUUID().toString();
    @SuppressWarnings("null")
String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
   
   //full path
   String filePath = path + File.separator+fileName1;

   // create folder if file created
   File f = new File(path);
   if(!f.exists()){
    f.mkdirs();
   }
Files.copy(file.getInputStream(),Path.of(filePath));
return name;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
    String fullPath= path+File.separator+fileName;
    InputStream is = new FileInputStream(fullPath);
// db logic to return inputstream
    return is;
    
    }

}
