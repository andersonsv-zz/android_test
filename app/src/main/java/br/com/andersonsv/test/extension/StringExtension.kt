package br.com.andersonsv.test.extension



fun String.currencyToPercentage(totalValue: Double): Double =
    (this.toDouble() / totalValue) * 100

