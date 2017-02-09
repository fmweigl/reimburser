package fm.weigl.reimburser

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

/**
 * Created by fweigl on 02.11.16.
 */

class FileLogger {

    fun logToFile(text: String) {

        try {
            FileWriter(Values.Companion.LOG_PATH, true).use({ fw ->
                BufferedWriter(fw).use({ bw ->
                    PrintWriter(bw).use({ out ->
                        out.println(text)
                    })
                })
            })
        } catch (e: IOException) {
            println(e.toString())
        }

    }

}