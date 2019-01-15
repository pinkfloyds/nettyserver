package com.mko.nettyserver

import java.util.concurrent.Executors

object ExecutorServiceLoop {
    private val workerThread = Executors.newFixedThreadPool(20)!!

    fun put(r: Runnable) = workerThread.submit(r)


}
