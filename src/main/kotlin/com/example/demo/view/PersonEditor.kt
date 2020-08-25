package com.example.demo.view

import javafx.scene.control.TableView

//import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.*
import java.text.DecimalFormat

class PersonEditor : View() {
    val controller: PersonController by inject()
//    var numbersTable: TableView<PhoneNumber> by singleAssign()

    override val root = form {
        fieldset("Personal Information") {
            field("Name") {
                textfield(controller.selectedPerson.name)
            }
            button("Save") {
                setOnAction {
                    controller.selectedPerson.commit()
                }
            }
        }
        fieldset("Phone Numbers") {
            vbox(5.0) {
                button("Add number") {
                    setOnAction {
                        println("hello")
                    }
                }
            }
        }
    }
}

class PersonEditorModal : Fragment("Add Customer") {
    val person: PersonModel by inject()

    override val root = form {
        fieldset {
            field("Name") {
                textfield(person.name).required()
            }

            field("ID") {
                textfield {
                    bind(person.id, format = DecimalFormat("0"))
                }
            }


        }
        button("Save and close") {
            isDefaultButton = true
            action {
                person.commit {
                    close()
                }
            }
        }
    }
}