package com.mko.nettyserver.handler.business

import com.mko.nettyserver.NettyserverApplication
import com.mko.nettyserver.repository.DeviceRepository

interface MysqlHandler {
    val deviceRepository: DeviceRepository
        get() = NettyserverApplication.springContext!!.getBean(DeviceRepository::class.java)

}