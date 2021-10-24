package dev.iaiabot.furufuru_kmm.android.feature

import android.app.Activity
import android.os.Bundle
import dev.iaiabot.furufuru_kmm.android.feature.ui.issue.IssueActivity

class SampleActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Furufuru.Builder(application).build()
        startActivity(IssueActivity.createIntent(this))
    }
}
