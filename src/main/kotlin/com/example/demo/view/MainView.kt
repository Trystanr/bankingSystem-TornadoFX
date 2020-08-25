package com.example.demo.view

import com.example.demo.CustomerModel
import com.example.demo.app.Styles.Companion.zip
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import javafx.stage.StageStyle
import tornadofx.*
import java.text.DecimalFormat

//class Person(id: Int, name: String) {
//    var id by property(id)
//    fun idProperty() = getProperty(Person::id)
//
//    var name by property(name)
//    fun nameProperty() = getProperty(Person::name)
//}

class PhoneNumber(countryCode: String, number: String) {
    var countryCode by property(countryCode)
    fun countryCodeProperty() = getProperty(PhoneNumber::countryCode)

    var number by property(number)
    fun numberProperty() = getProperty(PhoneNumber::number)
}

class Person {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val idProperty = SimpleStringProperty()
    var id by idProperty
}

class PersonModel : ItemViewModel<Person>(Person()) {
    val name = bind(Person::nameProperty)
    val id = bind(Person::idProperty)
}

class PersonDetailModel : ItemViewModel<Person>() {
    val id = bind { item?.idProperty }
    val name = bind { item?.nameProperty }
//    val phoneNumbers = bind { item?.phoneNumbersProperty() }
}

class PersonController: Controller() {
    val people = FXCollections.observableArrayList<Person>()
    val selectedPerson = PersonDetailModel()

    init {
        // Add some test persons for the demo
//        people.add(Person(42, "John Doe"))
//        people.add(Person(43, "Jane Doe"))

        val p = Person()
        p.id = "5"
        p.name = "Mr Lahey"

        people.add(p)
    }

}

class MainView : View("Banking System") {
    private val controller: PersonController by inject()

//    val people = FXCollections.observableArrayList<Person>()


//    override val root = borderpane {
//        center {
//            tableview(controller.people) {
//                column("Name", Person::nameProperty)
//                column("ID", Person::idProperty)
////                bindSelected(controller.selectedPerson)
//
//                smartResize()
//
//            }
//        }
//        bottom {
//            hbox(spacing = 10) {
//                button("Add Customer").action {
//                    // Create a new model and set some default values
//                    val newPerson = PersonModel()
//                    newPerson.name.value = "New name"
//                    newPerson.id.value = "0"
//                    // Create a new scope and inject the PersonModel into that scope
//                    // You can also add other objects that could work as parameters for the PersonEditor
//                    val newScope = Scope(newPerson)
//
//                    // Find the PersonEditor in the new scope and open a modal - wait for the result
////                    find<PersonEditor>(newScope).openModal(block = true)
//
//                    println(newPerson)
//
//                    controller.people.add(newPerson.item)
//                }
//            }
//        }
//    }

    override val root = hbox {
        add<PersonList>()
        add<PersonEditor>()
    }

}

//class PersonEditor : Fragment("Add Customer") {
//    val person: PersonModel by inject()
//
//    override val root = form {
//        fieldset {
//            field("Name") {
//                textfield(person.name).required()
//            }
//
//            field("ID") {
//                textfield {
//                    bind(person.id, format = DecimalFormat("0"))
//                }
//            }
//
//
//        }
//        button("Save and close") {
//            isDefaultButton = true
//            action {
//                person.commit {
//                    close()
//                }
//            }
//        }
//    }
//}

class BankController: Controller() {

//    val bank = Bank("Bank Name")

    fun logUserIn(username: String) {
        println("User logging in as ${username}");
    }

    fun createUser(username: String, password: String) {

    }
}