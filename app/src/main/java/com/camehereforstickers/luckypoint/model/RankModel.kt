package com.camehereforstickers.luckypoint.model

import java.util.*

data class RankModel(
    var id: Int?,
    var ticket: TicketModel,
    var user: UserModel,
    var date: Date
)