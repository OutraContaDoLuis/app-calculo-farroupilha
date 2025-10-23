package com.dema.appcalculofarroupilha

open class Calculate() {
    fun quantityContainers(volumeContainer : String,
                           volumePerArea : String,
                           totalArea : String) : Double {

        val volumeContainerSplit = volumeContainer.lowercase().split(' ')
        val volumePerAreaSplit = volumePerArea.lowercase().split(' ')
        val totalAreaSplit = totalArea.lowercase().split(' ')

        val sameMeasureVolume = if (volumeContainerSplit[1].contains("l")
            && !volumePerAreaSplit[1].contains("l")) {
            false
        } else if (volumeContainerSplit[1].contains("ml") && !volumePerAreaSplit[1].contains("ml")) {
            false
        } else if (volumeContainerSplit[1].contains("kg") && !volumePerAreaSplit[1].contains("kg")) {
            false
        } else if (volumeContainerSplit[1].contains("g") && !volumePerAreaSplit[1].contains("g")) {
            false
        } else {
            true
        }

        val sameMeasureArea = if (volumePerAreaSplit[1].contains("m²")
            && !totalAreaSplit[1].contains("m²")) {
            false
        } else if (volumePerAreaSplit[1].contains("cm²") && !totalAreaSplit[1].contains("cm²")) {
            false
        } else if (volumePerAreaSplit[1].contains("m³") && !totalAreaSplit[1].contains("m³")) {
            false
        } else if (volumePerAreaSplit[1].contains("cm³") && !totalAreaSplit[1].contains("cm³")) {
            false
        } else {
            true
        }

        if (sameMeasureVolume && sameMeasureArea) {
            //kg/m (volumePerArea) * m (total area)
            val totalVolumeToUse = volumePerAreaSplit[0].toDouble() * totalAreaSplit[0].toDouble()

            //operation1 / kg (capacityContainer)
            return totalVolumeToUse / volumeContainerSplit[0].toDouble()
        } else {
            var newVolumeContainer = volumeContainerSplit[0].toDouble()
            var newVolumePerArea = volumePerAreaSplit[0].toDouble()
            var newTotalArea = totalAreaSplit[0].toDouble()

            if (volumeContainerSplit[1].contains("l") && volumePerAreaSplit[1].contains("ml")) {
                newVolumeContainer = volumeContainerSplit[0].toDouble().times(1000)
            }

            if (volumeContainerSplit[1].contains("ml") && volumePerAreaSplit[1].contains("l")) {
                newVolumeContainer = volumeContainerSplit[0].toDouble().div(1000)
            }

            if (volumeContainerSplit[1].contains("kg") && volumePerAreaSplit[1].contains("g")) {
                newVolumeContainer = volumeContainerSplit[0].toDouble().times(100)
            }

            if (volumeContainerSplit[1].contains("g") && volumePerAreaSplit[1].contains("kg")) {
                newVolumeContainer = volumeContainerSplit[0].toDouble().div(100)
            }

            if (volumePerAreaSplit[1].contains("m²") && totalAreaSplit[1].contains("cm²")) {
                newTotalArea = totalAreaSplit[0].toDouble().div(10000)
            }

            if (volumePerAreaSplit[1].contains("cm²") && totalAreaSplit[1].contains("m²")) {
                newTotalArea = totalAreaSplit[0].toDouble().times(10000)
            }

            if (volumePerAreaSplit[1].contains("m³") && totalAreaSplit[1].contains("cm³")) {
                newTotalArea = totalAreaSplit[0].toDouble().div(1000000)
            }

            if (volumePerAreaSplit[1].contains("cm³") && totalAreaSplit[1].contains("m³")) {
                newTotalArea = totalAreaSplit[0].toDouble().times(1000000)
            }

            val totalVolumeToUse = newVolumePerArea * newTotalArea

            return totalVolumeToUse / newVolumeContainer
        }
    }
}