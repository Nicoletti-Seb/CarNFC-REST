import com.mbds.carnfcapirest.Car
import com.mbds.carnfcapirest.Person
import com.mbds.carnfcapirest.Place

class BootStrap {

    def init = { servletContext ->

        def car = new Car(matricule: "154dsq144d", )
        def place = new Place()
        def person = new Person()
    }

    def destroy = {
    }
}
