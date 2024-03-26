package org.s811286.client.gui;

import org.s811286.client.domain.Client;
import org.s811286.server.gui.ServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VolnaAdmGUI extends JFrame implements ClientView {
    public static final String FRM_TITLE = "ВОЛНА Административная панель " +
            "         (© Шамсутдинов Ринат Фаритович)";
    public static final String TXT_TOOLTIP = "Текстовое поле";
    public static final String BTN_TOOLTIP = "Послать сообщение";
    public static final String FD = "Dialog";

    private static final int LEFT = 300;
    public static final int TOP = 100;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 700;

    private Client client;

    JButton btnAISSave, btnReload, btnSaveProm,
            btnDopDel, btnDopSave;
    JButton btnLoadOld, btnLoad, btnLoadHead, btnSave,
            btnSaveVolna, btnExit;
    JTextField txtNewVersOE, txtDateOE, txtNewVers,
            txtDate, txtNumbDop;
    JTextArea tarDop;
    JLabel lblNewVersOE, lblDateOE, lblAICVers, lblVers;
    JLabel lblNewVers, lblDate, lblNumbDop, lblDop;
    JLabel lblBlank1, lblBlank2, lblBlank3, lblBlank4;
    Panel panVers, panSave, panDop, panTarDop, panList;

    public VolnaAdmGUI(ServerGUI server) {
        super(FRM_TITLE);
        client = new Client(this, server.getConnection());

        add(createMainPanel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocation(LEFT, TOP);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                txtNumbDop.requestFocusInWindow();
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

        setVisible(false);
    }

    private Component createMainPanel() {
        Panel panel = new Panel();
        panel.setBounds(5, 5, 790, 690);
        panel.setLayout(new BorderLayout());

        panel.add(createVersSavePanel(), BorderLayout.NORTH);
        panel.add(createDopPanel(), BorderLayout.CENTER);
        panel.add(createTarDopListPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createVersSavePanel() {
        Panel panel = new Panel();
        panel.setBounds(5, 25, 690, 280);
        panel.setLayout(new BorderLayout());

        panel.add(createVersPanel(), BorderLayout.WEST);
        panel.add(createSavePanel(), BorderLayout.EAST);
        return panel;
    }

    private Component createTarDopListPanel() {
        Panel panel = new Panel();
        panel.setBounds(5, 0, 595, 300);
        panel.setLayout(new BorderLayout());

        panel.add(createTarDopPanel(), BorderLayout.WEST);
        panel.add(createListPanel(), BorderLayout.EAST);
        return panel;
    }

    private Component createDopPanel() {
        panDop = new Panel();
        panDop.setLayout(null);
        panDop.setBounds(5, 5, 790, 50);

        btnDopDel = new JButton("Очистить дополнения в ВОЛНЕ");
        btnDopDel.setToolTipText(BTN_TOOLTIP);
        btnDopDel.setFont(new Font(FD, Font.BOLD, 16));
        btnDopDel.setHorizontalAlignment(JButton.CENTER);
        btnDopDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnDopSave = new JButton("Загрузить дополнения в ВОЛНУ");
        btnDopSave.setToolTipText(BTN_TOOLTIP);
        btnDopSave.setFont(new Font(FD, Font.BOLD, 16));
        btnDopSave.setHorizontalAlignment(JButton.CENTER);
        btnDopSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnDopDel.setBounds(10, 15, 360, 40);
        btnDopSave.setBounds(380, 15, 360, 40);
        panDop.add(btnDopDel, BorderLayout.WEST);
        panDop.add(btnDopSave, BorderLayout.EAST);
        return panDop;
    }

    private Component createVersPanel() {
        panVers = new Panel();
        panVers.setLayout(null);
        panVers.setBounds(5, 25, 640, 280);

        txtNewVersOE = new JTextField("", 20);
        txtNewVersOE.setFocusable(true);
        txtNewVersOE.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtNewVersOE.setFont(new Font(FD, Font.BOLD, 14));

        txtDateOE = new JTextField("", 20);
        txtDateOE.setFocusable(true);
        txtDateOE.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtDateOE.setFont(new Font(FD, Font.BOLD, 14));

        lblBlank1 = new JLabel();
        lblBlank2 = new JLabel();
        lblBlank3 = new JLabel();
        lblBlank4 = new JLabel();
        lblNewVersOE = new JLabel("Новая версия АИС ОЭ:");
        lblDateOE = new JLabel("Дата версии АИС ОЭ:");
        lblAICVers = new JLabel("Текущая версия АИС Пром:");
        lblVers = new JLabel("");
        lblNewVers = new JLabel("Новая версия АИС Пром:");
        lblDate = new JLabel("Дата версии АИС Пром:");
        lblNumbDop = new JLabel("Количество дополнений:");
        lblDop = new JLabel("дополнений");

        btnAISSave = new JButton("Загрузить АИС Пром в ВОЛНУ");
        btnAISSave.setFont(new Font(FD, Font.BOLD, 16));
        btnAISSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNewVers.requestFocusInWindow();
            }
        });

        txtNewVers = new JTextField("", 20);
        txtNewVers.setFocusable(true);
        txtNewVers.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtNewVers.setFont(new Font(FD, Font.BOLD, 14));

        txtDate = new JTextField("", 20);
        txtDate.setFocusable(true);
        txtDate.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtDate.setFont(new Font(FD, Font.BOLD, 14));

        txtNumbDop = new JTextField("", 20);
        txtNumbDop.setFocusable(true);
        txtNumbDop.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        txtNumbDop.setFont(new Font(FD, Font.BOLD, 14));

        lblNewVersOE.setBounds(10, 5, 160, 40);
        txtNewVersOE.setBounds(180, 5, 160, 40);
        lblBlank1.setBounds(350, 5, 280, 40);
        lblDateOE.setBounds(10, 50, 160, 40);
        txtDateOE.setBounds(180, 50, 160, 40);
        lblBlank2.setBounds(350, 50, 280, 40);
        lblAICVers.setBounds(10, 95, 160, 40);
        lblVers.setBounds(180, 95, 160, 40);
        btnAISSave.setBounds(350, 95, 280, 40);
        lblNewVers.setBounds(10, 140, 160, 40);
        txtNewVers.setBounds(180, 140, 160, 40);
        lblBlank3.setBounds(350, 140, 280, 40);
        lblDate.setBounds(10, 185, 160, 40);
        txtDate.setBounds(180, 185, 160, 40);
        lblBlank4.setBounds(350, 185, 280, 40);
        lblNumbDop.setBounds(10, 230, 160, 40);
        txtNumbDop.setBounds(180, 230, 60, 40);
        lblDop.setBounds(250, 230, 280, 40);
        panVers.add(lblNewVersOE);
        panVers.add(txtNewVersOE);
        panVers.add(lblBlank1);
        panVers.add(lblDateOE);
        panVers.add(txtDateOE);
        panVers.add(lblBlank2);
        panVers.add(lblAICVers);
        panVers.add(lblVers);
        panVers.add(btnAISSave);
        panVers.add(lblNewVers);
        panVers.add(txtNewVers);
        panVers.add(lblBlank3);
        panVers.add(lblDate);
        panVers.add(txtDate);
        panVers.add(lblBlank4);
        panVers.add(lblNumbDop);
        panVers.add(txtNumbDop);
        panVers.add(lblDop);
        return panVers;
    }

    private Component createSavePanel() {
        panSave = new Panel();
        panSave.setLayout(null);
        panSave.setBounds(650, 25, 140, 280);

        btnReload = new JButton("Обновить");
        btnReload.setToolTipText(BTN_TOOLTIP);
        btnReload.setFont(new Font(FD, Font.BOLD, 16));
        btnReload.setHorizontalAlignment(JButton.CENTER);
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnSaveProm = new JButton("Сохранить");
        btnSaveProm.setToolTipText(BTN_TOOLTIP);
        btnSaveProm.setFont(new Font(FD, Font.BOLD, 16));
        btnSaveProm.setHorizontalAlignment(JButton.CENTER);
        btnSaveProm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnReload.setBounds(5, 185, 130, 40);
        btnSaveProm.setBounds(5, 230, 130, 40);
        panSave.add(btnReload);
        panSave.add(btnSaveProm);
        return panSave;
    }

    private Component createTarDopPanel() {
        panTarDop = new Panel();
        panTarDop.setLayout(null);
        panTarDop.setBounds(5, 0, 580, 300);

        tarDop = new JTextArea();
        tarDop.setEditable(false);
        tarDop.setText("");
        tarDop.setFont(new Font(FD, Font.BOLD, 14));
        tarDop.setLineWrap(true);
        tarDop.setWrapStyleWord(true);
        tarDop.setFocusable(false);


        JScrollPane panTarDopScr = new JScrollPane(tarDop);
        panTarDopScr.setBounds(5, 0, 575, 300);

        panTarDop.add(panTarDopScr);
        return panTarDop;
    }

    private Component createListPanel() {
        panList = new Panel();
        panList.setLayout(null);
        panList.setBounds(585, 0, 200, 310);

        btnLoadOld = new JButton("Список до сохран.");
        btnLoadOld.setToolTipText(BTN_TOOLTIP);
        btnLoadOld.setFont(new Font(FD, Font.BOLD, 16));
        btnLoadOld.setHorizontalAlignment(JButton.CENTER);
        btnLoadOld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnLoad = new JButton("Текущий список");
        btnLoad.setToolTipText(BTN_TOOLTIP);
        btnLoad.setFont(new Font(FD, Font.BOLD, 16));
        btnLoad.setHorizontalAlignment(JButton.CENTER);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnLoadHead = new JButton("Новый список");
        btnLoadHead.setToolTipText(BTN_TOOLTIP);
        btnLoadHead.setFont(new Font(FD, Font.BOLD, 16));
        btnLoadHead.setHorizontalAlignment(JButton.CENTER);
        btnLoadHead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnSave = new JButton("Сохранить");
        btnSave.setToolTipText(BTN_TOOLTIP);
        btnSave.setFont(new Font(FD, Font.BOLD, 16));
        btnSave.setHorizontalAlignment(JButton.CENTER);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnSaveVolna = new JButton("Загрузить в ВОЛНУ");
        btnSaveVolna.setToolTipText(BTN_TOOLTIP);
        btnSaveVolna.setFont(new Font(FD, Font.BOLD, 16));
        btnSaveVolna.setHorizontalAlignment(JButton.CENTER);
        btnSaveVolna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientGUI();
            }
        });
        btnExit = new JButton("Выход");
        btnExit.setToolTipText(BTN_TOOLTIP);
        btnExit.setFont(new Font(FD, Font.BOLD, 16));
        btnExit.setHorizontalAlignment(JButton.CENTER);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarDop.setText("");
                messageClientSaveGUI();
            }
        });

        btnLoadOld.setBounds(0, 0, 195, 40);
        btnLoad.setBounds(0, 45, 195, 40);
        btnLoadHead.setBounds(0, 90, 195, 40);
        btnSave.setBounds(0, 135, 195, 40);
        btnSaveVolna.setBounds(0, 180, 195, 60);
        btnExit.setBounds(0, 255, 195, 40);
        panList.add(btnLoadOld);
        panList.add(btnLoad);
        panList.add(btnLoadHead);
        panList.add(btnSave);
        panList.add(btnSaveVolna);
        panList.add(btnExit);
        return panList;
    }

    public void messageClientGUI() {
        client.messageClientLog();
    }

    public void messageClientSaveGUI() {
        client.messageClientSave("");
    }

    @Override
    public void appendLogOnClient(String text) {
        tarDop.append(text);
    }
}
