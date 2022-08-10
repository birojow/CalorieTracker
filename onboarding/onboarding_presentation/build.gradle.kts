apply {
    from("$rootDir/compose-module-build.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.core_ui))
    "implementation"(project(Modules.onboardingDomain))
}
