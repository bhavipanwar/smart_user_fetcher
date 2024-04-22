// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("androidx.navigation.safeargs.kotlin") version ("2.7.7") apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false

}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.7"
        val kotlinVersion = "1.3.72"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}