package com.yuansfer.payment.utils;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuansfer.payment.exception.YuanpayException;

import okhttp3.CipherSuite;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;

public class OkHttpUtils {
	private static final OkHttpClient okHttpClient;
	private static final Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);
	
	static {
		ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
			    .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
			    .cipherSuites(
			          CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
			          CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
			          CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
			    .build();
		X509TrustManager x509TrustManager = systemDefaultTrustManager();
		HTTPSSocketFactory httpsSocketFactory = new HTTPSSocketFactory(x509TrustManager);
		okHttpClient = new OkHttpClient.Builder()
			    .connectionSpecs(Collections.singletonList(spec))
			    .retryOnConnectionFailure(false)
			    .connectionPool(new ConnectionPool(3, 45, TimeUnit.SECONDS))
			    .connectTimeout(10, TimeUnit.SECONDS)
			    .readTimeout(20, TimeUnit.SECONDS)
			    .writeTimeout(20, TimeUnit.SECONDS)
			    .sslSocketFactory(httpsSocketFactory, x509TrustManager)
			    .build();
		okHttpClient.dispatcher().setMaxRequestsPerHost(1000);
		okHttpClient.dispatcher().setMaxRequests(1000);
	}
	
	private static X509TrustManager systemDefaultTrustManager() {
	    try {
	      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
	          TrustManagerFactory.getDefaultAlgorithm());
	      trustManagerFactory.init((KeyStore) null);
	      TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
	      if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
	        throw new IllegalStateException("Unexpected default trust managers:"
	            + Arrays.toString(trustManagers));
	      }
	      return (X509TrustManager) trustManagers[0];
	    } catch (GeneralSecurityException e) {
	      throw new AssertionError(); // The system has no TLS. Just give up.
	    }
	}
	
	public static String doGet(String url, Map<String, String> params) {
        StringBuffer sb = new StringBuffer(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
               // .addHeader("token", "123456")
                .url(sb.toString())
                .build();
        return respStr(request);
    }
	
	public static String doJsonPost(String url, String jsonParams) {
        @SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return respStr(request);
    }

	private static String respStr(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if ((status / 100) != 2) {
            	StringBuilder builder = new StringBuilder();
            	builder.append("okHttp3 post response error:")
            			.append(" http code:")
            			.append(status);
            	if (response.isSuccessful()) {
                    builder.append(" body:").append(response.body().string());
                }
            	String msg = builder.toString();
            	logger.error(msg);
            	throw new YuanpayException(msg);
            }
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (YuanpayException e) {
        	throw e;
        } catch (Exception e) {
            logger.error("okHttp3 post error", e);
            throw new YuanpayException(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

}
