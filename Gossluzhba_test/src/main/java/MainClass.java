import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {

        // возможно нужно будет поменять путь к проекту во втором аргументе
        System.setProperty("webdriver.gecko.driver", "C:\\java_exercises\\Gossluzhba_test\\drivers\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://gossluzhba.gov.ru/");

        // первая проверка на нахождение кнопки Вакансии
        // first check
        //
        WebElement checkedElement1 = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul[2]/li[3]/a"));
        // вывод в консоль найденного текста
        System.out.println(checkedElement1.getText());
        // проверка
        if(checkedElement1.getText().equals("Вакансии")) {
            System.out.println("first check - OK");
        } else {
            System.out.println("first check - no...");
        }

        // проходим по ссыоке в Вакансии
        driver.findElement(By.xpath("/html/body/div/div/div/nav/ul[2]/li[3]/a")).click();

        // проверка на название вкладки в браузере
        // title check
        // Вывод в консоль найденного текста вкладки
        System.out.println(driver.getTitle());
        if(driver.getTitle().equals("Госслужба/Вакансии")) {
            System.out.println("title check - OK");
        } else {
            System.out.println("title check - no...");
        }

        // проверка на поиск слова на странице на нахождение на данной вкладке
        // second check
        WebElement checkedElement2 = driver.findElement(By.xpath("/html/body//div//p[@class='pull-left mr_5']"));
        // Вывод найденного в консольь
        System.out.println(checkedElement2.getText());
        // проверка
        if(checkedElement2.getText().equals("Сортировать:")) {
            System.out.println("second check - OK");
        } else {
            System.out.println("second check - no...");
        }

        // ввод в поле Сортировка вакансий по Региону
        driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys("Алтайский край");

        // вывод введенного текста на консоль getAttribute`ом
        System.out.println(driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).getAttribute("value"));

        // клик по найденному значению
        driver.findElement(By.xpath("//div/span[@class='select2-match']")).click();

        // очистка введенных данных (не через .clear() так как в этом поле нет поля с input)
        driver.findElement(By.xpath("//div/ul/li/a[@class='select2-search-choice-close']")).click();

        // закрыть окно сессии браузера
        driver.quit();
    }
}
