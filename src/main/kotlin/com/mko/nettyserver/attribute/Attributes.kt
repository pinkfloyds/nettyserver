package com.mko.nettyserver.attribute

import com.mko.nettyserver.protocol.Session
import io.netty.util.AttributeKey

object Attributes {
    val SESSION = AttributeKey.newInstance<Session>("session")!!
}
