package com.hjj.www.message;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author haojunjie
 * @create 2020-06-05 10:16
 */
public class InetAddressMain {
	public static void main(String[] args) throws UnknownHostException {
		String[] databasePath = "jdbc:postgresql://postgresql.host:9999,postgresql.host2:9999/ngsoc".split("://");

		System.out.println(hostnameChangeIp(databasePath[1]));
	}


	public static String hostnameChangeIp(String dataBaseUrl) {
		String dataBaseUrl1 = dataBaseUrl.split(",")[0];
		String dataBaseUrl2 = dataBaseUrl.split(",")[1];
		String dataBaseIp1 = null;
		String dataBaseIp2 = null;
		String hosts1 = dataBaseUrl1.split(":")[0];
		String hosts2 = dataBaseUrl2.split(":")[0];

		try {
			dataBaseIp1 = InetAddress.getByName(hosts1).getHostAddress();
			dataBaseIp2 = InetAddress.getByName(hosts2).getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(hosts1);
		System.out.println(dataBaseIp1);

		dataBaseUrl = dataBaseUrl.replaceFirst(hosts1, dataBaseIp1);
		dataBaseUrl = dataBaseUrl.replaceFirst(hosts2, dataBaseIp2);

		return dataBaseUrl;
	}

}
