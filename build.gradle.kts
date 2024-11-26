// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
//    repositories {
//        google()  // Add Google repository
//        mavenCentral()  // Add Maven central repository
//    }

    dependencies {
        // Add the classpath for the Google services plugin
        classpath("com.google.gms:google-services:4.4.2")  // Correct dependency
    }
}

//allprojects {
//    repositories {
//        google()  // Add Google repository for all subprojects
//        mavenCentral()  // Add Maven central repository
//    }
//}
