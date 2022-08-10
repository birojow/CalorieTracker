apply {
    from("$rootDir/non-compose-module-build.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
}
