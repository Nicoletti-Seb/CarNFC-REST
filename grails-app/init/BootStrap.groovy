import com.mbds.carnfcapirest.Car
import com.mbds.carnfcapirest.Person
import com.mbds.carnfcapirest.Place

class BootStrap {

    def init = { servletContext ->

        def person1 = new Person(firstName: "f1", lastName: "l1", mail: "m1", password: "p1").save(flush:true,failOnError: true)
        def person2 = new Person(firstName: "f2", lastName: "l2", mail: "m2", password: "p2").save(flush:true,failOnError: true)

        def car1 = new Car(matricule: "car1", marque: "marque1",
                nextMaintenance: new Date(date: 12, month: 10, year: 2017),
                endInsurance: new Date(date: 20, month: 02, year: 2019),
                person: person1,
                place: new Place(lat: 0.0, lon: 0.0)
        ).save(flush:true,failOnError: true)

        def car2 = new Car(matricule: "car2", marque: "marque2",
                nextMaintenance: new Date(date: 12, month: 10, year: 2017),
                endInsurance: new Date(date: 20, month: 02, year: 2019),
                person: person2,
                place: new Place()
        ).save(flush:true,failOnError: true)
    }

    def destroy = {
    }
}
