package torres.gabriel.temperatureapp

/**
 * Created by Gabriel on 24/08/2018.
 */
object Conversion {

    fun CelsiusToFahrenheit(number:Double):Double{
        return 9.0*number/5.0 + 32
    }

    fun CelsiusToKelvin(number:Double):Double{
        return number + 273.15
    }

    fun FahrenheitToCelsius(number:Double):Double{
        return 5.0*(number-32)/9.0
    }

    fun FahrenheitToKelvin(number:Double):Double{
        return 5.0*(number-32)/9.0 + 273.15
    }

    fun KelvinToCelsius(number:Double):Double{
        return number - 273.15
    }

    fun KelvinToFahrenheit(number:Double):Double{
        return 9.0*(number-273.15)/5.0 + 32
    }
}