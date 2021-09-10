
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.api.Action
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ApplicationVariantAction : Action<ApplicationVariant> {

  override fun execute(variant: ApplicationVariant) {
    val fileName = createFileName(variant)
    variant.outputs.all(VariantOutputAction(fileName))
  }

  private fun createFileName(variant: ApplicationVariant): String {
    return "app" +
      "_${variant.name}" +
      "_${Versions.VERSION_NAME}" +
      "_${Versions.getVersionCode()}" +
      "_${getDateTimeFormat()}.apk"
  }

  private fun getDateTimeFormat(): String {
    val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmm", Locale.US)
    return simpleDateFormat.format(Date())
  }

  class VariantOutputAction(
    private val fileName: String
  ) : Action<BaseVariantOutput> {
    override fun execute(output: BaseVariantOutput) {
      if (output is BaseVariantOutputImpl) {
        output.outputFileName = fileName
      }
    }
  }
}
