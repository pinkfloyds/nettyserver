package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.util.SessionUtil.getChannel
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * 发送授权码
 */
@ChannelHandler.Sharable
object SendAuthority : SimpleChannelInboundHandler<UploadRequestPacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: UploadRequestPacket?) {
        getChannel("")
    }

}