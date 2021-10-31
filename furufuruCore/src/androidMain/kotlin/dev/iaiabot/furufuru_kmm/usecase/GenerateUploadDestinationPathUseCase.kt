package dev.iaiabot.furufuru_kmm.usecase

import java.text.SimpleDateFormat
import java.util.*

actual class GenerateUploadDestinationPathUseCase {
    actual operator fun invoke(): String {
        val now = Date()
        val nowString = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.getDefault()).format(now)
        return "$nowString.jpg"
    }
}
