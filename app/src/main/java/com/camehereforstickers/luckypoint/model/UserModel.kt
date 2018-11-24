package com.camehereforstickers.luckypoint.model

data class UserModel(
    var id: Int?,
    var number: Long,
    var name: String,
    var rank: RankModel?,
    var tickets: List<TicketModel>
)