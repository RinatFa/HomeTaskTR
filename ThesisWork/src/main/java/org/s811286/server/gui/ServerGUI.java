package org.s811286.server.gui;

import org.s811286.server.domain.Server;
import org.s811286.server.repository.FileStorage;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView {
    public static final String FD = "Dialog";

    public static final int LEFT = 300;
    public static final int TOP = 200;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;

    private Server server;

    JTextArea tarNoteS;
    JPanel panNoteS;

    public ServerGUI() {
        server = new Server(this, new FileStorage());

        add(createMainPanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(LEFT, TOP);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        server.start();

        setVisible(false);
    }

    public Server getConnection() {
        return server;
    }

    private Component createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createNotePanel(), BorderLayout.CENTER);
        return panel;
    }

    private Component createNotePanel() {
        panNoteS = new JPanel(new GridLayout(1, 1));

        tarNoteS = new JTextArea();
        tarNoteS.setEditable(false);
        tarNoteS.setFont(new Font(FD, Font.BOLD, 14));
        tarNoteS.setLineWrap(true);
        tarNoteS.setWrapStyleWord(true);
        tarNoteS.setFocusable(false);

        panNoteS.add(new JScrollPane(tarNoteS));
        return panNoteS;
    }

    @Override
    public void appendLogOnServer(String text) {
        tarNoteS.append(text);
    }
}
