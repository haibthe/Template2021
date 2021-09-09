plugins {
  id("android-base-lib")
  id("android-base")
}

dependencies {
  appCompat()
  lifecycle()
  koin()
  rx()
  retrofit()

  api("com.google.android.play:core:1.10.0")
  api(project(BuildModules.Commons.THEME))
  api(Dependencies.Airbnb.MAVERICKS)
}
