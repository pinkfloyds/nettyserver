package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.protocol.response.SuccessResponsePacket
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 节点处理器
 */
@ChannelHandler.Sharable
object NodeHandler : SimpleChannelInboundHandler<UploadRequestPacket>(), MysqlHandler {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: UploadRequestPacket) {

    }

    fun onlineState(ctx: ChannelHandlerContext, msg: UploadRequestPacket) {

        GlobalScope.launch {
            //            println(msg)
        }

        ctx.channel().writeAndFlush(SuccessResponsePacket(msg.index))

        this.channelRead0(ctx, msg)
    }
}