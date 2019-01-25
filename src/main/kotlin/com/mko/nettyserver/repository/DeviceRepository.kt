package com.mko.nettyserver.repository

import com.mko.nettyserver.entity.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : JpaRepository<Device, Int> {

}