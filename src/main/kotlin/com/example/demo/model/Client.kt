package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

class Client {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val nameProperty = SimpleStringProperty()
    var name: String by nameProperty

    val identityProperty = SimpleStringProperty()
    var identity: String by identityProperty

    val accountProperty = SimpleListProperty<Account>()
    var accounts: ObservableList<Account> by accountProperty
}

class ClientModel : ItemViewModel<Client>(Client()) {
    val id = bind(Client::idProperty)
    val name = bind(Client::nameProperty)
    val identity = bind(Client::identityProperty)
    val accounts = bind(Client::accountProperty)
}

class ClientDetailModel : ItemViewModel<Client>() {
    val id = bind { item?.idProperty }
    val name = bind { item?.nameProperty }
    val identity = bind { item?.identityProperty }
    val accounts = bind { item?.accountProperty }
}