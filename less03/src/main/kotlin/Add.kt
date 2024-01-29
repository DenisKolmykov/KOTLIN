/*
— Команда AddPhone теперь должна добавлять новый телефон к записи соответствующего человека.
— Команда AddEmail теперь должна добавлять новый email к записи соответствующего человека.
 */

class Add (): Command {
    fun inputPersonData() {
        var flag = true
        while (flag) {
            val menuInputName = "Укажите имя (0 - отмена): "
            val inputName = InputData.inputChoice(menuInputName)
            if (inputName != "0") {
                val person : Person = if (PhoneBook.checkPersonNameInPhoneBook(inputName)){
                    println("!Контакт с именем $inputName существует!\nДанные будут обновлены")
                    PhoneBook.phoneBook[inputName]!! //если имя есть в списке (в PhoneBook) - тогда "достаем" из него Person по имени
                } else{
                    println("В PhoneBook будет создан новый контакт с именем: $inputName")
                    Person(inputName) //если имени в списке нет - создаем нового Person
                }

                val menuInputData =
                    "Для $inputName введите номер телефона(формат: +ххххххххххх>) или email(формат: х@х.х):\n--> "
                val choice = InputData.inputChoice(menuInputData)
                if (isValid(choice)) { // проверяем введенные значения на корректность формата и если норм - создаем объект класса Person
                    when (choice.substring(0, 1)) {
                        "+" -> {// в email не может быть первым символ '+'
                            person.phone.add(choice)
                        }
                        else -> {
                            person.email.add(choice)
                        }
                    }
//                    person.printPersonData() //для проверки
                    PhoneBook.phoneBook[inputName] = person //записываем в PhoneBook новую (обновленную) Person
                    println("save ok\n")
                    flag = false
                } else {
                    println("Не корректный формат.\nИзменения по $inputName не произведены\n")
                    flag = true
                }

            }else {
                println("Отмена ввода.\n")
                return
            }
        }
    }

    private fun isValid(input : String) : Boolean{
        val patternEmail = Regex("""\w+@\w+\.\w+""")
        val patternPhone = Regex("""^\+\d+""")
        return (input.matches(patternEmail) || input.matches(patternPhone))
    }


}

