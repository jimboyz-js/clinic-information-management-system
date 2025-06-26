package com.jimboyz.cims;

import com.jimboyz.cims.err.ErrorDialog;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class GetMacAddr {

    public static String getMac() {

        String mac_id = null;
        try{
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            if(network != null){
                byte[] mac = network.getHardwareAddress();
                if(mac != null){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < mac.length; i++){
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length -1) ? "-" : ""));
                    }
                    mac_id = sb.toString();
                }else{
                    ErrorDialog.show("MAC Address not available...");
                }
            }else{
                ErrorDialog.show("Network Interface for the specified IP not found.");
            }
        }catch(Exception e){
            ErrorDialog.show(e);
        }
        return mac_id;
    }
}
