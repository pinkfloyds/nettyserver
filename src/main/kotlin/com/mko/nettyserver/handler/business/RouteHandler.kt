package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.protocol.response.ErrorResponsePacket
import com.mko.nettyserver.protocol.response.UnLoginResponsePacket
import com.mko.nettyserver.util.SessionUtil.hasLogin
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

@ChannelHandler.Sharable
object RouteHandler : SimpleChannelInboundHandler<UploadRequestPacket>() {
    @Throws(Exception::class)
    override fun channelRead0(ctx: ChannelHandlerContext, packet: UploadRequestPacket) {
        if (hasLogin(ctx.channel())) {
            // 已登录
            when (packet.command.first().toUByte().toInt()) {
                1 -> AuthorityHandler // 报告授权状态
                2 -> AuthorityHandler // 注册回路/分区
                3 -> AuthorityHandler // 注销回路/分区
                4 -> AuthorityHandler // 注册节点
                5 -> AuthorityHandler // 注销节点
                6 -> AuthorityHandler // 设置回路/分区名称
                7 -> AuthorityHandler // 设置节点名称
                8 -> AuthorityHandler // 设置回路/分区类型
                9 -> AuthorityHandler // 设置节点类型
                10 -> AuthorityHandler // 回路/分区在线
                11 -> AuthorityHandler // 回路/分区离线
                12 -> AuthorityHandler // 节点在线
                13 -> AuthorityHandler // 节点离线
                14 -> AuthorityHandler // 主机在线
                15 -> AuthorityHandler // 主机离线
                17 -> AuthorityHandler // 系统信息复位
                in 20..31 -> AuthorityHandler // 主机故障类别
                in 32..39 -> AuthorityHandler // 主机预警类别
                in 40..47 -> AuthorityHandler // 主机报警类别
                48 -> AuthorityHandler // 主机关键操作
                49 -> AuthorityHandler // 主机远程查询返回
                51 -> AuthorityHandler // 主机当前状态
                52 -> AuthorityHandler // 主机信息复位
                53 -> AuthorityHandler // 主机所有回路/分区信息复位
                in 60..71 -> AuthorityHandler // 回路/分区故障类别
                in 72..79 -> AuthorityHandler // 回路/分区预警类别
                in 80..87 -> AuthorityHandler // 回路/分区报警类别
                88 -> AuthorityHandler // 回路/分区关键操作
                89 -> AuthorityHandler // 回路/分区远程查询返回
                91 -> AuthorityHandler // 回路/分区当前状态
                92 -> AuthorityHandler // 回路/分区信息复位
                93 -> AuthorityHandler // 回路/分区所有节点信息复位
                in 100..111 -> AuthorityHandler // 节点故障类别
                in 112..119 -> AuthorityHandler // 节点预警类别
                in 120..127 -> AuthorityHandler // 节点报警类别
                128 -> AuthorityHandler // 主机对节点关键操作
                129 -> AuthorityHandler // 分区对节点关键操作
                130 -> AuthorityHandler // 节点远程查询返回
                131 -> AuthorityHandler // 节点远程查询命令
                132 -> AuthorityHandler // 节点当前状态
                133 -> AuthorityHandler // 节点信息复位
                else -> {
                    ctx.channel().writeAndFlush(ErrorResponsePacket(packet.index))
                    throw Exception("command out of range")
                }
            }.channelRead(ctx, packet)
        } else {
            // 未登录
            ctx.channel().writeAndFlush(UnLoginResponsePacket(packet.index))
        }
    }


}
