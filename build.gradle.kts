// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  val kotlin_version by extra("1.4.31")
  repositories {
    google()
    jcenter()
    maven { url = uri("https://jitpack.io") }
  }
  dependencies {
    classpath ("com.android.tools.build:gradle:4.1.3")
    classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    maven { url = uri("https://jitpack.io") }
  }

  plugins.apply(BuildPlugins.KTLINT)
}

tasks.register("clean", Delete::class.java) {
  delete(rootProject.buildDir)
}
