plugins {
  id("android-base-lib")
  id("android-base")
}

dependencies {
  appCompat()
  epoxy()

  api(Dependencies.UI.PINNED_SECTION)
  api(Dependencies.UI.SHAPE_OF_VIEW)

  implementation(project(BuildModules.Commons.BASE))
  implementation(project(BuildModules.Commons.THEME))
}
