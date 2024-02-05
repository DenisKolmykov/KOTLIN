package units/*
— Теперь в телефонной книге могут храниться записи о нескольких людях.
  Используйте для этого наиболее подходящую структуру данных.

 */

object PhoneBook {
    var phoneBook: MutableMap<String, Person> = HashMap()

    fun checkPersonNameInPhoneBook (name : String) : Boolean{
        return phoneBook.containsKey(name)
    }

//    fun checkPersonNameInPhoneBook (name : String): Units.Person{
//        val person : Units.Person = if (phoneBook.containsKey(name)) {
//            println("!Контакт с именем $name существует!\nДанные будут обновлены\n")
//            phoneBook[name]!! //если имя есть в списке (в Units.PhoneBook) - тогда "достаем" из него Units.Person по имени
//        } else{
//            println("В Units.PhoneBook будет создан новый контакт с именем: $name\n")
//            Units.Person(name) //если имени в списке нет - создаем нового Units.Person
//        }
//        return person
//    }

    operator fun get(name: String): Person? {
        return phoneBook[name]
    }

    operator fun set(name: String, person: Person) {
        phoneBook[name] = person
    }


    fun printPhoneBook(){
        if (phoneBook.isNotEmpty()) for (p: Person in phoneBook.values){
            p.printPersonData()
        } else println("Units.PhoneBook не содержит ни одной записи\n")
    }
}