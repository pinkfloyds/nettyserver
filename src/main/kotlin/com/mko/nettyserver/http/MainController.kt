package com.mko.nettyserver.http

import com.mko.nettyserver.protocol.response.DownLoadResponsePacket
import com.mko.nettyserver.repository.DeviceRepository
import com.mko.nettyserver.util.SessionUtil.getChannel
import com.mko.nettyserver.util.SessionUtil.loginedChannelMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController
@RequestMapping("sendCommand")
class MainController {

    @Autowired
    lateinit var deviceRepository: DeviceRepository

    /**
     * 发送授权码
     */
    @GetMapping("authrity")
    fun authrity(prod_id: String, code: String): Mono<Any> {
//        getChannel(prod_id)?.writeAndFlush(DownLoadResponsePacket(0,"")) ?:return
        GlobalScope.launch {
            // launch new coroutine in background and continue
//            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//            println(.toString()) // print after delay
        }
        return "".toMono()
    }

    /**
     * 请求同步
     */
    @GetMapping("synchronization")
    fun synchronization(prod_id: String) {
        getChannel(prod_id)?.writeAndFlush(DownLoadResponsePacket(0, "")) ?: return
        return
    }

    /**
     * 主机远程查询命令
     */
    @GetMapping("master")
    fun masterQuery(prod_id: String) {
        getChannel(prod_id)?.writeAndFlush(DownLoadResponsePacket(0, "")) ?: return
        return
    }

    /**
     * 回路/分区远程查询命令
     */
    @GetMapping("partition")
    fun partition(prod_id: String) {

    }

    /**
     * 回路/分区远程查询命令
     */
    @GetMapping("node")
    fun node(prod_id: String) {

    }

    @GetMapping("logined")
    fun logined(): Mono<Any> {
        return loginedChannelMap.size.toMono()
    }
}