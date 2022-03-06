package com.junjie.xman.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * @author wenjunjie
 * @version 1.0
 */
public class GeneratorUtil {
    public GeneratorUtil() {
    }

    public static void copyDir(String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        String[] filePath = start.list();		//获取该文件夹下的所有文件以及目录的名字
        if(!end.exists()) {
            end.mkdir();
        }
        for(String temp:filePath) {
            //查看其数组中每一个是文件还是文件夹
            if(new File(sourcePath+File.separator+temp).isDirectory()) {
                //为文件夹，进行递归
                copyDir(sourcePath+File.separator+temp, newPath+File.separator+temp);
            }else {
                //为文件则进行拷贝
                copyFile(sourcePath+File.separator+temp, newPath+File.separator+temp);
            }
        }
    }

    public static void copyFile(String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(start));
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(end))) {
            int len = 0;
            byte[] flush = new byte[1024];
            while((len=bis.read(flush)) != -1) {
                bos.write(flush, 0, len);
            }
            bos.flush();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String upperFirstCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void replaceFileText(Path path, String t1, String t2) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(path))));
            Throwable var4 = null;

            try {
                StringBuilder strBuilder = new StringBuilder();

                String temp;
                while((temp = bufReader.readLine()) != null) {
                    if (temp.indexOf(t1) != -1) {
                        temp = temp.replaceAll(t1, t2);
                    }

                    strBuilder.append(temp);
                    strBuilder.append(System.lineSeparator());
                }

                BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(path))));
                Throwable var8 = null;

                try {
                    bufWriter.write(strBuilder.toString());
                    bufWriter.flush();
                } catch (Throwable var33) {
                    var8 = var33;
                    throw var33;
                } finally {
                    if (bufWriter != null) {
                        if (var8 != null) {
                            try {
                                bufWriter.close();
                            } catch (Throwable var32) {
                                var8.addSuppressed(var32);
                            }
                        } else {
                            bufWriter.close();
                        }
                    }

                }
            } catch (Throwable e) {
                var4 = e;
                throw e;
            } finally {
                if (bufReader != null) {
                    if (var4 != null) {
                        try {
                            bufReader.close();
                        } catch (Throwable var31) {
                            var4.addSuppressed(var31);
                        }
                    } else {
                        bufReader.close();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addComponentToFilelist(String name) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("list.resp", true)));
            out.write(name + "\n");
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static List<String> getComponentList() {
        int count = 1;
        ArrayList components = new ArrayList();

        try {
            Scanner scanner = new Scanner(new File("list.resp"));

            while(scanner.hasNextLine()) {
                String item = scanner.nextLine();
                components.add(item);
                System.out.println(count++ + ":" + item);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return components;
    }

    public static String matchFileContent(String filePath, String regex) throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);

            String line;
            while((line = br.readLine()) != null) {
                if (Pattern.compile(regex).matcher(line).find()) {
                    sb.append(line);
                    sb.append("\n");
                }
            }

            reader.close();
            br.close();
            return sb.toString().trim();
        }
    }
}

