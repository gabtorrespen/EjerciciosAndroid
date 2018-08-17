package Ejercicio1

import validarNumerico

fun main(args: Array<String>) {

    println("----- EJERCICIO 1 -----\n\n")
    print("Ingrese el valor pagado por hora de trabajo: ")
    val valorHoraIngresado = readLine().toString()
    if(!validarNumerico(valorHoraIngresado)){
        return
    }

    print("Ingrese las horas trabajadas en el periodo: ")
    val horasIngresado = readLine().toString()
    if(!validarNumerico(horasIngresado)){
        return
    }

    print("Ingrese la deducción salarial(Porcentaje): ")
    val deduccionIngresado = readLine().toString()
    if(!validarNumerico(deduccionIngresado)){
        return
    }

    val valorHora = valorHoraIngresado.toInt();
    val horas = horasIngresado.toInt();
    val deduccion = deduccionIngresado.toInt();

    if(deduccion<0 || deduccion>100){
        println("\n\nEl porcentaje de deducción debe ser un valor entre 0 y 100")
    }

    val salarioBruto = valorHora*horas
    val salario = salarioBruto - ((salarioBruto*deduccion)/100)

    println("\n\nSALARIO: El salario neto es de $ $salario")

}