package org.s811286;

import org.s811286.client.gui.ClientGUI;
import org.s811286.client.gui.VolnaAdmGUI;
import org.s811286.server.gui.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        VolnaAdmGUI volnaAdmGUI = new VolnaAdmGUI(serverGUI);
        ClientGUI clientGUI = new ClientGUI(serverGUI, volnaAdmGUI);
    }
}
