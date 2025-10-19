package com.dema.appcalculofarroupilha

data class ValidationModel(
    var typeValidate : TypeValidate = TypeValidate.VALID,
    var isValid : Boolean = true
)
