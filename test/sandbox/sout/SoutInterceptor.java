package sandbox.sout;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 25
 */
public class SoutInterceptor {

    private PipedInputStream pipedInputStream;
    private PrintStream originalPrint;

    public SoutInterceptor() {
        originalPrint = System.out;
        this.pipedInputStream = new PipedInputStream();
    }

    public String getMessages() throws IOException {
        byte[] messages = new byte[pipedInputStream.available()];
        pipedInputStream.read(messages, 0, messages.length);
        return new String(messages);
    }

    public void active() throws IOException {
        final PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
        PrintStream saveStream = new PrintStream(pipedOutputStream) {
            @Override
            public void println(String x) {
                try {
                    pipedOutputStream.write(x.getBytes());
                } catch (IOException e) {
                    System.out.println("error");
                }
                originalPrint.println(x);
            }
        };
        System.setOut(saveStream);
    }

}
