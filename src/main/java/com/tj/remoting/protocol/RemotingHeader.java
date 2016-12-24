package com.tj.remoting.protocol;

public class RemotingHeader {
    /**
     * the identifier of the request.
     * When the server response this request,the server should use the requestId to fill up this field.
     */
    private int requestId;

    private int version;
    
    private int flag;

    private int languageCode;
    /**
     * the remote communication type.It is a request or a response.
     */
    private int remotingType;
    
    /**
     * the request/response code.
     */
    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRemotingType() {
        return remotingType;
    }

    public void setRemotingType(int remotingType) {
        this.remotingType = remotingType;
    }

   

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(int languageCode) {
        this.languageCode = languageCode;
    }

}