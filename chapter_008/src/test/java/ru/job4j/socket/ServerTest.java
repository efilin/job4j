package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.lineSeparator();

    public void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskExitAndReturnPhrase() throws IOException {
        testServer("exit", String.format("Oracle is closing%s%s", LN, LN));
    }

    @Test
    public void whenSayHello() throws IOException {
        testServer(Joiner.on(LN).join(
                "hello",
                "exit"
        ), Joiner.on(LN + LN).join(
                "Hello, dear friend, I'm a oracle.",
                "Oracle is closing",
                ""
        ));
    }

    @Test
    public void whenSayUnknownWords() throws IOException {
        testServer(Joiner.on(LN).join(
                "what time is it now",
                "exit"
        ), Joiner.on(LN + LN).join(
                "I don't understand you",
                "Oracle is closing",
                ""
        ));
    }
}