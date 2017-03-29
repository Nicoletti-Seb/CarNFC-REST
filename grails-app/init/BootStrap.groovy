import com.mbds.carnfcapirest.Car
import com.mbds.carnfcapirest.Person
import com.mbds.carnfcapirest.Place

class BootStrap {

    def init = { servletContext ->

        def car = new Car(matricule: "154dsq144d").save()
        def place = new Place().save()
        def person1 = new Person(firstName: "f1", lastName: "l1", mail: "m1", password: "p1").save();
        def person2 = new Person(firstName: "f2", lastName: "l2", mail: "m2", password: "p2").save(flush:true,failOnError: true)
    }

    def destroy = {
    }
}
