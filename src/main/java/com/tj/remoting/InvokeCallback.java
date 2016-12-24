package com.tj.remoting;

import com.tj.remoting.common.ResponseFuture;

public interface InvokeCallback {
	public void operationComplete(final ResponseFuture responseFuture);
}