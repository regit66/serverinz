package test.connection;

import connection.TCPServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionTest {


    @Test
    public void testConnectionWork() throws IOException {

        TCPServer app =new TCPServer();

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost" , 20000));
        PrintWriter s_out = null;
        BufferedReader s_in = null;
        s_out = new PrintWriter( socket.getOutputStream(), true);
        s_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response;

        int t=0;
        t = s_in.read();

        Assert.assertEquals(114, t);

    }
}