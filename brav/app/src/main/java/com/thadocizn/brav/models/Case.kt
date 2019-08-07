package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Case(

    var id:Int? = null,
    var case_completed_at: String? = null,
    var description: String,
    var dispute_category: String? = null,
    var dispute_amount: String? = null,
    var parties_involved: String? = null,
    var parties_contact_info: String? = null,
    var court_case: Boolean? = null,
    var court_jurisdiction: String? = null,
    var court_number: String? = null,
    var court_filing_date: String? = null,
    var case_notes: String? = null,
    var case_accepted_at: String? = null,
    var case_declined_at: String? = null

)