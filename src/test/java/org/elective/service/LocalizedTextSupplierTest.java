package org.elective.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LocalizedTextSupplierTest {
    @Autowired
    LocalizedTextSupplier localizedTextSupplier;

    @Test
    void getLocalizedTextEn() {
        Assert.assertEquals(
                "Login",
                localizedTextSupplier.getLocalizedText("login.title", "en")
        );
    }

    @Test
    void getLocalizedTextUa() {
        Assert.assertEquals(
                "Вхід",
                localizedTextSupplier.getLocalizedText("login.title", "ua")
        );
    }
}