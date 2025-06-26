package com.jimboyz.cims.model.server;

import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class FileServer {

    public FileServer() {

//        try(FileInputStream fos = new FileInputStream(FileConfig.getFileConfig())){
//            Properties prop = new Properties();
//            prop.load(fos);
//            String host = prop.getProperty("host");
//            if(!host.equalsIgnoreCase("localhost") || !host.equalsIgnoreCase("127.0.0.1") ) {
//                if(host.equals(getIp())) {
//                    initServer();
//                }
//            }
//        }catch(IOException e) {
//            e.printStackTrace();
//        }

        initServer();
    }

    private void initServer() {

        try {System.out.println("Server");

            HttpServer server = HttpServer.create(new InetSocketAddress(777), 0);
            server.createContext("/", new FileHandler());

            server.setExecutor(null);
            server.start();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String getIp() {
        String hostAddress = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostAddress = inetAddress.getHostAddress();
            if(!hostAddress.equalsIgnoreCase("localhost") || !hostAddress.equalsIgnoreCase("127.0.0.1")) {
                return hostAddress;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Temp
    class FileConfig {
        public static String getFileConfig() {
            return System.getProperty("user.dir") + File.separator +  "client.conf";
        }
    }
}
