package test;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {
    public static void readFileByLines(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource("add_course.lua");

        // 获得File对象，当然也可以获取输入流对象
//        try {
            //        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            File file = classPathResource.getFile();

            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        FileTest.readFileByLines("");
    }
}
