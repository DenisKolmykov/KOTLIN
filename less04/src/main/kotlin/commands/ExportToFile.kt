package commands

import dsl.DSLToJson
import exeptions.MyExeption
import java.io.File

class ExportToFile : Command {

    fun writeToFile(fileName: String){
        try {
            File(fileName).writeText(DSLToJson.buildJson())
            println("Успешная запись в файл '$fileName'\n")
        } catch (e: Exception){
            throw MyExeption ("Нет доступа к файлу '$fileName'", e)
        }

    }
}