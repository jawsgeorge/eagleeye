package com.eagleeye.restful.model;

public class ResponseEagle {
private String responseCode;
public String getResponseCode() {
	return responseCode;
}
public void setResponseCode(String responseCode) {
	this.responseCode = responseCode;
}
public String getResponseMessage() {
	return responseMessage;
}
public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
}

private String responseMessage;

private Object object;
public Object getObject() {
	return object;
}
public void setObject(Object object) {
	this.object = object;
}


}

