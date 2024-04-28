package ir.moke.jpodman.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CommandExecutor {

    public static String execute(String... cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process process = builder.start();
            InputStream inputStream = process.getInputStream();
//            byte[] bytes = inputStream.readAllBytes();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            return "";
//            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream executeToStream(String... cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process process = builder.start();
            return process.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void pipExecute(InputStream inputStream, String... cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process process = builder.start();
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(inputStream.readAllBytes());
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
