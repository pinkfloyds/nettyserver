package com.mko.nettyserver.util

import java.nio.charset.Charset
import kotlin.experimental.and

object Unsigned {
    fun Byte.toUnsigned() =     //将data字节型数据转换为0~255 (0xFF 即BYTE)。
            this.and(0x0FF.toByte()).toInt() // 部分编译器会把最高位当做符号位，因此写成0x0FF.

    fun Int.toUnsigned() = this and 0x0FF

    fun getGB2312(byteArray: ByteArray) = byteArray.toString(Charset.forName("GB2312"))
}