package org.s811286.server.domain;

import org.s811286.client.domain.ClientAdm;
import org.s811286.server.gui.ServerView;
import org.s811286.server.repository.Repository;

import java.io.File;

public class ServerAdm {
    private static final String FILE_PATH = "src/main/java/org/s811286/server/repository/file5.txt";

    private boolean bStartstop = false;
    private ServerView serverView;
    private Repository<String> repository;

    public ServerAdm(ServerView serverView, Repository<String> repository) {
        this.serverView = serverView;
        this.repository = repository;
    }

    public void start() {
        File file = new File(FILE_PATH);
        if (file.exists() && !file.isDirectory()) {
            bStartstop = true;
        } else {
            bStartstop = false;
        }

    }

    public boolean connectUser(ClientAdm client) {
        if (!bStartstop) {
            return false;
        }
        return true;
    }

    public void messageServer(String text) {
        if (!bStartstop) {
            return;
        }
        setLog(text);
    }

    public void messageServerSave(String text) {
        if (!bStartstop) {
            return;
        }
        saveLog(text);
    }

    private void addLogToServer(String text) {
        serverView.appendLogOnServer(text + "\n");
    }

    public String getLog() {
        return (String) repository.load();
    }

    private void setLog(String text) {
        repository.save(text);
    }

    private void saveLog(String text) {
        repository.saveEmpty(text);
    }
}
