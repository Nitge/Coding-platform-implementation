package com.mysite.manage.file;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    public void saveFile(Integer id, String code){
        try(FileWriter fw = new FileWriter("code/"+id.toString()+".py")){
            fw.write(code);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
