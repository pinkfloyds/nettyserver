package com.mko.nettyserver

import com.mko.nettyserver.codec.MySpliter
import com.mko.nettyserver.codec.PacketCodecHandler
import com.mko.nettyserver.codec.Spliter
import com.mko.nettyserver.handler.*
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.lang.Exception
import java.time.LocalDateTime

@SpringBootApplication
class NettyserverApplication {
    companion object {
        private const val PORT = 8001

        @JvmStatic
        fun main(args: Array<String>) {
            //runApplication<NettyserverApplication>(*args)
            starNettyServer()
        }

        @Throws(Exception::class, kotlin.Exception::class)
        private fun starNettyServer() {
            val serverBootstrap = ServerBootstrap()
                    .group(NioEventLoopGroup(), NioEventLoopGroup()) // accept 新连接的线程组，处理每一条连接的数据读写的线程组
                    .channel(NioServerSocketChannel::class.java) // 指定 IO 模型
                    .option(ChannelOption.SO_BACKLOG, 1024) // 临时存放已完成三次握手的请求的队列的最大长度
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 是否开启TCP底层心跳机制，true为开启
                    .childOption(ChannelOption.TCP_NODELAY, true) // 是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启
                    .childHandler(object : ChannelInitializer<NioSocketChannel>() { //绑定处理类
                        override fun initChannel(ch: NioSocketChannel) {
//                            ch.pipeline().addLast(IMIdleStateHandler()) // 服务端空闲检测
                            ch.pipeline().addLast(LifeCyCleTestHandler()) //
                            ch.pipeline().addLast(MySpliter()) // 过滤消息包,同时解决粘包拆包
                            ch.pipeline().addLast(PacketCodecHandler) // 解码
                            ch.pipeline().addLast(HeartBeatRequestHandler) // 心跳回复



                        }
                    })
            bind(serverBootstrap, PORT)
        }

        private fun bind(serverBootstrap: ServerBootstrap, port: Int) {
            serverBootstrap.bind(port).addListener { future ->
                if (future.isSuccess) {
                    println("${LocalDateTime.now()}: 端口[$port]绑定成功!")
                } else {
                    println("端口[$port]绑定失败!")
                    bind(serverBootstrap, port + 1)
                }
            }
        }
    }
}



