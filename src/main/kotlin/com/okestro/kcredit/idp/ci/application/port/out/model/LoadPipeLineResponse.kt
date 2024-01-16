package com.okestro.kcredit.idp.ci.application.port.out.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Label(
    val name: String
)

data class Job(
    val _class: String,
    val name: String,
    val url: String,
    val color: String
)

data class View(
    val _class: String,
    val name: String,
    val url: String
)

data class OverallLoad(val details: String?) // Replace 'details' with the actual property name

data class QuietDownReason(val details: String?) // Replace 'details' with the actual property name

data class Hudson(
    @JsonProperty("_class") val clazz: String,
    val assignedLabels: List<Label>,
    val mode: String,
    val nodeDescription: String,
    val nodeName: String,
    val numExecutors: Int,
    val description: String?,
    val jobs: List<Job>,
    val overallLoad: OverallLoad,
    val primaryView: View,
    val quietDownReason: QuietDownReason?,
    val quietingDown: Boolean,
    val slaveAgentPort: Int,
    val unlabeledLoad: Any, // You may replace 'Any' with the actual data class for UnlabeledLoadStatistics
    val url: String,
    val useCrumbs: Boolean,
    val useSecurity: Boolean,
    val views: List<View>
)