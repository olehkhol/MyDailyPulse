package my.daily.pulse

import java.awt.Toolkit
import java.util.logging.Logger

actual class Platform() {

    actual val osName: String
        get() = "Java ${System.getProperty("java.version")}"

    actual val osVersion: String
        get() = System.getProperty("os.version") ?: "Unknown"

    actual val deviceModel: String
        get() = System.getProperty("os.name") ?: "Unknown"

    actual val density: Int
        get() {
            val toolkit = Toolkit.getDefaultToolkit()
            val screenResolution = toolkit.screenResolution
            return screenResolution / 160 // Assuming a base density of 160dpi
        }

    actual fun logSystemInfo() {
        val logger = Logger.getLogger("Daily Pulse")
        logger.info("($osName, $osVersion, $deviceModel, $density)")
    }
}
