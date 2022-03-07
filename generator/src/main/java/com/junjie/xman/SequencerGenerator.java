//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.junjie.xman;

import com.junjie.xman.util.CMDUtil;
import com.junjie.xman.util.GeneratorUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SequencerGenerator {

    public static void main(String[] args) {
        initialNewSequencerComposite();
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

    public static void depositeSequencerComposite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the design phase sequencer you need to deposit:");
        String name = scanner.nextLine();
        String output = CMDUtil.excuteCMDCommand("cmd /c start deposit.bat " + name);
        System.out.println(output);
        GeneratorUtil.addComponentToFilelist(name);
    }

    public static void initialNewSequencerComposite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the design phase sequencer:");
        String name = scanner.nextLine();
        System.out.println("The following list is the components you can use:");
        List<String> componentList = getComponentList();
        System.out.println("Please enter the components you want to connect in order (enter the serial numbers according to the list above). For example 1,2,3");
        String input = scanner.nextLine();
        int[] component_indexs = Arrays.asList(input.split(",")).stream().mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < component_indexs.length; ++i) {
            component_indexs[i] = component_indexs[i]-1;
        }

        initialSeqComposite(name, componentList, component_indexs);
    }

    private static void initialSeqComposite(String name, List<String> componentList, int[] component_indexs) {
        System.out.println("The sequencer compsite (" + name + ") is creating....");
        GeneratorUtil.copyDir("templateSequencer", name);
        Path filepath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component/MySequencer.java");
        File file = new File(String.valueOf(filepath));
        Path newpath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component", GeneratorUtil.upperFirstCase(name) + "Component.java");
        file.renameTo(new File(String.valueOf(newpath)));
        GeneratorUtil.replaceFileText(newpath, "MySequencer", GeneratorUtil.upperFirstCase(name) + "Component");
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "templateSequencer", name);
        List<String> codes = new ArrayList();

        for(int i = 0; i < component_indexs.length; ++i) {
            String component_name = (String)componentList.get(component_indexs[i]);
            codes.add("request = response.toRequest();");
            codes.add(String.format("%s %s = new %s();", GeneratorUtil.upperFirstCase(component_name) + "Component", component_name, GeneratorUtil.upperFirstCase(component_name) + "Component"));
            codes.add(String.format("response = %s.run(request);", component_name));
        }

        String added_code = (String)codes.stream().filter(Objects::nonNull).collect(Collectors.joining("\n\t\t"));
        System.out.println(added_code);
        GeneratorUtil.replaceFileText(newpath, "//code-slot", added_code);
        List<String> imports = new ArrayList();

        for(int i = 0; i < component_indexs.length; ++i) {
            String component_name = (String)componentList.get(component_indexs[i]);
            imports.add("import com.junjie.xman.component." + GeneratorUtil.upperFirstCase(component_name) + "Component;");
        }

        String added_imports = (String)imports.stream().filter(Objects::nonNull).collect(Collectors.joining("\n"));
//        System.out.println(added_code);
        GeneratorUtil.replaceFileText(newpath, "//import-slot", added_imports);
        List<String> dependencies = new ArrayList();

        for(int i = 0; i < component_indexs.length; ++i) {
            String component_name = (String)componentList.get(component_indexs[i]);
            dependencies.add("<dependency>\n            <groupId>com.junjie.xman</groupId>\n" + String.format("            <artifactId>%s</artifactId>\n", component_name) + "            <version>1.0-SNAPSHOT</version>\n            <scope>compile</scope>\n        </dependency>\n");
        }

        String added_dependencies = (String)dependencies.stream().filter(Objects::nonNull).collect(Collectors.joining("\n\t\t"));
//        System.out.println(added_dependencies);
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "<!--dependency-slot-->", added_dependencies);
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "//main-class", "com.junjie.xman.component." + GeneratorUtil.upperFirstCase(name) + "Component");
        System.out.println("The sequencer composite (" + name + ") have been created, please going to " + newpath + " to add your own code.");
    }
}
