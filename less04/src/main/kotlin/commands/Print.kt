package commands

import units.PhoneBook

data object Print : Command {
    fun printBook(){
        PhoneBook.printPhoneBook()
        println()
    }
}