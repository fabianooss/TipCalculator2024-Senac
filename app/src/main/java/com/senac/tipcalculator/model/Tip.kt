package com.senac.tipcalculator.model

data class Tip (
    val amount: Float = 0.0f,
    val custom: Int = 15
) {
    fun getTip15() = amount * 15 / 100

    fun getTipCustom() = amount * custom / 100

    fun getTotal15() = amount + getTip15()

    fun getTotalCustom() = amount + getTipCustom()

}