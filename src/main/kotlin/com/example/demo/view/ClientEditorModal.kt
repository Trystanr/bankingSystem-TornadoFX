package com.example.demo.view

import com.example.demo.model.ClientModel
import tornadofx.*
import java.text.DecimalFormat

class ClientEditorModal : Fragment("Add Customer") {
    val person: ClientModel by inject()

    override val root = form {
        fieldset {

            field("ID") {
                label {
                    bind(person.id, format = DecimalFormat("0"))
                }
            }

            field("Name") {
                textfield(person.name).required()
            }

            field("Identity Number") {
                textfield {
                    bind(person.identity)
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

        button("Cancel") {
            action {
                close()
            }
        }
    }
}