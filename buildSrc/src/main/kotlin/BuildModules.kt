/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Configuration of build modules
 */
object BuildModules {
  const val APP = ":app"

  object Features {
    const val ATTACHMENTS = ":features:attachments"
    const val DASHBOARD = ":features:dashboard"
  }


  object Commons {
    const val BASE = ":commons:base"
    const val UI = ":commons:ui"
    const val THEME = ":commons:theme"
    const val VIEWS = ":commons:views"
  }

  object Libraries {
    const val TEST_UTILS = ":libraries:test_utils"
  }
}
