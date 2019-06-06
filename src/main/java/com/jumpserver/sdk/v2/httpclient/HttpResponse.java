package com.jumpserver.sdk.v2.httpclient;


import java.io.Closeable;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HttpResponse extends Closeable {

	<T> T getEntity(Class<T> returnType);

	<T> List<T> getEntityList(Class<T> returnType);

	<T> T readEntity(Class<T> typeToReadAs);

	<T> List<T> readEntityList(Class<T> typeToReadAs);

	int getStatus();

	String getContentType();

	String getStatusMessage();

	InputStream getInputStream();

	String header(String name);

	Map<String, String> headers();
}
