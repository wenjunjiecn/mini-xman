package com.junjie.xman;

import com.junjie.xman.util.GeneratorUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SelectorDeployGenerator {

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

    public static void initial() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the deployment phase selector name:");
        String name = scanner.nextLine();
        System.out.println("The deployment phase selector (" + name + ") is creating....");
        GeneratorUtil.copyDir("templateSelectorDeployment", name);
        Path filepath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component/MySelectorDeployment.java");
        File file = new File(String.valueOf(filepath));
        Path newpath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component", GeneratorUtil.upperFirstCase(name) + "Component.java");
        file.renameTo(new File(String.valueOf(newpath)));
        GeneratorUtil.replaceFileText(newpath, "MySelectorDeployment", GeneratorUtil.upperFirstCase(name) + "Component");
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "templateSelectorDeployment", name);
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "//main-class", "com.junjie.xman.component." + GeneratorUtil.upperFirstCase(name) + "Component");
        System.out.println("Please enter the component name and port you want to use for the selection. For example, comp1:12222;comp2:13333");
        String input = scanner.nextLine();
        String[] comps = input.split(";");
        System.out.println("please enter all the condition, like \"1\".equals(request.getParameterByKey(\"bankid\"))");
        List<String> added_condition_code = new ArrayList();

        for(int i = 0; i < comps.length; ++i) {
            String[] item = comps[i].split(":");
            String comp_name = item[0];
            int port = Integer.parseInt(item[1]);
            System.out.println(String.format("for %s in port: %s, what's the condition", comp_name, String.valueOf(port)));
            String con = scanner.nextLine();
            if (i == 0) {
                added_condition_code.add(String.format("if(%s){request.addParameter(\"dest_port\",\"%s\");}", con, String.valueOf(port)));
            } else {
                added_condition_code.add(String.format("else if(%s){request.addParameter(\"dest_port\",\"%s\");}", con, String.valueOf(port)));
            }
        }

        String codes = (String)added_condition_code.stream().filter(Objects::nonNull).collect(Collectors.joining("\n"));
        GeneratorUtil.replaceFileText(newpath, "//condition-slot", codes);
    }
}
