package com.dema.appcalculofarroupilha

import android.util.Log

open class ValidationFormsVolumeArea() {

    private val tag = javaClass.name

    fun validateFormVolume(text : String) : ValidationModel {
        if (text.isEmpty())
            return ValidationModel(TypeValidate.TEXT_EMPTY, false)

        val splitText = text.split(" ")

        if (splitText.size < 2)
            return ValidationModel(TypeValidate.INVALID_TEXT_FORMAT, false)

        if (splitText[0].isEmpty())
            return ValidationModel(TypeValidate.MISSING_NUMBER, false)

        try {
            val convertTextToDouble = splitText[0].toDouble()
            Log.v(tag, "Convert text to double: $convertTextToDouble")
        } catch (ex: Exception) {
            Log.e(tag, "Error to convert text to double: ${ex.message}")
            return ValidationModel(TypeValidate.INVALID_NUMBER, false)
        }

        var containsMeasure = text.lowercase().contains("kg")
                || text.lowercase().contains("l")
                || text.lowercase().contains("ml")

        if (!containsMeasure)
            return ValidationModel(TypeValidate.MISSING_MEASURE, false)

        return ValidationModel()
    }

    fun validateFormArea(text : String) : ValidationModel {
        if (text.isEmpty())
            return ValidationModel(TypeValidate.TEXT_EMPTY, false)

        var containsMeasure = text.lowercase().contains("m²")
                || text.lowercase().contains("cm²")
                || text.lowercase().contains("m³")
                || text.lowercase().contains("cm³")

        if (!containsMeasure)
            return ValidationModel(TypeValidate.MISSING_MEASURE, false)

        return ValidationModel()
    }

}