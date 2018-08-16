package ru.cse.app.models

import java.util.*

data class Safekeep(val Id: String, val Date: Date, val Number: String, val Description: String, val WaybillList: List<Waybill>)
