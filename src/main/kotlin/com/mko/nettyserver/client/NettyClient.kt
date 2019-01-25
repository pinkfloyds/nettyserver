package com.mko.nettyserver.client

import com.mko.nettyserver.codec.MySpliter
import com.mko.nettyserver.codec.PacketCodecHandler
import com.mko.nettyserver.handler.LifeCyCleTestHandler
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

/**
 */
object NettyClient {
    private const val MAX_RETRY = 5
    private const val HOST = "127.0.0.1"
    private const val PORT = 8001

    @Throws
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val bootstrap = Bootstrap().group(NioEventLoopGroup())
                    .channel(NioSocketChannel::class.java)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(object : ChannelInitializer<SocketChannel>() {
                        override fun initChannel(ch: SocketChannel) {
//                            ch.pipeline().addLast(IMIdleStateHandler()) // 服务端空闲检测
                            ch.pipeline().addLast(LifeCyCleTestHandler())
                            ch.pipeline().addLast(MySpliter()) // 过滤消息包,同时解决粘包拆包
                            ch.pipeline().addLast(PacketCodecHandler) // 解码
                            ch.pipeline().addLast(HeartBeatTimerHandler)
                            ch.pipeline().addLast(ReturnResponseHandler)
                        }
                    })
            connect(bootstrap, HOST, PORT, MAX_RETRY)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun connect(bootstrap: Bootstrap, host: String, port: Int, retry: Int) {
        bootstrap.connect(host, port).addListener { future ->
            when {
                future.isSuccess -> println("${Date()}: 连接成功$port!")
                retry == 0 -> println("重试次数已用完，放弃连接！")
                else -> {
                    // 第几次重连
                    val order = MAX_RETRY - retry + 1
                    // 本次重连的间隔
                    val delay = 1 shl order
                    println("${LocalDateTime.now()}: 连接失败，第${order}次重连……")
                    bootstrap.config().group().schedule({ connect(bootstrap, host, port, retry - 1) }, delay.toLong(), TimeUnit
                            .SECONDS)
                }
            }
        }
    }
}
