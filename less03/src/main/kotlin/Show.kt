/*
— Команда show должна принимать в качестве аргумента имя человека и выводить связанные с ним телефоны и
  адреса электронной почты.
 */

data class Show(val name: String) : Command {

    fun showPersonData() {
        if (PhoneBook.checkPersonNameInPhoneBook(this.name)){
            PhoneBook.phoneBook[this.name]?.printPersonData()
        } else {
            println("Запись с именем $name отсутствует в PhoneBook\n")
        }
    }
}
