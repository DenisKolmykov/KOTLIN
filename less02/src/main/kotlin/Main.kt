/*
За основу берём код решения домашнего задания из предыдущего семинара и дорабатываем его.

— Создайте иерархию sealed классов, которые представляют собой команды.
В корне иерархии интерфейс Command.

— В каждом классе иерархии должна быть функция isValid():Boolean,
которая возвращает true, если команда введена с корректными аргументами.
Проверку телефона и email нужно перенести в эту функцию.

— Напишите функцию readCommand():Command,
которая читает команду из текстового ввода, распознаёт её и возвращает один из классов-наследников Command,
соответствующий введённой команде.

— Создайте data класс Person, который представляет собой запись о человеке.
Этот класс должен содержать поля:
name – имя человека
phone – номер телефона
email – адрес электронной почты

— Добавьте новую команду show, которая выводит последнее значение, введённой с помощью команды add.
Для этого значение должно быть сохранено в переменную типа Person.
Если на момент выполнения команды show не было ничего введено, нужно вывести на экран сообщение “Not initialized”.

— Функция main должна выглядеть следующем образом.
Для каждой команды от пользователя:
Читаем команду с помощью функции readCommand
Выводим на экран получившийся экземпляр Command
Если isValid для команды возвращает false, выводим help. Если true, обрабатываем команду внутри when.
 */

// вынес в "глобальные" переменные, чтобы не создавать объекты классов каждый раз при выполнении цикла while(true)

var show: Show = Show() // здесь однозначно "глобальная", чтоб можно было использовать в классе Add при записи последних введенных данных
var help: Help = Help()
var exit: Exit = Exit()
var add: Add = Add()

fun main(args: Array<String>) {
    while(true) {
        val mainMenu = "Введите команду (add, help, show, exit): "
        val command = readCommand(InputData.inputChoice(mainMenu))
        command.runCommand()

        when (command) {
            is Exit -> {
                println("Работа приложения завершена\n")
                return
            }
            is Add -> command.inputPersonData()
            is Help -> command.printHelp()
            is Show -> println(show.printLastData())
        }
    }
}


fun readCommand(input : String): Command {
    val command: Command
    when (input.lowercase()) {
        "exit" -> command = exit
        "help" -> command = help
        "add" -> command = add
        "show" -> command = show
        else -> {
            println("НЕКОРРЕКТНАЯ КОМАНДА. Повторите ввод (help - в помощь):")
            command = help
        }
    }
    return command
}


