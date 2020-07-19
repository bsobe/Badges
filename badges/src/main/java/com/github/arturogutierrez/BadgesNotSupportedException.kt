package com.github.arturogutierrez

class BadgesNotSupportedException(homePackage: String) : Exception(
    String.format(
        "The home launcher with package '%s' is not supported by Badges library",
        homePackage
    )
)
