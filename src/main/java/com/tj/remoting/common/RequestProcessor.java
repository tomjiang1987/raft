package com.tj.remoting.common;

import io.netty.channel.ChannelHandlerContext;

import com.tj.remoting.protocol.RemotingCommand;

public interface RequestProcessor {
	public RemotingCommand processRequest(ChannelHandlerContext ctx,
			RemotingCommand request) throws Exception;
}