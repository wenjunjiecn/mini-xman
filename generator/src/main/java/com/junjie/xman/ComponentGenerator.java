package com.junjie.xman;

import com.junjie.xman.util.CMDUtil;
import com.junjie.xman.util.GeneratorUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComponentGenerator {

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

    public static void depositeComponent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the component you need to deposit:");
        String name = scanner.nextLine();
        String output = CMDUtil.excuteCMDCommand("deposit.bat " + name);
        System.out.println(output);
        GeneratorUtil.addComponentToFilelist(name);
        try {
            GeneratorUtil.stripDuplicatesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialNewComponent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the new component name:");
        String name = scanner.nextLine();
        initializeNewComponent(name);
    }

    private static void initializeNewComponent(String name) {
        System.out.println("The component (" + name + ") is creating....");
        GeneratorUtil.copyDir("templateComponent", name);
        Path filepath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component/MyComponent.java");
        File file = new File(String.valueOf(filepath));
        Path newpath = Paths.get(System.getProperty("user.dir"), name, "src/main/java/com/junjie/xman/component", GeneratorUtil.upperFirstCase(name) + "Component.java");
        file.renameTo(new File(String.valueOf(newpath)));
        GeneratorUtil.replaceFileText(newpath, "MyComponent", GeneratorUtil.upperFirstCase(name) + "Component");
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "templateComponent", name);
        GeneratorUtil.replaceFileText(Paths.get(name, "pom.xml"), "//main-class", "com.junjie.xman.component." + GeneratorUtil.upperFirstCase(name) + "Component");
        System.out.println("The component (" + name + ") have been created, Please go to the file under this path and add your own business code: " + newpath );
    }
}
