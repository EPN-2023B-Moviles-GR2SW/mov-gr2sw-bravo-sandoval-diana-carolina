import java.util.*

fun main(args: Array<String>) {
    println("Hello Word!")

    //INMUTABLES (NO SE REASIGNAN "=")
    val inmutable: String = "Adrian";

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
    when (estadoCivilWhen) {
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
    calcularSueldo(10.00, 15.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros

    //usnado instancia de laclase
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor( //no es el constructo primario
        uno: Int,
        dos: Int
    ){
        //Bloque de codigo del onstructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

//CLase con kotlin
abstract class Numeros(//Constructor PRIMARIO)
    //Notas utiles
    //uno: Int, (Parametro (sin modificador de accseso))
    //private var uno: Int, //Propiedad Publica Clase numeros.uno
    //var uno: Int, //Propiedad de la clase (por defecto es pUBLIC=
    //public var uno; Int,


    //PROPIEDADES DE LA CLASE
    protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int,// Propiedad de la clase protected numeros.numerosDos
) {
    //var cedula: string= "" (public es por defecto)
    //private valorCalculado:  Int = 0 (private)

    init {
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos;//sin el this, eslo mismo
        println("Inicializando")
    }
}
//heredando
class Suma ( //Constructor primario Suma
    unoParametro: Int, //Parametro
    dosParametro: Int, //Parametro
): Numeros(unoParametro,dosParametro){ // Extendiendo y mandano los paramateros (super)
    init{ //Bloque codigo constructor primario
        this.numeroUno
        this.numeroDos
    }

constructor(//Segundo constructor)
        uno: Int?, //Parametros
        dos: Int
    ): this(
        if(uno==null) 0 else uno,
        dos
    )

constructor(//Tercer constructor)
    uno: Int,
    dos: Int?

):this(
    uno,
    if(dos==null) 0 else
    dos,

)
}






    //void -> Unit
    fun imprimirNombre(nombre: String): Unit {
        //"Nombre : "+nombre
        println("Nombre : ${nombre}") //template Strings

    }

    fun calcularSueldo(
        sueldo: Double, //Requerido
        tasa: Double = 12.00, //Opcional (defecto)
        bonoEspecial: Double? = null, //Opcion null->nullable

    ): Double {
        //Int -> Int? (nullable)
        //String -> sTRING? ( nullable)
        //Date -> Date? nullable
        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }
    }



