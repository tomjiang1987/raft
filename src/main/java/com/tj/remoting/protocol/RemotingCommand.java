package com.tj.remoting.protocol;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.annotation.JSONField;
import com.tj.Constants;
import com.tj.RemotingSerializable;
import com.tj.remoting.exception.RemotingCommandException;

public class RemotingCommand {
    private static AtomicInteger requestId = new AtomicInteger(0);
    private RemotingHeader header;
    private int packetLength;
    private int headerLength;
    /**
     * the body of the remote command
     */
    private transient byte[] body;

    public RemotingCommand(RemotingHeader hdr) {
    	this.header = hdr;
    }
    
    public static RemotingCommand createRequestCommand(int code){
        RemotingHeader hdr = RemotingCommand.createRemotingHeader(code, requestId.incrementAndGet(), RemotingCommandType.REQUEST_COMMAND.getType());
        return new RemotingCommand(hdr);
    }
    
    private byte[] buildHeader() {
    	//assert header not null 
        return RemotingSerializable.encode(header);
    }

    public ByteBuffer encodeHeader() {
        return encodeHeader(this.body != null ? this.body.length : 0);
    }

    /**
     * 只打包Header，body部分独立传输
     */
    public ByteBuffer encodeHeader(final int bodyLength) {
    	
    	int magicCodeSize = 2;
        int totalLengthSize = 4;
        int headerLengthSize = Constants.HEADER_LENGTH_BYTE_COUNT;
        
        byte[] headerData = this.buildHeader();

        ByteBuffer result = ByteBuffer.allocate(magicCodeSize + totalLengthSize + headerLengthSize + headerData.length);

        //put magic code
        result.putShort(Constants.MAGIC_CODE);
        // put total length
        int totalLength = headerLengthSize + headerData.length + bodyLength;
        result.putInt(totalLength);

        // put header length
        result.putShort((short)headerData.length);

        // put header data
        result.put(headerData);

        result.flip();

        return result;
    }

    public static RemotingCommand decode(final byte[] array) throws RemotingCommandException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        return decode(byteBuffer);
    }

    public static RemotingCommand decode(final ByteBuffer byteBuffer) throws RemotingCommandException {
        short magicCode = byteBuffer.getShort();//magic code
        int packetTotalLength= byteBuffer.getInt();// the total length of the packet
        int headerLength = byteBuffer.getShort();//the header length

        //valid the magic code
        if(magicCode != Constants.MAGIC_CODE){
        	throw new RemotingCommandException(String.format("request/response command magic code is invalid. %s is not match the correct code %s", magicCode, Constants.MAGIC_CODE));
        }   

        byte[] headerData = new byte[headerLength];
        byteBuffer.get(headerData);

        int bodyLength = packetTotalLength - Constants.HEADER_LENGTH_BYTE_COUNT - headerLength;
        byte[] bodyData = null;
        if (bodyLength > 0) {
            bodyData = new byte[bodyLength];
            byteBuffer.get(bodyData);
        }

        RemotingCommand cmd = new RemotingCommand(RemotingSerializable.decode(headerData,RemotingHeader.class));
        cmd.body = bodyData;

        return cmd;
    }

    public void markResponseType() {
        this.header.setRemotingType(RemotingCommandType.RESPONSE_COMMAND.getType());
    }

    /*@JSONField(serialize = false)*/
    public boolean isResponseType() {
        return this.header.getRemotingType() == RemotingCommandType.RESPONSE_COMMAND.getType();
    }

    public void markOnewayRPC() {
        /*int bits = 1 << RPC_ONEWAY;
        this.flag |= bits;*/
    }

    /*@JSONField(serialize = false)*/
    public boolean isOnewayRPC() {
        /*int bits = 1 << RPC_ONEWAY;
        return (this.flag & bits) == bits;*/
        return false;
    }

    public int getCode() {
        return this.header.getCode();
    }

    public void setCode(int code){
        this.header.setCode(code);
    }

    @JSONField(serialize = false)
    public RemotingCommandType getType() {
        if (this.isResponseType()) {
            return RemotingCommandType.RESPONSE_COMMAND;
        }
        return RemotingCommandType.REQUEST_COMMAND;
    }

    public byte[] getBody(){
        return this.body;
    }

    public void setBody(byte[] body){
        this.body = body;
    }

    public static RemotingCommand createResponseCommand(int code,int requestId){
        RemotingHeader hdr = RemotingCommand.createRemotingHeader(code, requestId, RemotingCommandType.RESPONSE_COMMAND.getType());
        return new RemotingCommand(hdr);
    }

    public RemotingHeader getHeader(){
        return this.header;
    }

    public void setHeader(RemotingHeader header){
        this.header = header;
    }

    public int getRequestId(){
        return this.header == null?0:this.header.getRequestId();
    }

    public void setRequestId(int requestId){
        if(null !=  this.header){
            this.header.setRequestId(requestId);
        }
    }

    /**
     * create the remote header
     */
    private static RemotingHeader createRemotingHeader(int code, int requestId, int commandType) {
        RemotingHeader hdr = new RemotingHeader();
        hdr.setCode(code);
        hdr.setRemotingType(commandType);
        hdr.setRequestId(requestId);
        hdr.setLanguageCode(LanguageCode.JAVA.getCode());
        hdr.setVersion(1);
        return hdr;
    }

    /**
     * create the response command
     * @param code the response code
     * @param requestId the request id of this response
     * @return the response command
     */
    public static RemotingCommand createResponseCommand(int code,byte[] body,int requestId){

        RemotingCommand command = RemotingCommand.createResponseCommand(code,requestId);
        command.body = body;
        return command;
    }

    public int getPacketLength() {
        return packetLength;
    }

    public void setPacketLength(int packetLength) {
        this.packetLength = packetLength;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }
}