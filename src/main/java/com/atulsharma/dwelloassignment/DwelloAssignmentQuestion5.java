package com.atulsharma.dwelloassignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;


public class DwelloAssignmentQuestion5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(br.readLine());
        String[] inputs = new String[lines];
        for(int i=0; i < lines; i++){
            inputs[i] = br.readLine();
        }
        DwelloAssignmentQuestion5 application = new DwelloAssignmentQuestion5();
        String[] results = application.checkIps(inputs);
        for(String result:results) {
            System.out.println(result);
        }
    }

    public String[] checkIps(String[] ip_array) {
        String[] result = new String[ip_array.length];
        for(int i=0;i<ip_array.length;i++) {
            try {
                InetAddress address = Inet4Address.getByName(ip_array[i]);
                if(!ip_array[i].equals(address.getHostAddress())) {
                    throw new Exception();
                }
                result[i] = "IPv4";
            } catch (Exception e4) {
                try {
                    InetAddress address = Inet6Address.getByName(ip_array[i]);
                    if(!ip_array[i].equals(address.getHostAddress())) {
                        throw new Exception();
                    }
                    result[i] = "IPv6";
                } catch (Exception e6) {
                    result[i] = "Neither";
                }

            }
        }
        return result;
    }
}