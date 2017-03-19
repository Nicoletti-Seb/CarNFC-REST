package com.mbds.carnfcapirest

class Place {

    float x
    float y

    static belongsTo = [car: Car]

    static constraints = {
    }
}
