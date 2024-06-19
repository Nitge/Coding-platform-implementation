package com.mysite.manage.file;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FtpClientUtil {
    private final FTPClient ftpClient;

    public FtpClientUtil(String host, int port, String username, String password) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(host, port);
        ftpClient.login(username, password);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
    }

    public boolean uploadFile(String remotePath, String localPath) throws IOException {
        try(FileInputStream fis = new FileInputStream(localPath)) {
            return ftpClient.storeFile(remotePath, fis);
        }
    }

    public void disconnect() throws IOException {
        if(ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

}
