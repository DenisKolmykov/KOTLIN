package dsl

import units.Person
import units.PhoneBook

object DSLToJson {
    fun buildJson(): String {
        val persons: List<Person> = PhoneBook.phoneBook.values.toList()
        var output = ""
        persons.forEach { person ->
            output += """
            {
                "name": "${person.name}",
                "phoneNumbers": [${person.phone.joinToString { it -> "\"$it\"" }}],
                "emails": [${person.email.joinToString { it -> "\"$it\"" }}]
            },
            
            """.trimIndent()

        }
        return output
    }
}