package br.com.andersonsv.test.extension

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

val ptBRLocale = Locale("pt", "BR")

val noSymbolFormatter =
    (NumberFormat.getCurrencyInstance(ptBRLocale) as DecimalFormat).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        positivePrefix = ""
        negativePrefix = "-"
        decimalFormatSymbols.currencySymbol = ""
    }

val noSymbolNoFractionFormatter =
    (NumberFormat.getCurrencyInstance(ptBRLocale) as DecimalFormat).apply {
        maximumFractionDigits = 0
        minimumFractionDigits = 0
        positivePrefix = ""
        negativePrefix = "-"
        decimalFormatSymbols.currencySymbol = ""
    }

fun Double.asBRL(includeCurrency: Boolean = false): String = if (includeCurrency)
    "R$ " + noSymbolFormatter.format(this) else noSymbolFormatter.format(this)

fun Double.asBRLNoFraction(includeCurrency: Boolean = false): String = if (includeCurrency)
"R$ " + noSymbolNoFractionFormatter.format(this) else noSymbolNoFractionFormatter.format(this)
