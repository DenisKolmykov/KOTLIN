class Show(var showLastData: Person? = null): Command {
    fun printLastData(): String{
        return if (showLastData != null){
            "последняя запись была у пользователя ${showLastData!!.name} введен ${showLastData!!.showLastField}: ${showLastData!!.showLastInput}\n"
        }else {
            "Not initialized\n"
        }
    }
}