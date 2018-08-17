package Ejercicio2

import validarNumerico

fun main(args: Array<String>) {
    var empleados = crearEmpleados()

    println("----- EJERCICIO 2 -----\n\n")

    var codigoIngresado = ""
    do {
        print("Ingrese el codigo de empleado: ")
        codigoIngresado = readLine().toString()
    }while (!validarNumerico(codigoIngresado))

    val empleado = empleados.find() { e -> e.codigo == codigoIngresado.toInt() }
    if(empleado == null) {
        print("El empleado con el código ingresado no existe. ")
        return
    }

    print("Ingrese el salario por hora: ")
    val salarioIngresado = readLine().toString()
    if(!validarNumerico(salarioIngresado)){
        return
    }

    print("Ingrese las horas trabajadas: ")
    val horasIngresado = readLine().toString()
    if(!validarNumerico(horasIngresado)){
        return
    }

    val salarioHora = salarioIngresado.toDouble();
    val horas = horasIngresado.toDouble();

    val salario = if(horas<48)
        horas*salarioHora
    else
        48*salarioHora + (horas-48)*(salarioHora*1.35)

    empleado.salario = salario

    println("\n\nSALARIO: El salario de ${empleado.nombre} es de $ ${empleado.salario}")
}

fun crearEmpleados(): Array<Empleado> {
    val empleado1 = Empleado(1,0.0,"Gabriel")
    val empleado2 = Empleado(2,0.0,"Jorge")
    val empleado3 = Empleado(3,0.0,"Juan")
    val empleado4 = Empleado(4,0.0,"Ana")

    return arrayOf(empleado1,empleado2,empleado3,empleado4)
}
