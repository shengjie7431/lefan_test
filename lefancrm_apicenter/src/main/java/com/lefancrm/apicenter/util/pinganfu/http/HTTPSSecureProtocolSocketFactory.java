package com.lefancrm.apicenter.util.pinganfu.http;


import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class HTTPSSecureProtocolSocketFactory implements ProtocolSocketFactory {//SecureProtocolSocketFactory  
    private SSLContext sslcontext = null;  
   
    private SSLContext createSSLContext() {  
        SSLContext sslcontext = null;  
        try {  
            sslcontext = SSLContext.getInstance("TLS");  
            sslcontext.init(null,  
                    new TrustManager[] { new TrustAnyTrustManager() },  
                    new java.security.SecureRandom());  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        }  
        return sslcontext;  
    }  
   
    private SSLContext getSSLContext() {  
        if (null == this.sslcontext) {  
            this.sslcontext = createSSLContext();  
        }  
        return this.sslcontext;  
    }  
   
    public Socket createSocket(Socket socket, String host, int port,  
            boolean autoClose) throws IOException, UnknownHostException {  
        return getSSLContext().getSocketFactory().createSocket(socket, host,  
                port, autoClose);  
    }  

    @Override
    public Socket createSocket(String host, int port) throws IOException,  
            UnknownHostException {  
        return getSSLContext().getSocketFactory().createSocket(host, port);  
    }  

    @Override
    public Socket createSocket(String host, int port, InetAddress clientHost,  
            int clientPort) throws IOException, UnknownHostException {  
        return getSSLContext().getSocketFactory().createSocket(host, port,  
                clientHost, clientPort);  
    }  

    @Override
    public Socket createSocket(String host, int port, InetAddress localAddress,  
            int localPort, HttpConnectionParams params) throws IOException,  
            UnknownHostException, ConnectTimeoutException {  
        if (params == null) {  
            throw new IllegalArgumentException("Parameters may not be null");  
        }  
        int timeout = params.getConnectionTimeout();  
        SocketFactory socketfactory = getSSLContext().getSocketFactory();  
        if (timeout == 0) {  
            return socketfactory.createSocket(host, port, localAddress,  
                    localPort);  
        } else {  
            Socket socket = socketfactory.createSocket();  
            SocketAddress localaddr = new InetSocketAddress(localAddress,  
                    localPort);  
            SocketAddress remoteaddr = new InetSocketAddress(host, port);  
            socket.bind(localaddr);  
            socket.connect(remoteaddr, timeout);  
            return socket;  
        }  
    } 
}