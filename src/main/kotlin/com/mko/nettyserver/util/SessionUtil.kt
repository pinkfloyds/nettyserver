package com.mko.nettyserver.util

import com.mko.nettyserver.attribute.Attributes
import com.mko.nettyserver.protocol.Session
import com.mko.nettyserver.protocol.response.TimeResponsePacket
import io.netty.channel.Channel
import org.springframework.scheduling.annotation.Scheduled
import java.util.concurrent.ConcurrentHashMap

object SessionUtil {
    @JvmField
    val loginedChannelMap = ConcurrentHashMap<String, Channel>()

    @JvmStatic
    fun bindSession(session: Session, channel: Channel) {
        loginedChannelMap[session.dec_id] = channel
        channel.attr(Attributes.SESSION).set(session)
        println("$session 登录成功!")
    }


    @JvmStatic
    fun unBindSession(channel: Channel) {
        if (hasLogin(channel)) {
            val session = getSession(channel)!!
            loginedChannelMap.remove(session.dec_id)
            channel.attr(Attributes.SESSION).set(null)
            println("$session 退出登录!")
        }
    }

    @JvmStatic
    fun hasLogin(channel: Channel) = getSession(channel) != null

    @JvmStatic
    fun getChannel(dec_id: String) = loginedChannelMap[dec_id]

    @JvmStatic
    fun getSession(channel: Channel) = channel.attr(Attributes.SESSION).get()

    @Scheduled(fixedRate = 1000 * 60 * 60)
    fun time() {
        println("发送时间包")
        val timeResponsePacket = TimeResponsePacket(index = 0)
        loginedChannelMap.forEach { _: String, u: Channel -> u.writeAndFlush(timeResponsePacket) }
    }
}
