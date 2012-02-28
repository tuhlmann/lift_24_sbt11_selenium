import org.specs2.mutable._
import org.eclipse.jetty.server.{ Connector, Server}
import org.eclipse.jetty.webapp.{ WebAppContext }
import com.thoughtworks.selenium._
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.specs2.specification.BeforeExample
import org.specs2.specification.AfterExample
import net.liftweb.http.S
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebDriver
import com.gargoylesoftware.htmlunit.BrowserVersion

object ServerContext {
  private var server: Server = null

  def initializeServer = {
    val GUI_PORT             = 8080
    
    /*
    // Setting up the jetty instance which will be running the
    // GUI for the duration of the tests
    server  = new Server(GUI_PORT)
    val context = new WebAppContext()
    context.setServer(server)
    context.setContextPath("/")
    context.setWar("src/main/webapp")
    server.addHandler(context)
    server.start()
    println("SERVER STARTED")
    //Thread.sleep(60000)
    */

    // Setting up the jetty instance which will be running the
    // GUI for the duration of the tests
    server  = new Server(GUI_PORT)
    val context = new WebAppContext()

    context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
    context.setResourceBase("src/main/webapp");
    context.setContextPath("/");
    context.setParentLoaderPriority(true);
    //context.setServer(server)
    //context.setContextPath("/")
    //context.setWar("src/main/webapp")
    server.setHandler(context)
    server.start()
    //server.join()
    println("SERVER STARTED")
    //Thread.sleep(60000)

  }

  def shutdownServer = {
    println("STOP SERVER")
    server.stop()
  }

}

class FirstSeleniumWebSpec extends Specification with BeforeExample with AfterExample {

  var driver: WebDriver = _

  def before = {
    ServerContext.initializeServer
    // driver = new FirefoxDriver()
    driver = new HtmlUnitDriver(true);
  }

  def after =  {
    driver.quit
    ServerContext.shutdownServer
  }

  "This tests the Lift Basic Homepage" should {
    "'Welcome to your project!' in Text" in {
      driver.get("http://localhost:8080")
      println("Page title is: " + driver.getTitle())
      val element: WebElement = driver.findElement(By.cssSelector("#main h2"));
      println("TEXT: "+element.getText)
      //assert(element.getText() == "Welcome to your project!")
      element.getText() must equalTo("Welcome to your project!")
    }
  }

  "Testing the User Registration process" should {
    "Registration should open up" in {
      driver.navigate.to("http://localhost:8080")
      val element: WebElement = driver.findElement(By.cssSelector("#main h2"));
      println("TEXT 1: "+element.getText)
      val register = driver.findElement(By.partialLinkText("Lift"))
      println("TEXT 2: "+register.getText)
      //assert(element.getText() == "Welcome to your project!")
      register.click
      driver.getCurrentUrl must endWith("http://www.liftweb.net/")
    }
  }



}