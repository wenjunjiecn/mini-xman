
package com.junjie.xman.starter;

import com.junjie.xman.util.CMDUtil;
import com.junjie.xman.util.GeneratorUtil;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartTool {

    public static void start_process() throws Exception {
        GeneratorUtil.getComponentList();
        System.out.println("please enter the component name(The list above shows only the components in the repository, but you can run the connectors defined during the deployment phase by simply entering the name):");
        Scanner scanner = new Scanner(System.in);
        final String name = scanner.nextLine();
        System.out.println("Please enter the port you want the part to run on:");
        int port = Integer.parseInt(scanner.nextLine());
        String match_content = GeneratorUtil.matchFileContent(Paths.get("repository",name, "pom.xml").toString(), "<argument>\\d*</argument>");
        GeneratorUtil.replaceFileText(Paths.get("repository",name, "pom.xml"), match_content, "<argument>" + port + "</argument>");
        (new Thread() {
            public void run() {
                String output = CMDUtil.excuteCMDCommand("cmd /k start startcomp.bat " + name);
                System.out.println(output);
            }
        }).start();
    }
}
