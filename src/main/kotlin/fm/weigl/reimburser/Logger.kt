package fm.weigl.reimburser

import java.util.*

/**
 * Created by fweigl on 02.11.16.
 */

class Logger(private val fileLogger: FileLogger) {

    fun log(tag: String, msg: String, logToFile: Boolean = false) {

        val timeStamp = Date().toLocaleString()

        val logText = "[$timeStamp] $tag ::: $msg"

        println(logText)

        if (logToFile) {
            fileLogger.logToFile(msg)
        }
    }


}