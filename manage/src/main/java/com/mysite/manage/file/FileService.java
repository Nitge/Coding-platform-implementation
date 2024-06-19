package com.mysite.manage.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    public void saveFile(Integer id, String code){
        String directoryPath = "code";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();  // 디렉토리가 존재하지 않으면 생성
        }
        try(FileWriter fw = new FileWriter(directoryPath + "/" + id.toString() + ".py")){
            fw.write(code);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
