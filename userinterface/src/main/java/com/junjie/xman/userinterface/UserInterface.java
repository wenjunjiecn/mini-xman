package com.junjie.xman.userinterface;

import com.junjie.xman.ComponentGenerator;
import com.junjie.xman.SelectorDeployGenerator;
import com.junjie.xman.SequencerDeployGenerator;
import com.junjie.xman.SequencerGenerator;
import com.junjie.xman.starter.MainStarter;
import com.junjie.xman.starter.StartTool;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============MINI-XMAN============");

        while(true) {
            while(true) {
                System.out.println("please select the option");
                System.out.println("1.create atomic component");
                System.out.println("2.deposite atomic component to the repository");
                System.out.println("3.view component list");
                System.out.println("4.create sequencer composite");
                System.out.println("5.deposite sequencer composite to the repository");
                System.out.println("6.go to deployment phase");
                System.out.println("-1.Exit");
                String input = scanner.nextLine();
                int i = Integer.parseInt(input);
                if (i == 1) {
                    ComponentGenerator.initialNewComponent();
                } else if (i == 2) {
                    ComponentGenerator.depositeComponent();
                } else if (i == 3) {
                    ComponentGenerator.getComponentList();
                } else if (i == 4) {
                    SequencerGenerator.initialNewSequencerComposite();
                } else if (i == 5) {
                    SequencerGenerator.depositeSequencerComposite();
                } else if (i == 6) {
                    goToDeploymentPhase();
                } else {
                    if (i == -1) {
                        return;
                    }

                    System.out.println("The option is not supported");
                }
            }
        }
    }

    private static void goToDeploymentPhase() throws Exception {
        while(true) {
            System.out.println("========Deployment Phase=========");
            System.out.println("enter the following option");
            System.out.println("1.create selector");
            System.out.println("2.create sequencer");
            System.out.println("3.start runing component");
            System.out.println("4.run the system");
            System.out.println("-1.Back to previous menu");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int i = Integer.parseInt(input);
            if (i == 1) {
                SelectorDeployGenerator.initial();
            } else if (i == 2) {
                SequencerDeployGenerator.initial();
            } else if (i == 3) {
                StartTool.start_process();
            } else if (i == 4) {
                MainStarter.main(new String[0]);
            } else {
                if (i == -1) {
                    System.out.println("exit deployment phase, back to design phase");
                    return;
                }

                System.out.println("The option is not supported");
            }
        }
    }
}
