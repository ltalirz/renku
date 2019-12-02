package ch.renku.acceptancetests.tooling

import java.io.File
import java.nio.file.Paths
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.ofPattern

import org.scalatestplus.selenium.{Driver, WebBrowser}
import org.openqa.selenium.WebDriver

trait ScreenCapturing {

  /**
    * Public helper function to write a screenshot of the current browser state.
    */
  def writeScreenshot(implicit browser: WebBrowser with Driver, webDriver: WebDriver) = {
    val captureDir = Paths.get("target")
    browser.setCaptureDir(captureDir.toFile.getAbsolutePath)

    val file = s"${now() format ofPattern("yyMMddHHmmss")}-${getClass.getSimpleName}.png"
    println(s"Screenshot saved to ${captureDir.toFile.getAbsolutePath}${File.separator}$file")
    browser.capture.to(file)(webDriver)
  }
}
