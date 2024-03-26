package org.s811286.client.domain;

import org.s811286.client.gui.ClientView;
import org.s811286.server.domain.ServerAdm;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientAdm {
    public static final String CONN_UP = "Вы успешно подключились!";
    public static final String CONN_DOWN = "Вы отключились!";
    public static final String CONN_NO = "    Нет подключения к серверу";

    private boolean bConnect = false;
    private String sName;
    private JPasswordField sPassw;
    private ClientView clientView;
    private ServerAdm server;

    public ClientAdm(ClientView clientView, ServerAdm server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean isbConnect() {
        return bConnect;
    }

    public boolean connectToServer(String sName, JPasswordField sPassw) {
        this.sName = sName;
        this.sPassw = sPassw;
        if (server.connectUser(this) &&
                sName.equals("Администратор") &&
                sPassw.getText().equals("P@ssw0rd")) {
            bConnect = true;

            String sMsg = "    " + LocalDateTime.now().format(DateTimeFormatter
                    .ofPattern("dd.MM.uuuu HH.mm.ss")) + "  " + CONN_UP;
            server.messageServer(sMsg);
            addLogToClient(sMsg);

            return true;
        } else {
            addLogToClient(CONN_NO);
            return false;
        }
    }

    public void disconnectFromServer() {
        bConnect = false;
        String sMsg = "    " + LocalDateTime.now().format(DateTimeFormatter
                .ofPattern("dd.MM.uuuu HH.mm.ss")) + "  " + CONN_DOWN;
        server.messageServer(sMsg);
        addLogToClient(sMsg);
    }

    public void messageClientLog() {
        if (bConnect) {

            String log = server.getLog();
            if (log != null) {
                addLogToClient(log);
            }

        } else {
            addLogToClient(CONN_NO);
        }
    }

    public void messageClientSave(String text) {
        if (bConnect) {
            server.messageServerSave("");
        } else {
            addLogToClient(CONN_NO);
        }
    }

    private void addLogToClient(String text) {
        clientView.appendLogOnClient(text + "\n");
    }
}
