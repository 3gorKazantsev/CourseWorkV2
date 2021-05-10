package org.kazantsev.coursework.data

import java.util.*

class TempData {
    companion object {
        fun getClientList(): List<Client> {
            val list = mutableListOf<Client>()
            for (i in 1..20) {
                list.add(
                    Client(
                        "ООО $i",
                        "$i",
                        "$i",
                        "$i",
                        "$i@gmail.com"
                    )
                )
            }
            return list
        }

        fun getDeliveryList(): List<Delivery> {
            val list = mutableListOf<Delivery>()
            for (i in 1..20) {
                list.add(Delivery(i, i, i*10, i*3, Date(), i))
            }
            return list
        }

        fun getEmployeeList(): List<Employee> {
            val list = mutableListOf<Employee>()
            for (i in 1..20) {
                list.add(
                    Employee(
                        "$i",
                        "$i",
                        "$i"
                    )
                )
            }
            return list
        }

        fun getOrderList(): List<Order> {
            val list = mutableListOf<Order>()
            for (i in 1..20) {
                list.add(
                    Order(i, i, i, i, i, "$i", i)
                )
            }
            return list
        }

        fun getProductList(): List<Product> {
            val list = mutableListOf<Product>()
            for (i in 1..20) {
                list.add(Product("$i", "Description", i, i, i, i % 2 == 0))
            }
            return list
        }

        fun getSupplierList(): List<Supplier> {
            val list = mutableListOf<Supplier>()
            for (i in 1..20) {
                list.add(Supplier("$i", "$i", "$i", "$i@gmail.com"))
            }
            return list
        }
    }
}