/*
Урок 1. Практика по основам Kotlin
Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>

После выполнения команды, кроме команды exit, программа ждёт следующую команду.

Имя – любое слово.

Если введена команда с номером телефона, нужно проверить, что указанный телефон может начинаться с +,
затем идут только цифры.
При соответствии введённого номера этому условию – выводим его на экран вместе с именем, используя строковый шаблон.
В противном случае - выводим сообщение об ошибке.

Для команды с электронной почтой делаем то же самое, но с другим шаблоном – для простоты,
адрес должен содержать три последовательности букв, разделённых символами @ и точкой.

Пример команд:
add Tom email tom@example.com
add Tom phone +7876743558
 */

fun main(args: Array<String>) {
    while(true) {
        print("Введите команду (add, help, exit): ")
        val input = readlnOrNull()!!
        if (input.isNotBlank()) {
            val inputData = input.split(" ")
            when {
                inputData.first().lowercase() == "exit" -> return //или так: input.lowercase().startsWith("exit")
                inputData.first().lowercase() == "help" -> printHelp()
                inputData.first().lowercase() == "add" && inputData.size == 4 -> addPersonData(inputData)
                else -> println("Команда или формат введеных данных не корректны. Повторите ввод (help - в помощь)\n")
            }
        }
    }
}

fun printHelp() {
    println("--------HELP----------------------------------------------------------------------------------------------------")
    println ("Приложение по заполнению телефонной книги")
    println ("Команды приложения:")
    println ("exit - выход из приложения")
    println ("help - меню с описанием основных команд")
    println ("add <Имя> phone <Номер телефона> (формат: +ххххххххххх>) - ввод номера телефона (ключевое слово 'phone' - обязательно)")
    println ("add <Имя> email <Адрес электронной почты, формат: х@х.х> - ввод адреса электронной почты (ключевое слово 'email' - обязательно)")
    println ("---------------------------------------------------------------------------------------------------------------")
}

fun addPersonData(inputData: List<String>) {
    when {// можно было использовать inputData.contains("phone")...
        inputData[2] == "phone" || inputData[2] == "email" -> savePersonData(inputData)
        else -> println("Введенные данные не содержат ключевого слова 'phone' или 'email'!\nПовторите ввод!\n")
    }
}

fun savePersonData(inputData: List<String>){
    val patternEmail = Regex("\\w+@\\w+\\.\\w+")
    val patternPhone = Regex("^\\+\\d+")
//    val patternPhone = """^\+\d+""".toRegex()

    if (inputData[3].matches(patternEmail) || inputData[3].matches(patternPhone)){
        println("Данные пользователя ${inputData[1]} успешно обновлены:")
        println("${inputData[1]} ${inputData[2]}: ${inputData[3]}\n")
    } else{
        println("${inputData[2]} пользователя ${inputData[1]} введен не корректно.\nПовторите ввод\n")
    }
}
