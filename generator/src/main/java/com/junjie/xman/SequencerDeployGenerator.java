package com.junjie.xman;

import com.junjie.xman.util.GeneratorUtil;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequencerDeployGenerator {

    public static void initial() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the deployment phase sequencer:");
        String name = scanner.nextLine();
        System.out.println("The deployment phase sequencer(" + name + ") is creating....");
        GeneratorUtil.copyDir("templateSequencerDeployment", name);
        Path filepath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component/MySequencerDeployment.java");
        File file = new File(String.valueOf(filepath));
        Path newpath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component", GeneratorUtil.upperFirstCase(name) + "Component.java");
        file.renameTo(new File(String.valueOf(newpath)));
        GeneratorUtil.replaceFileText(newpath, "MySequencerDeployment", GeneratorUtil.upperFirstCase(name) + "Component");
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "templateSequencerDeployment", name);
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "//main-class", "com.junjie.xman.component." + GeneratorUtil.upperFirstCase(name) + "Component");
        System.out.println("Please enter the component and port you want to connect in sequence(These component will be connected in order to execute). For example, comp1:12222;comp2:13333");
        String input = scanner.nextLine();
        String[] comps = input.split(";");
        List<String> ports = new ArrayList();

        for(int i = 0; i < comps.length; ++i) {
            String[] item = comps[i].split(":");
            String comp_name = item[0];
            String port = item[1];
            ports.add(port);
        }

        String added_code = (String)ports.get(0);

        for(int i = 1; i < ports.size(); ++i) {
            added_code = added_code + " , " + (String)ports.get(i);
        }

        added_code = "int[] dest_ports={" + added_code + "};";
        GeneratorUtil.replaceFileText(newpath, "//code-slot", added_code);
    }
}
