import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @Test
    void shouldInputValidName() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Иван Валерьевич");
        $("[data-test-id='phone'] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInputInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("John Colt");
        $("[data-test-id='phone'] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInputInvalidPhone() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Иван Валерьевич");
        $("[data-test-id='phone'] input").setValue("79111488111");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Иван Валерьевич");
        $("[data-test-id='phone'] input").setValue("+79111488111");
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNotInputName() {
        open("http://localhost:9999");
        $("[data-test-id='phone'] input").setValue("+79111488111");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotInputPhone(){
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Иван Валерьевич");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

}






