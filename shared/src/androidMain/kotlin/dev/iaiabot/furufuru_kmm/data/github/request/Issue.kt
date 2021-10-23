package dev.iaiabot.furufuru_kmm.data.github.request

@Serializable
internal data class Issue(
    val title: String,
    val body: String? = null,
    val assignee: String? = null,
    val milestone: Int? = null,
    val labels: List<String>? = null,
    val assignees: List<String>? = null
)
