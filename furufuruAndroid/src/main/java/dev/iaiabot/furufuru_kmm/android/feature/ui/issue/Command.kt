package dev.iaiabot.furufuru_kmm.android.feature.ui.issue

internal sealed class Command {
    object Finish : Command()
    class Error(val errorMessage: String) : Command()
}
