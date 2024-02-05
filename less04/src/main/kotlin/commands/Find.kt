package commands

import units.Person
import units.PhoneBook

/*
— Добавьте команду find, которая принимает email или телефон и выводит список людей,
  для которых записано такое значение.

 */

data class Find(val data: String) : Command {

    private fun isValid() : Boolean{
        val patternEmail = Regex("""\w+@\w+\.\w+""")
        val patternPhone = Regex("""^\+\d+""")
        return (data.matches(patternEmail) || data.matches(patternPhone))
    }


    fun showPersonList (){
        if (isValid()) {
            if (PhoneBook.phoneBook.isNotEmpty()) {
                val field : String // определяем поле класса, по которому будем искать
                when (data.substring(0, 1)) {
                    "+" -> {// в email не может быть первым символ '+' -> ищем по phone
                        field = "phone"
                    }
                    else -> {
                        field = "email" // ищем по email
                    }
                }

                val personFindResultList: MutableList<Person> = mutableListOf() // создаем список результатов
                for (p: Person in PhoneBook.phoneBook.values) {
                     if (p.getFieldValue(field).contains(data)){
                         personFindResultList.add(p) //добавляем в список результатов поиска
                     }
                }
                // можно было не создавать список результатов поиска а выводить в консоль в предыдущем цикле
                if (personFindResultList.isNotEmpty()){
                    for (p : Person in personFindResultList){
                        p.printPersonData()
                    }
                }else println("Пользователи с '$data' не найден\n")

            } else {
                println("Units.PhoneBook не содержит ни одной записи")
            }
        } else {
            println("Не корректный формат.Поиск отменен (help - в помощь)\n")
            Help
        }
    }
}