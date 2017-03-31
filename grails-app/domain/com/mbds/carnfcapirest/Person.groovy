package com.mbds.carnfcapirest

import grails.rest.Resource

@Resource
class Person {

    String firstName
    String lastName
    String mail
    String password

    static hasMany = [cars: Car]

    static constraints = {
        firstName       nullable: false, blank: false
        lastName        nullable: false, blank: false
        mail            nullable: false, mail:true, blank: false, unique: true
        password        nullable: false, blank: false
    }
}
