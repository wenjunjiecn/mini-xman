package com.junjie.xman.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @author wenjunjie
 * @version 1.0
 */
public class CMDUtil {
    public CMDUtil() {
    }

    public static String excuteCMDCommand(String cmdCommand) {
        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String excuteBatFile(String file, boolean isCloseWindow) {
        String cmdCommand = null;
        if (isCloseWindow) {
            cmdCommand = "cmd.exe /c " + file;
        } else {
            cmdCommand = "cmd.exe /k " + file;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static String excuteBatFileWithNewWindow(String file, boolean isCloseWindow) {
        String cmdCommand = null;
        if (isCloseWindow) {
            cmdCommand = "cmd /c start" + file;
        } else {
            cmdCommand = "cmd /k start" + file;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }
}

