package exeptions

class MyExeption : RuntimeException {
    constructor(message: String) : super (message)
    constructor(message : String, cause : Throwable) : super (message, cause)
}