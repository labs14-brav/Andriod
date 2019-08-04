package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Case(
    var caseAcceptedAt: String? = null,
    var caseCompletedAt: String? = null,
    var caseDeclinedAt: String? = null,
    var caseNotes: String? = null,
    var courtCase: String? = null,
    var courtFilingDate: String? = null,
    var courtJurisdiction: String? = null,
    var courtNumber: String? = null,
    var description: String,
    var disputeAmount: String? = null,
    var dispute_category: String? = null,
    var partiesContactInfo: String? = null,
    var partiesInvolved: String? = null

)