import commands.*
import exeptions.MyExeption
import view.InputData

/*
За основу берём код решения домашнего задания из предыдущего семинара и дорабатываем его.

— Создайте иерархию sealed классов, которые представляют собой команды.
В корне иерархии интерфейс Comands.Command.

— В каждом классе иерархии должна быть функция isValid():Boolean,
которая возвращает true, если команда введена с корректными аргументами.
Проверку телефона и email нужно перенести в эту функцию.

— Напишите функцию readCommand():Comands.Command,
которая читает команду из текстового ввода, распознаёт её и возвращает один из классов-наследников Comands.Command,
соответствующий введённой команде.

— Создайте data класс Units.Person, который представляет собой запись о человеке.
Этот класс должен содержать поля:
name – имя человека
phone – номер телефона
email – адрес электронной почты

— Добавьте новую команду show, которая выводит последнее значение, введённой с помощью команды add.
Для этого значение должно быть сохранено в переменную типа Units.Person.
Если на момент выполнения команды show не было ничего введено, нужно вывести на экран сообщение “Not initialized”.

— Функция main должна выглядеть следующем образом.
Для каждой команды от пользователя:
Читаем команду с помощью функции readCommand
Выводим на экран получившийся экземпляр Comands.Command
Если isValid для команды возвращает false, выводим help. Если true, обрабатываем команду внутри when.
 */

/*
рок 3. Практика по функциям в Kotlin
Продолжаем дорабатывать домашнее задание из предыдущего семинара. За основу берём код решения из предыдущего домашнего задания.

— Измените класс Units.Person так, чтобы он содержал список телефонов и список почтовых адресов, связанных с человеком.
— Теперь в телефонной книге могут храниться записи о нескольких людях.
  Используйте для этого наиболее подходящую структуру данных.
— Команда AddPhone теперь должна добавлять новый телефон к записи соответствующего человека.
— Команда AddEmail теперь должна добавлять новый email к записи соответствующего человека.
— Команда show должна принимать в качестве аргумента имя человека и выводить связанные с ним телефоны и
  адреса электронной почты.
— Добавьте команду find, которая принимает email или телефон и выводит список людей,
  для которых записано такое значение.
 */

/*
Урок 4. Практика по исключениям и взаимодействию с Java
Продолжаем дорабатывать домашнее задание из предыдущего семинара.
За основу берём код решения из предыдущего домашнего задания.

— Добавьте новую команду export, которая экспортирует добавленные значения в текстовый файл в формате JSON.
Команда принимает путь к новому файлу. Например
export /Users/user/myfile.json
— Реализуйте DSL (предметно ориентированный язык) на Kotlin, который позволит конструировать JSON и
преобразовывать его в строку.
— Используйте этот DSL для экспорта данных в файл.
— Выходной JSON не обязательно должен быть отформатирован, поля объектов могут идти в любом порядке.
Главное, чтобы он имел корректный синтаксис. Такой вывод тоже принимается:
[{"emails": ["ew@huh.ru"],"name": "Alex","phones": ["34355","847564"]},{"emails": [],"name": "Tom","phones": ["84755"]}]

Записать текст в файл можно при помощи удобной функции-расширения writeText:
File("/Users/user/file.txt").writeText("Text to write")

Под капотом она использует такую конструкцию


FileOutputStream(file).use {
it.write(text.toByteArray(Charsets.UTF_8))
}
 */

fun main() {
    while(true) {
        val mainMenu = "Введите команду (add, help, show, find, print, export, exit): "
        val command = readCommand(InputData.inputChoice(mainMenu))
        command.runCommand()

        when (command) {
            is Exit -> {
                println("Работа приложения завершена\n")
                return
            }
            is Add -> command.inputPersonData()
            is Help -> command.printHelp()
            is Show -> command.showPersonData()
            is Find -> command.showPersonList()
            is Print -> command.printBook()
            is ExportToFile -> try{
                    command.writeToFile("src/main/kotlin/phonebook.txt")
                } catch (e: MyExeption){
                println("${e.message}:\n${e.cause}\n")
            }
        }
    }
}


fun readCommand(input : String): Command {
    val command: Command
    val inputData = input.lowercase().split(" ")
    when (inputData[0]) {
        "exit" -> command = Exit
        "help" -> command = Help
        "add" -> command = Add()
        "show" -> command = if (inputData.size < 2) {
                                println  ("!Вы не вели имя! Повторите ввод (help - в помощь)\n")
                                Help
                            } else Show(inputData[1].capitalize()) //не стал менять на предложение от IDE,думаю, в данной задаче не принципиально
        "find" -> command = if (inputData.size < 2) {
                                println  ("!Вы не вели параметр для поиска! Повторите ввод (help - в помощь)\n")
                                Help
                            } else Find(inputData[1])
        "print" -> command = Print
        "export" -> command = ExportToFile()
        else -> {
            println("НЕКОРРЕКТНАЯ КОМАНДА. Повторите ввод (help - в помощь):")
            command = Help
        }
    }
    return command
}


