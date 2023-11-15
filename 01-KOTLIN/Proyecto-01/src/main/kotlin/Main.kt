import java.util.*

fun main(args: Array<String>){
    println("Hello Word!")

    //INMUTABLES (NO SE REASIGNAN "=")
    val inmutable: String ="Adrian";

    //MUTABLES (Re asignar)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    //val > var
    //DUCK TYPING
    var ejemploVariable = "Adrian Eguez "
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemoplo;

    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val fechaNacimiento: Date = Date()

    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")

        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00,15.00,20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo=10.00, tasa=14.00) //Parametros

}
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        //Bloque de codigo del onstructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}
















//void -> Unit
fun imprimirNombre(nombre: String): Unit{
    //"Nombre : "+nombre
    println("Nombre : ${nombre}") //template Strings

}
fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null->nullable

): Double{
    //Int -> Int? (nullable)
    //String -> sTRING? ( nullable)
    //Date -> Date? nullable
    if(bonoEspecial == null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa)+ bonoEspecial
    }
}

