//Constantes
val NUMERIC_REG = "^(0|[1-9][0-9]*)\$"


fun validarNumerico(valor:String): Boolean {
    if(NUMERIC_REG.toRegex().matches(valor.toString())){
        return true;
    }
    println("\n\nEl valor ingresado no es un valor númerico")
    return false;
}