package com.sugonYH.Chat;


import java.io.*;

public class FileRecord {

    public static void writeFile(String name,String message){
        File filePath = new File("F:\\Java\\Projectxt\\"+name+".txt");
        try {
            if (!filePath.exists()){
                filePath.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath,true));
            out.write(message);
            out.newLine();
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
