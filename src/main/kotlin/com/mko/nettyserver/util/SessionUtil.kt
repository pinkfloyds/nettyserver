package com.mko.nettyserver.util

import com.mko.nettyserver.protocol.response.TimeResponsePacket
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.group.ChannelGroup
import org.springframework.boot.web.servlet.server.Session
import java.util.concurrent.ConcurrentHashMap

object SessionUtil {
    private val userIdChannelMap = ConcurrentHashMap<String, Channel>()

//    fun bindSession(session: Session, channel: Channel) {
//        userIdChannelMap[session.getUserId()] = channel
//        channel.attr<Any>(Attributes.SESSION).set(session)
//    }
//
//    fun unBindSession(channel: Channel) {
//        if (hasLogin(channel)) {
//            val session = getSession(channel)
//            userIdChannelMap.remove(session!!.getUserId())
//            channel.attr<Any>(Attribute.SESSION).set(null)
//            println(session!! + " 退出登录!")
//        }
//    }
//
//    fun hasLogin(channel: Channel): Boolean {
//        return getSession(channel) != null
//    }
//
//    fun getSession(channel: Channel): Session? {
//        return channel.attr<Any>(Attributes.SESSION).get()
//    }

    fun time(){
        val timeResponsePacket = TimeResponsePacket()
        userIdChannelMap.forEach { t: String, u: Channel ->
            run {
                u.writeAndFlush(timeResponsePacket)
            }
        }
    }
}
