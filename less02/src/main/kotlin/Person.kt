/*
— Создайте data класс Person, который представляет собой запись о человеке.
Этот класс должен содержать поля:
name – имя человека
phone – номер телефона
email – адрес электронной почты
 */
data class Person (
    var name : String,
    var phone : String = "",
    var email : String = "",
//    var showLastInput : String = "", // последнее введенное значение
//    var showLastField : String = "" // поле последнего введенного значения
){

    //заполнение полей последними введенными значениями
//    fun setLastInput(showLastField: String, showLastInput : String){
//        this.showLastField = showLastField
//        this.showLastInput = showLastInput
//    }
}
