package commands

sealed interface Command {

    fun runCommand(){
        println("Запуск команды '${this.javaClass.simpleName}':")
    }
}