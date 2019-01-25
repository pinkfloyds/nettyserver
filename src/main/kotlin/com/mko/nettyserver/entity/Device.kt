package com.mko.nettyserver.entity

import java.util.*
import javax.persistence.*

@Table(name = "device")
@Entity
data class Device(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var deviceId: Int = 0,
        var deviceCode: String = "",
        var isOnline: Int = 0,
        var authStatus: Int = 0,
        var faultNumber: Int = 0,
        var warnNumber: Int = 0,
        var alarmNumber: Int = 0,
        var gmtCreate: Date = Date(0)
)