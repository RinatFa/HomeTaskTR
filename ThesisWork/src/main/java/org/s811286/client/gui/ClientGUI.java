package org.s811286.client.gui;

import org.s811286.client.domain.Client;
import org.s811286.server.gui.ServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    public static final String FRM_TITLE = "Административная панель Volna " +
            "         (© Шамсутдинов Ринат Фаритович)";
    public static final String TXT_TOOLTIP = "Текстовое поле";
    public static final String BTN_TOOLTIP = "Послать сообщение";
    public static final String FD = "Dialog";

    private static final int LEFT = 300;
    public static final int TOP = 200;
    public static final int WIDTH = 550;
    public static final int HEIGHT = 600;

    private Client client;
    private VolnaAdmGUI volnaAdmGUI;

    JButton btnLogin, btnLogout, btnLog, btnEmpty, btnVolna;
    JTextField txtLogin;
    JPasswordField txtPassw;
    JTextArea tarNote;
    Panel panSend, panLogin, panNote;

    public ClientGUI(ServerGUI server, VolnaAdmGUI volnaAdmGUI) {
        super(FRM_TITLE);
        this.volnaAdmGUI = volnaAdmGUI;
        client = new Client(this, server.getConnection());

        add(createMainPanel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocation(LEFT, TOP);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                txtPassw.requestFocusInWindow();
            }
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет!"};
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    client.disconnectFromServer();
                    event.getWindow().setVisible(false);
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        setVisible(true);
    }

    private Component createMainPanel() {
        Panel panel = new Panel();
        panel.setBounds(0, 10, 535, 580);
        panel.setLayout(new BorderLayout());

        panel.add(createLoginPanel(), BorderLayout.NORTH);
        panel.add(createNotePanel(), BorderLayout.CENTER);
        panel.add(createSendPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createLoginPanel() {
        panLogin = new Panel();
        panLogin.setLayout(null);
        panLogin.setBounds(0, 10, 535, 60);
        txtLogin = new JTextField("Администратор", 20);
        txtLogin.setEditable(false);
        txtLogin.setFocusable(false);
        txtLogin.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtLogin.setFont(new Font(FD, Font.BOLD, 14));
        txtPassw = new JPasswordField("", 20);
        txtPassw.setFocusable(true);
        txtPassw.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtPassw.setFont(new Font(FD, Font.BOLD, 14));
        btnLogin = new JButton("login");
        btnLogin.setFont(new Font(FD, Font.BOLD, 16));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarNote.setText("");
                client.connectToServer(txtLogin.getText(), txtPassw);
                if (client.isbConnect()) {
                    btnLogin.setEnabled(false);
                    btnLogout.setEnabled(true);
                }
                txtPassw.requestFocusInWindow();
            }
        });

        btnLogout = new JButton("logout");
        btnLogout.setFont(new Font(FD, Font.BOLD, 16));
        btnLogout.setEnabled(false);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.disconnectFromServer();
                if (!client.isbConnect()) {
                    btnLogin.setEnabled(true);
                    btnLogout.setEnabled(false);
                }
            }
        });

        btnLogout.setBounds(10, 5, 125, 50);
        txtLogin.setBounds(140, 5, 125, 50);
        txtPassw.setBounds(270, 5, 125, 50);
        btnLogin.setBounds(400, 5, 125, 50);
        panLogin.add(btnLogout);
        panLogin.add(txtLogin);
        panLogin.add(txtPassw);
        panLogin.add(btnLogin);
        return panLogin;
    }

    private Component createNotePanel() {
        panNote = new Panel();
        panNote.setLayout(null);
        panNote.setBounds(10, 110, 530, 380);

        tarNote = new JTextArea();
        tarNote.setEditable(false);
        tarNote.setText("");
        tarNote.setFont(new Font(FD, Font.BOLD, 14));
        tarNote.setLineWrap(true);
        tarNote.setWrapStyleWord(true);
        tarNote.setFocusable(false);

        JScrollPane panNoteScr = new JScrollPane(tarNote);
        panNoteScr.setBounds(10, 0, 515, 440);

        panNote.add(panNoteScr);
        return panNote;
    }

    private Component createSendPanel() {
        panSend = new Panel();
        panSend.setLayout(null);
        panSend.setBounds(0, 510, 535, 60);

        btnLog = new JButton("показать лог");
        btnLog.setToolTipText(BTN_TOOLTIP);
        btnLog.setFont(new Font(FD, Font.BOLD, 16));
        btnLog.setHorizontalAlignment(JButton.CENTER);
        btnLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarNote.setText("");
                messageClientGUI();
            }
        });

        btnEmpty = new JButton("очистить лог");
        btnEmpty.setToolTipText(BTN_TOOLTIP);
        btnEmpty.setFont(new Font(FD, Font.BOLD, 16));
        btnEmpty.setHorizontalAlignment(JButton.CENTER);
        btnEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarNote.setText("");
                messageClientSaveGUI();
            }
        });

        btnVolna = new JButton("загрузить Адм.панель");
        btnVolna.setToolTipText(BTN_TOOLTIP);
        btnVolna.setFont(new Font(FD, Font.BOLD, 16));
        btnVolna.setHorizontalAlignment(JButton.CENTER);
        btnVolna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                volnaAdmGUI.setVisible(true);
            }
        });

        btnEmpty.setBounds(10, 5, 145, 50);
        btnLog.setBounds(160, 5, 145, 50);
        btnVolna.setBounds(310, 5, 215, 50);
        panSend.add(btnEmpty);
        panSend.add(btnLog);
        panSend.add(btnVolna);
        return panSend;
    }

    public void messageClientGUI() {
        client.messageClientLog();
    }

    public void messageClientSaveGUI() {
        client.messageClientSave("");
    }

    @Override
    public void appendLogOnClient(String text) {
        tarNote.append(text);
    }
}
