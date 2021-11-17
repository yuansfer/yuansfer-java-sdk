package com.yuansfer.payment.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HTTPSSocketFactory extends SSLSocketFactory{
	private final SSLSocketFactory sslSocketFactory;
	

	public HTTPSSocketFactory(X509TrustManager x509TrustManager) {
		SSLContext sslcontext = null;  
        try {  
            sslcontext = SSLContext.getInstance("TLS");  
            sslcontext.init(null,  
            		new TrustManager[]{x509TrustManager},  
                    new java.security.SecureRandom());
            
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        }  
		sslSocketFactory = sslcontext.getSocketFactory();
	}
	
	@Override
	public String[] getDefaultCipherSuites() {
		return sslSocketFactory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return sslSocketFactory.getSupportedCipherSuites();
	}

	private void setSocket(SSLSocket sslSocket, String host) {
		SSLParameters params = sslSocket.getSSLParameters();
		
		if (params.getServerNames() == null || params.getServerNames().isEmpty()) {
	    	SNIHostName serverName = new SNIHostName(host);
	    	List<SNIServerName> serverNames = new ArrayList<>(1);
	    	serverNames.add(serverName);
	    	params.setServerNames(serverNames);
	    	sslSocket.setSSLParameters(params);
		}
	}
	 
	private void setSocket(SSLSocket sslSocket, InetAddress host) {
		SSLParameters params = sslSocket.getSSLParameters();
		
		if (params.getServerNames() == null || params.getServerNames().isEmpty()) {
	    	SNIHostName serverName = new SNIHostName(host.getHostName());
	    	List<SNIServerName> serverNames = new ArrayList<>(1);
	    	serverNames.add(serverName);
	    	params.setServerNames(serverNames);
	    	sslSocket.setSSLParameters(params);
		}
	}
	
	@Override
	public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		SSLSocket sslSocket =  (SSLSocket)sslSocketFactory.createSocket(s, host, port, autoClose);
		setSocket(sslSocket, host);
    	return sslSocket;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		SSLSocket sslSocket =  (SSLSocket)sslSocketFactory.createSocket(host, port);
		setSocket(sslSocket, host);
    	return sslSocket;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
			throws IOException, UnknownHostException {
		SSLSocket sslSocket =  (SSLSocket)sslSocketFactory.createSocket(host, port, localHost, localPort);
		setSocket(sslSocket, host);
    	return sslSocket;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		SSLSocket sslSocket =  (SSLSocket)sslSocketFactory.createSocket(host, port);
		setSocket(sslSocket, host);
    	return sslSocket;
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		SSLSocket sslSocket =  (SSLSocket)sslSocketFactory.createSocket(address, port, localAddress, localPort);
		setSocket(sslSocket, address);
    	return sslSocket;
	}

}
