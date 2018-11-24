package com.camehereforstickers.luckypoint.model

import java.util.*

data class TicketModel(
    var id: Int?,
    var rank: RankModel,
    var user: UserModel,
    var latitude: Float,
    var longitude: Float,
    var createdAt: Date
)