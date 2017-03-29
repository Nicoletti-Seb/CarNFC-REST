package com.mbds.carnfcapirest

import grails.converters.JSON
import grails.converters.XML

class PersonController {

    def index() {
        render "PersonController"
    }

    void renderApplication(obj) {
        if(request.getHeader("Accept").contains("xml")) {
            render obj as XML
        } else if(request.getHeader("Accept").contains("json")) {
            render obj as JSON
        } else {
            render obj as JSON
        }
    }

    def list() {
        switch (request.getMethod()) {
            case "GET":
                def persons = Person.getAll();
                renderApplication(persons)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    def get() {
        switch (request.getMethod()) {
            case "GET":
                def personInstance = Person.get(params.id)

                if (!personInstance) {
                    render(status: 404, text: "person not found")
                    return
                }
                renderApplication(personInstance)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    def add() {
        switch (request.getMethod()) {
            case "POST":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                System.out.println("params:" + request.JSON)

                def personInstance = new Person(request.JSON)

                if (personInstance.save(flush: true)) {
                    response.status = 201
                    renderApplication(personInstance)
                } else {
                    render(status: 400, text: "person has not been created")
                    personInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : POST")
                break;
        }
    }

    def update() {
        switch (request.getMethod()) {
            case "PUT":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                def personInstance = Person.get(params.id)

                if (!personInstance) {
                    render(status: 404, text: "person id not found")
                    return
                }

                System.out.println("params:" + request.JSON)
                personInstance.setProperties(request.JSON)

                if (personInstance.save(flush: true)) {
                    response.status = 200
                    renderApplication(personInstance)
                } else {
                    render(status: 400, text: "person has not been updated")
                    personInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : PUT")
                break;
        }
    }

    def delete() {
        switch (request.getMethod()) {
            case "DELETE":
                def personInstance = Person.get(params.id)

                if (!personInstance) {
                    render(status: 404, text: "person id not found")
                    return
                }

                personInstance.delete()

                if(!Person.get(params.id))
                    response.status = 204
                else {
                    render(status: 400, text: "person has not been deleted")
                    personInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : DELETE")
                break;
        }
    }

    def login() {
        switch (request.getMethod()) {

        }
    }

    def register() {
        switch (request.getMethod()) {

        }
    }
}
