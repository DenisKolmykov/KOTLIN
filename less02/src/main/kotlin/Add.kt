class Add (): Command {
    fun inputPersonData() {
        var flag = true
        while (flag) {
            val menuInputName = "Укажите имя (0 - отмена): "
            val inputName = InputData.inputChoice(menuInputName)
            if (inputName != "0") {
                val menuInputData =
                    "Для $inputName введите номер телефона(формат: +ххххххххххх>) или email(формат: х@х.х):\n--> "
                val choice = InputData.inputChoice(menuInputData)
                if (isValid(choice)) { // проверяем введенные значения на корректность формата и если норм - создаем объект класса Person
                    val person = Person(inputName)
//                    val lastField: String
                    when (choice.substring(0, 1)) {
                        "+" -> {// в email не может быть первым символ '+'
                            person.phone = choice
//                            lastField = "phone"
                        }
                        else -> {
                            person.email = choice
//                            lastField = "email"
                        }
                    }
//                    person.setLastInput(lastField, choice) // записываем в объект Person последние введнные значения
                    Show.showLastInputDataperson = person // добавляем последние введенные значения в класс Show
                    println("save ok")
                    flag = false
                } else {
                    println("Не корректный формат.\nИзменения по $inputName не произведены\n")
                    flag = true
//                   val command = Help()
//                   command.printHelp()
                }
            }else {
                println("Отмена ввода.\n")
                return
            }
        }
    }

    fun isValid(input : String) : Boolean{
        val patternEmail = Regex("""\w+@\w+\.\w+""")
        val patternPhone = Regex("""^\+\d+""")
        return (input.matches(patternEmail) || input.matches(patternPhone))
    }
}