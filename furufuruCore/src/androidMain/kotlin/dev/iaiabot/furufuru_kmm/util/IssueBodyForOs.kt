package dev.iaiabot.furufuru_kmm.util

import dev.iaiabot.furufuru_kmm.BuildConfig

actual class IssueBodyForOs {
    actual operator fun invoke(): String {
        var body = ""
        body = body.replace(IssueBodyTemplate.FURUFURU_VERSION_NAME, BuildConfig.VERSION_NAME)
        body = body.replace(
            IssueBodyTemplate.FURUFURU_VERSION_CODE,
            BuildConfig.VERSION_CODE.toString()
        )
        return body

        /*
        Furufuru.getApplicationName()?.let {
            body = body.replace(IssueBodyTemplate.APP_NAME, it)
        }

        Furufuru.getAppVersionName()?.let {
            body = body.replace(IssueBodyTemplate.APP_VERSION, it)
        }
         */
    }
}
