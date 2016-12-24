package com.tj.remoting;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutorService;

import com.tj.remoting.common.RequestProcessor;
import com.tj.remoting.exception.RemotingSendRequestException;
import com.tj.remoting.exception.RemotingTimeoutException;
import com.tj.remoting.protocol.RemotingCommand;

public interface RemotingServer extends RemotingService {
	/**
	 * get the server's listening port
	 * 
	 * @return the listening port of the server
	 */
	public int localPort();

	public RemotingCommand invokeSync(final Channel channel, final RemotingCommand request,
            final long timeoutMillis) throws InterruptedException, RemotingSendRequestException,
            RemotingTimeoutException;


    public void invokeAsync(final Channel channel, final RemotingCommand request, final long timeoutMillis,
            final InvokeCallback invokeCallback) throws InterruptedException, RemotingTimeoutException, RemotingSendRequestException;


    public void invokeOneway(final Channel channel, final RemotingCommand request, final long timeoutMillis)
            throws InterruptedException, RemotingTimeoutException,
            RemotingSendRequestException;

	public void registerProcessor(final int requestCode,
			final RequestProcessor processor, final ExecutorService executor);
	
	 public void registerDefaultProcessor(final RequestProcessor processor, final ExecutorService executor);
}