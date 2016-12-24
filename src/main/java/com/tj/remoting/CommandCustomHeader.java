package com.tj.remoting;

import com.tj.remoting.exception.RemotingCommandException;

public interface CommandCustomHeader {
	public void checkFields() throws RemotingCommandException;
}