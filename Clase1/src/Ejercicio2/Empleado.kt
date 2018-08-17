package Ejercicio2

class Empleado(nombre:String): Persona(nombre) {

    var codigo:Int? = null
    var salario:Double? = null

    constructor(codigo:Int,salario:Double,nombre:String):this(nombre){
        this.codigo = codigo
        this.salario = salario
    }
}