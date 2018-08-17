package Ejercicio3

import validarNumerico

val INEFICIENTE = "No eficiente"
val INEFICAZ = "No eficaz"
val EFICAZ_EFICIENTE = "Eficaz y eficiente"

fun main(args: Array<String>) {
    val tickets = crearTickets()

    println("----- EJERCICIO 3 -----\n\n")

    var codigoIngresado = ""
    do {
        print("Ingrese el codigo de ticket: ")
        codigoIngresado = readLine().toString()
    }while (!validarNumerico(codigoIngresado))

    val ticket = tickets.find() { t -> t.id == codigoIngresado.toInt() }
    if(ticket == null) {
        print("El ticket con el código ingresado no existe. ")
        return
    }

    when(ticket.complejidad){
        1->
            when{
                ticket.tiempoRespuesta>1->println(INEFICIENTE)
                ticket.satisfaccionCliente<=3->println(INEFICAZ)
                else->println(EFICAZ_EFICIENTE)
            }
        2->
            when{
                ticket.tiempoRespuesta>2->println(INEFICIENTE)
                ticket.satisfaccionCliente<=3->println(INEFICAZ)
                else->println(EFICAZ_EFICIENTE)
            }
        3->
            when{
                ticket.tiempoRespuesta>4->println(INEFICIENTE)
                ticket.satisfaccionCliente<=3->println(INEFICAZ)
                else->println(EFICAZ_EFICIENTE)
            }
        4->
            when{
                ticket.tiempoRespuesta>8->println(INEFICIENTE)
                ticket.satisfaccionCliente<=3->println(INEFICAZ)
                else->println(EFICAZ_EFICIENTE)
            }
        //5
        else->
            when{
                ticket.tiempoRespuesta>16->println(INEFICIENTE)
                ticket.satisfaccionCliente<=3->println(INEFICAZ)
                else->println(EFICAZ_EFICIENTE)
            }
    }
}

fun crearTickets(): Array<Ticket> {
    val ticket1 = Ticket(1,"Armando","Telefonoia",1,1,4)
    val ticket2 = Ticket(2,"Laura","Servicios",2,2,3)
    val ticket3 = Ticket(3,"Heidi","Tecnologia",3,4,3)
    val ticket4 = Ticket(4,"Pedro","Ventas",4,5,3)
    val ticket5 = Ticket(5,"Nelson","Compras",5,4,3)

    return arrayOf(ticket1,ticket2,ticket3,ticket4,ticket5)
}