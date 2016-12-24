package com.tj.remoting;

import java.util.concurrent.ExecutorService;

import com.tj.remoting.common.RequestProcessor;
import com.tj.remoting.exception.RemotingConnectException;
import com.tj.remoting.exception.RemotingSendRequestException;
import com.tj.remoting.exception.RemotingTimeoutException;
import com.tj.remoting.protocol.RemotingCommand;

public interface RemotingClient extends RemotingService {
	public RemotingCommand invokeSync(final String addr,
			final RemotingCommand request, final long timeoutMillis)
			throws InterruptedException, RemotingConnectException,
			RemotingSendRequestException, RemotingTimeoutException;

	public void invokeAsync(final String addr, final RemotingCommand request,
			final long timeoutMillis, final InvokeCallback invokeCallback)
			throws InterruptedException, RemotingConnectException, RemotingTimeoutException,
			RemotingSendRequestException;

	public void invokeOneway(final String addr, final RemotingCommand request,
			final long timeoutMillis) throws InterruptedException,
			RemotingConnectException,
			RemotingTimeoutException, RemotingSendRequestException;

	public void registerProcessor(final int requestCode,
			final RequestProcessor processor, final ExecutorService executor);
}