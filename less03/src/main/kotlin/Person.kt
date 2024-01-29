/*
— Создайте data класс Person, который представляет собой запись о человеке.
Этот класс должен содержать поля:
name – имя человека
phone – номер телефона
email – адрес электронной почты
 */
/*
— Измените класс Person так, чтобы он содержал список телефонов и список почтовых адресов, связанных с человеком.
 */

data class Person (
    var name : String,
    var phone: MutableList<String> = mutableListOf(),
    var email : MutableList<String> = mutableListOf()
){
    fun printPersonData(){
        println("$name phone: $phone, email: $email \n")
    }

    // возвращает значение (List) указанного поля field
    fun getFieldValue(field: String): List<String> {
        return when (field) {
            "phone" -> phone
            else -> email
        }
    }
}