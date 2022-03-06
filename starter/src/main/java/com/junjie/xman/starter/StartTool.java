
package com.junjie.xman.starter;

import com.junjie.xman.util.CMDUtil;
import com.junjie.xman.util.GeneratorUtil;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartTool {

    public static void start_process() throws Exception {
        GeneratorUtil.getComponentList();
        System.out.println("please choose the component in the repository");
        Scanner scanner = new Scanner(System.in);
        final String name = scanner.nextLine();
        System.out.println("please enter the port:");
        int port = Integer.parseInt(scanner.nextLine());
        String match_content = GeneratorUtil.matchFileContent(Paths.get(name, "pom.xml").toString(), "<argument>\\d*</argument>");
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), match_content, "<argument>" + port + "</argument>");
        (new Thread() {
            public void run() {
                String output = CMDUtil.excuteCMDCommand("cmd /k start startcomp.bat " + name);
                System.out.println(output);
            }
        }).start();
    }
}
