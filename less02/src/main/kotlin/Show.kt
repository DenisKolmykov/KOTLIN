data object Show: Command {
    var showLastInputDataperson: Person? = null
    fun printLastData(): String{
        return if (showLastInputDataperson != null){
            "последняя запись была у пользователя ${showLastInputDataperson!!.name} - введено: ${showLastInputDataperson!!.email} ${showLastInputDataperson!!.phone}\n"
        }else {
            "Not initialized\n"
        }
    }
}