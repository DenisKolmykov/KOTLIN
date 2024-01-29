/*
синглтон для ввода данных от пользователя из консоли
 */

object InputData {
    fun inputChoice(menu: String) : String{
        var flag = true
        var input = ""
        while(flag) {
            print(menu)
            input = readlnOrNull()!!
            if (input.isNotBlank()) {
                flag = false
            }
        }
        return input
    }
}