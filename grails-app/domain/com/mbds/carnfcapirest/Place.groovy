package com.mbds.carnfcapirest

import grails.rest.Resource

@Resource
class Place {

    float lat
    float lon

    static belongsTo = [car: Car]

    static constraints = {
        lat           nullable: false
        lon           nullable: false
    }
}
