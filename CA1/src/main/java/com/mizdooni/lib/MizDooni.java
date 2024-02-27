package com.mizdooni.lib;

import com.mizdooni.lib.listener.CommandListener;
import java.util.Scanner;

public class MizDooni {

    private final CommandListener commandListener;

    public MizDooni() {
        this.commandListener = CommandListener.getInstance();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String cli;
        while (!(cli = scanner.nextLine()).isEmpty()) {
            try {
                String[] parts = cli.trim().split(" ", 2);

                if (parts.length != 2) {
                    throw new IllegalArgumentException("invalid usage!\ncorrect usage: <cmd> <arg>");
                }

                String cmd = parts[0];
                String arg = parts[1];
                commandListener.run(cmd, new String[]{arg});
            } catch (Throwable exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

}
