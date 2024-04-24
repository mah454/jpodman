package ir.moke.jpodman.utils;

import java.io.InputStream;

public class CommandExecutor {

    public static String execute(String... cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process process = builder.start();
            InputStream inputStream = process.getInputStream();
            byte[] bytes = inputStream.readAllBytes();

            return new String(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream executeToStream(String... cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process process = builder.start();
            return process.getInputStream();
        } catch (Exception e) {
            return null;
        }
    }
}
