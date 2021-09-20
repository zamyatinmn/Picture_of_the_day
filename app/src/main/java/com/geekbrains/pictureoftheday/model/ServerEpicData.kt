package com.geekbrains.pictureoftheday.model


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


data class Element (
    val identifier: String,
    val caption: String,
    val image: String,
    val version: String,
    val centroid_coordinates: CentroidCoordinates,
    val dscovr_j2000_position: J2000Position,
    val lunar_j2000_position: J2000Position,
    val sun_j2000_position: J2000Position,
    val attitude_quaternions: AttitudeQuaternions,
    val date: String,
    val coords: Coords
)

data class AttitudeQuaternions (
    val q0: Double,
    val q1: Double,
    val q2: Double,
    val q3: Double
)

data class CentroidCoordinates (
    val lat: Double,
    val lon: Double
)

data class Coords (
    val centroid_coordinates: CentroidCoordinates,
    val dscovr_j2000_position: J2000Position,
    val lunar_j2000_position: J2000Position,
    val sun_j2000_position: J2000Position,
    val attitude_quaternions: AttitudeQuaternions
)

data class J2000Position (
    val x: Double,
    val y: Double,
    val z: Double
)